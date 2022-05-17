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
public class MM1K extends StochasticModel {

    int K = 0;

    public MM1K(double arrivalRate, double serviceRate, int capacity) {
        super(arrivalRate, serviceRate, 1, capacity);
        K = capacity;
    }

    public double calcRo() {
        return (this.arrivalRate / this.serviceRate);
    }

    public double calcPk() {
        double Pk = 0;
        double ro = calcRo();
        if (ro == 1) {
            Pk = 1 / (1 + K);
        } else {
            double f1 = 1 - ro;
            double f2 = 1 - (Math.pow(ro, K + 1));
            Pk = (f1 * f2) * (Math.pow(ro, K));
        }
        return Pk;
    }

    public double lamdaDash() {
        return this.arrivalRate * (1 - calcPk());
    }

    @Override
    public double getL() {
        double ro = calcRo();

        if (ro == 1) {
            this.L = K / 2;
        } else {
            double f1 = (1 - ((K + 1) * (Math.pow(ro, K))) + (K * Math.pow(ro, K + 1)));
            double f2 = ((1 - ro) * (1 - (Math.pow(ro, K + 1))));
            this.L = (ro * (f1 / f2));
        }
        return this.L;
    }

    @Override
    public double getLq() {
        this.Lq = getWq() * lamdaDash();
        return this.Lq;
    }

    @Override
    public double getW() {
        this.W = ((getL()) / lamdaDash());
        return this.W;
    }

    @Override
    public double getWq() {
        this.Wq = (getW() - (1 / this.serviceRate));
        return this.Wq;
    }

    @Override
    public String toString() {
        return "MM1K{" + "L=" + getL() + ", Lq=" + getLq() + " ... W=" + getW() + " ....Wq=" + getWq() + '}';
    }
}
