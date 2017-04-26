package org.alias.studyconnect.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.alias.studyconnect.model.Request;
import org.alias.studyconnect.model.UserDetails;
import org.alias.studyconnect.services.RequestService;

import com.fasterxml.jackson.core.JsonProcessingException;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RequestResource {
	
	private RequestService requestService;
	
	// Get my received request
	@Path("received")
	@GET
	public Response myReceivedRequest(@PathParam ("userId") int userId) throws JsonProcessingException{
		requestService = new RequestService();
		String result = requestService.getReceivedRequest(userId);
		return Response.status(Status.OK)
						.entity(result)
						.build();
	}
	
	//Get Sent Request
	@Path("sent")
	@GET
	public Response mySentRequest(@PathParam ("userId") int userId) throws JsonProcessingException{
		requestService = new RequestService();
		String result = requestService.getSentRequest(userId);
		return Response.status(Status.OK)
						.entity(result)
						.build();
	}
	
	/*	Create a new request in the request table with the request object recieved
		Add the request to the corresponding sent user hashtable
		Add the request to the corresponding received user hashtable
		Add to subjects list
		Throw incomplete if any of the steps fails
	*/
	@POST
	public Response addRequest (Request request){
		requestService = new RequestService();
		int result = requestService.addRequest(request);
		if(result != 1)
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		return Response.status(Status.OK)
						.build();
	}
	
	//	Completing or rejecting a request
	//	Delete the request with this primary key from the module table
	//	Delete it from both the requesting and the sent user hash set
	//	Close the chat room
	//	Remove users from each others request list
	@DELETE
	public Response deleteRequest (Request request){
		requestService = new RequestService();
		int result = requestService.deleteRequest(request);
		return Response.status(Status.OK)
						.build();
	}
	
	
//	Set the corresponding request flag to 1."
//	Change accept to complete in mobile"
//	Add the user to the chat list of both the users. "
	//Accepting a request
	@Path("accept")
	@PUT
	public Response acceptRequest(Request request){
		requestService = new RequestService();
		int result = requestService.acceptRequest(request);
		return Response.status(Status.OK)
					   .build();
	}
	

}
