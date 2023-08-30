package com.patient;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class WelcomeMenu {
	private Connection con;
    public BufferedReader br;
	WelcomeMenu(Connection con) {
		this.con = con;
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	Scanner sc = new Scanner(System.in);

	public void printMenu() throws SQLException, IOException {
		while(true) {
			System.out.println("\nMedical Management System\n--------------------------\n");	
			System.out.println("1. Doctor Login");
			System.out.println("2. Patient Login");
			System.out.println("3. Doctor Registration");
			System.out.println("4. Patient Registration");
			System.out.println("5. Admin Login");
			System.out.println("6. Exit");

			System.out.print("\nEnter choice: ");
			int choice = sc.nextInt();

			switch(choice) {
			case 1: doctorLogin();
			break;
			case 2: patientLogin();
			break;
			case 3: doctorRegistration();
			break;
			case 4: patientRegistration();
			break;
			case 5: adminLogin();
			break;
			case 6: System.out.println("Thank you for using our services!");
					con.close();
					System.exit(0);
			default: System.out.println("Invalid choice! Please try again.");

			}
		}

	}
	private void adminLogin() throws SQLException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Enter your user email ");
	    String email = br.readLine();
	    System.out.println("Enter your password ");
	    String pas = br.readLine();
//	    System.out.println(name + " " + pas);
	    Statement smt = (Statement) con.createStatement();
	    
	    ResultSet rs = ((java.sql.Statement) smt).executeQuery("select email_id, password from admin where email_id = '"+email+"' and  password = '"+pas+"' ");
	    
	    if(rs.next()) {
	    	System.out.println("Admin logged in!");
//			Statement smt = con.createStatement();
			admin ad = new admin(smt, con);
			ad.run();
	    }
	    else {
	    	System.out.println("please enter correct admin details");
	    	adminLogin();
	    }
		

	}
	private void patientRegistration() throws IOException {
		System.out.print("Enter name: ");
		String name = br.readLine();
		//		System.out.println(name);
		System.out.print("Enter age: ");
		int age = sc.nextInt();

		System.out.print("Enter email: ");
		String email = sc.next();

		String password = "", password2 = "";

		do {
			if (!password.equals(password2))
				System.out.println("\nPasswords do not match! Please try again.\n");

			System.out.print("Enter password: ");
			password = sc.next();

			System.out.print("Confirm password: ");
			password2 = sc.next();
		} while	(!password.equals(password2));	


		System.out.print("Enter contact number: ");
		long contact = sc.nextLong();
		
		System.out.print("Enter address: ");
		String address = br.readLine();

		System.out.print("Enter blood group: ");
		String bg = sc.next().toUpperCase();

		try {
			PreparedStatement psmt = con.prepareStatement("insert into patient values(?,?,?,?,?,?,?)");
			psmt.setString(1, email);
			psmt.setString(2, password);
			psmt.setString(3, name);
			psmt.setInt(4, age);
			psmt.setLong(5, contact);
			psmt.setString(6, address);
			psmt.setString(7, bg);

			psmt.executeUpdate();

			System.out.println("Patient Registered!\n\n");

		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private void doctorRegistration() throws IOException {
		System.out.print("Enter name: ");
		String name = br.readLine();
		//		System.out.println(name);
		System.out.print("Enter age: ");
		int age = sc.nextInt();

		System.out.print("Enter email: ");
		String email = sc.next();

		String password = "", password2 = "";

		do {
			if (!password.equals(password2))
				System.out.println("\nPasswords do not match! Please try again.\n");

			System.out.print("Enter password: ");
			password = sc.next();

			System.out.print("Confirm password: ");
			password2 = sc.next();
		} while	(!password.equals(password2));	
		sc.nextLine();
		System.out.println("Enter specilization:");
		String specilization = sc.nextLine();
		sc.nextLine();
		
		System.out.println("Enter Fees:");
		int fees = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Enter start time:");
		String start_time = br.readLine();
		
		System.out.println("Enter end time:");
		String end_time = br.readLine();
		
		System.out.print("Enter contact number: ");
		String contact = br.readLine();

		System.out.print("Enter address: ");
		String address = br.readLine();

		System.out.print("Enter blood group: ");
		String bg = sc.next().toUpperCase();
		
		String status = "pending";

		try {
			PreparedStatement psmt = con.prepareStatement("insert into doctor values(?,?,?,?,?,?,?,?,?,?,?,?)");
			psmt.setString(1, email);
			psmt.setString(2, password);
			psmt.setString(3, name);
			psmt.setInt(4, age);
			psmt.setString(5,specilization );
			psmt.setInt(6,fees);
			psmt.setString(7, start_time);
			psmt.setString(8, end_time);
			psmt.setString(9, contact);
			psmt.setString(10, address);
			psmt.setString(11, bg);
			psmt.setString(12, status);

			psmt.executeUpdate();
			System.out.println("Doctor registered!");

		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	private void patientLogin() {
		while(true)
		{
			System.out.println("\nPatient Login\n--------------------\n");	
			System.out.print("Enter email: ");
			String email = sc.next();
//			System.out.println(email);

			System.out.print("Enter password: ");
			String password = sc.next();
//			System.out.println(password);
			
			try {
				Statement smt = con.createStatement();
				ResultSet rs = smt.executeQuery("select * from patient where email_id ='"+email+"' and password='"+password+"'");
				

//				rs.next();
//				System.out.println(rs.getString(1));
				boolean f =false;
//				while(rs.next())
//				{
//				
//					 f =true;
//				}
//				
				if(rs.next())
				{
					System.out.println("Patient logged in!\n\n");
					String e = null;
					String pass = null;
					String name = null;
					int age = 0;
					String contact = "";
					String address = null;
					String bloodGroup = null;
				
						e = rs.getString(1);
						pass = rs.getString(2);
						name = rs.getString(3);
						age = rs.getInt(4);
						contact = rs.getString(5);
						address = rs.getString(6);
						bloodGroup = rs.getString(7);
					patient p = new patient(con,e,pass,name,age,contact,address,bloodGroup);
					p.viewPatientMenu();
					
				}
				else {
					System.out.println("Invalid Credentials! Please try again.\n");
				}
//				con.close();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	private void doctorLogin() {

		while(true)
		{
			System.out.println("\nDoctor Login\n--------------------\n");	
			System.out.print("Enter email: ");
			String email = sc.next();
			System.out.println(email);

			System.out.print("Enter password: ");
			String password = sc.next();
			System.out.println(password);

			try {
				Statement smt = con.createStatement();
				ResultSet rs = smt.executeQuery("select * from doctor where email_id ='"+email+"' and password='"+password+"' and  status = 'approved'");

//				rs.next();
//				System.out.println(rs.getString(1));
				boolean f =false;
//				rs.next();
//				System.out.println(rs.getString(1));
				if(rs.next())
				{
					System.out.println("Doctor logged in!\n\n");
					String e = null;
					String pass = null;
					String name = null;
					int age = 0;
					String specialization;
					int fees;
					String start_time;
					String end_time;
					String contact = "";
					String address = null;
					String status;
					String bloodGroup = null;
				
						e = rs.getString(1);
						pass = rs.getString(2);
						name = rs.getString(3);
						age = rs.getInt(4);
						specialization = rs.getString(5);
						fees = rs.getInt(6);
						start_time  = rs.getString(7);
						end_time = rs.getString(8);
						contact = rs.getString(9);
						address = rs.getString(10);
						bloodGroup = rs.getString(11);
						status = rs.getString(12);
					doctor d = new doctor(con,e,pass,name,age,specialization,fees,start_time,end_time,contact,address,bloodGroup,status);
					d.viewDoctorMenu();
					
				}
				else {
					System.out.println("Invalid Credentials or Account Approval Pending! Please try again later.\n");
					return;
				}
////				con.close();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}

	}
}
