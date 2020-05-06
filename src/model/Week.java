package model;

public class Week extends java.util.Observable implements java.io.Serializable{
	private static final long serialVersionUID = 1;
	private Day[] days;

	/**
	 * This is the constructor method for the Week object, and it takes in a weekNum (int version
	 * of the day of the week).
	 * @param weekNum The int day of the week
	 * This just takes in the int weekNum and sets the weekNum field, then
	 * creates the array of Day objects for the week. 
	 */
	public Week(int weekNum) {
		days = new Day[7]; 
	}
	
	/**
	 * This constructor takes in the weekNum plus an array of Day objects 
	 * that we can set as the Week's days
	 * @param weekNum The day of the week int
	 * @param days The array of Days to set as the week's Days
	 * This method sets the Week's fields to the passed in objects
	 */
	public Week(int weekNum, Day[] days) {
		this.days = days;
	}
	
	/**
	 * This method sets the Week's day of the week to the inputed Day
	 * @param dayNum The day of the week to put the Day into
	 * @param day The Day object to place into the week
	 * This method puts the Day object into the Week Day array 
	 * by subtracting one from the dayNum to get the 0 inclusive
	 * index
	 */
	public void setDay(int dayNum, Day day) {
		if (dayNum <= 7) {
			days[dayNum - 1] = day;
		}
	}
	
	/**
	 * This method sets the Day array of the Week to the 
	 * inputed Day array
	 * @param days The array of days
	 * Simple setter that does exactly what it says
	 */
	public void setDays(Day[] days) {
		this.days = days;
	}
	
	/**
	 * This method takes in a dayNum which is the day of the week
	 * and returns the Day object at the week's Day for that int
	 * @param dayNum The day of the week int at which to grab the Day object
	 * @return Day the day object
	 * The method subtracts by one to convert from the "human" indexing of the week
	 * to the array's indexing and grabs the Day; if the day int doesn't exist, returns 
	 * null
	 */
	public Day getDay(int dayNum) {
		if (dayNum <= 7) {
			return days[dayNum - 1];
		}
		return null;
	}
	
	/**
	 * This method returns the array of Day objects for the week
	 * @return Day[] the array of days 
	 * Simple getter that does exactly what it says
	 */
	public Day[] getDays() {
		return days;
	}
}
