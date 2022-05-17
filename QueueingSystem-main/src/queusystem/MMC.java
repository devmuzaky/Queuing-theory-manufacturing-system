package queusystem;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Mohamed Karm
 */
public class MMC extends StochasticModel {

    double r = (super.arrivalRate / super.serviceRate);
    int C = 0;

    public MMC(double arrivalRate, double serviceRate, int capacity, int C) {
        super(arrivalRate, serviceRate, capacity, C);
        this.r = arrivalRate / serviceRate;
        this.C = C;
    }

    @Override
    public double getL() {
        this.L = getLq() + this.r;
        return this.L;
    }

    @Override
    public double getLq() {
        double f1 = ((Math.pow(this.r, this.C)) * (this.arrivalRate * this.serviceRate));
        
        //البسط
        double f2 = (calculateFactorial(this.C - 1));
        
        double f3 = (Math.pow((this.C * this.serviceRate) - this.arrivalRate, 2));

        double f4 = f2 * f3; //المقام
        
        double f5 = (f1 / f4);
//        double f6=BigDecimal.valueOf(f5).setScale(3,RoundingMode.HALF_UP).doubleValue();
        this.Lq = Math.round((calculatep0() * f5) * 1000.0) / 1000.0;

        return this.Lq;
    }

    @Override
    public double getW() {
        this.Wq = getWq() + (1 / this.serviceRate);
        return this.Wq;
    }

    @Override
    public double getWq() {
        this.Wq = (getLq()) / (this.arrivalRate);

        return this.Wq;
    }

    public double calculatep0() {
        double p0 = 0;
        if ((this.r / this.C) < 1) {
            double f1 = calculateSummetion(0, this.C - 1);

            double f2 = ((this.C) * (Math.pow(r, this.C)) / ((calculateFactorial(this.C)) * (this.C - this.r)));
            double f3 = f1 + f2;
            p0 = (1.0 / f3);
        } else {
            double f1 = calculateSummetion(0, this.C - 1);
            double f2 = (Math.pow(r, this.C));
            double f3 = (1) / (calculateFactorial(this.C));
            double f4 = (this.C * this.serviceRate) / ((this.C * this.serviceRate) - this.arrivalRate);
            double f5 = f2 + f3 + f4;
            double f6 = f5 + f1;
            p0 = 1 / f6;
        }

        return p0;
    }

    public double calculateFactorial(double n) {
        double result = 1;
        for (double i = n; i > 0; i--) {
            result = result * (i);
        }
        return result;
    }

    public double calculateSummetion(double first, double end) {
        double res = 0;
        for (; first <= end; first++) {
            res += (Math.pow(this.r, first)) / (calculateFactorial(first));

        }
        return res;
    }

    @Override
    public String toString() {
        return "MMC{" + "L=" + getL() + ", Lq=" + getLq() + " ... W=" + getW() + " ....Wq=" + getWq() + '}';
    }

}
