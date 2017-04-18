package org.alias.studyconnect.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.alias.studyconnect.services.DepartmentService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentResource {

	@GET
	public Response deptList(){
		
		DepartmentService deptService = new DepartmentService();
		String result = deptService.getDeptList();
		if(result ==null || result.equals(""))
			return Response.status(Status.NO_CONTENT).build();
		return Response.ok(result).build();
	}
	
	
}