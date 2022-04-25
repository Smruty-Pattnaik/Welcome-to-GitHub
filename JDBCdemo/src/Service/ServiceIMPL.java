package Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;

import InvalidException.InvalidIDexception;

public class ServiceIMPL implements EmployeeService{
	static Scanner sc=new Scanner(System.in);
	static String url="jdbc:mysql://localhost:3306/";
	static String dbname="EMPDETAILS";
	static String username="root";
	static String password="Sagarika@00";
	
	static Statement st=null;
	static ResultSet rs=null;
	@Override
	public void dbConnection() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url+dbname,username,password);
			st=con.createStatement();
			rs=st.executeQuery("select * from Employees");
			System.out.println("----Employee Details----");
			System.out.println("ID\tName\tState\tSalary\t\n");
			while(rs.next()) {
				if(rs.getInt(4)>20000) {
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+" "+rs.getString(3)+ "\t"+rs.getInt(4));
					
				}
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void show() {
		  try {
	            rs=st.executeQuery("select * from Employees");

        System.out.println("=========Displaying Employee Details=========\n");
        System.out.println("ID"+ "\t" + "NAME" + "\t\t  " + "STATE" + "\t" + "SALARY");
        while(rs.next())
        {  System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t  "+rs.getString(3)+"\t"+rs.getInt(4));
        }
    }
	catch (Exception e) {
          
        System.out.println(e);

	}
	}

	

	@Override
	public void updateSalary()  {
		// TODO Auto-generated method stub
		System.out.println("\nUpdate Employee salary by ID");
		System.out.println("Enter ID: ");
		int update_id=sc.nextInt();
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url+dbname,username,password);
			st=con.createStatement();
			rs=st.executeQuery("select * from Employees" );
			int id=0;
			while(rs.next()) {
				if(rs.getInt(1)==update_id) {
					id=rs.getInt(1);
					break;
				}
			}
			try {
				if(id==0) {
					throw new InvalidIDexception("invalid id");
				}else {
					System.out.print("Enter salary: ");

					int empSal=sc.nextInt();

					st.executeUpdate("UPDATE Employees SET SALARY='"+empSal+"' WHERE ID = '"+update_id+"'");


					System.out.println("\n Salary updated Successfully!!!!!!");
					dbConnection();
				}
				
			}
			catch(InvalidIDexception e) {
				e.printStackTrace();
			}
		}
			catch(Exception e) {
				System.out.println(e);
			}
			
			
			
		
		
	}
	
}

