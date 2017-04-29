package org.alias.studyconnect.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.alias.studyconnect.model.UserDetails;
import org.alias.studyconnect.services.LoginService;
import org.alias.studyconnect.services.TokenService;

import com.fasterxml.jackson.core.JsonProcessingException;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class TokenResource {

	@Path("/addToken")
	@PUT
	public Response setToken(UserDetails user) {
		TokenService tokenService = new TokenService();
		int result = tokenService.setToken(user);
		if (result != 0) {
			return Response.status(Status.OK).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@Path("/deleteToken")
	@PUT
	public Response deleteToken(UserDetails user){
		TokenService tokenService = new TokenService();
		int result = tokenService.deleteToken(user);
		if (result != 0) {
			return Response.status(Status.OK).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

}
