package model;
/**
 * This class represents an Event object that contains a variety of information about a given calendar event.
 * This class uses CalendarView to allow the view to present events to the user, as well as the Day class. 
 */
public class Event implements java.io.Serializable, Comparable<Event>{
	private static final long serialVersionUID = 1;
	private Day day;
	private String label;
	private int sH;
	private int sM;
	private int eH;
	private int eM;
	private String notes;
	private String loc;
	private String color;
	
	/**
	 * Constructor for the event object. 
	 * Note: notes and loc can be null and this should be accounted for 
	 * @param day The day object that is attributed to the event
	 * @param label The title of the event
	 * @param sH The start hour of the event
	 * @param sM The start minute of the event
	 * @param eH The end hour of the event
	 * @param eM The end minute of the event
	 * @param notes The notes or description of the event (may be null)
	 * @param loc The attributed location of the event (may be null)
	 * @param color The color of the event
	 * This constructor takes in all the parameters and sets the Event fields to 
	 * the given parameters.
	 */
	public Event(Day day, String label, int sH, int sM, int eH, int eM, String notes, String loc, String color) {
		this.day = day;
		this.label = label;
		this.sH = sH;
		this.sM = sM;
		this.eH = eH;
		this.eM = eM;
		this.notes = notes;
		this.loc = loc;
		this.color = color;
		
		
	}
	
	/**
	 * Returns the Day object that the event is attributed to
	 * @return Day the day object
	 * Simple getter, does exactly as said above
	 */
	public Day getDay() {
		return day;
	}
	
	/**
	 * This method sets the color of the Event to the inputed color
	 * @param color The color to set to 
	 * Simple setter
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * This method returns the color of the event
	 * @return String the event color
	 * Simple getter, returns the color as a String
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * Returns the label of the event
	 * @return String the label
	 * Simple getter, returns the String label 
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Returns the start hour of the event
	 * @return int the start hour
	 * Simple getter for the start hour which is an integer (24 hour time)
	 */
	public int getSH() {
		return sH;
	}
	
	/**
	 * Returns the start minute of the event
	 * @return int the start minute
	 * Gets the start minute of the event and returns as an integer
	 */
	public int getSM() {
		return sM;
	}
	
	/**
	 * Returns the end hour of the event
	 * @return int the end hour
	 * The end hour of the event is returned as an integer (24 hour time)
	 */
	public int getEH() {
		return eH;
	}
	
	/**
	 * Returns the end minute of the event
	 * @return int the end minute
	 * Gets the end minute of an event as an integer
	 */
	public int getEM() {
		return eM;
	}
	
	/**
	 * Returns the notes of the event. If null,
	 * no notes exist for the event
	 * @return String the notes (if any)
	 * Will return null if no notes, this should be noticed and watched out 
	 * for by the programmer
	 */
	public String getNotes() {
		return notes;
	}
	
	/**
	 * Returns the location of the event. If null, 
	 * no location exists for the event
	 * @return String the location (if any)
	 * Will return null if no location, this should be noticed and watched out
	 * for by the programmer
	 */
	public String getLoc() {
		return loc;
	}
	
	/**
	 * Sets the Day object of the event to the passed in day
	 * @param day The new day object
	 * This method sets the Day in which the event is to the passed in Day object
	 */
	public void setDay(Day day) {
		this.day = day;
	}
	
	/**
	 * Sets the label to the new passed in label
	 * @param label The new label
	 * Sets the Event label to the given label
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * Sets the start hour of the event to the new start hour
	 * @param sH The new start hour
	 * Sets the Event start hour to the given start hour
	 */
	public void setSH(int sH) {
		this.sH = sH;
	}
	
	/**
	 * Sets the start minute to the new passed in start minute
	 * @param sM The new start minute
	 * Sets the Event start minute to the given start minute, sets the event as changed, 
	 * and notifies the observer.
	 */
	public void setSM(int sM) {
		this.sM = sM;
	}
	
	/**
	 * Sets the end hour to the passed in end hour
	 * @param eH The new end hour
	 * Sets the Event end hour to the given end hour
	 */
	public void setEH(int eH) {
		this.eH = eH;
	}
	
	/**
	 * Sets the end minute to the passed in end minute
	 * @param eM The new end minute
	 * Sets the Event end minute to the given end minute
	 */
	public void setEM(int eM) {
		this.eM = eM;
	}
	
	/**
	 * Sets the notes of the event to the new passed in notes
	 * @param notes The new notes
	 * Sets the Event notes to the given notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	/**
	 * Sets the location to the new passed in location
	 * @param loc The new location
	 * Sets the Event location to the given location
	 */
	public void setLoc(String loc) {
		this.loc = loc;
	}
		
	/**
	 * Gets the duration of the event in minutes
	 * @return int The minute duration of the event
	 * This method uses the start and end hours and converts these to minutes as
	 * well as uses the start and end minutes, subtracts the start and end total minutes, 
	 * and returns the result for the int duration.
	 */
	public int getDuration() {
		int high = (sH * 60) + sM;
		int low = (eH * 60) + eM;
		return low - high;
		
	}

	/**
	 * This method overrides the Comparable method. 
	 * @param Event the other event to compare this event to
	 * Takes in another Event object
	 * to compare the current event to, and compares the two events based on 
	 * start hours and minutes, returning negative one if the current event is before 
	 * the other event, and one if the other event is before this event, 0 if they start at the same 
	 * time. 
	 */
	@Override
	public int compareTo(Event event) {
		if (event == null) {
			throw new NullPointerException();
		} else if (this.sH < event.sH) {
			return -1;
		} else if (this.sH == event.sH && this.sM < event.sM) {
			return -1;
		} else if (this.sH == event.sH && this.sM == event.sM) {
			return 0;
		} else {
			return 1;
		}
	}
	


}
