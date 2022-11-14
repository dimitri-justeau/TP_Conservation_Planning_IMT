package TP;

import org.chocosolver.solver.Solution;

public class Step2 {

    public static void main(String[] args) {
        /**
         * STEP 2 - Identify a protected area covering all the species
         */
        TP.ConservationPlanningModel TP2 = new TP.ConservationPlanningModel();
        Solution s2 = TP2.solveStep2();
        TP2.validateBaseModel(s2);
        TP2.validateCoveringSet(s2, 1);
        TP2.printSolution(s2);
    }
}
