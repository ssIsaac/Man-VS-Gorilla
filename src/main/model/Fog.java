
/**
 * A class that creates Fog instances
 *
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */
public class Fog extends WeatherKind
{
    // instance variables - replace the example below with your own
    // 1 h 
    private static final int MIN_IT_WILL_LAST = 4;
    // 3 h 
    private static final int MAX_IT_WILL_LAST = 12;

     /**
     * Constructor for objects of class Fog
     */
    public Fog()
    {
        super();        
    }

    /**
     * Return a String that reports the Fog’s status.
     * @return   String weatherStatus
     */
    public String impact()
    {
       // The fog will make predators anable to hunt.
       String weatherStatus = "fog";
       return weatherStatus;
    }
    
    /**
     * Determine how long this instance of the phenomenon will last.
     */
    protected void determineHowLongItWillLast()
    {
       howLongItWillLast = rand.nextInt(MAX_IT_WILL_LAST-MIN_IT_WILL_LAST) + MIN_IT_WILL_LAST;
    }
}

