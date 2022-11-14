package TP;

import org.chocosolver.solver.Solution;

public class Step1 {

    public static void main(String[] args) {
        /**
         * STEP 1 - Build the base model
         */
        ConservationPlanningModel TP1 = new ConservationPlanningModel();
        Solution s1 = TP1.solveStep1();
        TP1.validateBaseModel(s1);
        TP1.printSolution(s1);
    }
}
