package TP;

import org.chocosolver.solver.Solution;

public class Step5 {

    public static void main(String[] args) {
        /**
         * STEP 5 - Same as STEP 4, but we want the protected area to be connected
         */
        ConservationPlanningModel TP5 = new ConservationPlanningModel();
        Solution s5 = TP5.solveStep5();
        TP5.validateBaseModel(s5);
        TP5.validateCoveringSetPlants(s5, 1);
        TP5.validateCoveringSetAnimals(s5, 2);
        if (s5.getIntVal(TP5.nbPUs) > 9) {
            throw new AssertionError("The solution is not optimal");
        }
        TP5.printSolution(s5);
    }
}
