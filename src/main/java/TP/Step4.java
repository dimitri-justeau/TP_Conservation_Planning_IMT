package TP;

import org.chocosolver.solver.Solution;

public class Step4 {

    public static void main(String[] args) {
        /**
         * STEP 4 - Same as STEP 3, but we want at least two occurrences of each species
         */
        TP.ConservationPlanningModel TP4 = new TP.ConservationPlanningModel();
        Solution s4 = TP4.solveStep4();
        TP4.validateBaseModel(s4);
        TP4.validateCoveringSetPlants(s4, 1);
        TP4.validateCoveringSetAnimals(s4, 2);
        if (s4.getIntVal(TP4.nbPUs) > 4) {
            throw new AssertionError("The solution is not optimal");
        }
        TP4.printSolution(s4);
    }
}
