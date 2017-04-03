package org.alias.studyconnect.resources;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.alias.studyconnect.model.UserDetails;
import org.alias.studyconnect.services.userservices.RegistrationService;;

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class UserResource {
	
	@GET
	public String messageWelcome(){
		return "Welcome to StudyConnect";
	}
	
	@Path("/myregistration")
	@POST
	public UserDetails message(UserDetails user){
		RegistrationService rs = new RegistrationService();
		UserDetails userNew = rs.registerUser(user);
		return userNew;
//		return "This request needs to register a new user to the database and send back the response as ok"
//				+ "or not ok with redirection to the add courses page";
	}
	
	@GET
	@Path("/{userId}")
	public String message1(){
		return "This response will fetch the user details and send user details - for the settings/profile page";
	}
	
	@Path("/login")
	@GET
	public String message2(){
		return "This methhod will attempt validating login and return the response with all the"
				+ "subject that a student has enrolled and redirect to the dashboard";
	}
	
	
	@Path("/{userId}/subject")
	public SubjectResource message3(){
		return new SubjectResource();
	}
	
	@Path("/search/")
	public SearchSubjectResource message4(){
		return new SearchSubjectResource();
	}
	
	@Path("/request/")
	public RequestResource message5(){
		return new RequestResource();
	}
	
}