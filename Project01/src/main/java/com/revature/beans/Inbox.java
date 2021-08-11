package com.revature.beans;

import java.util.Objects;
import java.util.UUID;

public class Inbox {
	
	private UUID messageId;
	private String title;
	private String message;
	private AlertType alert;
	
	public Inbox() {
		super();
	}
	public Inbox(UUID messageId, String title, String message, AlertType alert) {
		this.messageId = messageId;
		this.title = title;
		this.message = message;
		this.alert = AlertType.Neutral;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public AlertType getAlert() {
		return alert;
	}
	public void setAlert(AlertType alert) {
		this.alert = alert;
	}

	public UUID getMessageId() {
		return messageId;
	}
	public void setMessageId(UUID messageId) {
		this.messageId = messageId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(alert, message, messageId, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inbox other = (Inbox) obj;
		return alert == other.alert && Objects.equals(message, other.message)
				&& Objects.equals(messageId, other.messageId) && Objects.equals(title, other.title);
	}
	@Override
	public String toString() {
		return "Inbox [messageId=" + messageId + ", title=" + title + ", message=" + message + ", alert=" + alert + "]";
	}
	
	
}
