package com.revature.data;

import java.util.List;
import java.util.UUID;

import com.revature.beans.Inbox;

public interface InboxDAO {

	List<Inbox> getInbox (String employee);
	
	UUID addInbox (Inbox inbox);
	
	public void updateAlert(Inbox inbox);
	
//	public void deleteByTitle();
}
