package datagenerator;

import java.util.ArrayList;
import java.util.List;

import serialdeserial.AddPlacePlayload;
import serialdeserial.Location;

public class TestDataBuild {

	public AddPlacePlayload appPlacePayload(String name, String language, String address)
	{
		
		AddPlacePlayload a = new AddPlacePlayload();
		a.setAccuracy(50);
		a.setAddress(address);
		a.setLanguage(language);
		a.setName(name+" enterprises private limited");
		a.setPhone_number("+91 7702066520");
		a.setWebsite("https://www.rawoofbhai.com");
		
		
		
		List<String> types =new ArrayList<String>();
		types.add("park");
		types.add("shopping complex");
		a.setTypes(types);
		
		
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.5436453);
		a.setLocation(loc);
		
		return a;
		
	}
	
	
	
	public String getDeletePlacePayLoad(String p)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+p+"\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
}
