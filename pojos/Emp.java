package pojos;

import java.sql.Date;

public class Emp {
	private int empId;
	private String name;
	private String addr;
	private double sal;
	private String deptId;
	private Date date;

	public Emp(int empId, String name, String addr, double sal, String deptId, Date date) {
		super();
		this.empId = empId;
		this.name = name;
		this.addr = addr;
		this.sal = sal;
		this.deptId = deptId;
		this.date = date;
	}

	// default constr for pojo specs.
	public Emp() {
		super();
	}

	public Emp(String name, String addr, double sal, String deptId, Date date) {
		super();
		this.name = name;
		this.addr = addr;
		this.sal = sal;
		this.deptId = deptId;
		this.date = date;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Emp [empId=" + empId + ", name=" + name + ", addr=" + addr + ", sal=" + sal + ", deptId=" + deptId
				+ ", date=" + date + "]";
	}

}
