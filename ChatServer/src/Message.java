import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
    private String sender, content, recipient;
    private MessageType type;
    private boolean success; // Indica si la operación fue exitosa o no 
    
    public Message(MessageType type) {
    	this.type = type;
    }
    
    public Message(MessageType type, String content) {
    	this.type = type;
    	this.content = content;
    }
    
    public Message(MessageType type, String sender, String content, String recipient){
        this.type = type; this.sender = sender; this.content = content; this.recipient = recipient; this.success = true;
    }
    
    public boolean getSuccess() {
    	return success;
    }
    
    public void setSuccess(boolean val) {
    	this.success = val;
    }
    
    public String getSender() {
		return sender;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String value) {
		this.content = value;
	}

	public String getRecipient() {
		return recipient;
	}

	public MessageType getType() {
		return type;
	}

	@Override
    public String toString(){
        return "{type='"+type+"', sender='"+sender+"', content='"+content+"', recipient='"+recipient+"'}";
    }
}