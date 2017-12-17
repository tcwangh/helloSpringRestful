package idv.tim.test.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class Message {
	
	private String name;
	private String text;
	public Message() {
		this.name = "";
		this.text = "";
	}
	
	public Message(String name,String text) {
		this.name = name;
        this.text = text;
	}
	@XmlElement
	public String getName() {
        return name;
    }
	@XmlElement  
    public String getText() {
        return text;
    }
}
