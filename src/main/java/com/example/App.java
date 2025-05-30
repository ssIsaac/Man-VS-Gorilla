package com.example;

import com.example.app.view.Simulator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // 1) Make your simulator
        Simulator sim = new Simulator();
        // 2) Either run a fixed number of steps…
        sim.runLongSimulation();
        // or if you want more control:
        // sim.simulate(1000);
    }
}
