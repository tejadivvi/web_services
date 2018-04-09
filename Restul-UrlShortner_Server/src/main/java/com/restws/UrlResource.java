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

import com.sun.jersey.spi.resource.Singleton;

@Path("/targetresource")
@Produces("application/xml")
@Singleton
public class UrlResource {

	private TreeMap<Integer, TargetUrl> urlMap = new TreeMap<Integer, TargetUrl>();

	public UrlResource() {

		TargetUrl url = new TargetUrl();
		url.setAddress("www.gmail.com");
		url.setId(0);
		urlMap.put(0, url);
	}

	@POST
	@Path("{url}")
	@Produces("text/plain")
	@Consumes("application/xml")
	public Response addUrl(@PathParam("url") String url) {
		boolean newUrl = true;
		TargetUrl tu = new TargetUrl();
		
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
			urlMap.put(id, tu);
			String output = "Shorter version of url: " + url + " is " + tu.getId();
			return Response.status(201).entity(output).build();
		} else {
			int id = urlMap.size();
			tu.setId(id);
			tu.setAddress(url);
			urlMap.put(id, tu);
			String output = "Error, URL already in the map.";
			return Response.status(400).entity(output).build();
		}
	}

// Example: http://localhost:8080/Restul-UrlShortner/service/targetresource/url/www.gmail.com
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
			String output = "Shorter version of url: " + url + " is " + tu.getId();
			return Response.status(200).entity(output).build();
		} else {
			String output = "Error, URL not found in the map.";
			return Response.status(400).entity(output).build();
		}
	}

// Example: http://localhost:8080/Restul-UrlShortner/service/targetresource/id/0	
	@GET
	@Path("/id/{id}")
	@Produces("text/plain")
	@Consumes("application/xml")
	public Response returnUrl(@PathParam("url") int id) {
		boolean found = false;
		TargetUrl tu = new TargetUrl();
		
		if (!urlMap.isEmpty()) {			
			for (int urlId : urlMap.keySet()) {
				if (urlId == id) {
					tu = urlMap.get(urlId);
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
			output = "Identificator " + id + " not found.";
			responseId = 404;
		}
		return Response.status(responseId).entity(output).build();
	}
	
	@PUT
	@Path("/id/{id}")
	@Produces("text/plain")
	@Consumes("application/xml")
	public Response handlePUTId(@PathParam("url") int id) {
			String output = "Operation not allowed.";
			int responseId = 400;
		return Response.status(responseId).entity(output).build();
	}
	
	@DELETE
	@Path("{id}")
	@Produces("text/plain")
	@Consumes("application/xml")
	public Response deleteUrl(@PathParam("id") int id) {
		boolean found = false;
		
		if (!urlMap.isEmpty()) {			
			for (int urlId : urlMap.keySet()) {
				if (urlId == id) {
					urlMap.remove(id);
					found = true;
					break;					
				} 
			}
		}
		int responseId;
		String output;
		
		if (found) {
			output = "Identificator " + id + " removed";
			responseId = 204;
		} else {
			output = "Identificator " + id + " not found.";
			responseId = 404;
		}
		return Response.status(responseId).entity(output).build();
	}
	
	
}