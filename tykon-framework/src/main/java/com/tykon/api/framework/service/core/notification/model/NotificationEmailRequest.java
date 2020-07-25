/**
 * 
 */
package com.tykon.api.framework.service.core.notification.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.InputStreamSource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tykon.api.framework.service.core.notification.api.IRecipient;

/**
 * @author sachin
 *
 */
@JsonIgnoreProperties(value = { "attachmentStreamSource" })
public class NotificationEmailRequest {
	private String subject;
	private String templateFileName;
	private Map<String, Object> viewVars;
	private IRecipient from;
	private List<IRecipient> toRecipients;
	private List<IRecipient> ccRecipients;
	private List<IRecipient> bccRecipients;
	private String attachmentFileName;
	private InputStreamSource attachmentStreamSource;

	public NotificationEmailRequest(String subject, List<IRecipient> toRecipients, String attachmentFileName,
			InputStreamSource attachmentStreamSource) {
		super();
		this.subject = subject;
		this.toRecipients = toRecipients;
		this.attachmentFileName = attachmentFileName;
		this.attachmentStreamSource = attachmentStreamSource;
	}

	public NotificationEmailRequest(String subject, String templateFileName, Map<String, Object> viewVars,
			IRecipient from, List<IRecipient> toRecipients, List<IRecipient> ccRecipients,
			List<IRecipient> bccRecipients) {
		super();
		this.subject = subject;
		this.templateFileName = templateFileName;
		this.viewVars = viewVars;
		this.from = from;
		this.toRecipients = toRecipients;
		this.ccRecipients = ccRecipients;
		this.bccRecipients = bccRecipients;
	}

	public NotificationEmailRequest(String subject, String templateFileName, Map<String, Object> viewVars,
			IRecipient from, List<IRecipient> toRecipients, List<IRecipient> ccRecipients) {
		super();
		this.subject = subject;
		this.templateFileName = templateFileName;
		this.viewVars = viewVars;
		this.from = from;
		this.toRecipients = toRecipients;
		this.ccRecipients = ccRecipients;
	}

	public NotificationEmailRequest(String subject, String templateFileName, Map<String, Object> viewVars,
			IRecipient from, List<IRecipient> toRecipients) {
		super();
		this.subject = subject;
		this.templateFileName = templateFileName;
		this.viewVars = viewVars;
		this.from = from;
		this.toRecipients = toRecipients;
	}

	public NotificationEmailRequest(String subject, String templateFileName, Map<String, Object> viewVars,
			IRecipient from, List<IRecipient> toRecipients, List<IRecipient> ccRecipients,
			List<IRecipient> bccRecipients, String attachmentFileName, InputStreamSource attachmentStreamSource) {
		super();
		this.subject = subject;
		this.templateFileName = templateFileName;
		this.viewVars = viewVars;
		this.from = from;
		this.toRecipients = toRecipients;
		this.ccRecipients = ccRecipients;
		this.bccRecipients = bccRecipients;
		this.attachmentFileName = attachmentFileName;
		this.attachmentStreamSource = attachmentStreamSource;
	}

	public NotificationEmailRequest(String subject, String templateFileName, Map<String, Object> viewVars,
			List<IRecipient> toRecipients, List<IRecipient> ccRecipients, List<IRecipient> bccRecipients) {
		super();
		this.subject = subject;
		this.templateFileName = templateFileName;
		this.viewVars = viewVars;
		this.toRecipients = toRecipients;
		this.ccRecipients = ccRecipients;
		this.bccRecipients = bccRecipients;
	}

	public NotificationEmailRequest(String subject, String templateFileName, Map<String, Object> viewVars,
			List<IRecipient> toRecipients, List<IRecipient> ccRecipients) {
		super();
		this.subject = subject;
		this.templateFileName = templateFileName;
		this.viewVars = viewVars;
		this.toRecipients = toRecipients;
		this.ccRecipients = ccRecipients;
	}

	public NotificationEmailRequest(String subject, String templateFileName, Map<String, Object> viewVars,
			List<IRecipient> toRecipients) {
		super();
		this.subject = subject;
		this.templateFileName = templateFileName;
		this.viewVars = viewVars;
		this.toRecipients = toRecipients;
	}

	public NotificationEmailRequest(String subject, String templateFileName, Map<String, Object> viewVars,
			String recipientAddress) {
		super();
		this.subject = subject;
		this.templateFileName = templateFileName;
		this.viewVars = viewVars;
		this.toRecipients = new ArrayList<>();
		this.toRecipients.add(new EmailRecipient(recipientAddress));
	}

	public NotificationEmailRequest(String subject, String templateFileName, Map<String, Object> viewVars,
			List<IRecipient> toRecipients, List<IRecipient> ccRecipients, List<IRecipient> bccRecipients,
			String attachmentFileName, InputStreamSource attachmentStreamSource) {
		super();
		this.subject = subject;
		this.templateFileName = templateFileName;
		this.viewVars = viewVars;
		this.toRecipients = toRecipients;
		this.ccRecipients = ccRecipients;
		this.bccRecipients = bccRecipients;
		this.attachmentFileName = attachmentFileName;
		this.attachmentStreamSource = attachmentStreamSource;
	}

	public NotificationEmailRequest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the templateFileName
	 */
	public String getTemplateFileName() {
		return templateFileName;
	}

	/**
	 * @param templateFileName
	 *            the templateFileName to set
	 */
	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}

	/**
	 * @return the viewVars
	 */
	public Map<String, Object> getViewVars() {
		return viewVars;
	}

	/**
	 * @param viewVars
	 *            the viewVars to set
	 */
	public void setViewVars(Map<String, Object> viewVars) {
		this.viewVars = viewVars;
	}

	/**
	 * @return the from
	 */
	public IRecipient getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(IRecipient from) {
		this.from = from;
	}

	/**
	 * @return the toRecipients
	 */
	public List<IRecipient> getToRecipients() {
		return toRecipients;
	}

	/**
	 * @param toRecipients
	 *            the toRecipients to set
	 */
	public void setToRecipients(List<IRecipient> toRecipients) {
		this.toRecipients = toRecipients;
	}

	/**
	 * @return the ccRecipients
	 */
	public List<IRecipient> getCcRecipients() {
		return ccRecipients;
	}

	/**
	 * @param ccRecipients
	 *            the ccRecipients to set
	 */
	public void setCcRecipients(List<IRecipient> ccRecipients) {
		this.ccRecipients = ccRecipients;
	}

	/**
	 * @return the bccRecipients
	 */
	public List<IRecipient> getBccRecipients() {
		return bccRecipients;
	}

	/**
	 * @param bccRecipients
	 *            the bccRecipients to set
	 */
	public void setBccRecipients(List<IRecipient> bccRecipients) {
		this.bccRecipients = bccRecipients;
	}

	/**
	 * @return the attachmentFileName
	 */
	public String getAttachmentFileName() {
		return attachmentFileName;
	}

	/**
	 * @param attachmentFileName
	 *            the attachmentFileName to set
	 */
	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	/**
	 * @return the attachmentStreamSource
	 */
	public InputStreamSource getAttachmentStreamSource() {
		return attachmentStreamSource;
	}

	/**
	 * @param attachmentStreamSource
	 *            the attachmentStreamSource to set
	 */
	public void setAttachmentStreamSource(InputStreamSource attachmentStreamSource) {
		this.attachmentStreamSource = attachmentStreamSource;
	}

}
