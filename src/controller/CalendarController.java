package controller;


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
		day.addEvent(day.getEvents().size()-1, event);
	}
	
	/**
	 * Adds the given event to the given day's list of events. Returns false if 
	 * the event already existed for the day
	 * @param index The index at which the event is to be added
	 * @param day The day to add the event to
	 * @param event The event to be added
	 * @return boolean indicating if the event was successfully added or not
	 */
	public boolean addEvent(int index, Day day, Event event) {
		return day.addEvent(index, event);
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
