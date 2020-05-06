package model;

import java.util.Observer;

import view.CalendarView;

/**
 * This represents a Month of a calendar, and uses the Week, Day, Year, and Event classes to model 
 * a month, as well as CalendarView in order to allow the view to access the month to present visually.
 */
public class Month implements java.io.Serializable{
	
	private static final long serialVersionUID = 1;
	private String name;
	private int year;
	private Day[] days;
	private int startDayOfWeek;
	private CalendarView observer;
	private int numDays;

	
	/**
	 * Constructor, takes in the month name, year its in, and the view
	 * @param name The month name
	 * @param year The year the month is in
	 * @param view The view the month will be looked at in
	 * The method takes in the above parameters, and first uses the get offset method to 
	 * figure out on what day of the week the month actually starts on in the given year. The
	 * method then uses the name to figure out how many days are in the month and creates the array
	 * of day objects accordingly. 
	 */
	public Month(String name, int year) {
		this.name = name;
		this.year = year;
		int offset = getOffset();
		startDayOfWeek = offset + 1;
		days = new Day[42];
		if (name.equals("February")) {
			if (leap(year)) {
				for (int i = offset; i < 29 + offset; i++) {
					days[i] = new Day(i % 7, i - offset, name);
					numDays = 29;
				}
			} else {
				for (int i = offset; i < 28 + offset; i++) {
					days[i] = new Day(i % 7, i - offset, name);
					numDays = 28;
				}
			}
		} else {
			switch(name) {
				case "January":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name);
						numDays = 31;
					}
					break;
				case "March":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name);
						numDays = 31;
					}
					break;
				case "April":
					for (int i = offset; i < 30 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name);
						numDays = 30;
					}
					break;
				case "May":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name);
						numDays = 31;
					}
					break;
				case "June":
					for (int i = offset; i < 30 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name);
						numDays = 30;
					}
					break;
				case "July":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name);
						numDays = 31;
					}
					break;
				case "August":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name);
						numDays = 31;
					}
					break;
				case "September":
					for (int i = offset; i < 30 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name);
						numDays = 30;
					}
					break;
				case "October":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name);
						numDays = 31;
					}
					break;
				case "November":
					for (int i = offset; i < 30 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name);
						numDays = 30;
					}
					break;
				case "December":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name);
						numDays = 31;
					}
					break;
			}
		}
	}
	
	/**
	 * This method calculates the offset for a given month (aka how many days of the week
	 * we should count up until we start the month). 
	 * @return int The offset for the month object
	 * The method first calculates a basic offset, then operates a switch case on the month name in 
	 * order to see how much more of an offset should be added to the offset. Finally at the end,
	 * we mod the offset by seven to get the correct offset amount for the Month object.
	 */
	private int getOffset() {
		int offset = ((35 + ((year-1) / 4) - ((year-1) / 100) + ((year-1) / 400) + (year-1)) + 1) % 7;
		if (!name.contentEquals("January") && !name.contentEquals("February") && leap(year)) {
			offset++;
		}
		switch(name) {
		case "February":
			offset += 31;
			break;
		case "March":
			offset += 59;
			break;
		case "April":
			offset += 90;
			break;
		case "May":
			offset += 120;
			break;
		case "June":
			offset += 151;
			break;
		case "July":
			offset += 181;
			break;
		case "August":
			offset += 212;
			break;
		case "September":
			offset += 243;
			break;
		case "October":
			offset += 273;
			break;
		case "November":
			offset += 304;
			break;
		case "December":
			offset += 334;
			break;
		}
		return offset % 7;
	}
	
	/**
	 * Sets the Day object at the given dayNum in the array
	 * to the Day object that is passed in. 
	 * @param day The day object to set into the array
	 * @param dayNum The index at which to put in the Day object
	 * The method sets the day object at the inputed index minus one to the 
	 * given day (this is so that the days of the week are more intuitively numbered for the
	 * view). The Month then calls the observable setChanged and notifyObservers methods. 
	 */
	public void setDay(Day day, int dayNum) {
		if (dayNum <= days.length) {
			days[dayNum - 1] = day;
		}
	}
	
	/**
	 * Returns the Day object at the dayNum inputed (the date)
	 * @param dayNum The date at which to grab the Day object
	 * @return Day the grabbed day object
	 * This method takes in the int dayNum (which is the date) and 
	 * returns the Day object at that given date, or null if the date is not valid for 
	 * the month. 
	 */
	public Day getDay(int dayNum) {
		if (dayNum <= days.length) {
			return days[dayNum - 1];
		}
		return null;
	}
	
	/**
	 * Returns the day array of the month
	 * @return Day[] the array of Day objects 
	 * This method simply returns the array of Day objects.
	 */
	public Day[] getDays() {
		return days;
	}
	
	/**
	 * Returns the number of days in the month
	 * @return int the month's number of days
	 * This method simply returns the number of days in the month.
	 */
	public int getNumDays() {
		return numDays;
	}
	
	/**
	 * Returns the month name
	 * @return String the month name
	 * This method simply returns the name of the month as a String.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the year in which the month is
	 * @return int The month's year
	 * This method simply returns the year the month is in as an integer.
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Returns a boolean indicating if the year in which the month is is a leap year or not
	 * @param year The year in which the month is
	 * @return boolean Indicating true if the year is a leap year
	 * This method checks if the year that the month is in is a leap year using
	 * the four qualifiers for a leap year: If the year is divisible by four evenly, cannot be
	 * divided evenly by 100, and is evenly divisible by 400. If the year meets these qualifications,
	 * the method returns true.
	 */
	private boolean leap(int year) {
		if (!(year % 4 == 0)) {
			return false;
		} else if (!(year % 100 == 0)) {
			return true;
		} else if (!(year % 400 == 0)) {
			return false;
		} else {
			return true;
		}
	}
}
