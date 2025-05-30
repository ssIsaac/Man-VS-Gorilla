import java.util.Random;

/**
 * Abstract class WeatherKind - a superclass for defining particular weather phenomena. 
 *
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28 */
abstract public class WeatherKind
{
    // instance varaibles 
    // how long the weather phenomenon has been present for
    protected int howLongItHasLasted;
    // if the weather phenomenon is still happening 
    protected boolean ifStillHappenning;
    // how long the weather phenomenon will last
    protected int howLongItWillLast;
    
    // A shared random number generator the weather. 
    protected static final Random rand = Randomizer.getRandom();
    
    /**
     * Constructor for objects of class WeatherKind. 
     * Set the time a phenomenon has lasted for as 0. 
     * Randomly determine how long the phenomenon will last. 
     */
    public WeatherKind()
    {
        howLongItHasLasted = 0;
        ifStillHappenning = true;
        determineHowLongItWillLast();
    }
    
    /**
     * Determine how long this instance of the phenomenon will last.
     */
    abstract protected void determineHowLongItWillLast();
    
    /**
     * Return a String that reports the ThunderStorm’s status.
     * @return   String weatherStatus
     */
    abstract protected String impact();
    
    /**
     * Finish this phenomenon. 
     */
    protected void finishThisWeather()
    {
        ifStillHappenning = false;
    }
    
    /**
     * Increment how long this phenomenon has lasted for. 
     */
    protected void incrementHowLongItHasLasted()
    {
        howLongItHasLasted++; 
    }
    
    /**
    * Get how long this phenomenon has been present for.
    */
    protected int getHowLongItHasLasted()
    {
        return howLongItHasLasted; 
    }
    
    /**
    * Get how long this phenomenon will last for.
    */
    protected int getHowLongItWillLast()
    {
        return howLongItWillLast; 
    }
}
