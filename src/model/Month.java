package model;

import java.util.Observer;

import view.CalendarView;

public class Month extends java.util.Observable implements java.io.Serializable{
	
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
	 */
	public Month(String name, int year, CalendarView view) {
		this.observer = view;
		this.name = name;
		this.year = year;
		int offset = getOffset();
		startDayOfWeek = offset + 1;
		this.addObserver((Observer) view);
		days = new Day[42];
		if (name.equals("February")) {
			if (leap(year)) {
				for (int i = offset; i < 29 + offset; i++) {
					days[i] = new Day(i % 7, i - offset, name, view);
					days[i].addObserver(view);
					numDays = 29;
				}
			} else {
				for (int i = offset; i < 28 + offset; i++) {
					days[i] = new Day(i % 7, i - offset, name, view);
					days[i].addObserver(view);
					numDays = 28;
				}
			}
		} else {
			switch(name) {
				case "January":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
						numDays = 31;
					}
					break;
				case "March":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
						numDays = 31;
					}
					break;
				case "April":
					for (int i = offset; i < 30 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
						numDays = 30;
					}
					break;
				case "May":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
						numDays = 31;
					}
					break;
				case "June":
					for (int i = offset; i < 30 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
						numDays = 30;
					}
					break;
				case "July":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
						numDays = 31;
					}
					break;
				case "August":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
						numDays = 31;
					}
					break;
				case "September":
					for (int i = offset; i < 30 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
						numDays = 30;
					}
					break;
				case "October":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
						numDays = 31;
					}
					break;
				case "November":
					for (int i = offset; i < 30 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
						numDays = 30;
					}
					break;
				case "December":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
						numDays = 31;
					}
					break;
			}
		}
	}
	
	/**
	 * Returns the offset of days of the week before the month actually starts
	 * @return int The offset number
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
	 */
	public void setDay(Day day, int dayNum) {
		if (dayNum <= days.length) {
			days[dayNum - 1] = day;
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Returns the Day object at the dayNum inputted (the date)
	 * @param dayNum The date at which to grab the Day object
	 * @return Day the grabbed day object
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
	 */
	public Day[] getDays() {
		return days;
	}
	
	/**
	 * Returns the number of days in the month
	 * @return int the month's number of days
	 */
	public int getNumDays() {
		return numDays;
	}
	
	/**
	 * Returns the month name
	 * @return String the month name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the year in which the month is
	 * @return int The month's year
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Returns a boolean indicating if the year in which the month is is a leap year or not
	 * @param year The year in which the month is
	 * @return boolean Indicating true if the year is a leap year
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
