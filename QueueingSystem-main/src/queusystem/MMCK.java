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
public class MMCK extends StochasticModel {

    double r = (super.arrivalRate / super.serviceRate);
    int C = 0;
    int K = 0;
    double ro = 0;

    public MMCK(double arrivalRate, double serviceRate, int serversNumber, int capacity) {
        super(arrivalRate, serviceRate, capacity, serversNumber);
        this.r = arrivalRate / serviceRate;
        this.C = serversNumber;
        this.K = capacity;
        this.ro = this.r / this.C;

    }

    public double calculatePn(double r, int n, int c) {
        if (n >= 0 && n < c) {
            double f1 = Math.pow(r, n) * calculateP0(r, c);
            double f2 = f1 / calculateFactorial(n);
            return f2;
        } else if (n >= c && n <= this.K) {
            double f1 = Math.pow(r, n) * calculateP0(r, c);
            double f2 = calculateFactorial(c) * (Math.pow(C, n - c));
            double f3 = f1 / f2;
            return f3;
        } else {
            System.out.println("error");
            return 0;
        }
    }

    public double calculateP0(double r, int c) {
        double res = 0;
        if (this.ro != 1) {
            res += calculateSummetion(0, this.C - 1);
            double f1 = Math.pow(this.r, this.C);
            double f2 = f1 / (calculateFactorial(this.C));
            double f3 = 1 - (Math.pow(this.ro, this.K - this.C + 1));
            double f4 = 1 - this.ro;
            double f5 = f2 * (f3 / f4);
            res += f5;
            res = 1 / res;
        } else {
            res += calculateSummetion(0, this.C - 1);
            double f1 = Math.pow(this.r, this.C);
            double f2 = f1 / (calculateFactorial(this.C));
            double f3 = this.K - this.C + 1;
            double f4 = f3 * f2;
            res += f4;
            res = 1 / res;
        }

        return res;
    }

    public double calcSumForL(double first, double end) {
        double res = 0;
        for (; first <= end; first++) {
            res += ((Math.pow(this.r, first)) / (calculateFactorial(first)))
                    * (this.C - first);
        }
        return res;
    }

    public double calculateSummetion(double first, double end) {
        double res = 0;
        for (; first <= end; first++) {
            res += (Math.pow(this.r, first)) / (calculateFactorial(first));
        }
        return res;
    }

    public double lamdaDash() {
        return this.arrivalRate * (1 - calculatePn(this.r, this.K, this.C));
    }

    @Override
    public double getL() {
        double f1 = getLq() + this.C;
        double f2 = 0;
        double res = calculateP0(this.r, this.C) * calcSumForL(0, this.C - 1);
        f2 = res;
        double f3 = f1 - f2;
        return f3;
    }

    @Override
    public double getLq() {
        double f0 = this.ro * (Math.pow(this.r, this.C));
//         System.out.println("f0=this.ro*(Math.pow(this.r, this.C))="+f0);
        double f1 = calculateP0(this.r, this.C) * (f0);
//      System.out.println("f1=calculateP0(this.r, this.C)*(f0)="+f1);

        double f2 = calculateFactorial(this.C) * (Math.pow(1 - this.ro, 2));
//System.out.println("f2=calculateFactorial( this.C)*(Math.pow(1-this.ro,2))="+f2);

        double f3 = f1 / f2;
//        System.out.println("f3=f1/f2="+f3);
        double f4 = 1 - (Math.pow(this.ro, this.K - this.C + 1)) - ((1 - this.ro) * (this.K - this.C + 1) * (Math.pow(this.ro, this.K - this.C)));
//                System.out.println(" f4=1-(Math.pow(this.ro,this.K-this.C+1))-((1-this.ro)*(this.K-this.C+1)*(Math.pow(this.ro,this.K-this.C)))="+f4);

        double f5 = f4 * f3;
        return f5;
    }

    @Override
    public double getW() {
        return (getL() / lamdaDash());
    }

    @Override
    public double getWq() {
        return (getLq() / lamdaDash());
    }

    public double calculateFactorial(double n) {
        double result = 1;
        for (double i = n; i > 0; i--) {
            result = result * (i);
        }
        return result;
    }

    @Override
    public String toString() {
        return "MMCK{" + "L=" + getL() + ", Lq=" + getLq() + " ... W=" + getW() + " ....Wq=" + getWq() + '}';
    }
}
