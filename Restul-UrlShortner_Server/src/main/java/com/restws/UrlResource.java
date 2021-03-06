package com.restws;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.validator.UrlValidator;

import com.sun.jersey.spi.resource.Singleton;

@Path("/targetresource")
@Produces("application/xml")
@Singleton
public class UrlResource {

	private TreeMap<String, TargetUrl> urlMap = new TreeMap<String, TargetUrl>();

	public UrlResource() {

		TargetUrl url = new TargetUrl();
		url.setAddress("www.gmail.com");
		url.setId(0);
		url.setShortUrl(0);
		urlMap.put(url.getShortUrl(), url);
	}

	@POST
	@Path("/{url}")
	@Produces("text/plain")
	@Consumes("application/xml")
	public Response addUrl(@PathParam("url") String url) {

		String output = "";
		int responseStatus;
		
		if(isValidInputUrl(url)) {
			
			boolean newUrl = true;
			TargetUrl tu = new TargetUrl();

			System.out.println("url received - post method: " + url);
			
			if (!urlMap.isEmpty()) {			
				for (TargetUrl storedUrl : urlMap.values()) {
					if (storedUrl.getAddress().equals(url)) {
						newUrl = false;
						tu = storedUrl;
						break;					
					} 
				}
			}
			
			if (newUrl) {
				int id = urlMap.size();
				tu.setId(id);
				tu.setAddress(url);
				tu.setShortUrl(id);
				urlMap.put(tu.getShortUrl(), tu);
				output = "Shorter version of url: " + url + " is " + tu.getShortUrl();
				responseStatus = 201;
				
			} else {
				output = "Error, URL already in the map.";
				responseStatus = 400;
			}
		}
		else {
			output = "Invalid URL inserted.";
			responseStatus = 400;
		}
		
		return Response.status(responseStatus).entity(output).build();
	}

	@GET
	@Path("/url/{url}")
	@Produces("text/plain")
	@Consumes("application/xml")
	public Response returnId(@PathParam("url") String url) {
		boolean found = false;
		TargetUrl tu = new TargetUrl();
		
		if (!urlMap.isEmpty()) {			
			for (TargetUrl storedUrl : urlMap.values()) {
				if (storedUrl.getAddress().equals(url)) {
					found = true;
					tu = storedUrl;
					break;					
				} 
			}
		}
		
		if (found) {
			String output = "Shorter version of url: " + url + " is " + tu.getShortUrl();
			return Response.status(200).entity(output).build();
		} else {
			String output = "Error, URL not found in the map.";
			return Response.status(400).entity(output).build();
		}
	}


	@GET
	@Path("/id/{shortVersion}")
	@Produces("text/plain")
	@Consumes("application/xml")
	public Response returnUrl(@PathParam("shortVersion") String shortVersion) {
		boolean found = false;
		TargetUrl tu = new TargetUrl();
		
		if (!urlMap.isEmpty()) {			
			for (String shortUrl : urlMap.keySet()) {
				if (shortUrl.equals(shortVersion)) {
					tu = urlMap.get(shortVersion);
					found = true;
					break;					
				} 
			}
		}
		int responseId;
		String output;
		
		if (found) {
			output = "Url: " + tu.getAddress();
			responseId = 301;
		} else {
			output = "Identificator " + shortVersion + " not found.";
			responseId = 404;
		}
		return Response.status(responseId).entity(output).build();
	}
	
	@GET
	@Produces("text/plain")
	@Consumes("application/xml")
	public Response returnKeys() {
		boolean found = false;
		TargetUrl tu = new TargetUrl();
		
		int responseId = 200;
		String output;
		output = "Keys: " + urlMap.keySet();
		
		return Response.status(responseId).entity(output).build();
	}	
	
	@PUT
	@Path("/id/{shortVersion}")
	@Produces("text/plain")
	@Consumes("application/xml")
	public Response handlePUTId(@PathParam("shortVersion") String shortVersion) {
			String output = "Operation not allowed.";
			int responseId = 400;
		return Response.status(responseId).entity(output).build();
	}
	
	@DELETE
	@Path("/id/{shortVersion}")
	@Produces("text/plain")
	@Consumes("application/xml")
	public Response deleteUrl(@PathParam("shortVersion") String shortVersion) {
		System.out.println("Entered in the delete method.");
		boolean found = false;
		
		if (!urlMap.isEmpty()) {			
			for (String shortUrl : urlMap.keySet()) {
				if (shortUrl.equals(shortVersion)) {
					urlMap.remove(shortUrl);
					found = true;
					break;					
				} 
			}
		}
		int responseId;
		String output;
		
		if (found) {
			output = "Identificator " + shortVersion + " removed";
			responseId = 204;
		} else {
			output = "Identificator " + shortVersion + " not found.";
			responseId = 404;
		}
		return Response.status(responseId).entity(output).build();
	}
	
	@DELETE
	@Path("")
	@Produces("text/plain")
	@Consumes("application/xml")
	public Response deleteIgnore() {

		int responseId;
		String output;
		output = "Delete operation without parameters is not supported.";
		responseId = 204;
		return Response.status(responseId).entity(output).build();
	}
	
	private boolean isValidInputUrl(String urlInput) {
		boolean result;
		
		if (!urlInput.contains("http://") && !urlInput.contains("https://")) {
			urlInput = "http://" + urlInput;
		}
		
		UrlValidator urlValidator = new UrlValidator();
	    if (urlValidator.isValid(urlInput)) {
	       System.out.println("url is valid");
	       result = true;
	    } else {
	       System.out.println("url is invalid: " + urlInput);
	       result = false;
	    }
	    
	    return result;
	}
	
}
