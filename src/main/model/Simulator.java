import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

/**
 * A simple predator-prey simulator, based on a rectangular field
 * containing rabbits and foxes.
 * 
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */
public class Simulator
{
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 120;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;
    // The probability that a wolf will be created in any given grid position.
    private static final double WOLF_CREATION_PROBABILITY = 0.03;
    // The probability that a cat will be created in any given grid position.
    private static final double CAT_CREATION_PROBABILITY = 0.04;  
    // The probability that a bird will be created in any given grid position.
    private static final double BIRD_CREATION_PROBABILITY = 0.05;
    //The probability that a mouse will be created in any given grid position.
    private static final double MOUSE_CREATION_PROBABILITY = 0.06; 
    //The probability that a fly will be created in any given grid position.
    private static final double FLY_CREATION_PROBABILITY = 0.06;  
    //The probability that a fruit will be created in any given grid position.
    private static final double FRUIT_CREATION_PROBABILITY = 0.01;  
    //The probability that a patch of grass will be created in any given grid position.
    private static final double GRASS_CREATION_PROBABILITY = 0.01;  

    // List of animals in the field.
    private List<Actor> actors;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;
    // A timetracker 
    private TimeTracker timeTracker;
    // The current time
    private String timeOfTheDay;
    private String time;
    // The current weather
    private Weather weather;
    private String weatherStatus;
    
    
    /**
     * Construct a simulation field with default size.
     */
    public Simulator()
    {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }
    
    /**
     * Create a simulation field with the given size.
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width)
    {
        if(width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }
        
        actors = new ArrayList<>();
        field = new Field(depth, width);

        // Create a view of the state of each location in the field.
        view = new SimulatorView(depth, width);
        view.setColor(Cat.class, Color.ORANGE);
        view.setColor(Wolf.class, Color.BLUE);
        view.setColor(Bird.class, Color.RED);
        view.setColor(Fly.class, Color.YELLOW);
        view.setColor(Mouse.class, Color.BLACK);
        view.setColor(Fruit.class, Color.PINK);
        view.setColor(Grass.class, Color.GREEN);
        
        // Setup a valid starting point.
        reset();
        
        timeTracker = new TimeTracker();
        weather = new Weather();
        weatherStatus = null;
        time = timeTracker.getTimeString();
    }
    
     /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 steps).
     */
    public void runLongSimulation()
    {
        simulate(4000);
    }
    
    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps)
    {
        for(int step = 1; step <= numSteps && view.isViable(field); step++) {
            simulateOneStep();
            // delay(60);   // uncomment this to run more slowly
        }
    }
    
    /**
     * Run the simulation from its current state for a single step.
     * Iterate over the whole field updating the state of each
     * fox and rabbit.
     */
    public void simulateOneStep()
    {
        step++;
        timeTracker.incrementTimeTracker();
        timeOfTheDay = timeTracker.checkTheTimeOfTheDay();
        // accessing time data for their display
        time = timeTracker.getTimeString();
        // accessing weather status data to display what weather 
        // was implemented in the previous step
        if (weather.weatherImpact(field) == null){
            weatherStatus = "standard & optimal";
        }
        else {
            weatherStatus = weather.weatherImpact(field);
        }
        // set weather
        weather.setWeather();
        
        // Provide space for newborn animals.
        List<Actor> newActors = new ArrayList<>();        
        // Let all rabbits act.
        for(Iterator<Actor> it = actors.iterator(); it.hasNext(); ) {
            Actor actor = it.next();
            actor.act(newActors, timeOfTheDay, weatherStatus);
            if(! actor.isAlive()) {
                it.remove();
            }
        }
               
        // Add the newly born foxes and rabbits to the main lists.
        actors.addAll(newActors);

        view.showStatus(step, field, weatherStatus, time);
    }
        
    /**
     * Reset the simulation to a starting position.
     */
    public void reset()
    {
        step = 0;
        actors.clear();
        populate();
        
        // Show the starting state in the view.
        view.showStatus(step, field, weatherStatus, time);
    }
    
    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate()
    {
        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= WOLF_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Wolf wolf = new Wolf(field, location, true, false);
                    actors.add(wolf);
                }
                else if(rand.nextDouble() <= CAT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Cat cat = new Cat(field, location, true, false);
                    actors.add(cat);
                }
                else if(rand.nextDouble() <= BIRD_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Bird bird = new Bird(field, location, true, false);
                    actors.add(bird);
                }
                else if(rand.nextDouble() <= FLY_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fly fly = new Fly(field, location, true, false);
                    actors.add(fly);
                }
                else if(rand.nextDouble() <= MOUSE_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Mouse mouse = new Mouse(field, location, true, false);
                    actors.add(mouse);
                }
                else if(rand.nextDouble() <= FRUIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fruit fruit = new Fruit(field, location, true, false);
                    actors.add(fruit);
                }
                else if(rand.nextDouble() <= GRASS_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Grass grass = new Grass(field, location, true, false);
                    actors.add(grass);
                }
                // else leave the location empty.
            }
        }
    }
    
    /**
     * Pause for a given time.
     * @param millisec  The time to pause for, in milliseconds
     */
    private void delay(int millisec)
    {
        try {
            Thread.sleep(millisec);
        }
        catch (InterruptedException ie) {
            // wake up
        }
    }
}

