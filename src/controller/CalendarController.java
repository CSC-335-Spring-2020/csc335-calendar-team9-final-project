package controller;


import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import model.Day;
import model.Event;
import model.Month;
import model.Year;
import view.Calendar;
import view.CalendarView;

public class CalendarController extends Observable{
	private CalendarView view;
	private int currYear;
	private Map<Integer, Year> years;
	
	/**
	 * The constructor for the controller, taking in an int representing the current year
	 * @param currYear
	 */
	public CalendarController(int currYear, CalendarView view) {
		this.currYear = currYear;
		this.view = view;
		years = new HashMap<Integer, Year>();
		years.put(currYear, new Year(currYear, view));
		years.put(currYear - 1, new Year(currYear - 1, view));
		years.put(currYear + 1, new Year(currYear + 1, view));
		this.addObserver((Observer) view);
	
	}
	
	/**
	 * Adds an event to the given day object
	 * @param day The object to add the event to
	 * @param label The label of the event
	 * @param sH The starting hour of the event
	 * @param sM The starting minute of the event
	 * @param eH The end hour of the event
	 * @param eM The end minute of the minute
	 * @param notes The notes of the event (can be null)
	 * @param loc The location of the event (can be null)
	 */
	public void addEvent(Day day, String label, int sH, int sM, int eH, int eM, String notes, String loc) {
		Event event = new Event(day, label, sH, sM, eH, eM, notes, loc);
		day.addEvent(event);
	}
	
	/**
	 * Adds the given event to the given day's list of events. Returns false if 
	 * the event already existed for the day
	 * @param day The day to add the event to
	 * @param event The event to be added
	 * @return boolean indicating if the event was successfully added or not
	 */
	public boolean addEvent(Day day, Event event) {
		return day.addEvent(event);
	}
	
	/**
	 * This method returns the current year
	 * @return int The calendar's current year
	 */
	public int getYear() {
		return this.currYear;
	}
	
	/**
	 * This method takes in a year and changes the current year of the 
	 * calendar to the inputed year
	 * @param year The year to put in as the new current year
	 */
	public void changeYear(int year) {
		if (years.containsKey(year)) {
			currYear = year;
		} else {
			years.put(year, new Year(year, view));
			currYear = year;
		}
	}
	/**
	 * Takes in a year and a month and returns the array of Day objects for that
	 * month
	 * @param monthName The month to grab the days of
	 * @param year The year in which the month is
	 * @return Day[] the array of days for that month
	 */
	public Day[] getDays(String monthName) {
		return years.get(currYear).getMonth(monthName).getDays();
	
	}

	

}
