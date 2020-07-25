package com.tykon.api.framework.service.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tykon.api.framework.service.core.constant.AppConstant;

public class DateUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

	private static int tokenExpiryTimeInMinutes = 2 * 60;// 2 hours

	/**
	 * @author AshishSr
	 *         <p>
	 *         convertStringToDate.
	 *         </p>
	 *         <p>
	 *         Converts a date string of the specified format into a date object.
	 *         </p>
	 *
	 * @param dateString a {@link java.lang.String} object.
	 * @param format     a {@link java.lang.String} object.
	 * @return a {@link java.util.Date} object.
	 * @throws java.text.ParseException if any.
	 * @throws java.text.ParseException
	 * @throws java.lang.Exception      if any.
	 */
	public static final Date convertStringToDate(String dateString, String format)
			throws ParseException, java.text.ParseException {
		Date targetDate = null;
		SimpleDateFormat sdf;
		if (dateString != null) {
			sdf = new SimpleDateFormat(format);
			sdf.setLenient(Boolean.FALSE);
			targetDate = sdf.parse(dateString);
		}
		return targetDate;
	}

	/**
	 * @author AshishSr
	 *         <p>
	 *         convertDateToString.
	 *         </p>
	 *         <p>
	 *         Converts a date object into a string in the specified format.
	 *         </p>
	 *
	 * @param date   a {@link java.util.Date} object.
	 * @param format a {@link java.lang.String} object.
	 * @return a {@link java.lang.String} object.
	 * @throws java.text.ParseException if any.
	 * @throws java.lang.Exception      if any.
	 */
	public static final String convertDateToString(Date date, String format) throws ParseException, Exception {
		String targetDate = "";
		SimpleDateFormat sdf;
		if (date != null) {
			sdf = new SimpleDateFormat(format);
			sdf.setLenient(Boolean.FALSE);
			targetDate = sdf.format(date);
		}
		return targetDate;
	}

	public static Date calculateExpiryDate() {
		Calendar cal = Calendar.getInstance();
		// cal.setTime(new Timestamp(cal.getTime().getTime()));
		cal.add(Calendar.MINUTE, tokenExpiryTimeInMinutes);
		return cal.getTime();
	}

	public static Long calculateExpiryDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, tokenExpiryTimeInMinutes);
		return cal.getTime().getTime();
	}

	public static Date calculateExpiryDate(Date date, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minute);
		return cal.getTime();
	}

	public static Long getCurrentDateInMillis() {
		Calendar cal = Calendar.getInstance();
		// cal.setTime(new Timestamp(cal.getTime().getTime()));
		return cal.getTime().getTime();
	}

	public static boolean isExpired(Date createdDate, int expiryTimeInMinutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(createdDate);
		cal.add(Calendar.MINUTE, expiryTimeInMinutes);
		Date curDate = new Date();
		return curDate.after(cal.getTime());
	}

	/**
	 *
	 * @return the current date into string formate
	 */
	public static String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	/**
	 * fetch current date to date formate
	 *
	 * @return
	 */
	public static String getCurentDate() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	/**
	 * fetch 1st date and last date of the current month
	 *
	 * @param dayOfMonth
	 * @return int value like Jan :- start date :-1 and end date :- 31
	 */
	public static int fetchLastAndFirstDateInMonth(int dayOfMonth) {
		Calendar cal = Calendar.getInstance();
		int firstDateOrLastDateOfMonth;
		if (dayOfMonth > 0) {
			firstDateOrLastDateOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		} else {
			firstDateOrLastDateOfMonth = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		}
		return firstDateOrLastDateOfMonth;
	}

	/**
	 *
	 * @param inputDate
	 * @return month from given input date in integer
	 */
	public static int fetchMonthFromGivenInputDate(Date inputDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(inputDate);
		return cal.get(Calendar.MONTH);
	}

	/**
	 * increase and decrease no of days from giving input date
	 *
	 * @param date
	 * @param days
	 * @return Date
	 */
	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static Date getNeverExpiryDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 100);
		return calendar.getTime();// new Date();
	}

	public static Date getDatewithoutTime(Date date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.parse(formatter.format(date));
	}

	public static Date getDatewithmaxTime(Date date) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * convert date in valid UTC formate
	 *
	 * @param date
	 * @param timeZone
	 * @return
	 */
	public static Date convertValidUTCDate(Date date, TimeZone timeZone) {
		String targetDateStrTZ = "";
		Date targetDateTZ = null;
		SimpleDateFormat simpleDateFormatTZ;
		if (date != null) {
			simpleDateFormatTZ = new SimpleDateFormat();
			simpleDateFormatTZ.setTimeZone(timeZone);
			targetDateStrTZ = simpleDateFormatTZ.format(date);
			try {
				SimpleDateFormat sdf = new SimpleDateFormat();
				targetDateTZ = sdf.parse(targetDateStrTZ);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return targetDateTZ;
	}

	/**
	 * server date reduced by 5:30 hours
	 *
	 * @param date
	 * @return
	 */
	public static Date reduceGivenDateTimeForIndianDateTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, -5);
		cal.add(Calendar.MINUTE, -30);
		return cal.getTime();
	}

	public static Date addGivenDateTimeForIndianDateTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, 5);
		cal.add(Calendar.MINUTE, 30);
		return cal.getTime();
	}

	/**
	 * Get current date time for given formate
	 *
	 * @param dateFormate
	 * @return
	 */
	public static Date getCurrentDateTimeForGivenFormate(String dateFormate) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormate);
		try {
			return formatter.parse(formatter.format(date));
		} catch (ParseException e) {
			LOGGER.error("error occurs while parsing the date", e);
		}
		return date;
	}

	/**
	 * add extra 15 min in given input date
	 *
	 * @param date
	 * @return
	 */
	public static Date addExtraMinInGivenDate(Date date, int min) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, +min);
		return cal.getTime();
	}

	/**
	 * make a custom date compare method
	 *
	 * @param start
	 * @param end
	 * @return true when given date is greater than or equal to date,else false
	 * @throws ParseException
	 */
	public static boolean CompareTwoDates(Date start, Date end) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = simpleDateFormat.parse(simpleDateFormat.format(start));
		Date d2 = simpleDateFormat.parse(simpleDateFormat.format(end));
		if (d1.compareTo(d2) > 0) {
			return true;
		} else if (d1.compareTo(d2) < 0) {
			return false;
		} else if (d1.compareTo(d2) == 0) {
			return true;
		}
		return false;
	}

	public static void main(String a[]) {
		Date date = new Date();
		try {
			System.out.println("Date is" + date);
			System.out.println(getCurrentDateTimeForGivenFormate(AppConstant.DATE_FORMATE));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
