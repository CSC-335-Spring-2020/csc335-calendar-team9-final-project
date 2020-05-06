package model;

public class Week extends java.util.Observable implements java.io.Serializable{
	private static final long serialVersionUID = 1;
	private Day[] days;
	private int weekNum;

	
	public Week(int weekNum) {
		this.weekNum = weekNum;
		days = new Day[7]; 
	}
	
	public Week(int weekNum, Day[] days) {
		this.weekNum = weekNum;
		this.days = days;
	}
	
	public void setDay(int dayNum, Day day) {
		if (dayNum <= 7) {
			days[dayNum - 1] = day;
		}
	}
	
	public void setDays(Day[] days) {
		this.days = days;
	}
	
	public Day getDay(int dayNum) {
		if (dayNum <= 7) {
			return days[dayNum - 1];
		}
		return null;
	}
	
	public Day[] getDays() {
		return days;
	}
}
