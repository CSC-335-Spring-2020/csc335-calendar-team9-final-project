package view;

public class Month {
	
	private String name;
	private int year;
	private Day[] days;
	
	public Month(String name, boolean leapYear, int year) {
		
	}
	
	public Month(String name, int year) {
		this.name = name;
		this.year = year;
		if (name.equals("February")) {
			if (leap(year)) {
				days = new Day[29];
			} else {
				days = new Day[28];
			}
		} else {
			switch(name) {
				case "January":
					days = new Day[31];
					break;
				case "March":
					days = new Day[31];
					break;
				case "April":
					days = new Day[30];
					break;
				case "May":
					days = new Day[31];
					break;
				case "June":
					days = new Day[30];
					break;
				case "July":
					days = new Day[31];
					break;
				case "August":
					days = new Day[31];
					break;
				case "September":
					days = new Day[30];
					break;
				case "October":
					days = new Day[31];
					break;
				case "November":
					days = new Day[30];
					break;
				case "December":
					days = new Day[31];
					break;
			}
		}
	}
	
	public void setDay(Day day, int dayNum) {
		if (dayNum <= days.length) {
			days[dayNum - 1] = day;
		}
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
