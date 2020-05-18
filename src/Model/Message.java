package Model;

public class Message {
	

	private String contents;
	private String queue;

	public Message(String queue, String contents) {

		this.queue = queue;
		this.contents = contents;
	}

	public String getQueue() {
		return queue;
	}

	public void getQueue(String queue) {
		this.queue = queue;
	}
	
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
