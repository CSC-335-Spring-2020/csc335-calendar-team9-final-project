package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;

import view.CalendarView;

public class Year extends java.util.Observable{
	
	private Map<String, Month> months = new HashMap<String, Month>();
	private Map<Integer, String> monthNames = new HashMap<Integer, String>();
	private int year;

		public Year(int year, CalendarView view) {
			fillMonthNames();

			this.year = year;
			this.addObserver((Observer) view);
			months.put("January", new Month("January", year, view));
			months.put("February", new Month("February", year, view));
			months.put("March", new Month("March", year, view));
			months.put("April", new Month("April", year, view));
			months.put("May", new Month("May", year, view));
			months.put("June", new Month("June", year, view));
			months.put("July", new Month("July", year, view));
			months.put("August", new Month("August", year, view));
			months.put("September", new Month("September", year, view));
			months.put("October", new Month("October", year,view));
			months.put("November", new Month("November", year,view));
			months.put("December", new Month("December", year,view));
			
		}
		
		public Year(int year, List<Month> months) {
			fillMonthNames();
			
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
