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
public class MM1 extends StochasticModel {

    public MM1(double arrivalRate, double serviceRate, int capacity, int serversNumber) {
        super(arrivalRate, serviceRate, capacity, serversNumber);
    }

    @Override
    public double getL() {
        return (arrivalRate / (serviceRate - arrivalRate));
    }

    ;
     @Override
    public double getLq() {
       return ((arrivalRate*arrivalRate) / (serviceRate*(serviceRate - arrivalRate)));
    }

    ;
     @Override
    public double getW() {
        return (1/(serviceRate - arrivalRate));
    }

    ;
    @Override
    public double getWq() {
        return ((arrivalRate) / (serviceRate*(serviceRate - arrivalRate)));
    }
;
}
