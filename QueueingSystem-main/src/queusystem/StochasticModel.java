/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queusystem;

/**
 *
 * @author Mohamed Karm
 */
public abstract class StochasticModel extends QueueingClass{

    public StochasticModel(double arrivalRate, double serviceRate, int capacity, int serversNumber) {
        super(arrivalRate, serviceRate, capacity, serversNumber);
    }
    
    public abstract double getL();
    public abstract double getLq();
    public abstract double getW();
    public abstract double getWq();
}
