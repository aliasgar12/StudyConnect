package org.alias.studyconnect.resources;

import java.util.List;
import java.util.Set;

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

import org.alias.studyconnect.model.Subject;
import org.alias.studyconnect.services.SubjectService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectResource {
	
	private SubjectService ss;
	private ObjectMapper objectMapper;
	
	// "fetch details of student id " + Id + " and send back the list of subject as reponse";
	@GET
	public Response mySubjects(@PathParam("userId") int Id) throws JsonProcessingException{
		ss = new SubjectService();
		Set<Subject> mySubjects = ss.mySubjects(Id);
		objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		String mySubjectJson = objectMapper.writeValueAsString(mySubjects);
		return Response.status(Status.OK)
						.entity(mySubjectJson)
						.build();
	}
	
	@POST
	public Response addSubject (@PathParam("userId") int id, Subject subject){
		ss= new SubjectService();
		ss.addSubject(id, subject);
		return Response.ok(subject).build();
//				return "Call the service to add the course and send the response";
	}
	
	@DELETE
	public Response deleteSubject (@PathParam("userId") int id, Subject subject) throws JsonProcessingException{
		ss= new SubjectService();
		Set<Subject> leftSubjects= ss.delSubject(id, subject);
		objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		String leftSubs = objectMapper.writeValueAsString(leftSubjects);
		return Response.ok(leftSubs).build();
	}
//		return "Call the service to delete the subject and send the appropriate response  ";
	
	@Path("/{subjectId}/")
	public ModuleResource message4(){
		return new ModuleResource();
	}
}
