package org.alias.studyconnect.resources;

import java.util.HashSet;
import java.util.Set;

import org.alias.studyconnect.model.College;
import org.alias.studyconnect.model.Department;
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
//		session.beginTransaction();
//		
//		for(String collegename : collegeList){
//			college.setCollegeName(collegename);	
//			session.save(college);
//			session.flush();
//			session.clear();
//			
//		}		
		
		
//		Department dept = new Department();
//		Set<String> deptList = new HashSet<>();
//		deptList.add("Accounting");
//		deptList.add("Business Administration");
//		deptList.add("Finance");
//		deptList.add("Information System and Decision Sciences");
//		deptList.add("Management");
//		deptList.add("Marketing");
		
		session.beginTransaction();
//		
//		for(String deptName : deptList){
//			dept.setCollegeId(session.get(College.class,15));
//			dept.setName(deptName);
//			session.save(dept);
//			session.flush();
//			session.clear();
//		}
		// adding a few subjects
//		session.beginTransaction();
		session.flush();
		session.clear();
		Subject sub1 = new Subject();
		sub1.setSubjectCRN(6);
		sub1.setSubjectName("Investment Banking");
		sub1.setCollege(session.get(College.class, 15));
		sub1.setDept(session.get(Department.class,24));
		session.save(sub1);
//		sub1 = new Subject();
//		sub1.setSubjectCRN(2);
//		sub1.setSubjectName("Networking Programming");
//		sub1.setCollege(session.get(College.class, 8));
//		sub1.setDept(session.get(Department.class,17));
//		session.save(sub1);
//		sub1 = new Subject();
//		sub1.setSubjectCRN(3);
//		sub1.setSubjectName("Advanced OOP");
//		sub1.setCollege(session.get(College.class, 8));
//		sub1.setDept(session.get(Department.class,17));
//		session.save(sub1);
//		sub1 = new Subject();
//		sub1.setSubjectCRN(4);
//		sub1.setSubjectName("Graduate Practicum");
//		sub1.setCollege(session.get(College.class, 8));
//		sub1.setDept(session.get(Department.class,17));
//		session.save(sub1);
//		sub1 = new Subject();
//		sub1.setSubjectCRN(5);
//		sub1.setSubjectName("Software Testing");
//		sub1.setCollege(session.get(College.class, 8));
//		sub1.setDept(session.get(Department.class,17));
//		session.save(sub1);
		
		session.getTransaction().commit();
		session.close();
	}

}
