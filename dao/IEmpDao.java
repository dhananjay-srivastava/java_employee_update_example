package dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import pojos.Emp;

public interface IEmpDao {
// to fetch emp details by criter
	ArrayList<Emp> getDetails(String dept, Date beginDate, Date endDate) throws Exception;

	HashMap<String, Double> getSalbyDep(String addr) throws Exception;
	
	String hireEmp(Emp e) throws Exception;
	
	String updateEmpDetails(int empid,String deptid,double sal) throws Exception;
	
}
