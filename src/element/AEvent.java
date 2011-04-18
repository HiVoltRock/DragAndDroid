package element;

import global.EventType;

public class AEvent {
	public String type;
	public String name;
	public EventType event;
	
	public AEvent(String type, String name, EventType e) {
		this.type = type;
		this.name = name;
		this.event = e;		
	}
	
	public String toString() {
		String s="";
		s += type + " " + name + " " + event.toString();
		return s;
	}
}
