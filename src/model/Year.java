package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Year extends java.util.Observable{
	
	private Map<String, Month> months = new HashMap<String, Month>();
	private Map<Integer, String> monthNames = new HashMap<Integer, String>();
	private int year;
	private boolean leapYear;

		public Year(int year) {
			fillMonthNames();
			leapYear = leap(year);
			this.year = year;
			months.put("January", new Month("January", year));
			months.put("February", new Month("February", year));
			months.put("March", new Month("March", year));
			months.put("April", new Month("April", year));
			months.put("May", new Month("May", year));
			months.put("June", new Month("June", year));
			months.put("July", new Month("July", year));
			months.put("August", new Month("August", year));
			months.put("September", new Month("September", year));
			months.put("October", new Month("October", year));
			months.put("November", new Month("November", year));
			months.put("December", new Month("December", year));
			
		}
		
		public Year(int year, List<Month> months) {
			fillMonthNames();
			leapYear = leap(year);
			this.year = year;
			for (Month month : months) {
				this.months.put(month.getName(), month);
			}
		}
		
		private void fillMonthNames() {
			monthNames.put(1, "January");
			monthNames.put(2, "February");
			monthNames.put(3, "March");
			monthNames.put(4, "April");
			monthNames.put(5, "May");
			monthNames.put(6, "June");
			monthNames.put(7, "July");
			monthNames.put(8, "August");
			monthNames.put(9, "September");
			monthNames.put(10, "October");
			monthNames.put(11, "November");
			monthNames.put(12, "December");
		}
		
		public void setMonth(Month month) {
			months.put(month.getName(), month);
		}
		
		public Month getMonth(int monthNum) {
			return months.get(monthNames.get(monthNum));
		}
		
		public Month getMonth(String monthName) {
			return months.get(monthName);
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
