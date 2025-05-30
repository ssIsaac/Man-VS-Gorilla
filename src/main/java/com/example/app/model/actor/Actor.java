package com.example.app.model.actor;
import java.util.List;
import java.util.Random;

import com.example.app.statistics.Field;
import com.example.app.statistics.Location;
import com.example.app.statistics.Randomizer;
import com.example.app.view.Simulator;

/**
 * A class representing shared characteristics of actors.
 * 
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */
public abstract class Actor
{    
    //instance variables 
    // Whether the actor is alive or not.
    protected boolean alive;
    // The actor's field.
    protected Field field;
    // The actor's position in the field.
    protected Location location;
    // A shared random number generator to control breeding.
    protected static final Random rand = Randomizer.getRandom();
    //Whether this actor has a disease
    protected boolean disease;
    //The simulator
    protected Simulator simulator;

    // Individual characteristics (instance fields).
    // The actor's age.
    protected int age;
    //probability of an actor being born with a disease
    protected static final double PROBABILITY_OF_DISEASE = 0.01;

    /**
     * Create a new actor at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to an actor
     * @param disease Whether an actor has a disease
     */
    public Actor(Field field, Location location, boolean randomAge, boolean disease)
    {
        alive = true;
        this.field = field;
        setLocation(location);
        this.disease = disease;
        if(randomAge) {
            //generates an integer number that is within the max age limit
            //then assigns to the actor
            int maxAge = getMaxAge();
            age = rand.nextInt(maxAge);
        }
        else {
            age = 0;
        }
    }

    /**
     * Make this actor act - that is: make it do
     * whatever it wants/needs to do.
     * @param newActor A list to receive newly born actors.
     * @param timeOfTheDay The time in the simulation at that instance 
     * @param weatherStatus The current weather in the simulation
     */
    public void act(List<Actor> newActors, String timeOfTheDay, String weatherStatus)
    {
        incrementAge();
    }

    /**
     * Check whether the actor is alive or not.
     * @return true if the actor is still alive.
     */
    public boolean isAlive()
    {
        return alive;
    }

    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    public void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
     */
    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    /**
     * Increase the age. This could result in the actor's death.
     * If actor has a disease, it ages faster
     */
    protected void incrementAge()
    {
        int max_age = getMaxAge();
        if (hasDisease()){
            age = age + 100;
        }
        else {
            age++;
        }

        //If the age of an actor exceeds its maximum age, it dies
        if(age > max_age) {
            setDead();
        }
    }


    /**
     * @return the field currently occupied.
     */
    protected Field getField()
    {
        return field;
    }

    /**
     * @return The actor's location within the field.
     */
    protected Location getLocation()
    {
        return location;
    }

    /**
     * @return whether the actor has a disease
     */
    protected boolean hasDisease ()
    {
        return disease;
    }

    /**
     * Set the boolean disease to true
     * Actor has a disease
     */
    protected void setDisease()
    {
        disease = true;
    }

    /**
     * Get the maximum age an actor can live up to
     */
    abstract public int getMaxAge();

}
