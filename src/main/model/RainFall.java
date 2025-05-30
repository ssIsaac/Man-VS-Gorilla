
/**
 * A class that creates RainFall instances.
 *
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */
public class RainFall extends WeatherKind
{
    // instance variables - replace the example below with your own
    // 0.5 h 
    private static final int MIN_IT_WILL_LAST = 2;
    // 5 h 
    private static final int MAX_IT_WILL_LAST = 20;

     /**
     * Constructor for objects of class RainFall
     */
    public RainFall()
    {
        super();        
    }

    /**
     * Return a String that reports the RainFall’s status.
     * @return   String weatherStatus
     */
    public String impact()
    {
       // The rainfall will make the plants grow (i.e. spread out) faster.
       String weatherStatus = "rainfall";
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
