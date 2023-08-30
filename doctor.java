package com.patient;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class doctor {


	private String email;
	private String name;
	private String password;
	private String address;
	private String specialization;
	private String start_time;
	private String end_time;
	private int fees;
	private String contact;
	private int age;
	private String bloodGroup;
	private String status;
	Connection con;	
	Scanner sc = new Scanner(System.in);
	BufferedReader br;
	doctor(Connection con,String email,String password,String name,int age,String specialization,int fees,String start_time,
			String end_time,String contact,String address,String bloodGroup,String status)
	{
		this.con  = con;
		this.email = email;
		this.name = name;
		this.password = password;
		this.address = address;
		this.specialization = specialization;
		this.start_time = start_time;
		this.end_time = end_time;
		this.fees = fees;
		this.contact = contact;
		this.age = age;
		this.bloodGroup = bloodGroup;
		this.status =status;

		br = new BufferedReader(new InputStreamReader(System.in));
	}

	public void viewDoctorMenu() throws SQLException, IOException {
		while(true) {
			System.out.println("\nChoose your activity\n--------------------------\n");	
			System.out.println("1. View profile");
			System.out.println("2. Update profile");
			System.out.println("3. View Appointment");
			System.out.println("4. Write Prescription");
			System.out.println("5. Exit");

			System.out.print("\nEnter choice: ");
			int choice = sc.nextInt();

			switch(choice) {
			case 1: viewDoctorProfile();
			break;
			case 2: updateDoctorProfile();
			break;
			case 3: viewDoctorAppointments();
			break;
			case 4: writePrescriptions();
			break;
			case 5: System.out.println("Thank you for using our services!");
			con.close();
			System.exit(0);
			default: System.out.println("Invalid choice! Please try again.");

			}
		}

	}

	public void viewDoctorProfile(){

		System.out.println("Id:"+this.email);
		System.out.println("Name:"+this.name);
		System.out.println("Age:"+this.age);
		System.out.println("Specialization:"+this.specialization);
		System.out.println("Fees:"+this.fees);
		System.out.println("Start time:"+this.start_time);
		System.out.println("End time:"+this.end_time);
		System.out.println("Address:"+this.address);
		System.out.println("Contact"+this.contact);
		System.out.println("Age"+this.age);
		System.out.println("Blood Group"+this.bloodGroup);
		System.out.println("Status:"+this.status);

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public int getFees() {
		return fees;
	}

	public void setFees(int fees) {
		this.fees = fees;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	private void doctorUpdateMenu() {
		System.out.println("\nUpdate Doctor Details\n--------------------------\n");	
		System.out.println("1. Edit Name");
		System.out.println("2. Edit Email");
		System.out.println("3. Edit Password");
		System.out.println("4. Edit Age");
		System.out.println("5. Edit Contact");
		System.out.println("6. Edit Address");
		System.out.println("7. Edit Blood Group");
		System.out.println("8. Exit\n");

	}
	private void update_curr_instance()
	{

		try {
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery("select * from doctor where email_id ='"+email+"' and password='"+password+"' and  status = 'approved'");

			//			rs.next();
			//			System.out.println(rs.getString(1));
			boolean f =false;
			//			rs.next();
			//			System.out.println(rs.getString(1));
			if(rs.next())
			{


				email = rs.getString(1);
				password = rs.getString(2);
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

			}
			else {
				System.out.println("You have Changed either email or password please login again .\n");
				return;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	private void updateDoctorProfile() throws IOException, SQLException {
		while(true) {
			doctorUpdateMenu();

			System.out.print("Enter choice: ");
			int choice = sc.nextInt();

			switch(choice) {
			case 1: updateName();
			break;
			case 2: updateEmail();
			break;
			case 3: updatePassword();
			break;
			case 4: updateAge();
			break;
			case 5: updateContact();
			break;
			case 6: updateAddress();
			break;
			case 7: updateBloodGroup();
			break;
			case 8: System.out.println("Thank you for using our services!");
			return;
			default: System.out.println("Invalid choice! Please try again.");
			}
		}
	}
	private void updateBloodGroup() throws IOException, SQLException {
		System.out.print("Enter Blood Group: ");
		String bg = br.readLine().toUpperCase();

		String query1 = "update doctor set blood_group = '" + bg + "' where email_id = '"+this.email+"'";
		Statement smt = (Statement) con.createStatement();

		int count = smt.executeUpdate(query1);	

		if(count > 0) {
			System.out.println("Doctor Blood Group Updated!");
			update_curr_instance();
		}
		else {
			System.out.println("Doctor Blood Group could not be updated! Please try again");
			updateBloodGroup();
			return;
		}
	}

	private void updateAddress() throws IOException, SQLException {
		System.out.print("Enter Address: ");
		String address = br.readLine();

		String query1 = "update doctor set address = '" + address + "' where email_id = '"+this.email+"'";
		Statement smt = (Statement) con.createStatement();

		int count = smt.executeUpdate(query1);	

		if(count > 0) {
			System.out.println("Doctor Address Updated!");
			update_curr_instance();
		}
		else {
			System.out.println("Doctor Address could not be updated! Please try again");
			updateAddress();
			return;
		}
	}

	private void updateContact() throws IOException, SQLException {
		System.out.print("Enter Contact: ");
		String contact = br.readLine();

		String query1 = "update doctor set contact = '" + contact + "' where email_id = '"+this.email+"'";
		Statement smt = (Statement) con.createStatement();

		int count = smt.executeUpdate(query1);	

		if(count > 0) {
			System.out.println("Doctor Contact Updated!");
			update_curr_instance();
		}
		else {
			System.out.println("Doctor Contact could not be updated! Please try again");
			updateContact();
			return;
		}
	}

	private void updateAge() throws IOException, SQLException {
		System.out.print("Enter Age: ");
		int age = sc.nextInt();

		String query1 = "update doctor set age = " + age + " where email_id = '"+this.email+"'";
		Statement smt = (Statement) con.createStatement();

		int count = smt.executeUpdate(query1);	

		if(count > 0) {
			System.out.println("Doctor Age Updated!");
			update_curr_instance();
		}
		else {
			System.out.println("Doctor Age could not be updated! Please try again");
			updateAge();
			return;
		}
	}

	private void updatePassword() throws IOException, SQLException {
		Statement smt = (Statement) con.createStatement();
		ResultSet rs = ((java.sql.Statement) smt).executeQuery("select password from doctor where email_id='" + this.email +"'");

		String o_pass="";
		while(rs.next()){
			//	    int eid = rs.getInt(1);
			o_pass = rs.getString(1);
			//	    System.out.println(rs.getString(1));
		}

		System.out.print("Enter old password: ");
		String old_pass = br.readLine();

		if (!o_pass.equals(old_pass)) {
			System.out.println("You have entered the wrong password!");
			return;
		}

		String new_pass, n_pass;
		int cnt = 0;
		do {
			if(cnt++ >= 3)
			{
				System.out.println("3 incorrect attempts. You will receive a $15000 penalty!");
				return;
			}
			System.out.print("Enter new password: ");
			new_pass = br.readLine();
			System.out.print("Confirm new password: ");
			n_pass = br.readLine();
		} while(!new_pass.equals(n_pass));


		String query1 = "update doctor set password = '" + new_pass + "' where email_id = '"+this.email+"'";


		int count = smt.executeUpdate(query1);	

		if(count > 0) {
			System.out.println("Doctor Password Updated!");
			update_curr_instance();
		}
		else {
			System.out.println("Doctor Password could not be updated! Please try again");
			updatePassword();
			return;
		}
	}

	private void updateEmail() throws IOException, SQLException {
		System.out.print("Enter email: ");
		String em_id = br.readLine();

		String query1 = "update doctor set email_id = '" + em_id + "' where email_id = '"+this.email+"'";
		Statement smt = (Statement) con.createStatement();

		int count = smt.executeUpdate(query1);	

		if(count > 0) {
			System.out.println("Doctor Email Updated!");
			update_curr_instance();
		}
		else {
			System.out.println("Doctor Email could not be updated! Please try again");
			updateEmail();
			return;
		}
	}

	private void updateName() throws IOException, SQLException {
		System.out.print("Enter Name: ");
		String name = br.readLine();

		String query1 = "update doctor set name = '" + name + "' where email_id = '"+this.email+"'";
		Statement smt = (Statement) con.createStatement();

		int count = smt.executeUpdate(query1);	

		if(count > 0) {
			System.out.println("Doctor Name Updated!");
			update_curr_instance();
		}
		else {
			System.out.println("Doctor Name could not be updated! Please try again");
			updateName();
			return;
		}
	}

	private void viewDoctorAppointments() {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from appointment where doctor_email='"+email+"'");
			int check =0;
			while(rs.next()) {
				check =1;
				System.out.println("Patient Email ID: "+rs.getString(1)+" Doctor Email ID: "+rs.getString(2)+
						" Appointment Date: "+rs.getString(3)+" Slot: "+rs.getInt(4)+" Appointment Time: "
						+rs.getString(5)+" Bill: "+rs.getInt(6)+" Prescription: "+rs.getString(7));
			}
			if(check==0)
			{
				System.out.println("You have no appointments..");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void writePrescriptions() {
		
		viewDoctorAppointments();
		System.out.println("Enter the patient details for which you want to write precription..");
		System.out.println("Enter email id:");
		sc.nextLine();
		String pEmail = sc.nextLine();
		System.out.println("Enter date (yyyy-mm-mm):");
		String date = sc.nextLine();
		System.out.println("Enter slot:");
		int slot = sc.nextInt();
		
		try {
			Statement smt = con.createStatement();
			ResultSet rs = smt.executeQuery("Select * from appointment where patient_email ='"+pEmail+"' and appointment_date = '"+date+"' and slot ="+slot);
			if(!rs.next())
			{
				System.out.println("No record found..");
				return;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<String> prescription = new ArrayList<>();
		String finalPresc="";
		System.out.println("Enter prescription");
		int c=1;
		do {
			System.out.print(c +".");
			sc.nextLine();
			String s = sc.nextLine();
			prescription.add(c+"."+s);
			c++;
			System.out.println("for more insert press y");
			String choice = sc.next();
			if(choice.equalsIgnoreCase("Y"))
				continue;
			else
			{
				for(String st : prescription)
				{
					finalPresc = finalPresc + st + "\n";
				}
				break;
			}
		}while(true);
		
		try {
			PreparedStatement psmt = con.prepareStatement("update appointment set perscription ='"+finalPresc+ "' where patient_email = '"+pEmail+"' and appointment_date ='"+date+"' and slot ="+slot);
			int valid = psmt.executeUpdate();
			if(valid>0)
			{
				System.out.println("Prescription is uploaded");
			}
			else
			{
				System.out.println("Error ..");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


