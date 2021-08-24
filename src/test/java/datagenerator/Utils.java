package datagenerator;

import java.io.FileOutputStream;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	static RequestSpecification reqspec;
	JsonPath jp;
	
	
	public RequestSpecification requestSpecification() throws Exception
	{
		String date = new SimpleDateFormat("ddmmyyyy hhmmss").format(new Date());
		if(reqspec==null) {
		PrintStream stream = new PrintStream(new FileOutputStream(date+"logging.txt"));
		 reqspec= new RequestSpecBuilder()
				.setBaseUri(getPropertyValue("base_uri"))
				.setContentType("application/json")
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(stream))
				.addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.build();
						
		 return reqspec;
		}
		else
			return reqspec;
	}
	
	
	public String getPropertyValue(String key) throws IOException
	{
		FileInputStream fis = 
		new FileInputStream(System.getProperty("user.dir")+"//config.properties");
		
		Properties p =new Properties();
		p.load(fis);
		return p.getProperty(key);
		
	}
	
	
	public String getValueFromJSON(Response response,String path)
	{
		 jp = new JsonPath(response.asString());
		System.out.println(jp.getString(path));
		return jp.getString(path);
		
	}
	
	public Response httpMethod(RequestSpecification body,String httpReqMethod,String httpMethod)
	
	{
		APIResources resource =APIResources.valueOf(httpReqMethod);
		
		if(httpMethod.equalsIgnoreCase("POST"))
			return body.when().post(resource.getResource());
		else if(httpMethod.equalsIgnoreCase("GET"))
			return when().get(resource.getResource());
		else if(httpMethod.equalsIgnoreCase("DELETE"))
			return body.when().delete(resource.getResource());
		else
			return null;
			
			
		
	}
	
	
}
