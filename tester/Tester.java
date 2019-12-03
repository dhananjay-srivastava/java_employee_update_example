package tester;

import java.sql.Date;
import java.util.Scanner;

import dao.EmpDaoImpl;
import pojos.Emp;

public class Tester {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			// create a DAO instance
			EmpDaoImpl dao = new EmpDaoImpl();
			boolean exit = false;
			while (!exit) {
				System.out.println("1: Get employee details 2. Get Salary grouped by department. 3. hire employee 4. update details 10. Exit.");
				try {
					switch (sc.nextInt()) {
					case 1:
						System.out.println("Enter dept, begin date n end date (yyyy-mm-dd)");
						dao.getDetails(sc.next(), Date.valueOf(sc.next()), Date.valueOf(sc.next()))
								.forEach(e -> System.out.println(e));
						break;

					case 2:
						System.out.println("Enter Address: ");
						dao.getSalbyDep(sc.next()).forEach((k, v) -> System.out.println(k + "\t" + v));
						break;

					case 3:
						System.out.println("Enter emp details String name, String addr, double sal, String deptId, Date date");
						Emp e = new Emp(sc.next(),sc.next(),sc.nextDouble(),sc.next(),Date.valueOf(sc.next()));
						System.out.println(dao.hireEmp(e));
						break;
					case 4:
						System.out.println("Enter update details: ID | Dept id | sal inc");
						//int empid, String deptid, double sal
						System.out.println(dao.updateEmpDetails(sc.nextInt(), sc.next(), sc.nextDouble()));
						break;
					case 10:
						dao.cleanUp();
						exit = true;
						break;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
