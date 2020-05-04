package TestSuite;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import controller.CalendarController;
import model.Day;
import model.Event;
import model.Month;
import model.Week;
import model.Year;
import view.CalendarView;


public class Tests {
	
	//Controller tests -------------------------------------------------------------
	@Test
	void testConstructor() {
		CalendarView view = new CalendarView();
		int currYear = 2020;
		CalendarController controller =  new CalendarController(currYear, view);
		assertEquals(2020, controller.getYear());
		
	}
	
	@Test
	void test_addEvents() {
		CalendarView view = new CalendarView();
		Day day = new Day(1, 12, "April", view);
		int currYear = 2020;
		CalendarController controller =  new CalendarController(currYear, view);
		controller.addEvent(day, "Crying", 0, 0, 23, 0, "Hahahaha", "My house");
		assertEquals("Crying", day.getEvents().get(0).getLabel());
		
	}
	
	@Test
	void test_changeYear() {
		CalendarView view = new CalendarView();
		int currYear = 2020;
		CalendarController controller =  new CalendarController(currYear, view);
		controller.changeYear(2030);
		assertEquals(2030, controller.getYear());
		controller.changeYear(2021);
		assertEquals(2021, controller.getYear());
		
	}
	
	@Test
	void test_getDays() {
		CalendarView view = new CalendarView();
		int currYear = 2020;
		CalendarController controller =  new CalendarController(currYear, view);
		assertEquals(null, controller.getDays("April")[0]);
		
	}

	//Month Tests---------------------------------------------------------------------------
	@Test
	void test_setDay() {
		CalendarView view = new CalendarView();
		Month month = new Month("April", 2022, view);
		Day day = new Day(1, 12, "April", view);
		//month array starts at 0
		month.setDay(day, 11);
		assertEquals(day, month.getDay(11));
		month.setDay(day, 50);
		assertEquals(null, month.getDay(50));
	}
	
	@Test 
	void test_getNumDays() {
		CalendarView view = new CalendarView();
		Month month = new Month("April", 2022, view);
		assertEquals(30, month.getNumDays());
		
	}
	
	@Test
	void test_getName() {
		CalendarView view = new CalendarView();
		Month month = new Month("April", 2022, view);
		assertEquals("April", month.getName());
	}
	
	@Test
	void test_getYear() {
		CalendarView view = new CalendarView();
		Month month = new Month("April", 2022, view);
		assertEquals(2022, month.getYear());
		
	}
	
	@Test
	void test_leapYear() {
		CalendarView view = new CalendarView();
		Month month = new Month("February", 400, view);
		assertEquals(29, month.getNumDays());
		Month month2 = new Month("February", 1800, view);
		assertEquals(28, month2.getNumDays());
		
	}
	
	//Year tests-----------------------------------------------------------------------
	
	@Test
	void test_yearFill() {
		CalendarView view = new CalendarView();
		List<Month> monthNames = new ArrayList<Month>();
		monthNames.add(new Month("January", 2020, view));
		monthNames.add(new Month("February", 2020, view));
		monthNames.add(new Month("March", 2020, view));
		monthNames.add(new Month("April", 2020, view));
		monthNames.add(new Month("May", 2020, view));
		monthNames.add(new Month("June", 2020, view));
		monthNames.add(new Month("July", 2020, view));
		monthNames.add(new Month("August", 2020, view));
		monthNames.add(new Month("September", 2020, view));
		monthNames.add(new Month("October", 2020, view));
		monthNames.add(new Month("November", 2020, view));
		monthNames.add(new Month("December", 2020, view));
		Year year = new Year(2020, monthNames);
		//month numbers start at 1
		assertEquals("January", year.getMonth(1).getName());
	}
	
	//Week Tests----------------------------------------------------------------------------
	@Test
	void test_wConstructor1() {
		Week week = new Week(2);
		assertEquals(7, week.getDays().length);
	}
 	
	@Test 
	void test_wConstructor2() {
		Day[] days = new Day[7];
		List<Event> events = new ArrayList<Event>();
		for (int i = 0; i < 7; i++) {
			days[i] = new Day(i+1, 24, "April", events);
		}
		Week week = new Week(2, days);
		assertEquals(7, week.getDays().length);
		assertEquals(days, week.getDays());
		Day day = new Day(0, 24, "March", events);
		week.setDay(1, day);
		assertEquals("March", week.getDay(1).getMonth());
		Day[] days2 = new Day[7];
		week.setDays(days2);
		assertEquals(days2, week.getDays());
	}
	
	@Test
	void test_get_nulls() {
		Week week = new Week(2);
		assertEquals(null, week.getDay(12));
		List<Event> events = new ArrayList<Event>();
		Day day2 = new Day(0, 24, "March", events);
		Event e = new Event(day2, "I'm so tired", 1, 30, 2, 30, ":(", "My House");
		events.add(e);
		events.add(e);
		Day day = new Day(0, 24, "March", events);
		week.setDay(9, day);
		assertEquals(null, week.getDay(1));
	}
		
	//Day tests----------------------------------------------------------------------
		
	@Test
	void test_addEventDay() {
		List<Event> events = new ArrayList<Event>();
		Day day = new Day(0, 24, "March", events);
		Event e = new Event(day, "I'm so tired", 1, 30, 2, 30, ":(", "My House");
		day.addEvent(e);
		assertEquals(false, day.addEvent(e));
	}
	
	@Test
	void test_setEvents() {
		List<Event> events = new ArrayList<Event>();
		CalendarView view = new CalendarView();
		Day day2 = new Day(0, 24, "March", events);
		Event e = new Event(day2, "I'm so tired", 1, 30, 2, 30, ":(", "My House");
		events.add(e);
		events.add(e);
		Day day = new Day(5, 12, "April", view);
		day.setEvents(events);
		assertEquals(events, day.getEvents());
	}
	
	@Test
	void test_getDate() {
		CalendarView view = new CalendarView();
		Day day = new Day(5, 12, "April", view);
		assertEquals(12, day.getDate());
	}
	
	@Test
	void test_setters() {
		CalendarView view = new CalendarView();
		Day day = new Day(5, 12, "April", view);
		day.setMonth("March");
		assertEquals("March", day.getMonth());
		day.setDate(13);
		assertEquals(13, day.getDate());
		day.setDay(1);
		assertEquals("Sunday", day.getDay());
		day.setDay(2);
		assertEquals("Monday", day.getDay());
		day.setDay(3);
		assertEquals("Tuesday", day.getDay());
		day.setDay(4);
		assertEquals("Wednesday", day.getDay());
		day.setDay(5);
		assertEquals("Thursday", day.getDay());
		day.setDay(6);
		assertEquals("Friday", day.getDay());
		day.setDay(7);
		assertEquals("Saturday", day.getDay());
		day.setDay(8);
		assertEquals("", day.getDay());
		
	}
	
	//Event tests--------------------------------------------------------------------
	@Test
	void test_duration() {
		List<Event> events = new ArrayList<Event>();
		Day day = new Day(0, 24, "March", events);
		Event e = new Event(day, "I'm so tired", 1, 30, 2, 30, ":(", "My House");
		assertEquals(60, e.getDuration());
		
	}
	
	@Test
	void test_getters_event() {
		List<Event> events = new ArrayList<Event>();
		Day day = new Day(0, 24, "March", events);
		Event e = new Event(day, "I'm so tired", 1, 30, 2, 30, ":(", "My House");
		assertEquals(day, e.getDay());
		assertEquals("I'm so tired", e.getLabel());
		assertEquals(1, e.getSH());
		assertEquals(30, e.getSM());
		assertEquals(2, e.getEH());
		assertEquals(30, e.getEM());
		assertEquals(":(", e.getNotes());
		assertEquals("My House", e.getLoc());
	}
	
	@Test
	void test_setters_event() {
		List<Event> events = new ArrayList<Event>();
		Day day = new Day(0, 24, "March", events);
		Event e = new Event(day, "I'm so tired", 1, 30, 2, 30, ":(", "My House");
		Day day2 = new Day(0, 24, "March", events);
		e.setDay(day2);
		assertEquals(day2, e.getDay());
		e.setLabel("Hahaha");
		assertEquals("Hahaha", e.getLabel());
		e.setNotes(null);
		assertEquals(null, e.getNotes());
		e.setLoc(null);
		assertEquals(null, e.getLoc());
		e.setSH(2);
		e.setSM(45);
		e.setEH(3);
		e.setEM(45);
		assertEquals(2, e.getSH());
		assertEquals(45, e.getSM());
		assertEquals(3, e.getEH());
		assertEquals(45, e.getEM());
		
		
	}
		
		
		
	
	

}
