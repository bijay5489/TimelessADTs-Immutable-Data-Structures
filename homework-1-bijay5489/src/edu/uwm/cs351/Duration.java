package edu.uwm.cs351;
//I did have a tutor helping me understand some part of the code.
// I did watch youtube videos
// I did google (No I didn't steal code) 
// I am in the 351 discord where students talked. (No code was shared) 
// I was at the ems280 building
// source	https://stackoverflow.com/questions/5992209/override-the-equals-method 
//https://www.youtube.com/watch?v=wDf5aMF80EY

/**
 * An amount of time, always positive.
 * To create a duration, scale an existing duration.
 */
public class Duration implements Comparable<Duration> {
	//public static final String MINUTE = null;
	//had to comment this out for a bit for us to instant the mi
	private final long extent; // this must remain private.  Do NOT add a getter!
	
	// this constructor must remain private:
	private Duration(long milliseconds) {
		extent = milliseconds;
	}
	 
	// TODO: For all constants, have a line:
// public static final Duration CONSTANT = new Duration(...);
// when running test duration Not only does the debug tell you INSTANTANEOUS, MILLISECOND, SECOND, MINUTE, 
// and etc can't be resolved or is not a field. So u know u have to add that. Line 28 give us what to call it 	
//private static Duration[] constants = { Duration.INSTANTANEOUS, Duration.MILLISECOND,
///		Duration.SECOND, Duration.MINUTE, Duration.HOUR, Duration.DAY, Duration.YEAR
//	Just using that I know to create those. 
	
	public static final Duration INSTANTANEOUS = new Duration(0);
	public static final Duration MILLISECOND = new Duration(1);
	public static final Duration SECOND = new Duration(1000);
	public static final Duration MINUTE = new Duration(60000);
	//I just google hour to milliseconds and got 3.3e6
	public static final Duration HOUR = new Duration((long) 3.6e6);
	//Eclipse it self added a long.Also we need a long because of how long the number is, u can't use an int. 
	public static final Duration DAY = new Duration((long) 8.64e7);
	//I just google day to milliseconds and got 3.3e6, another way would be doing hour * 24. THat would also work.
	//3.154e+10 is 1 year in milliseconds but in the unlock test we said a year it not 365 days but 365.25 days, so I will do day* 365.25 but idk how to do that.
	//public static final Duration YEAR = new Duration((long) (1.152641e13));
	public static final Duration YEAR = new Duration((long) (DAY.extent * (365.25)));

	
	// If you are overriding a method from a super class, always
	// annotate it "@Override" as here, overriding Object#equals(Object)
	@Override // implementation
	public boolean equals(Object x) {
		// equals is already a method ofcoure so we need to override it
		if (this == x)
		//First we check if this is x 
			//If it’s the same variable as X you return true.
				
			return true;
		if (x == null)
			return false;
//		You’re checking if X equals nothing 
		//	If true you return false, if x null nothing can be equal to it.
		if (getClass() != x.getClass())
			//Whenever we implement the equals method and override it
			//The input is always as an object not the type of class so it’s object x not duration X
			//Get class returns the class of the object were in so far this case it would return duration. This.get class would also work. 
			//So here we also want to check if x has the same class as the object. if it does not have the same class it cannot be true.
			return false;
		Duration o = (Duration) x;
		//X is a type of object so we have to cast it into duration. If we want to access its extent variable. 
		//Since the object class does not have any extent variable. The compiler checks the type of variable and then checks if 
		//there’s extent or not so we need to do this.
		return this.extent == o.extent;
	// source	https://stackoverflow.com/questions/5992209/override-the-equals-method
	// I posted a question on plazza about this!
	// TODO 
	}
	// implementation
	@Override 
	public int hashCode() {
		//return 0; // TODO: Do something efficient.  Do NOT create a String.
	return	Long.hashCode(extent);
	//Java has the long which already has an implementation of the hash code.
	}
	
	// If you are overriding a method from an interface, then Java 5
	// says you CANNOT use Override, but anything later says you MAY.
	// Please always do unless you write a javadoc comment. 
	@Override // required
	public int compareTo(Duration other) {
		return Long.compare(this.extent, other.extent);
	//
 // TODO: Remember what compareTo is supposed to return.
	}
	
	@Override // implementation
	public String toString() {
			if (extent >= YEAR.extent) {
				return ((double) extent) / YEAR.extent + " years";
			} else if (extent >= DAY.extent) {
				return ((double) extent) / DAY.extent + " days";
			} else if (extent >= HOUR.extent) {
				return ((double) extent) / HOUR.extent + " hr.";
			} else if (extent >= MINUTE.extent) {
				return ((double) extent) / MINUTE.extent + " min.";
			} else if (extent >= SECOND.extent) {
				return ((double) extent) / SECOND.extent + " s.";
			} else {
				return ((double) extent) + " ms.";
			}
			//So let’s say they extent we have in milliseconds is equal to one year. 
			//Then this will return in one year but if it’s 365  it will return 365 days instead of 0.9… year
		}
	//
	//	return null; // TODO
	
	
	// Methods that are not standard or private must have a documentation comment:
	
	/**
	 * Add two durations together to get a larger duration.
	 * @param d duration being added to this, must not be null
	 * @return new duration equal to the combined duration of this and the argument.
	 * @throws NullPointerException if d is null
	 */
	public Duration add(Duration d) {
			return new Duration(this.extent + d.extent);
		}
	//we add the duration contained in this variable and the duration contained in the D variable
	// Return a new duration that when added to the argument will be equal
		 //to this. The argument must not be longer than this.
	//Then we create a new duration with the two,add them and return that
	 // TODO
	
	// TODO: three other public methods: subtract, scale & divide
	// Don't forget to write documentation comments.
	
	/**
	 
	 //
	 // @param d duration to remove, must not be null, must not be bigger than this
	 // @return the new duration that when added to the argument will be equal to
	//         this
	// @throws NullPointerException if d is null
	 // @throws ArithmeticException  if d is too large
	 */
	//https://www.youtube.com/watch?v=wDf5aMF80EY
	
	public Duration subtract(Duration d) {
		if (d.extent > this.extent)
			//We need to check so we don’t have a negative duration.  We throw the it’s not allowed exception.
			throw new ArithmeticException();
		return new Duration(this.extent - d.extent);
	}
	
	
	/**
	 * Return a new duration that is the argument times bigger than this duration.
	 * 
	 * @param scale amount to scale by, must not be negative.
	 * @return scaled duration
	 * @throws IllegalArgumentException if scale is negative.
	 */
	public Duration scale(double scale) {
		if (scale < 0)
			//Same logic here scale gotta be bigger than zero
			throw new IllegalArgumentException();
		return new Duration((long) Math.round(this.extent * scale));
	}
	
	public double divide(Duration d) {
		if (d.equals(INSTANTANEOUS))
			//If we try to divide by instantaneous which is 0 then we get that error.
			throw new ArithmeticException();
		return ((double) this.extent) / d.extent;
	}
}

	
	
	
	
	
	
	
	

