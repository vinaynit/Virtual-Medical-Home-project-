package com.patient;

import java.time.LocalDate;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.time.DayOfWeek;

import java.util.*;

import java.util.Date;

import java.sql.*;

public class slots {

	public ArrayList<String> dateGenerator() {

		ArrayList <String> arr = new ArrayList<String>();

		LocalDate currentDate = LocalDate.now();

		LocalDate nextDate = currentDate;

		//System.out.println(currentDate);

		arr.add(currentDate.toString());

		for (int i = 0; i < 4; ) {

			nextDate = nextDate.plusDays(1);

			if (nextDate.getDayOfWeek() != DayOfWeek.SATURDAY && nextDate.getDayOfWeek() != DayOfWeek.SUNDAY) {

				//System.out.println(nextDate);

				arr.add(nextDate.toString());

				i++;

			}

		}

		return arr;

	}

	public ArrayList<String> fetchdoctortimeslots(Statement stmt,Statement smt,String selectedDate,String choice) {

		ResultSet rs;
		ResultSet rs1;
		ArrayList<String> ret= new ArrayList<>();
		try {

			rs = stmt.executeQuery("select * from Slots where d_email_id =" + "'"+choice+"'"+" and dates ="+"'"+selectedDate+"'");
			rs1 =  smt.executeQuery("Select start_time,end_time from doctor where email_id = '" + choice + "'");

			String st = null,et = null;

			if(rs1.next())
			{
				st = rs1.getString(1);
				et = rs1.getString(2);
			}
			ArrayList<String> timeslotarray = TimeSlotArray(st,et);
			ret.addAll(timeslotarray);

			if(rs.next()) {

				for(int i = 3;i<=6;i++) {

					if(rs.getString(i).charAt(0) == 'Y') {

						System.out.println((i-2) + ". "+ "Slot:" + (i-2) +" available:" +timeslotarray.get(i-3) );

					}

				}

			}else {

				for(int i = 1;i<=4;i++) {
					System.out.println(i + ". "+ "Slot:" + i +" available:" + timeslotarray.get(i-1));

				}

			}			

		}

		catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
		return ret;

	}

	public void fetchdoctorslots(Statement stmt,ArrayList<String> arr,String choice) {

		ResultSet rs;

		try {

			rs = stmt.executeQuery("select * from Slots where d_email_id =" + "'"+choice+"'");

			if(rs.next()) {


				if(arr.contains(rs.getDate(2).toString()) && rs.getString(3).charAt(0) == 'N' && rs.getString(4).charAt(0) == 'N' && rs.getString(5).charAt(0) == 'N' && rs.getString(6).charAt(0) == 'N' ) {

					arr.remove(rs.getDate(2).toString());

				}

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	public ArrayList<String> TimeSlotArray(String st,String et) {

		String startTimeStr = st;

		String endTimeStr = et;

		//ResultSet r;

		//try {
		//
		//r = stmt.executeQuery("Select start_time,end_time from doctor where email_id = '" + choice + "'");
		//if(r.next()) {
		//	System.out.println(r.getString(1));
		//	System.out.println(r.getString(2));
		//}
		//}catch(Exception e) {
		//	e.printStackTrace();
		//	
		//}

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

		ArrayList<String> timeSlots = new ArrayList<>();

		try {

			Date startTime = sdf.parse(startTimeStr);

			Date endTime = sdf.parse(endTimeStr);

			Date currentTime = startTime;

			while (currentTime.before(endTime) || currentTime.equals(endTime)) {

				String slot = sdf.format(currentTime) + " - ";

				// Add 30 minutes to the current time

				long timeInMillis = currentTime.getTime();

				timeInMillis += 30 * 60 * 1000;

				currentTime.setTime(timeInMillis);

				slot += sdf.format(currentTime);

				timeSlots.add(slot);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		// Print the time slots added to the array

		// for (String slot : timeSlots) {

		// System.out.println(slot);

		// }

		return timeSlots;

	}

}


