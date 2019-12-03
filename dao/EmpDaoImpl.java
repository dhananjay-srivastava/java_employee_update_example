package dao;

import static Utils.DBUtils.fetchConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import pojos.Emp;

public class EmpDaoImpl implements IEmpDao {

	private Connection cn;
	private PreparedStatement pst1, pst2,pst3,pst4,pst5;

	public EmpDaoImpl() throws Exception {
		// get cn from db util

		cn = fetchConnection();
		pst1 = cn.prepareStatement("select * from my_emp where deptid=? and join_date between ? and ?");
		pst2 = cn.prepareStatement("select deptid,sum(sal) from my_emp where addr=? group by deptid ");
		pst3 = cn.prepareStatement("select max(empid) from my_emp");
		pst4 = cn.prepareStatement("insert into my_emp values(?,?,?,?,?,?)");
		pst5 = cn.prepareStatement("update my_emp set deptid=?,sal =sal+? where empid = ?");
		System.out.println("dao created");

	}

	public void cleanUp() throws Exception {
		if (pst1 != null) {
			pst1.close();
		}
		if (pst2 != null) {
			pst2.close();
		}
		if (pst3 != null) {
			pst3.close();
		}
		if (pst4 != null) {
			pst4.close();
		}
		if (pst5 != null) {
			pst5.close();
		}
		if (cn != null) {
			cn.close();
		}
		System.out.println("dao cleaned up");
	}

	@Override
	public ArrayList<Emp> getDetails(String dept, Date beginDate, Date endDate) throws Exception {

		// empty al
		ArrayList<Emp> al = new ArrayList<>();

		// set in params
		pst1.setString(1, dept);
		pst1.setDate(2, beginDate);
		pst1.setDate(3, endDate);
		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next())
				al.add(new Emp(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDouble(4), rst.getString(5),
						rst.getDate(6)));
		}

		return al;
	}

	@Override
	public HashMap<String, Double> getSalbyDep(String addr) throws Exception {
		HashMap<String, Double> hm = new HashMap<String, Double>();
		pst2.setString(1, addr);
		try (ResultSet rst = pst2.executeQuery()) {
			while (rst.next()) {
				hm.put(rst.getString(1), rst.getDouble(2));
			}

		}

		return hm;
	}

	public int getNextId() throws Exception{
		try(ResultSet rst1 = pst3.executeQuery()){
			if(rst1.next()) return rst1.getInt(1)+1;
		}
		return 1;
	}
	
	@Override
	public String hireEmp(Emp e) throws Exception {
		
			pst4.setInt(1, getNextId());
			pst4.setString(2, e.getName());
			pst4.setString(3, e.getAddr());
			pst4.setDouble(4, e.getSal());
			pst4.setString(5, e.getDeptId());
			pst4.setDate(6,e.getDate());
			int updateCount = pst4.executeUpdate();
			if(updateCount==1)return"Emp hiring succesful";
		
		return "Emp hiring failed";
	}

	@Override
	public String updateEmpDetails(int empid, String deptid, double sal) throws Exception {
		pst5.setInt(1, empid);
		pst5.setString(3,deptid);
		pst5.setDouble(2,sal);
		int updateCount = pst5.executeUpdate();
		if(updateCount>=1)return"Emp update succesful";	
		return "Emp update failed";
	}

}
