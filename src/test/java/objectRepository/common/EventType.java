package objectRepository.common;

import java.util.ArrayList;

public enum EventType{
	MO("MO"),
	MT("MT"),
	MB("MB"),
	MOMT("MO & MT"),
	ALL("All");
	public String value;  
	private EventType(String value){  
		this.value=value;  
	}
	public static ArrayList<String> getValues(){
		ArrayList<String> list=new ArrayList<>();
		for (EventType item : EventType.values()) {
			list.add(item.value);
		}
		return list;
	}
}
