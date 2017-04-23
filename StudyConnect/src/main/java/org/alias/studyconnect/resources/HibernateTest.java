package org.alias.studyconnect.resources;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.alias.studyconnect.model.College;
import org.alias.studyconnect.model.Department;
import org.alias.studyconnect.model.Module;
import org.alias.studyconnect.model.Request;
import org.alias.studyconnect.model.RequestId;
import org.alias.studyconnect.model.Subject;
import org.alias.studyconnect.model.UserDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
//		UserDetails users = new UserDetails();
//		users = session.get(UserDetails.class, 4986985);
//		Set<Subject> subjects = users.getSubjectList();
//		System.out.println(subjects.toString());
		
//		Subject subject = new Subject();
//		subject = session.get(Subject.class, 2);
//		Set<UserDetails> users = subject.getStudentList();
//		for(UserDetails user: users){
//			System.out.println(user.getUserName());
//			System.out.println(user.getUserId());
//		}
		
		
		
		
		
//		Add User
		
//		UserDetails user = new UserDetails ();
//		user.setUserName("Ali Asgar");
//		user.setUserId(4986985);
//		user.setEmail("aliasgar@mail.usf.edu");
//		user.setPassword("workhardpartyharder");
//		user.setApp_token("f-yVNbe00_s:APA91bEVjdK0Tk4vy54NEbL8GZ96A5YoFIhktT4vg_PKpo4ck-1L701WCzYWgCHp5ZglDDNbQm7b-3z3JuU20yWRPrgh2axVc-7KkygJaa0bXew5QBf58BL0PL35vzyVTPEEIopJ0-Ly");
//		session.save(user);
//		Add colleges
		
//		College college = new College();
//		Set<String> collegeList = new HashSet<>();
//		collegeList.add("Arts & Sciences");
//		collegeList.add("Behavioral & Community Sciences");
//		collegeList.add("Business");
//		collegeList.add("College of Pharmacy");
//		collegeList.add("College of Arts");
//		collegeList.add("Education");
//		collegeList.add("Engineering");
//		collegeList.add("Global Sustainability");
//		collegeList.add("Graduate School");
//		collegeList.add("Honors College");
//		collegeList.add("Marine Science");
//		collegeList.add("Medicine");
//		collegeList.add("Nursing");
//		collegeList.add("Public Health");
//		collegeList.add("Undergraduate Studies");
//
//		
//		for(String collegename : collegeList){
//			college.setCollegeName(collegename);	
//			session.save(college);
//			session.flush();
//			session.clear();		
//		}		
		
		//Add department to a particualar college
//		Department dept = new Department();
//		Set<String> deptList = new HashSet<>();
//		deptList.add("Chemical Engineering");
//		deptList.add("Interdisciplinary Engineering");
//		deptList.add("Electrical Engineering");
//		deptList.add("Mechanical Engineering");
//		deptList.add("Industrial and Management Systems");
//		deptList.add("Civil and Environmental Engineering");
//		deptList.add("Computer Science & Engineering");
//		deptList.add("Information Technology");
//		
//			
//		for(String deptName : deptList){
//			dept.setCollegeId(session.get(College.class,8));
//			dept.setName(deptName);
//			session.save(dept);
//			session.flush();
//			session.clear();
//		}
		
		
//		 adding subjects
		session.flush();
		session.clear();
		Subject sub1 = new Subject();
		sub1.setSubjectCRN(6);
		sub1.setSubjectName("Personal Finance");
		sub1.setCollege(session.get(College.class, 15));
		sub1.setDept(session.get(Department.class, 17));
		session.save(sub1);
		sub1 = new Subject();
		sub1.setSubjectCRN(7);
		sub1.setSubjectName("Fundmntls of Business Finance");
		sub1.setCollege(session.get(College.class, 15));
		sub1.setDept(session.get(Department.class,17));
		session.save(sub1);
		sub1 = new Subject();
		sub1.setSubjectCRN(8);
		sub1.setSubjectName("Principles of Finance");
		sub1.setCollege(session.get(College.class, 15));
		sub1.setDept(session.get(Department.class,17));
		session.save(sub1);
		sub1 = new Subject();
		sub1.setSubjectCRN(9);
		sub1.setSubjectName("International Finance");
		sub1.setCollege(session.get(College.class, 15));
		sub1.setDept(session.get(Department.class,17));    
		session.save(sub1);
		sub1 = new Subject();
		sub1.setSubjectCRN(10);
		sub1.setSubjectName("Working Capital Management");
		sub1.setCollege(session.get(College.class, 15));
		sub1.setDept(session.get(Department.class,17));
		session.save(sub1);
		sub1 = new Subject();
		sub1.setSubjectCRN(11);
		sub1.setSubjectName("Advanced Corporation Finance");
		sub1.setCollege(session.get(College.class, 15));
		sub1.setDept(session.get(Department.class,17));
		session.save(sub1);
		sub1 = new Subject();
		sub1.setSubjectCRN(12);
		sub1.setSubjectName("Financial Policies/Strategies");
		sub1.setCollege(session.get(College.class, 15));
		sub1.setDept(session.get(Department.class,17));
		session.save(sub1);
		sub1 = new Subject();
		sub1.setSubjectCRN(13);
		sub1.setSubjectName("Financial Statement Analysis");
		sub1.setCollege(session.get(College.class, 15));
		sub1.setDept(session.get(Department.class,17));
		session.save(sub1);
		sub1 = new Subject();
		sub1.setSubjectCRN(14);
		sub1.setSubjectName("Principles of Investments");
		sub1.setCollege(session.get(College.class, 15));
		sub1.setDept(session.get(Department.class,17));
		session.save(sub1);
		sub1 = new Subject();
		sub1.setSubjectCRN(15);
		sub1.setSubjectName("Applied Securities Analysis");
		sub1.setCollege(session.get(College.class, 15));
		sub1.setDept(session.get(Department.class,17));
		session.save(sub1);
		sub1 = new Subject();
		sub1.setSubjectCRN(16);
		sub1.setSubjectName("Independent Study");
		sub1.setCollege(session.get(College.class, 15));
		sub1.setDept(session.get(Department.class,17));
		session.save(sub1);
		sub1 = new Subject();
		sub1.setSubjectCRN(17);
		sub1.setSubjectName("Financial Modeling");
		sub1.setCollege(session.get(College.class, 15));
		sub1.setDept(session.get(Department.class,17));
		session.save(sub1);
		sub1 = new Subject();
		sub1.setSubjectCRN(18);
		sub1.setSubjectName("Finance Internship");
		sub1.setCollege(session.get(College.class, 15));
		sub1.setDept(session.get(Department.class,17));
		session.save(sub1);
		sub1 = new Subject();
		sub1.setSubjectCRN(19);
		sub1.setSubjectName("Financial Management");
		sub1.setCollege(session.get(College.class, 15));
		sub1.setDept(session.get(Department.class,17));
		session.save(sub1);
		sub1 = new Subject();
		sub1.setSubjectCRN(20);
		sub1.setSubjectName("Adv Financial Management");
		sub1.setCollege(session.get(College.class, 15));
		sub1.setDept(session.get(Department.class,17));
		session.save(sub1);
		sub1 = new Subject();
		sub1.setSubjectCRN(21);
		sub1.setSubjectName("Financial Policy");
		sub1.setCollege(session.get(College.class, 15));
		sub1.setDept(session.get(Department.class,17));
		session.save(sub1);
		
		
		//adding modules to a subject

//		session.flush();
//		session.clear();
//		Subject subject = session.get(Subject.class, 2);
//		Module module = new Module();
//		module.setModuleName("Development Framework");
//		module.setSubjectId(subject);
//		session.save(module);
//		module = new Module();
//		module.setModuleName("C# Programming");
//		module.setSubjectId(subject);
//		session.save(module);
//		module = new Module();
//		module.setModuleName("Network Protocols");
//		module.setSubjectId(subject);
//		session.save(module);
//		module = new Module();
//		module.setModuleName("Basic Network Programming");
//		module.setSubjectId(subject);
//		session.save(module);
//		module = new Module();
//		module.setModuleName("Network Programming Development");
//		module.setSubjectId(subject);
//		session.save(module);
//		module = new Module();
//		module.setModuleName("Distributed Systems");
//		module.setSubjectId(subject);
//		session.save(module);
//		module = new Module();
//		module.setModuleName("Fully Distributed Mutually Exclusive Protocols");
//		module.setSubjectId(subject);
//		session.save(module);
		
		//Adding request
		
//		Request request = new Request();
//		UserDetails toUser = session.get(UserDetails.class, 45259783);
//		UserDetails fromUser = session.get(UserDetails.class, 4986985);
//		Subject subject = session.get(Subject.class, 6);
//		request.setFlag(0);
//		request.setSubject(subject);
//		request.setUserSent(fromUser);
//		request.setUserReceived(toUser);
//		request.setToUserName(toUser.getUserName());
//		request.setFromUserName(fromUser.getUserName());
//		RequestId reqId = new RequestId();
//		reqId.setToUserId(toUser.getUserId());
//		reqId.setFromUserId(fromUser.getUserId());
//		reqId.setSubjectCRN(subject.getSubjectCRN());
//		request.setRequestId(reqId);
//		session.save(request);
		session.getTransaction().commit();
		session.close();
	}

}









// List of Dept

//Accounting
//Business Administration
//Finance
//Information System and Decision Sciences
//Management
//Marketing

//ECH	- Chemical Engineering
//EGB	- Interdisciplinary Engineering
//EGE	- Electrical Engineering
//EGR	- Mechanical Engineering
//EGS	- Industrial and Management Systems
//EGX	- Civil and Environmental Engineering


//		Personal Finance
//		Fundmntls of Business Finance
//		Principles of Finance
//		International Finance
//		Working Capital Management
//		Advanced Corporation Finance
//		Financial Policies/Strategies
//		Financial Statement Analysis
//		Principles of Investments
//		Applied Securities Analysis
//		Independent Study
//		Financial Modeling
//		Finance Internship
//		Financial Management
//		Adv Financial Management
//		Financial Policy



































