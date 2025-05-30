package com.example.app.model.actor;
import java.util.List;
import java.util.Random;
import java.util.Iterator;

import com.example.app.statistics.Field;
import com.example.app.statistics.Location;

/**
 * A simple model of an Gorilla.
 * Gorillas age, move, breed, and die.
 * 
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */

public class Gorilla extends Actor
{
    //instance variables
    protected int foodLevel;
    protected boolean female;
    protected int stepsSinceLastEating;

        // Characteristics shared by all wolves (class variables).
    // The age at which a wolf can start to breed.
    private static final int BREEDING_AGE = 15;
    // The age to which a wolf can live.
    private static final int MAX_AGE = 960;
    // The likelihood of a wolf breeding.
    private static final double BREEDING_PROBABILITY = 0.32;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 3;
    // number of steps a wolf can go before it has to eat again since birth
    private static final int AT_BIRTH_FOOD_LEVEL = 240;
    // food value provided when the wolf is eaten
    private static final int CALORIES_PROVIDED = 240;

    /**
     * Create a new Gorilla at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to an Gorilla
     * @param disease Whether an Gorilla has a disease
     */
    public Gorilla(Field field, Location location, boolean randomAge, boolean disease)
    {
        super(field, location, randomAge, disease);
        female = (rand.nextInt(2) == 1);
        stepsSinceLastEating = 0;
    }

    /**
     * Make this Gorilla act - that is: make it do
     * whatever it wants/needs to do.
     * @param newGorilla A list to receive newly born Gorillas.
     * @param timeOfTheDay The time in the simulation at that instance 
     * @param weatherStatus The current weather in the simulation
     */
    public void act(List<Actor> newActors, String timeOfTheDay, String weatherStatus)
    {
        //everytime an Gorilla acts (a step simulated),
        //its food level reduces by one
        incrementHunger();
        //steps since last eating increases by one
        stepsSinceLastEating++;
        //act method inherited from actor class
        super.act(newActors, timeOfTheDay, weatherStatus);

        if(isAlive()){
            Location newLocation;
            //96 is the amount of steps in 24 hours
            //If an Gorilla has not eaten in 24 hours,
            //it will attempt to hunt for food
            
            newLocation = justMove();
            
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }

    /**
     * Make this wolf more hungry. This could result in the wolf's death.
     */
    public void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }

    


    /**
     * @return Free adjacent locations
     */
    protected Location justMove()
    {
        Field field = getField();
        Location newLocation;
        newLocation = field.freeAdjacentLocation(location);
        return newLocation;
    }


    /**
     * @return The maximum age a wolf can live up to. This could result in the wolf's death.
     */
    public int getMaxAge()
    {
        return MAX_AGE;
    }


    /**
     * Check if object is a wolf. 
     * @param the object near the wolf
     * @return true if the actor is of type wolf
     */
    public boolean isInstanceOf(Object object)
    {
        return object instanceof Gorilla;
    }
}
