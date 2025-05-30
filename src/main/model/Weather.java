import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * Weather class controls the weather in our simulated world. 
 * It keeps track of the current weather type in the simulation.
 *
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */
public class Weather
{
    // instance variables
    // the current weather type
    private WeatherKind whatWeatherNow;
    // a field for a reference to the field affected by the the weather 
    private Field field;
     // a field for storing a calculated number of thunder strikes that will happen in every step
    private int numberOfThundersPerField;
    
    // the probability that a non standard phenomenon will start 
    // when there is a standard weather on the field 
    private static final double PROBABILITY_NON_STANDRAD_WEATHER_STARTS = 0.02;
    // the probability of a thunder striking a paticular location
    private static final double PROBABILITY_A_LOCATION_GETS_STRICKEN = 0.03;
    
    // A shared random number generator to control the weather phenomena.
    protected static final Random rand = Randomizer.getRandom();

    /**
     * Constructor for objects of class Weather. Set the weather as standard intially.
     */
    public Weather()
    {
       whatWeatherNow = null;
       setWeather();
    }

    /**
     * Set the current weather based on probability of a non-standard weather phenomenon occurring. 
     * Randomly designate the weather phenomenon that will affect the field. 
     * A non-standard weather phenomenon will only get initiated if 
     * the current weather is null, which stands for "standard & optimal”. 
     */
    public void setWeather()
     {
       if (whatWeatherNow == null){
        if(rand.nextDouble() <= PROBABILITY_NON_STANDRAD_WEATHER_STARTS)
        {
            int x = rand.nextInt(3); // or change for differing probabilities - better
            if (x == 0){
                whatWeatherNow = new ThunderStorm();
            }
            else if(x == 1){
                whatWeatherNow = new RainFall();
            }
            else{
                whatWeatherNow = new Fog();
            }
        }
      }
    }
    
    /**
     * Method acting as a mechanism for invoking what should happen
     * with the weather at each step of the smulation. 
     * It will also finish a weather phenomenon if the time has come.
     * @param  field  the field that will get affected by the weather
     * @return  a String that will pass the information on the current weather 
     * to the relevant other classes. 
     */
     public String weatherImpact(Field field)
    {
      if(whatWeatherNow != null){
       whatWeatherNow.incrementHowLongItHasLasted();
       if (whatWeatherNow.getHowLongItHasLasted() < whatWeatherNow.getHowLongItWillLast())
       {
          if(whatWeatherNow instanceof ThunderStorm){
              ThunderStrike(field);
          }
          return whatWeatherNow.impact();
       }
       else {
          // the current phenomenon has reached its lifespan's end; 
          // finish it and set the weather to null, i.e. "standard & optimal”
          whatWeatherNow.finishThisWeather();
          whatWeatherNow = null;
          return null;
       }
      }
      else{
          // the whatWeatherNow remains null, i.e. "standard & optimal”
          return null;
      }
    }
    
    /**
     * Randomly draw locations that will get affected by a thunder strike.
     * Remove from the field and set as dead the animals that get striken by a thunder.  
     * @param  field  the field that will get affected by the thunders
     */
     private void ThunderStrike(Field field)
    {
        // Thunder strikes implementation
       numberOfThundersPerField = (int) (field.getSize() * PROBABILITY_A_LOCATION_GETS_STRICKEN);
       List<Integer> xCoordinates= drawXCoordinatesForAStroke(field);
       List<Integer> yCoordinates= drawYCoordinatesForAStroke(field);
       int i;
       for(i = 0; i < numberOfThundersPerField; i++){
        Integer x = xCoordinates.get(i);
        Integer y = yCoordinates.get(i);
        Object object = field.getObjectAt(x.intValue(),y.intValue());
         if (object != null){
         Actor actor = (Actor) object;
         actor.setDead();
        }
       }
    }
    
    /**
     * Create an ArrayList storing coordinates of the locations that will get affected. 
     * @param  field  the field that will get affected by the thunders
     * @return an ArrayList storing coordinates of the locations that will get affected
     */
    private List<SetOfCoordinates> drawCoordinatesForAStroke(Field field)
    {
        List<SetOfCoordinates> coordinates = new ArrayList<SetOfCoordinates>();
        List<Integer> xSet = drawXCoordinatesForAStroke(field);
        List<Integer> ySet = drawYCoordinatesForAStroke(field);
        int i;
        for (i = 0; i< numberOfThundersPerField; i++){
          coordinates.add(new SetOfCoordinates(xSet.get(i), ySet.get(i)));
        }
        return coordinates;
    }
    
    /**
     * Create an ArrayList storing randomly drawn x coordinates of teh field locations. 
     * @param  field  the field that will get affected by the thunders
     * @return an ArrayList storing x coordinates of the locations that will get affected
     */
    private List<Integer> drawXCoordinatesForAStroke(Field field)
    {
      List<Integer> xCoordinates = new ArrayList<Integer>();
      int i;
      for (i = 0; i< numberOfThundersPerField; i++){
        xCoordinates.add(rand.nextInt(field.getDepth()));
      }
      return xCoordinates;
    }
    
    /**
     * Create an ArrayList storing randomly drawn y coordinates of teh field locations. 
     * @param  field  the field that will get affected by the thunders
     * @return an ArrayList storing y coordinates of the locations that will get affected
     */
    private List<Integer> drawYCoordinatesForAStroke(Field field)
    {
      List<Integer> yCoordinates = new ArrayList<Integer>();
      int i;
      for (i = 0; i< numberOfThundersPerField; i++){
        yCoordinates.add(rand.nextInt(field.getWidth()));
      }
      return yCoordinates;
    }
}

