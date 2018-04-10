package  com.restws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.apache.http.client.ClientProtocolException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class clientUrlShortener {

public static final String PARAM_ID = "id";
public static final String PARAM_URL = "url";
public static final String GET_REQUEST = "GET";
public static final String POST_REQUEST = "POST";
public static final String PUT_REQUEST = "PUT";
public static final String DELETE_REQUEST = "DELETE";

public static void main(String[] args) throws ClientProtocolException, IOException {
 
  ClientConfig config = new DefaultClientConfig();
  Client client = Client.create(config);
  
  WebResource service = client.resource(UriBuilder.fromUri("http://localhost:8080/Restul-UrlShortner/service/targetresource/").build());
  // getting XML data
  //System.out.println(service. path(PARAM_ID).path("0").accept(MediaType.TEXT_PLAIN).get(String.class));
  //System.out.println(service. path("url").path("www.gmail.com").accept(MediaType.TEXT_PLAIN).get(String.class));

  // Test Cases
  ArrayList<ArrayList<String>> testCases = new ArrayList<ArrayList<String>>();
  ArrayList<String> scenario1 = new ArrayList<String>();

  scenario1.add(GET_REQUEST);
  scenario1.add(PARAM_ID);
  scenario1.add("group5_0");
  scenario1.add("error 301");
  
  testCases.add(scenario1);
  
  ArrayList<String> scenario2 = new ArrayList<String>();

  scenario2.add(PUT_REQUEST);
  scenario2.add(PARAM_ID);
  scenario2.add("group5_1");
  scenario2.add("Error 400");
  
  testCases.add(scenario2);
  
  ArrayList<String> scenario3 = new ArrayList<String>();

  scenario3.add(POST_REQUEST);
  scenario3.add("");
  scenario3.add("www.google.com");
  scenario3.add(TargetUrl.HTTP_GROUP5+"1");
  
  testCases.add(scenario3);
  
  ArrayList<String> scenario4 = new ArrayList<String>();

  scenario4.add(GET_REQUEST);
  scenario4.add("");
  scenario4.add("");
  scenario4.add("[group5_0,group5_1]");
  
  testCases.add(scenario4);
  
  ArrayList<String> scenario5 = new ArrayList<String>();

  scenario5.add(DELETE_REQUEST);
  scenario5.add(PARAM_ID);
  scenario5.add(TargetUrl.HTTP_GROUP5+"1");
  scenario5.add("Identificator " + TargetUrl.HTTP_GROUP5+"1" + " removed.");
  
  testCases.add(scenario5);
  
  for (ArrayList<String> testCase : testCases) {
	  invokeServer(service, testCase.get(0), testCase.get(1), testCase.get(2), testCase.get(3));
  }
  
 }
 
 public static void invokeServer(WebResource service, String restMethod, String paramType, String value, String expectedResult) {
	 
	 String result = "";
	 ClientResponse errorResponse = null;
	 boolean isError = false;
	 
	 try {
		 if (GET_REQUEST.equals(restMethod)) {
			 result = service. path(paramType).path(value).accept(MediaType.TEXT_PLAIN). get(String.class);
		 } else if (POST_REQUEST.equals(restMethod)) {
			 result = service. path(paramType).path(value).accept(MediaType.TEXT_PLAIN). post(String.class);
		 } else if (PUT_REQUEST.equals(restMethod)) {
			 result = service. path(paramType).path(value).accept(MediaType.TEXT_PLAIN). put(String.class);
		 } else if (DELETE_REQUEST.equals(restMethod)) {
			 result = service. path(paramType).path(value).accept(MediaType.TEXT_PLAIN). delete(String.class);
		 }
		 
	 } catch(UniformInterfaceException e) {
		 isError = true;
		 errorResponse = e.getResponse();		 
	 }
	 
	 if(!isError) {
		 System.out.println("Rest Method: " + restMethod + 
				 " | " + "Parameter(Path): " + paramType + 
				 " | " + "Value: " + value +
				 " | " + "Expected Result: " + expectedResult +
				 " | " + "Obtained Result: " + result);
	 } else {
		 System.out.println("Rest Method: " + restMethod + 
				 " | " + "Parameter(Path): " + paramType + 
				 " | " + "Value: " + value +
				 " | " + "Expected Result: " + expectedResult +
				 " | " + "Obtained Result: " + errorResponse);
	 }
	 
 }
 
}
