package TestSuite;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import controller.CalendarController;
import model.Day;
import model.Event;
import model.Month;
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
		Event event = new Event(day, "Crying", 0, 0, 23, 0, "Hahahaha", "My house");
		controller.addEvent(day, event);
		assertEquals("Crying", day.getEvents().get(1).getLabel());
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
	
	@Test
	void test_setMonth_year() {
		
	}
 	
	

}
