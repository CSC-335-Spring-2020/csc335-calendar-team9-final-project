package model;

public class Month extends java.util.Observable{
	
	private String name;
	private int year;
	private Day[] days;
	private int startDayOfWeek;
	
	public Month(String name, boolean leapYear, int year) {
		
	}
	
	public Month(String name, int year) {
		this.name = name;
		this.year = year;
		int offset = getOffset();
		startDayOfWeek = offset + 1;
		if (name.equals("February")) {
			if (leap(year)) {
				days = new Day[29 + offset];
			} else {
				days = new Day[28 + offset];
			}
		} else {
			switch(name) {
				case "January":
					days = new Day[31 + offset];
					break;
				case "March":
					days = new Day[31 + offset];
					break;
				case "April":
					days = new Day[30 + offset];
					break;
				case "May":
					days = new Day[31 + offset];
					break;
				case "June":
					days = new Day[30 + offset];
					break;
				case "July":
					days = new Day[31 + offset];
					break;
				case "August":
					days = new Day[31 + offset];
					break;
				case "September":
					days = new Day[30 + offset];
					break;
				case "October":
					days = new Day[31 + offset];
					break;
				case "November":
					days = new Day[30 + offset];
					break;
				case "December":
					days = new Day[31 + offset];
					break;
			}
		}
	}
	
	private int getOffset() {
		int offset = ((35 + (year / 4) - (year / 100) + (year / 400) + year) - 1) % 7;
		if (!name.contentEquals("January") && !name.contentEquals("February") && leap(year)) {
			offset++;
		}
		switch(name) {
		case "February":
			offset += 31;
			break;
		case "March":
			offset += 59;
			break;
		case "April":
			offset += 90;
			break;
		case "May":
			offset += 120;
			break;
		case "June":
			offset += 151;
			break;
		case "July":
			offset += 181;
			break;
		case "August":
			offset += 212;
			break;
		case "September":
			offset += 243;
			break;
		case "October":
			offset += 273;
			break;
		case "November":
			offset += 304;
			break;
		case "December":
			offset += 334;
			break;
		}
		return offset % 7;
	}
	
	public void setDay(Day day, int dayNum) {
		if (dayNum <= days.length) {
			days[dayNum - 1] = day;
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public Day getDay(int dayNum) {
		if (dayNum <= days.length) {
			return days[dayNum - 1];
		}
		return null;
	}
	
	public Day[] getDays() {
		return days;
	}
	
	public int getNumDays() {
		return days.length;
	}
	
	public String getName() {
		return name;
	}
	
	public int getYear() {
		return year;
	}
	
	private boolean leap(int year) {
		if (!(year % 4 == 0)) {
			return false;
		} else if (!(year % 100 == 0)) {
			return true;
		} else if (!(year % 400 == 0)) {
			return false;
		} else {
			return true;
		}
	}
}
