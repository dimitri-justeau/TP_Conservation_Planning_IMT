package TP;

import org.chocosolver.solver.Solution;

public class Step3 {

    public static void main(String[] args) {
        /**
         * STEP 3 - Same as STEP 2, but find a minimal-cost protected area
         */
        TP.ConservationPlanningModel TP3 = new TP.ConservationPlanningModel();
        Solution s3 = TP3.solveStep3();
        TP3.validateBaseModel(s3);
        TP3.validateCoveringSet(s3, 1);
        if (s3.getIntVal(TP3.nbPUs) > 3) {
            throw new AssertionError("The solution is not optimal");
        }
        TP3.printSolution(s3);
    }
}
