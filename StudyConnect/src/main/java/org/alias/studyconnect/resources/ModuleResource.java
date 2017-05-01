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

import org.alias.studyconnect.model.Module;
import org.alias.studyconnect.services.ModuleService;

import com.fasterxml.jackson.core.JsonProcessingException;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ModuleResource {
	
	private ModuleService moduleService;
	
	//Fetch all the modules from a subject and send response
	@GET
	public Response getModules(@PathParam("subjectId") int subjectId){
		moduleService = new ModuleService();
		String result ="";
		try {
			result = moduleService.moduleBySubject(subjectId);
		} catch (JsonProcessingException e ) {
			e.printStackTrace();
			result = "Could not convert to JSON";
			return Response.status(Status.NOT_FOUND).entity(result)
					.build();
		}catch (NullPointerException e ) {
			e.printStackTrace();
			result = "No modules found for the subject";
			return Response.status(Status.NOT_FOUND).entity(result)
					.build();
		}
		return Response.status(Status.OK)
						.entity(result)
						.build();
	}
	
	
	//Fetch all the students who completed a module
	@GET
	@Path("module/{moduleId}/")
	public Response getStudentByModule(@PathParam("moduleId") int moduleId) throws JsonProcessingException{
		moduleService = new ModuleService();
		String moduleList = moduleService.getStudentByModule(moduleId);
		return Response.status(Status.OK)
						.entity(moduleList)
						.build();
	}
	
	//A student completes a module
//	Add to the completed module list in the user details
//	Add the student to the list completed student to the particular module
	@PUT
	@Path("module/")
	public Response moduleCompleted(@PathParam("userId") int userId , Module module){
		moduleService = new ModuleService();
		String result = moduleService.moduleCompleted(userId, module);
		if(!result.equals(""))
			return Response.ok(result)
					.build();	
		return Response.status(Status.NOT_FOUND).build();
		
	}
	
	
	
	
}



// NOT VALID ACCORDING TO CURRENT DATABASE DESIGN

//@POST
//@Path("module")
//public String message5(){
//	return "Add the module to module list of a student subject ";
//}
//@DELETE
//@Path("module")
//public String message6(){
//	return "Delete the module ";
//}
//@PUT
//@Path("module")
//public String message7(){
//	return "Edit the module name ";
//}
