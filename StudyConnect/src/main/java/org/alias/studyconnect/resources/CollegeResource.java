package org.alias.studyconnect.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.alias.studyconnect.services.CollegeService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CollegeResource {

	@GET
	public Response collegeList(){
		
		CollegeService collegeService = new CollegeService();
		String result = collegeService.getCollegeList();
		if(result ==null || result.equals(""))
			return Response.status(Status.NO_CONTENT).build();
		return Response.ok(result).build();
	}
	
	
}
