package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * This class represents a single day, which contains various important information
 * about the day, including date, day (of the week), events, and the month the day is in.
 * This uses the Month, Week, Year, and Event classes to model 
 * a day.
 */
public class Day extends java.util.Observable implements java.io.Serializable{
	private static final long serialVersionUID = 1;
	private int day;
	private int date;
	private String month;
	private List<Event> events;
	
	/**
	 * This is the primary constructor that creates a day object,
	 * also initiating an empty list of events for that day. 
	 * @param i The given day of the week
	 * @param date The given date
	 * @param month The given month that the day is in
	 * This method sets all of the fields that are given as parameters,
	 * along with creating an empty arraylist of 
	 * events to set as the Day's events
	 */
	public Day(int i, int date, String month) {
		this.day = i;
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
	 * This method does everything the above constructor does
	 * as well as taking in a list of events to set as the Day's events
	 */
	public Day(int day, int date, String month, List<Event> events) {
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
	 * Does exactly what it says, simple getter
	 */
	public List<Event> getEvents() {
		return events;
		
	}
	
	/**
	 * Adds the given event to the events list of the day, returns false if the event 
	 * already exists
	 * @param event The event to add
	 * @return boolean indicating if the event is able to be added or not
	 * This method takes in an Event object to add to the Day's list of events and 
	 * adds it to the list, then sorts the event list based on start time. 
	 * True is returned if added successfully 
	 * (if the event wasn't already in the list of events)
	 */
	public boolean addEvent(Event event) {
		if (events.contains(event)) {
			return false;
		}
		events.add(event);
		Collections.sort(events);
		return true;
		
	}	
	
	
	/**
	 * This method sets the events to the passed in list of events
	 * @param events The new events list to set as the Day's events
	 * This method sets the list of events to the passed in list of 
	 * events by making a deep copy, then the Day is set as changed.
	 */
	public void setEvents(List<Event> events) {
		this.events = new ArrayList<Event>();
		for (int i = 0; i < events.size(); i++) {
			this.events.add(events.get(i));
		}
		this.notifyObservers();
	}
	
	/**
	 * Returns the day of the week of the day object
	 * @return a string indicating day of the week
	 * This method uses a switch case and the day field to 
	 * figure out what word day of the week corresponds to the Day object's day
	 * of the week integer, and it returns the int.
	 */
	public String getDay() {
		String dayOf = "";
		switch (this.day) {
		case 1:
			dayOf =  "Sunday";
			break;
		case 2:
			dayOf =  "Monday";
			break;
		case 3:
			dayOf =  "Tuesday";
			break;
		case 4:
			dayOf =  "Wednesday";
			break;
		case 5:
			dayOf =  "Thursday";
			break;
		case 6:
			dayOf =  "Friday";
			break;
		case 7:
			dayOf = "Saturday";
			break;
		}
		return dayOf;
	}
	
	/**
	 * This sets the current day of the week to the passed in day
	 * @param day The new day of the week to attribute to the day
	 * This method takes in an integer representing the day of the week and
	 * sets accordingly. 
	 */
	public void setDay(int day) {
		this.day = day;
	}
	
	/**
	 * Returns the month that the day is in
	 * @return a string indicating the month
	 * This is a simple getter that returns the month name the day is in.
	 */
	public String getMonth() {
		return month;
	}
	
	/**
	 * Sets the month the Day is in to the new passed in month
	 * @param month The new month the day is in
	 * This method sets the month the day is in to the passed in month.
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	
	/**
	 * Returns the day's date
	 * @return int indicating the date
	 * This simply gets the int date of the Day
	 */
	public int getDate() {
		return date;
	}
	
	/**
	 * Sets the Day's date to the new passed in date
	 * @param date The new date for the Day object
	 * This takes in an int date and sets the Day's date to the 
	 * int.
	 */
	public void setDate(int date) {
		this.date = date;
	}

}
