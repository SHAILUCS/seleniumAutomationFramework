package objectRepository.common;

import java.util.ArrayList;

public enum TADIG {
	AUSTA("AUSTA"),
	ALBAM("ALBAM"),
	AFGAW("AFGAW"), 
	ARGCM("ARGCM"),
	BMU01("BMU01"),
	GBRME("GBRME"),
	GBROR("GBROR"),
	GBRTP("GBRTP"),
	OMNGT("OMNGT"),
	NLDPT("NLDPT"),
	USAJS("USAJS"),
	USAW6("USAW6"), 
	AUSOP("AUSOP"), 
	BELKO("BELKO"), 
	NZLTM("NZLTM"), 
	GABCT("GABCT"), 
	MLTGO("MLTGO"), 
	COLCO("COLCO"), 
	USA16("USA16"), 
	USA27("USA27"), 
	USA31("USA31"), 
	USA58("USA58"), 
	USAD1("USAD1"), 
	USASC("USASC"), 
	USAST("USAST"), 
	USAW0("USAW0"), 
	USAW1("USAW1"), 
	USAW2("USAW2"), 
	USAW3("USAW3"), 
	USAW4("USAW4"), 
	USAW5("USAW5"), 
	;
	public String value;  
	private TADIG(String value){  
		this.value=value;  
	} 

	public static ArrayList<String> getValues(){
		ArrayList<String> list=new ArrayList<>();
		for (TADIG item : TADIG.values()) {
			list.add(item.value);
		}
		return list;
	}
}
