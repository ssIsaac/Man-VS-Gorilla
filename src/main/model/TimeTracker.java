
/**
 * TimeTracker class keeps track of the tiime in our simulated world.
 *
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */
public class TimeTracker
{
    // instance variables
    private int minute;
    private int hour;
    private int day;

    /**
     * Constructor for objects of class TimeTracker. 
     * Set all time-measuring fields to 0. 
     */
    public TimeTracker()
    {
        // initialise instance variables
        minute = 0;
        hour = 0;
        day = 0;
    }

    /**
     * Increases the time of the simulated world.
     */
    public void incrementTimeTracker()
    {
        minute = minute + 15;

        if (minute > 59){
            minute = 0;
            hour++;
        }

        if (hour > 23){
            hour = 0;
            day++;
        }
    }

    /**
     * Check if it's night time.
     * @return boolean true - if it is.
     */
    private boolean ifNight()
    {
        return (hour < 7 || hour >= 23);
    }

    /**
     * Check if it's afternoon time.
     * @return boolean true - if it is.
     */
    private boolean ifAfternoon()
    {
        return (hour < 18 && hour >= 13);
    }

    /**
     * Check if it's morning time.
     * @return boolean true - if it is.
     */
    private boolean ifMorning()
    {
        return (hour < 12 && hour >= 7);
    }

    /**
     * Check if it's evening time.
     * @return boolean true - if it is.
     */
    private boolean ifEvening()
    {
        return (hour < 23 && hour >= 18);
    }

    /**
     * Check what time of the day it is.
     * @return String time of the day signifying status
     */
    public String checkTheTimeOfTheDay()
    {
        String string = new String();
        if(ifNight()){
            string = "night";
        }
        else if(ifAfternoon()){
            string = "afteroon";
        } 
        else if(ifMorning()){
            string = "morning";   
        }
        else if(ifEvening()){
            string = "evening";
        }
        return string;
    }

    /**
     * Get the time decribing String for the display. 
     * @return String time description
     */
    public String getTimeString()
    {
        String string = new String();
        // which day of the simulation it is 
        int numberOfTheDay = day + 1;
        string = "Day: " + numberOfTheDay + " Time: " + hour + ":" + minute + "  " + "\n" + checkTheTimeOfTheDay() + "  ";
        return string;
    }
}
