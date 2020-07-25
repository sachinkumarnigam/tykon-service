package com.tykon.api.framework.entity.constant;

import java.util.HashMap;
import java.util.Map;

public class Enums {
	public enum MessageKind{
		Normal,
		Scheduled,
		Cab,
		Daycare,
		CabInOut,
		cabPickDrop,
		fee_reminder,
		FeeGenerated,
		FeePaid,
		Attendance,
		dailyReport,
		BirthdayReminder,
		KidNotFound,
		Suggestions,
		Helpline,
		HPFreeTrial,
		SchoolInquiry,
		Feedback
	}

	public enum MessageType{
		//Admin -> Group
		SentToGroup("Custom"),

		//Parent -> Admin
		SentToAdmin("SentToAdmin"),


		//Admin -> Teachers + Students
		All("All Teachers and Students"),

		//Admin -> Students
		AllStudents("All Students"),

		//Admin -> Teachers
		AllTeachers("All Teachers"),

		//HelloParent -> Anyone
		HelloParent("Team HelloParent");

		private final String displayName;

		MessageType(final String display) {
			this.displayName = display;
		}
		@Override public String toString()
		{
			return this.displayName;
		}
	}

	public enum EventRepeatType {
		DoNotRepeat("DoNotRepeat", "Do not repeat"), Weekly("Weekly", "Weekly"), Monthly("Monthly", "Monthly"),
		Quarterly("Quarterly", "Every 3 Months"), Biannually("Biannually", "Every 6 months"),
		Yearly("Yearly", "Yearly");

		private final String key;
		private final String value;

		EventRepeatType(String key, String value) {
			this.key = key;
			this.value = value;
		}
		public String getKey() {
			return key;
		}
		public String getValue() {
			return value;
		}
	}
	
	public enum ReceiverType{
		Student,
		User
	}

	public enum PushReceiverType{
		Parent,
		Teacher
	}

	public enum LeadStatus{
		New("New"),
		Inprogress("In Progress"),
		Junk("Junk"),
		Lost("Lost"),
		Won("Won");

		private final String displayName;

		LeadStatus(final String display) {
			this.displayName = display;
		}
		
		@Override public String toString()
		{
			return this.displayName;
		}
	}

	public enum PictureApprovalStatus{
		Pending,
		Approved,
		Rejected
	}

	public enum ApprovalRequestType{
		Student,
		Father,
		Mother
	}

	public enum BlogStatus{
		Draft,
		Publish,
		Public
	}

	public enum BlogType{
		HelloParent("Hello Parent"),
		School("School"),
		User("User");

		private final String displayName;

		BlogType(final String display) {
			this.displayName = display;
		}
		
		@Override public String toString()
		{
			return this.displayName;
		}
	}

	public enum Periodicity{
		Monthly("Monthly"),
		Yearly("Yearly"),
		OneTime("One Time");

		private final String displayName;

		Periodicity(final String display) {
			this.displayName = display;
		}
		
		@Override public String toString()
		{
			return this.displayName;
		}
	}

	public enum FeeStatus{
		PendingApproval("Pending Approval"),
		Approved("Approved"),
		Paid("Paid"),
		PartialPaid("Partial Paid"),
		Cancelled("Cancelled");

		private final String displayName;

		FeeStatus(final String display) {
			this.displayName = display;
		}
		
		@Override public String toString()
		{
			return this.displayName;
		}
	}

	public enum AmountMode{
		Cash("Cash"),
		BankDraft("Bank Transfer"),
		Cheque("Cheque"),
		OnlinePayment("Online Payment"),
		CardPayment("Card Payment"),
		IMPS("IMPS");

		private final String displayName;

		AmountMode(final String display) {
			this.displayName = display;
		}
		
		@Override public String toString()
		{
			return this.displayName;
		}
	}

	public enum TransactionStatus{
		Pending,
		Completed,
		Cancelled
	}

	public enum ReportType{
		Monthly,
		Weekly,
		Daily
	}

	public enum AlbumVisibility{
		Public("Public- Visible to Everyone"),
		Private("Private- Visible to only School Parents");

		private final String displayName;

		AlbumVisibility(final String display) {
			this.displayName = display;
		}
		
		@Override public String toString()
		{
			return this.displayName;
		}
	}

	public enum TagType{
		Age,
		Topic
	}

	public enum ForumSortBy{
		Newest,
		Oldest,
		Popular,
		LatestReplied,
		MostReplied
	}

	public enum ForumSortByDateRange{
		Today,
		ThisWeek,
		ThisMonth,
		ThisYear,
		AllTime,
	}

	public enum TransactionItemType{
		Fee
	}

	public enum NotificationLogStatus{
		Pending,
		Success,
		Failure
	}

	public enum NotificationDeviceType{
		Android,
		Apple
	}

	public enum StudentFeeFrequency{
		Monthly,
		Quarterly,
		HalfYearly,
		Annually
	}

	public enum LinkType{
		YouTube,
		Other,
		Video
	}

	public enum NewMessageType {

	}

	public enum DayCareMenuType{
		Breakfast,
		Lunch,
		Snacks
	}

	public enum BlogSortBy{
		MostViewed,
		MostComments,
		MostLiked,
		Recent
	}

	public enum SubscriptionPlan{
		Premium,
		Basic,
		Standard,
		Advanced
	}

	public enum UserType{
		HPCreated(0),
		Free(1);
		private int value;
	    private static Map<Integer, UserType> map = new HashMap<>();

	    private UserType(int value) {
	        this.value = value;
	    }

	    static {
	        for (UserType userType : UserType.values()) {
	            map.put(userType.value, userType);
	        }
	    }

	    public static UserType valueOf(int userType) {
	        return (UserType) map.get(userType);
	    }

	    public int getValue() {
	        return value;
	    }
		
	}

	public enum TeacherActivityType{
		ClassTeacher("Class Teacher"),

		SubjectTeacher("Subject Teacher");

		private final String displayName;

		TeacherActivityType(final String display) {
			this.displayName = display;
		}
		
		@Override public String toString()
		{
			return this.displayName;
		}
	}

	public enum ActivityType{
		ClassWork,
		HomeWork
	}

	public enum InvoiceType{
		Annually("Annually"),
		BiAnnually("Bi-Annually"),
		Quarterly("Quarterly");

		private final String displayName;

		InvoiceType(final String display) {
			this.displayName = display;
		}
		
		@Override public String toString()
		{
			return this.displayName;
		}
	}

	public enum AuthorizedPersonApprovalType{
		Add,
		Delete,
		Update
	}

	public enum PostType{
		Post,
		Poll
	}

	public enum MediaType{
		Image,
		Video
	}

	public enum SMSType{
		Transactional,
		Marketing
	}

	public enum LateFeeType{
		Fixed,
		Daily,
		Weekly,
		Monthly
	}

	public enum SchoolType{
		PreSchool,
		K12,
		Coaching
	}
	public enum ContentCardType {
		BLOG,
		FORUM, 
		TIMELINE, 
		STATIC,
		URL
	}

	public enum TeacherContentCardType
	{

		STATIC,
		URL
	}

	public enum CardFor {
		Teachers,
		Parents
	}
	public enum TripType{
        Pick,
        Drop,
        Absent
    }
	public enum AuthorizedPersonType{
        Mother,
        Father,
        Other,
        Self
    }
    public enum EngagementType{
        Alert,
        Response,
        ImageRequest,
        Question,
        Tooltip,
        HpBlog,
        Blog,
        Album,
        ReviewRequest,
        ForumComment,
        Forum,
        Event,
        Holiday,
        PublicEvent,
        BlogAlert,
        EventAlert,
        UpdatedEvent,
        SendConsent,
        ConsentReminder,
        MealUpload,
        ActivityUpload,
        DayCare,
        Birthday,
        TimelinePost,
        DailyReport,
        AuthorizedPersonApproval,
        PollResult,
        Logout,
        TeachKidsContent,
        Fees
    }

}
