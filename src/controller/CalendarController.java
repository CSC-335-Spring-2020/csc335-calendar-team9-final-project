package controller;


import java.util.Observable;
import java.util.Observer;

import model.Day;
import model.Event;
import model.Month;
import model.Year;
import view.Calendar;
import view.CalendarView;

public class CalendarController extends Observable{
	private Year[] years;
	private CalendarView view;
	
	/**
	 * The constructor for the controller, taking in an int representing the current year
	 * @param currYear
	 */
	public CalendarController(int currYear, CalendarView view) {
		years = new Year[3];
		years[0] = new Year(currYear-1, view); years[1] = new Year(currYear, view); 
		years[2] = new Year(currYear + 1, view);
		this.addObserver((Observer) view);
	
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
	public Day[] getDays(String monthName, Year year) {
		return year.getMonth(monthName).getDays();
	
	}

	

}
