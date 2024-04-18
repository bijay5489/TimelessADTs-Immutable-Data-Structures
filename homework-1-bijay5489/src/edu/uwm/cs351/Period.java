package edu.uwm.cs351;

/**
 * A time period within historical time.
 */
public class Period {
	// TODO: Choose fields
	
	private final Time start;
	private final Duration length;

	
	/**
	 * Construct a period given the start time and length.
	 * @param s start time, must not be null
	 * @param l length, must not be null
	 */
	public Period(Time s, Duration l) {
		start = s;
		length = l;
	}

	
	/**
	 * Construct a period given the start and end time
	 * @param from start time, must not be null
	 * @param to end time, must not be null or before the start time
	 */
	public Period(Time from, Time to) {
		start = from;
		length = to.difference(from);
	}
	//From is when the period starts and to is when the period ends
	
	/**
	 * Construct a period given the length and end time.
	 * @param len length of the period, must not be null
	 * @param end end time of the period.
	 */
	public Period(Duration len, Time end) {
		start = end.subtract(len);
		length = len;
	}
	//You have the ending time and you subtract the length of the duration from the ending time to get start
	
	/**
	 * Return the start time of the period.
	 * @return beginning time
	 */
	public Time getStart() {
		return start;
	}
//To get starting time

	
	/**
	 * Return the stop time of the period.
	 * @return end time
	 */
	public Time getStop() {
		return start.add(length);
	}
// you have the starting time and then you add length(duration) to get the ending time.
	
	/**
	 * Return the length of the period.
	 * @return the amount of time in this period
	 */
	public Duration getLength() {
		return length;
	}
	
	
	@Override // implementation
	public boolean equals(Object x) {
		if (this == x)
			return true;
		if (x == null)
			return false;
		if (getClass() != x.getClass())
			return false;
		Period o = (Period) x;
		return o.start.equals(this.start) && o.length.equals(this.length);
		//When are two periods the same, it’s when they have the same starting time in the same duration. So this checks that. We use the equals 
		//methods from Times to check if starting time of all o this object is the same and if it is. Then we check if the length of their duration is the same.
		
	}
	
	@Override // implementation
	public int hashCode() {
		return Long.hashCode(start.hashCode() + 31 * length.hashCode());
	}
	//Two things that define a period . The starting time and length of duration. The implement of the hash code is so if two variables are equal 
	//they have the same hash code if they’re not equal
	//they might or might not have the same hash code. The +31 is there so we don’t have the same hash code. 
	//It doesn’t have to be +31 it could be any odd number.
	//
	@Override // implementation
	public String toString() {
		return "[" + start.toString() + "; " + length.toString() + "]";
	}

	
	/**
	 * Return whether this period overlaps with the parameter.
	 * If one appointment starts where the other ends, they do not overlap.
	 * @param p period to compare to, must not be null
	 * @return whether this period overlaps the parameter
	 */
	//I don't know where the stackoverflow link is. I will look. 
	public boolean overlap(Period p) {
		return p.getStart().compareTo(this.getStop()) < 0  && 
				this.getStart().compareTo(p.getStop()) < 0;
	}
	//If two periods 1 and 2 and they overlap start1 should be less than end2 and start2 should be smaller than end1
}
