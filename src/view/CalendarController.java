package view;

public class CalendarController {
	
	public CalendarController() {
		//TODO
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

}
