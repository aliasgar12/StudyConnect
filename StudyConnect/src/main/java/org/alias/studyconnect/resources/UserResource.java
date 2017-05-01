package org.alias.studyconnect.resources;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.alias.studyconnect.model.UserDetails;
import org.alias.studyconnect.services.LoginService;
import org.alias.studyconnect.services.RegistrationService;
import org.alias.studyconnect.services.UserService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;;

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class UserResource {
	
	private UserService us;
	private ObjectMapper objectMapper;
	
	
	
	@GET
	public String messageWelcome(){
		return "Welcome to StudyConnect";
	}
	
	
	@Path("/myregistration")	//Register a new user
	@POST
	public Response register(UserDetails user){
		RegistrationService rs = new RegistrationService();
		String userNew = rs.registerUser(user);
		if(userNew.equals("rollback"))
			return Response.status(Status.CONFLICT).build();
		if(userNew.equals("json"))
			return Response.status(Status.BAD_REQUEST).build();
		return Response.ok(userNew).build();
	}
	
	@Path("/update")
	@PUT
	public Response update(UserDetails user){
		RegistrationService rs = new RegistrationService();
		String result = rs.updateUserInfo(user);
		if(result.equals("rollback"))
			return Response.status(Status.BAD_REQUEST).build();
		return Response.ok().build();
	}
	
	//Get user details
	@GET						
	@Path("/{userId}")
	public Response profile(@PathParam("userId") int id){
		us = new UserService();
		UserDetails user = us.fetchProfile(id);
		return Response.status(javax.ws.rs.core.Response.Status.OK)
					   .entity(user)
					   .build();
	}
	
	
	//Login resource
	@Path("/login")				
	@POST
	public Response login(UserDetails user) throws JsonProcessingException{
			LoginService loginService = new LoginService();
			String result = loginService.login(user.getUserId(), user.getPassword());
			if(result != null){
				return Response.status(Status.OK)
					.entity(result)
					.build();
			}else{
				return Response.status(Status.NOT_FOUND)
						.build();
			}
	}
	
	// Subject Resource
	@Path("/{userId}/subject")		
	public SubjectResource message3(){
		return new SubjectResource();
	}
	
	
	// Resource for subject from database on various criteria
	@Path("/search/")				
	public SearchSubjectResource message4(){
		return new SearchSubjectResource();
	}
	
	@Path("{userId}/request/")
	public RequestResource message5(){
		return new RequestResource();
	}
	
	@Path("/college")
	public CollegeResource fetchCollegeList(){
		return new CollegeResource();
	}
	
	@Path("/dept")
	public DepartmentResource fetchDeptList(){
		return new DepartmentResource();
	}
	
	@Path("/token")
	public TokenResource handleToken(){
		return new TokenResource();
	}
}
