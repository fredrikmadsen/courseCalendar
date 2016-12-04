package no.uib.fma008.info233.v15.oblig2.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class is responsible for static support methods, like converting
 * datatypes and adding elements for String output
 * 
 * @author fma008
 * @version 1.0
 *
 */
public abstract class Utilities {

	/**
	 * method for converting number as String to Integer
	 * 
	 * @param number
	 * @return the number
	 */
	public static int toInt(String number) {
		return Integer.parseInt(number);
	}

	/**
	 * method for converting time in String format to Calendar object
	 * 
	 * @param time
	 * @return the cal
	 * @throws ParseException
	 */
	public static Calendar toCalendarFormat(String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		// String dateInString = time;
		Date clocktime = sdf.parse(time);
		Calendar cal = Calendar.getInstance();
		cal.setTime(clocktime);

		return cal;
	}

	/**
	 * method for converting hourNumb as Integer to String
	 * 
	 * @param hourNumb
	 * @return the
	 */
	public static String checkHourTime(int hourNumb) {
		String hourAsString = hourNumb + "";
		if (hourNumb == 0) {
			hourAsString = "12";
		}
		if (hourNumb == 8) {
			hourAsString = "08";
		}
		return hourAsString;
	}

	/**
	 * method for converting minute as Integer to String
	 * 
	 * @param minute
	 * @return minuteAsString
	 */
	public static String checkMinuteTime(int minute) {
		String minuteAsString = minute + "";
		if (minute == 0) {
			minuteAsString = minute + "0";
		}
		return minuteAsString;
	}

	/**
	 * method for trimming a String line input
	 * 
	 * @param line
	 * @param start
	 * @param end
	 * @return the line
	 */
	public static String trimString(String line, int start, int end) {
		return line.substring(start, end);
	}

	/**
	 * method for retrieving current week
	 * 
	 * @return the week
	 */
	public static int getWeek() {
		return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
	}

}
