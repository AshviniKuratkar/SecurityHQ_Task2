package com.app.task2.model;

import java.util.Comparator;

public class SortDates implements Comparator<User>
{
//Ascending order
	@Override
	public int compare(User dateOfBirth, User dateOfJoining) {
		// TODO Auto-generated method stub
		return dateOfBirth.getDateOfBirth().compareTo(dateOfJoining.getDateOfJoining());
	}
	
	//Descending order
	public int compare2(User dateOfBirth, User dateOfJoining)
	{
		return dateOfJoining.getDateOfJoining().compareTo(dateOfBirth.getDateOfBirth());
	}

}
