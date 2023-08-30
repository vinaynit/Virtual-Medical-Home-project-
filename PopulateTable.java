package com.patient;
import java.sql.*;

public class PopulateTable {
	static Connection con ;
	PopulateTable(Connection con){
		PopulateTable.con = con;
	}
	public static boolean tableExists( String tableName) throws SQLException {
		DatabaseMetaData meta = con.getMetaData();
		ResultSet resultSet = meta.getTables(null, null, tableName, new String[] {"TABLE"});
		return resultSet.next();
	}
	public void createPatientTable() throws SQLException {
		boolean tableExist = tableExists("PATIENT");

		if(tableExist) {
			System.out.println("Patient table exists");
		}
		else {
			try {
				Statement smt = (Statement) con.createStatement();
				((java.sql.Statement) smt).executeUpdate("Create table patient(email_id varchar2(20) not null, password varchar(20) not null, name varchar2(20) not null, age number(3) not null, contact varchar2(12) , address varchar2(100) , blood_group varchar2(3) ) ");
			}
			catch(Exception e) {
				System.out.println("patient table is not created " + e);

			}
		}
	}
	public void createDoctorTable() throws SQLException {
		boolean tableExist = tableExists("DOCTOR");

		if(tableExist) {
			System.out.println("Doctor table exists");
		}
		else {
			try {
				Statement smt = (Statement) con.createStatement();
				((java.sql.Statement) smt).executeUpdate("Create table doctor(email_id varchar2(20) primary key, password varchar(20) not null, name varchar2(100) not null, age number(3) not null, specialization varchar(30) not null, fees number(5), start_time varchar2(10), end_time varchar2(10), contact varchar2(12) , address varchar2(100) , blood_group varchar2(3), status varchar2(10) ) ");
			}
			catch(Exception e) {
				System.out.println("Doctor table is not created " + e);

			}
		}
	}
	public void createAdminTable() throws SQLException {
		boolean tableExist = tableExists("ADMIN");

		if(tableExist) {
			System.out.println("admin table exists");
		}
		else {
			try {
				Statement smt = (Statement) con.createStatement();
				((java.sql.Statement) smt).executeUpdate("Create table admin(email_id varchar2(20) not null, password varchar(20) not null, name varchar2(20) not null) ");
			}
			catch(Exception e) {
				System.out.println("admin table is not created " + e);

			}
		}
	}
	
	public void createSlotTable() throws SQLException {
		boolean tableExist = tableExists("SLOTS");

		if(tableExist) {
			System.out.println("slots table exists");
		}
		else {
			try {
				Statement smt = (Statement) con.createStatement();
				((java.sql.Statement) smt).executeUpdate("Create table slots(d_email_id varchar2(50) , dates date , slot1 varchar2(20), slot2 varchar2(20), slot3 varchar2(20), slot4 varchar2(20)  ) ");
			}
			catch(Exception e) {
				System.out.println("slots table is not created " + e);

			}
		}
	}
}
