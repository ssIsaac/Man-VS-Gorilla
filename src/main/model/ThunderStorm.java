import java.util.List;
import java.util.ArrayList;

/**
 * A class that creates ThunderStorm instances.
 *
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */
public class ThunderStorm extends WeatherKind
{
    // instance variables - replace the example below with your own
    // 0.25 h 
    private static final int MIN_IT_WILL_LAST = 1;
    // 6 h 
    private static final int MAX_IT_WILL_LAST = 24;

    private int numberOfThundersPerField;

    /**
     * Constructor for objects of class ThunderStorm
     */
    public ThunderStorm()
    {
        super();        
    }

    /**
     * Return a String that reports the ThunderStorm’s status.
     * @return   String weatherStatus
     */
    public String impact()
    {
        // During a thunderstorm thunderstrikes will strike randomly and kill animals.
        // There will also be rainfall that will make the plants grow faster.
        String weatherStatus = "thunderstorm";
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
