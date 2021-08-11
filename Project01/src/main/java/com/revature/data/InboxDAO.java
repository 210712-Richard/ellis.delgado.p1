package com.revature.data;

import java.util.List;

import com.revature.beans.Inbox;

public interface InboxDAO {

	List<Inbox> getInbox (String employee);
}
