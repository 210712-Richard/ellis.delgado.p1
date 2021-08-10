package com.revature.beans;

import java.util.Objects;

public class Inbox {

	private String title;
	private String message;
	private AlertType alert;
	
	public Inbox() {
		super();
	}
	public Inbox(String title, String message, AlertType alert) {
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
	@Override
	public int hashCode() {
		return Objects.hash(alert, message, title);
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
		return alert == other.alert && Objects.equals(message, other.message) && Objects.equals(title, other.title);
	}
	@Override
	public String toString() {
		return "Inbox [title=" + title + ", message=" + message + ", alert=" + alert + "]";
	}
	
}
