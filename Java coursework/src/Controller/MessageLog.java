package Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import Model.Message;

public class MessageLog  {
	
	private Map<Integer, List<Message>> messages;
	
	
	public MessageLog() {
		
		messages = new TreeMap<Integer, List<Message>>();
		
		List<Message> list = new ArrayList<Message>();
		messages.put(0, list);
	}
	
	
}
	

