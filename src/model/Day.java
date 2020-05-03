package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import view.CalendarView;

/**
 * 
 * This class represents a single day, which contains various important information
 * about the day, including date, day (of the week), events, and the month the day is in.
 *
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
	 */
	public Day(int i, int date, String month, CalendarView view) {
		this.day = i;
		this.date = date;
		this.month = month;
		this.addObserver((Observer) view);
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
	public Day(int day, int date, String month, List<Event> events) {
		this.day = day;
		this.date = date;
		this.month = month;
		this.events = new ArrayList<Event>();
		for (int i = 0; i < events.size(); i++) {
			this.events.add(events.get(i));
		}
	}
	
	public void setObserver(CalendarView view) {
		for (Event event : events) {
			event.deleteObservers();
			event.addObserver(view);
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
	 * Adds the given event to the events list of the day, returns false if the event 
	 * already exists
	 * @param event The event to add
	 * @return boolean indicating if the event is able to be added or not
	 */
	public boolean addEvent(Event event) {
		if (events.contains(event)) {
			return false;
		}
		events.add(event);
		this.setChanged();
		this.notifyObservers();
		return true;
		
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
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Returns the day of the week of the day object
	 * @return a string indicating day of the week
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
	 */
	public void setDay(int day) {
		this.day = day;
		this.setChanged();
		this.notifyObservers();
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
		this.setChanged();
		this.notifyObservers();
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
		this.setChanged();
		this.notifyObservers();
	}

}
