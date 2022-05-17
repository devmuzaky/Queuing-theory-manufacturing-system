
package queusystem;


public class DeterministicModel extends Queueing {


    int initialEntities; // equal M
    int capacity;//capacity = K-1
    int firstBalkTime;//equal ti
    double arrivalRate;
    double serviceRate;

    public DeterministicModel(int M, int cap, double arrivalRate, double serviceRate) {
        this.initialEntities = M;
        this.capacity = cap;
        this.arrivalRate = arrivalRate;
        this.serviceRate = serviceRate;
    }

    // function to get ti (minimum accepted value of ti by trial and error )
    int getFirstBalkTime() {
        int K = capacity + 1;
        if (arrivalRate > serviceRate) {
            firstBalkTime = (int) Math.floor((K - (serviceRate / arrivalRate)) / (arrivalRate - serviceRate));
            int trialTime = (int) (firstBalkTime - (1 / arrivalRate));
            boolean isNotEnd = true;
            while (isNotEnd) {
                if (K == (int) (Math.floor(arrivalRate * trialTime) - Math.floor(serviceRate * trialTime - serviceRate / arrivalRate))) {
                    trialTime = (int) (trialTime - (1 / arrivalRate));
                } else {
                    firstBalkTime = (int) (trialTime + (1 / arrivalRate));
                    isNotEnd = false;
                }
            }
            return firstBalkTime;
        } else {
            firstBalkTime = (int) Math.floor(initialEntities / (serviceRate - arrivalRate));
            int trialTime = (int) (firstBalkTime - (1 / arrivalRate));
            boolean isNotEnd = true;
            while (isNotEnd) {
                if (initialEntities == (int) (Math.floor(serviceRate * trialTime) - Math.floor(arrivalRate * trialTime))) {
                    trialTime = (int) (trialTime - (1 / arrivalRate));
                } else {
                    firstBalkTime = (int) (trialTime + (1 / arrivalRate));
                    isNotEnd = false;
                }
            }
            return firstBalkTime;
        }
    }

    //function to get n(t)
    public int getWaitingLength(int time) {
       
        int waitingLength;
        int K = capacity + 1;
        getFirstBalkTime();
        if (arrivalRate > serviceRate) {
            if (time >= firstBalkTime) {
                System.out.println("Waiting Length equal " + (K - 1) + " or " + (K - 2));
                return K - 1;
            }
            waitingLength = (int) (Math.floor(arrivalRate * time) - Math.floor(serviceRate * time - serviceRate / arrivalRate));
        } else if (arrivalRate < serviceRate) {
            if (time >= firstBalkTime) {
                System.out.println("Waiting Length equal " + (0) + " or " + (1));
                return 0;
            }
            waitingLength = (int) (initialEntities +
                    Math.floor(arrivalRate * time) - Math.floor(serviceRate * time));
            
        } else {
            waitingLength = initialEntities;
        }
       
        return waitingLength;
    }

    //function to get Wq(n)
    public double getWaitingTime(int entities) {
        double waitingTime;
        if (entities == 0) {
            waitingTime = (initialEntities - 1) / (2 * serviceRate);
        } else {
            getFirstBalkTime();
            if (arrivalRate > serviceRate) {
                if (entities >= (arrivalRate * firstBalkTime)) {
                    waitingTime = (1 / serviceRate - 1 / arrivalRate) * (arrivalRate * firstBalkTime - 2);
                    double watingTime2 = Math.round((1 / serviceRate - 1 / arrivalRate) * (arrivalRate * firstBalkTime - 3));
                    waitingTime = Math.round(waitingTime);
                    System.out.println("Wq(n)= " + waitingTime + " or " + watingTime2);
                    return waitingTime;
                }
                waitingTime = (1 / serviceRate - 1 / arrivalRate) * (entities - 1);
            } else if (serviceRate > arrivalRate) {
                if (entities >= (arrivalRate * firstBalkTime)) {
                    return 0;
                }
                waitingTime = ((initialEntities - 1 + entities) / serviceRate) - (entities / arrivalRate);
            } else {
                waitingTime = (initialEntities - 1) / serviceRate;
            }
        }
        waitingTime = Math.round(waitingTime);
        return waitingTime;
    }


}
