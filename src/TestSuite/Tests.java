package TestSuite;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import controller.CalendarController;
import javafx.scene.paint.Color;
import model.Day;
import model.Event;
import model.Month;
import model.Week;
import model.Year;
import view.CalendarView;

/**
 * This is the test suite for the model and controller aspects of the project.
 * The view is not tested per the project spec. 
 * The various tests are labelled by what class they are intended to test.
 */
public class Tests {
	
	//Controller tests -------------------------------------------------------------
	/**
	 * This method tests the constructor of the CalendarController class.
	 * The test method initiates the current year and then tests that the controller's year
	 * is equal to the year it should be. 
	 */
	@Test
	void testConstructor() {
		CalendarView view = new CalendarView();
		int currYear = 2020;
		CalendarController controller =  new CalendarController(currYear, view);
		assertEquals(2020, controller.getYear());
		
	}
	
	/**
	 * This method tests the addEvents method in the controller.
	 * The method passes in a view and creates a day for the controller, then 
	 * ensures that an event with the label "Crying" has been successfully added to
	 * the day. 
	 */
	@Test
	void test_addEvents() {
		CalendarView view = new CalendarView();
		Day day = new Day(1, 12, "April", view);
		int currYear = 2020;
		CalendarController controller =  new CalendarController(currYear, view);
		controller.addEvent(day, "Crying", 0, 0, 23, 0, "Hahahaha", "My house", "BLUE");
		assertEquals("Crying", day.getEvents().get(0).getLabel());
		
	}
	
	/**
	 * This method tests the changeYear method within the CalendarController class.
	 * The method creates a view and the controller object, then changes the year twice and 
	 * ensures that the year is correct to the changed year. 
	 */
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
	
	/**
	 * This method tests the getDays method of the CalendarController.
	 * The method creates the view and controller, and checks that the first "day" object
	 * is equal to null for April within the year (this method is primarily checking that the
	 * year correctly initiates the month objects inside of it).
	 */
	@Test
	void test_getDays() {
		CalendarView view = new CalendarView();
		int currYear = 2020;
		CalendarController controller =  new CalendarController(currYear, view);
		assertEquals(null, controller.getDays("April")[0]);
		
	}

	//Month Tests---------------------------------------------------------------------------
	/**
	 * This method checks the setDay method for the Month object.
	 * This method initiates the view, month, and a day object, and sets the day 
	 * twice and checks that the given day is equal to the new set day within the month. 
	 */
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
	
	/**
	 * This method checks the getNumDays method within the Month object. 
	 * This checks that the Month object returns the correct number of days in the month 
	 * by using April, 2020 as an example, which should have 30 days. 
	 */
	@Test 
	void test_getNumDays() {
		CalendarView view = new CalendarView();
		Month month = new Month("April", 2022, view);
		assertEquals(30, month.getNumDays());
		
	}
	
	/**
	 * This method checks the getName method within the Month object.
	 * The method creates the month and ensures that after being constructed,
	 * the Month getName will return April if the name is initially set to April. 
	 */
	@Test
	void test_getName() {
		CalendarView view = new CalendarView();
		Month month = new Month("April", 2022, view);
		assertEquals("April", month.getName());
	}
	
	/**
	 * This method checks the getYear method within the Month object.
	 * The method tests that the getYear method correctly returns the int 2022 since that is 
	 * what was passed into the constructor. 
	 */
	@Test
	void test_getYear() {
		CalendarView view = new CalendarView();
		Month month = new Month("April", 2022, view);
		assertEquals(2022, month.getYear());
		
	}
	
	/**
	 * This tests the leapYear method within the Month object which tests if 
	 * the year the Month is in is a leap year or not. 
	 * The method creates two years which are known outlier years that use all four 
	 * qualifications of a leap year, and test to make sure they return the correct boolean values
	 * via seeing how many days in February there are in those years. 
	 */
	@Test
	void test_leapYear() {
		CalendarView view = new CalendarView();
		Month month = new Month("February", 400, view);
		assertEquals(29, month.getNumDays());
		Month month2 = new Month("February", 1800, view);
		assertEquals(28, month2.getNumDays());
		
	}
	
	//Year tests-----------------------------------------------------------------------
	
	/**
	 * This method tests the yearFill method within the Year object. 
	 * The method creates a list of months and checks that the year correctly fills itself
	 * with months when given a list of months. 
	 */
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
	/**
	 * This method checks the first week constructor.
	 * This method creates a week object with a week number (the number of week in the month)
	 * and checks that the amount of days in the week is 7.
	 */
	@Test
	void test_wConstructor1() {
		Week week = new Week(2);
		assertEquals(7, week.getDays().length);
	}
 	
	/**
	 * This method checks the second week constructor. 
	 * This method takes in an array of Day objects, and fills the array. 
	 * Then the test method checks that the constructor correctly set 
	 * the fields when given the Day array. 
	 */
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
	
	/**
	 * This method checks the week get methods when the grabbed values 
	 * from the week have null values. 
	 * The method creates the week and asserts various statements on methods that should
	 * return null. Essentially this method is checking that the week does not return a value where
	 * it should not.
	 */
	@Test
	void test_get_nulls() {
		Week week = new Week(2);
		assertEquals(null, week.getDay(12));
		List<Event> events = new ArrayList<Event>();
		Day day2 = new Day(0, 24, "March", events);
		Event e = new Event(day2, "I'm so tired", 1, 30, 2, 30, ":(", "My House", "BLUE");
		events.add(e);
		events.add(e);
		Day day = new Day(0, 24, "March", events);
		week.setDay(9, day);
		assertEquals(null, week.getDay(1));
	}
		
	//Day tests----------------------------------------------------------------------
	/**
	 * This method checks the addEvent method within the Day object. This method
	 * creates the day object and passes in a list of events, and checks that the addEvent
	 * method correctly returns false when a duplicate event is attempted to be added. 
	 */
	@Test
	void test_addEventDay() {
		List<Event> events = new ArrayList<Event>();
		Day day = new Day(0, 24, "March", events);
		Event e = new Event(day, "I'm so tired", 1, 30, 2, 30, ":(", "My House", "BLUE");
		day.addEvent(e);
		assertEquals(false, day.addEvent(e));
	}
	
	/**
	 * This method checks that the setEvents method in the Day object works. 
	 * This method creates the event and a couple day objects and checks that 
	 * setting the Day's events field is correctly set when setEvents is called
	 * using in a passed in list of events. 
	 */
	@Test
	void test_setEvents() {
		List<Event> events = new ArrayList<Event>();
		CalendarView view = new CalendarView();
		Day day2 = new Day(0, 24, "March", events);
		Event e = new Event(day2, "I'm so tired", 1, 30, 2, 30, ":(", "My House", "BLUE");
		events.add(e);
		events.add(e);
		Day day = new Day(5, 12, "April", view);
		day.setEvents(events);
		assertEquals(events, day.getEvents());
	}
	
	/**
	 * This method checks the getDate method of the Day.
	 * This method creates the date to be 12th of April,
	 * and makes sure that's the number that the method getDate returns. 
	 */
	@Test
	void test_getDate() {
		CalendarView view = new CalendarView();
		Day day = new Day(5, 12, "April", view);
		assertEquals(12, day.getDate());
	}
	
	/**
	 * This method tests the various set methods of the Day. 
	 * The method goes through each set method and ensures
	 * that whatever field is being set was set correctly by double checking 
	 * with the parameter value. 
	 */
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
	/**
	 * This method checks the getDuration method of the Event class.
	 * This method creates an event with a given day, and ensures that it returns the correct
	 * duration in minutes, which should be 60 minutes. 
	 */
	@Test
	void test_duration() {
		List<Event> events = new ArrayList<Event>();
		Day day = new Day(0, 24, "March", events);
		Event e = new Event(day, "I'm so tired", 1, 30, 2, 30, ":(", "My House", "BLUE");
		assertEquals(60, e.getDuration());
		
	}
	
	/**
	 * This method tests the various getters for the Event class.
	 * The method goes through all of the getters and checks that they return
	 * the correct values for the given values passed into the constructor. 
	 */
	@Test
	void test_getters_event() {
		List<Event> events = new ArrayList<Event>();
		Day day = new Day(0, 24, "March", events);
		Event e = new Event(day, "I'm so tired", 1, 30, 2, 30, ":(", "My House", "BLUE");
		assertEquals(day, e.getDay());
		assertEquals("I'm so tired", e.getLabel());
		assertEquals(1, e.getSH());
		assertEquals(30, e.getSM());
		assertEquals(2, e.getEH());
		assertEquals(30, e.getEM());
		assertEquals(":(", e.getNotes());
		assertEquals("My House", e.getLoc());
	}
	
	/**
	 * This method tests the various setters for the Event class. 
	 * The method goes through all of the setters and checks that they
	 * correctly set the values, using the above getters to do so. 
	 */
	@Test
	void test_setters_event() {
		List<Event> events = new ArrayList<Event>();
		Day day = new Day(0, 24, "March", events);
		Event e = new Event(day, "I'm so tired", 1, 30, 2, 30, ":(", "My House", "BLUE");
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
