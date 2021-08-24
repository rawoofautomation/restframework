package stepDefinations;

import io.cucumber.java.Before;

public class Hooks {
	
	AddPlaceAPI a=new AddPlaceAPI();
	
	@Before("@DeletePlaceAPI")
	public void beforeScenario() throws Exception
	{
		if(AddPlaceAPI.res_place == null)
		{
			a.add_place_payload("abdul kalam", "urdu", "jamia masjid");
			a.user_calls_with_post_http_request("addPlaceAPI", "post");
			a.verify_from_is_matching("abdul kalam", "getPlaceAPI", "get");
		}
	}

}
