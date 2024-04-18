package edu.uwm.cs351;
//https://stackoverflow.com/questions/49451976/simpledateformat-settimezone-not-working
//used as a example to give me ideas. All for date and time.
//https://www.youtube.com/watch?v=dMqHjHXsA_4
// https://www.youtube.com/watch?v=JtAplwiTOXc 
// NO code was copy. 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * A point in time.
 */
public class Time implements Comparable<Time> {
	// TODO: data structure for Time (very simple)
	// The solution also has a private constructor,
	// which is very useful.
	
		private final long elapsed;
	//	We are capturing time here by storing the number of milliseconds that occur after a particular time.
		
		private Time(long milliseconds) {
			elapsed = milliseconds;
		}
		//Sets elapsed equal to milliseconds
		
	/**
	 * Create a time for now.
	 * https://www.youtube.com/watch?v=7KThZb9G7II
	 * https://www.youtube.com/watch?v=PVOlFfQAqao
	 */
	public Time() {
		this(System.currentTimeMillis());
	}
//Returns the current time in that particular format
//It also passes it to the constructor
	
	/**
	 * Create a time according to the time parameter.
	 * @param c calendar object representing a time, must not be null
	 */
	public Time(Calendar c) {
		this(c.getTimeInMillis());
	}
	//We use that calendar object that’s passed to us and then get the time in millis from the calendar object.
	
	
	// Override/implement methods standard for immutable classes.
	
	// implementation
		@Override
		//override the duration one
		public boolean equals(Object x) {
			if (this == x)
				return true;
			if (x == null)
				return false;
			if (getClass() != x.getClass())
				return false;
			Time o = (Time) x;
			return this.elapsed == o.elapsed;
		}
		//Same concept as duration equal methods

		// implementation
		@Override
		//override the duration one
		public int hashCode() {
			return Long.hashCode(this.elapsed);		
		}

		// implementation
		@Override
		public String toString() {
			SimpleDateFormat sdf = new SimpleDateFormat();
	        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			sdf.applyPattern("z G yyyy/MM/dd HH:mm:ss");
	        return sdf.format(new Date(elapsed));
		}
		//https://stackoverflow.com/questions/49451976/simpledateformat-settimezone-not-working
		//Java library class give format date to use
				//The small Z refers to the time zone, G refers to ad or bc then The rest is pretty obvious.
				//text input as a data object then we initialized with milliseconds that has elapsed then  applyPattern("z G yyyy/MM/dd HH:mm:ss")
			
		// required
		@Override
		public int compareTo(Time o) {
			return Long.compare(this.elapsed, o.elapsed);
		}
		//Compare takes two parameters
		//Compares two values
	
	
	/**
	 * Return the difference between two time points.
	 * The order doesn't matter --- the difference is always a
	 * (positive) Duration.
	 * @param t time point to compute difference with
	 * @return duration between two time points.
	 */
		public Duration difference(Time t) {
			return Duration.MILLISECOND.scale(Math.abs(this.elapsed - t.elapsed));
		}
	/**
	 * Return the time point after a particular duration.
	 * If the point advances too far into the future,
	 * more than a hundred million years from now, this
	 * method may malfunction.
	 * @param d duration to advance, must not be null
	 * @return new time after given duration
	 */
		public Time add(Duration d) {
			return new Time(this.elapsed + (long) d.divide(Duration.MILLISECOND));
		}
		////You have the duration D, we then divided by the number of milliseconds which will return the number of
		//milliseconds in the duration. We take that double and cast it into long

	
	/**
	 * Return the time point before a particular duration.
	 * If a point regresses too far into the past,
	 * more than a hundred million years from now,
	 * this method may malfunction.
	 * @param d duration to regress, must not be null
	 * @return new time before this one by the given duration.
	 */
		public Time subtract(Duration d) {
			return new Time(this.elapsed - (long) d.divide(Duration.MILLISECOND));
		}
		// same idea 
	
	/**
	 * Return the time as a (mutable) Calendar object.
	 * @return new Calendar object for time.
	 */
	public Calendar asCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(point);
		return cal;
	}
}
