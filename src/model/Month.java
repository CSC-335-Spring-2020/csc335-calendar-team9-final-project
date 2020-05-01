package model;

import java.util.Observer;

import view.CalendarView;

public class Month extends java.util.Observable{
	
	private String name;
	private int year;
	private Day[] days;
	private int startDayOfWeek;
	private CalendarView observer;
	
	public Month(String name, int year, CalendarView view) {
		this.observer = view;
		this.name = name;
		this.year = year;
		int offset = getOffset();
		startDayOfWeek = offset + 1;
		this.addObserver((Observer) view);
		days = new Day[42];
		if (name.equals("February")) {
			if (leap(year)) {
				System.out.println("leap");
				for (int i = offset; i < 29 + offset; i++) {
					days[i] = new Day(i % 7, i - offset, name, view);
					days[i].addObserver(view);
				}
			} else {
				for (int i = offset; i < 28 + offset; i++) {
					days[i] = new Day(i % 7, i - offset, name, view);
					days[i].addObserver(view);
				}
			}
		} else {
			switch(name) {
				case "January":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
					}
					break;
				case "March":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
					}
					break;
				case "April":
					for (int i = offset; i < 30 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
					}
					break;
				case "May":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
					}
					break;
				case "June":
					for (int i = offset; i < 30 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
					}
					break;
				case "July":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
					}
					break;
				case "August":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
					}
					break;
				case "September":
					for (int i = offset; i < 30 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
					}
					break;
				case "October":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
					}
					break;
				case "November":
					for (int i = offset; i < 30 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
					}
					break;
				case "December":
					for (int i = offset; i < 31 + offset; i++) {
						days[i] = new Day(i % 7, i - offset, name, view);
						days[i].addObserver(view);
					}
					break;
			}
		}
	}
	
	private int getOffset() {
		int offset = ((35 + ((year-1) / 4) - ((year-1) / 100) + ((year-1) / 400) + (year-1)) + 1) % 7;
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
