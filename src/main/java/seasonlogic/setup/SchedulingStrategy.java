package seasonlogic.setup;

import seasonlogic.calendar.Calendar;

public interface SchedulingStrategy {

  Calendar createCalendar();
}
