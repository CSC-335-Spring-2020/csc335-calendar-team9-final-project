import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class represents a single day, which contains various important information
 * about the day, including date, day (of the week), events, and the month the day is in.
 *
 */
public class Day {
	private String day;
	private int date;
	private String month;
	private List<Event> events;
	
	/**
	 * This is the primary constructor that creates a day object,
	 * also initiating an empty list of events for that day. 
	 * @param day The given day of the week
	 * @param date The given date
	 * @param month The given month that the day is in
	 */
	public Day(String day, int date, String month) {
		this.day = day;
		this.date = date;
		this.month = month;
		events = new ArrayList<Event>();
	}
	
	/**
	 * This is another constructor that does all of the above, with the added
	 * functionality of setting a list of events to be the day's events.  
	 * @param day The given day of the week
	 * @param date The given date
	 * @param month The given month that the day is in
	 * @param events The given list of events to set as the day's events
	 */
	public Day(String day, int date, String month, List<Event> events) {
		this.day = day;
		this.date = date;
		this.month = month;
		this.events = new ArrayList<Event>();
		for (int i = 0; i < events.size(); i++) {
			this.events.add(events.get(i));
		}
		
		
	}
	
	/**
	 * Returns the day's events
	 * @return the list of events
	 */
	public List<Event> getEvents() {
		return events;
		
	}
	
	/**
	 * This method sets the events to the passed in list of events
	 * @param events The new events list to set as the Day's events
	 */
	public void setEvents(List<Event> events) {
		this.events = new ArrayList<Event>();
		for (int i = 0; i < events.size(); i++) {
			this.events.add(events.get(i));
		}
	}
	
	/**
	 * Returns the day of the week of the day object
	 * @return a string indicating day of the week
	 */
	public String getDay() {
		return day;
	}
	
	/**
	 * This sets the current day of the week to the passed in day
	 * @param day The new day of the week to attribute to the day
	 */
	public void setDay(String day) {
		this.day = day;
		
	}
	
	/**
	 * Returns the month that the day is in
	 * @return a string indicating the month
	 */
	public String getMonth() {
		return month;
		
	}
	
	/**
	 * Sets the month the Day is in to the new passed-in month
	 * @param month The new month the day is in
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	
	/**
	 * Returns the day's date
	 * @return int indicating the date
	 */
	public int getDate() {
		return date;
		
	}
	
	/**
	 * Sets the Day's date to the new passed in date
	 * @param date The new date for the Day object
	 */
	public void setDate(int date) {
		this.date = date;
	}

}
