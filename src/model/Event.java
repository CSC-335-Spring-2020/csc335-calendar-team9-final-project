package model;

/**
 * This class represents an Event object that contains a variety of information about a given calendar event. 
 */
public class Event extends java.util.Observable implements java.io.Serializable{
	private static final long serialVersionUID = 1;
	private Day day;
	private String label;
	private int sH;
	private int sM;
	private int eH;
	private int eM;
	private String notes;
	private String loc;
	
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
	 */
	public Event(Day day, String label, int sH, int sM, int eH, int eM, String notes, String loc) {
		this.day = day;
		this.label = label;
		this.sH = sH;
		this.sM = sM;
		this.eH = eH;
		this.eM = eM;
		this.notes = notes;
		this.loc = loc;
		
		
	}
	
	/**
	 * Returns the Day object that the event is attributed to
	 * @return Day the day object
	 */
	public Day getDay() {
		return day;
	}
	
	/**
	 * Returns the label of the event
	 * @return String the label
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * Returns the start hour of the event
	 * @return int the start hour
	 */
	public int getSH() {
		return sH;
	}
	
	/**
	 * Returns the start minute of the event
	 * @return int the start minute
	 */
	public int getSM() {
		return sM;
	}
	
	/**
	 * Returns the end hour of the event
	 * @return int the end hour
	 */
	public int getEH() {
		return eH;
	}
	
	/**
	 * Returns the end minute of the event
	 * @return int the end minute
	 */
	public int getEM() {
		return eM;
	}
	
	/**
	 * Returns the notes of the event. If null,
	 * no notes exist for the event
	 * @return String the notes (if any)
	 */
	public String getNotes() {
		return notes;
	}
	
	/**
	 * Returns the location of the event. If null, 
	 * no location exists for the event
	 * @return String the location (if any)
	 */
	public String getLoc() {
		return loc;
	}
	
	/**
	 * Sets the Day object of the event to the passed in day
	 * @param day The new day object
	 */
	public void setDay(Day day) {
		this.day = day;
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Sets the label to the new passed in label
	 * @param label The new label
	 */
	public void setLabel(String label) {
		this.label = label;
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Sets the start hour of the event to the new start hour
	 * @param sH The new start hour
	 */
	public void setSH(int sH) {
		this.sH = sH;
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Sets the start minute to the new passed in start minute
	 * @param sM The new start minute
	 */
	public void setSM(int sM) {
		this.sM = sM;
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Sets the end hour to the passed in end hour
	 * @param eH The new end hour
	 */
	public void setEH(int eH) {
		this.eH = eH;
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Sets the end minute to the passed in end minute
	 * @param eM The new end minute
	 */
	public void setEM(int eM) {
		this.eM = eM;
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Sets the notes of the event to the new passed in notes
	 * @param notes The new notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Sets the location to the new passed in location
	 * @param loc The new location
	 */
	public void setLoc(String loc) {
		this.loc = loc;
		this.setChanged();
		this.notifyObservers();
	}
		
	
	public int getDuration() {
		int high = (sH * 60) + sM;
		int low = (eH * 60) + eM;
		return low - high;
		
	}
	


}
