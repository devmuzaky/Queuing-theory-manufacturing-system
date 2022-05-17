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
public class QueueingClass {
    double arrivalRate , serviceRate;
    int capacity , serversNumber;
    double L=0;
double Lq=0;
double W=0;
double Wq=0;
public QueueingClass(){}
    public QueueingClass(double arrivalRate, double serviceRate, int capacity, int serversNumber) {
        this.arrivalRate = arrivalRate;
        this.serviceRate = serviceRate;
        this.capacity = capacity;
        this.serversNumber = serversNumber;
    }

    public double getArrivalRate() {
        return arrivalRate;
    }

    public void setArrivalRate(double arrivalRate) {
        this.arrivalRate = arrivalRate;
    }

    public double getServiceRate() {
        return serviceRate;
    }

    public void setServiceRate(double serviceRate) {
        this.serviceRate = serviceRate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getServersNumber() {
        return serversNumber;
    }

    public void setServersNumber(int serversNumber) {
        this.serversNumber = serversNumber;
    }
    
}
