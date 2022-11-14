package TP;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.UndirectedGraphVar;
import org.chocosolver.util.objects.graphs.GraphFactory;
import org.chocosolver.util.objects.graphs.UndirectedGraph;
import org.chocosolver.util.objects.setDataStructures.SetType;
import org.chocosolver.util.tools.ArrayUtils;

import java.util.stream.IntStream;

public class ConservationPlanningModel {

    /**
     * The Choco Model
     */
    Model model;

    /**
     * Boolean variables corresponding to the planning units selected for the protected area
     */
    BoolVar[] selected;

    /**
     * Integer variable corresponding to the number of selected planning units
     */
    IntVar nbPUs;

    /**
     * Integer variables corresponding to the number of occurrences of each species in the protected area
     */
    IntVar occTreeA;
    IntVar occTreeB;
    IntVar occTreeC;
    IntVar occTreeD;
    IntVar occFern;
    IntVar occBird;
    IntVar occGecko;

    IntVar[] plantSpecies;
    IntVar[] animalSpecies;
    IntVar[] species;

    public ConservationPlanningModel() {
        model = new Model("Conservation planning");
        initVariables();
        plantSpecies = new IntVar[] { occTreeA, occTreeB, occTreeC, occTreeD, occFern };
        animalSpecies = new IntVar[] { occBird, occGecko };
        species = ArrayUtils.concat(plantSpecies, animalSpecies);
        postBaseModelConstraint();
        model.getSolver().showShortStatistics();
    }

    public void initVariables() {
        selected = model.boolVarArray("PUs", Data.N);
        nbPUs = model.intVar("NbPUs", 0, Data.N);
        occTreeA = model.intVar(0, Data.NB_OCC_TREE_A);
        occTreeB = model.intVar(0, Data.NB_OCC_TREE_B);
        occTreeC = model.intVar(0, Data.NB_OCC_TREE_C);
        occTreeD = model.intVar(0, Data.NB_OCC_TREE_D);
        occFern = model.intVar(0, Data.NB_OCC_FERN);
        occBird = model.intVar(0, Data.NB_OCC_BIRD);
        occGecko = model.intVar(0, Data.NB_OCC_GECKO);
    }

    public void postBaseModelConstraint() {
        model.sum(selected, "=", nbPUs).post();
        model.scalar(selected, Data.OCC_TREE_A, "=", occTreeA).post();
        model.scalar(selected, Data.OCC_TREE_B, "=", occTreeB).post();
        model.scalar(selected, Data.OCC_TREE_C, "=", occTreeC).post();
        model.scalar(selected, Data.OCC_TREE_D, "=", occTreeD).post();
        model.scalar(selected, Data.OCC_FERN, "=", occFern).post();
        model.scalar(selected, Data.OCC_BIRD, "=", occBird).post();
        model.scalar(selected, Data.OCC_GECKO, "=", occGecko).post();
    }

    public Solution solveStep1() {
        return model.getSolver().findSolution();
    }

    public Solution solveStep2() {
        for (IntVar s : species) {
            model.arithm(s, ">=", 1).post();
        }
        return model.getSolver().findSolution();
    }

    public Solution solveStep3() {
        for (IntVar s : species) {
            model.arithm(s, ">=", 1).post();
        }
        return model.getSolver().findOptimalSolution(nbPUs, false);
    }

    public Solution solveStep4() {
        for (IntVar s : plantSpecies) {
            model.arithm(s, ">=", 1).post();
        }
        for (IntVar s : animalSpecies) {
            model.arithm(s, ">=", 2).post();
        }
        return model.getSolver().findOptimalSolution(nbPUs, false);
    }

    public Solution solveStep5() {
        // TODO
        return null;
    }

    public void printSolution(Solution s) {
        System.out.println("Conservation planning solution:");
        System.out.println("  Nb PUs: " + s.getIntVal(nbPUs));
        System.out.println("  Occurrences: ");
        System.out.println("    Tree A: " + s.getIntVal(occTreeA));
        System.out.println("    Tree B: " + s.getIntVal(occTreeB));
        System.out.println("    Tree C: " + s.getIntVal(occTreeC));
        System.out.println("    Tree D: " + s.getIntVal(occTreeD));
        System.out.println("    Fern: " + s.getIntVal(occFern));
        System.out.println("    Bird: " + s.getIntVal(occBird));
        System.out.println("    Gecko: " + s.getIntVal(occGecko));
        System.out.println("  - - - - - - - - - -");
        for (int row = 0; row < Data.HEIGHT; row++) {
            String l = "| ";
            for (int col = 0; col < Data.WIDTH; col++) {
                int v = s.getIntVal(selected[getIndexFromRowCol(row, col)]);
                l += v + " ";
            }
            System.out.println(l + "|");
        }
        System.out.println("  - - - - - - - - - -");
    }

    private int getIndexFromRowCol(int row, int col) {
        return row * Data.WIDTH + col;
    }

    public void validateBaseModel(Solution s) throws AssertionError {
        int countOccTreeA = IntStream.range(0, Data.N).map(i -> s.getIntVal(selected[i]) * Data.OCC_TREE_A[i]).sum();
        if (countOccTreeA != s.getIntVal(occTreeA)) {
            throw new AssertionError("The number of occurrences of tree A is not correct");
        }
        int countOccTreeB = IntStream.range(0, Data.N).map(i -> s.getIntVal(selected[i]) * Data.OCC_TREE_B[i]).sum();
        if (countOccTreeB != s.getIntVal(occTreeB)) {
            throw new AssertionError("The number of occurrences of tree B is not correct");
        }
        int countOccTreeC = IntStream.range(0, Data.N).map(i -> s.getIntVal(selected[i]) * Data.OCC_TREE_C[i]).sum();
        if (countOccTreeC != s.getIntVal(occTreeC)) {
            throw new AssertionError("The number of occurrences of tree C is not correct");
        }
        int countOccTreeD = IntStream.range(0, Data.N).map(i -> s.getIntVal(selected[i]) * Data.OCC_TREE_D[i]).sum();
        if (countOccTreeD != s.getIntVal(occTreeD)) {
            throw new AssertionError("The number of occurrences of tree D is not correct");
        }
        int countOccFern = IntStream.range(0, Data.N).map(i -> s.getIntVal(selected[i]) * Data.OCC_FERN[i]).sum();
        if (countOccFern != s.getIntVal(occFern)) {
            throw new AssertionError("The number of occurrences of fern is not correct");
        }
        int countOccBird = IntStream.range(0, Data.N).map(i -> s.getIntVal(selected[i]) * Data.OCC_BIRD[i]).sum();
        if (countOccBird != s.getIntVal(occBird)) {
            throw new AssertionError("The number of occurrences of bird is not correct");
        }
        int countOccGecko = IntStream.range(0, Data.N).map(i -> s.getIntVal(selected[i]) * Data.OCC_GECKO[i]).sum();
        if (countOccGecko != s.getIntVal(occGecko)) {
            throw new AssertionError("The number of occurrences of gecko is not correct");
        }
    }

    public void validateCoveringSetPlants(Solution s, int minOcc) throws AssertionError {
        if (s.getIntVal(occTreeA) < minOcc) {
            throw new AssertionError("Not enough occurrences of Tree A");
        }
        if (s.getIntVal(occTreeB) < minOcc) {
            throw new AssertionError("Not enough occurrences of Tree B");
        }
        if (s.getIntVal(occTreeC) < minOcc) {
            throw new AssertionError("Not enough occurrences of Tree C");
        }
        if (s.getIntVal(occTreeD) < minOcc) {
            throw new AssertionError("Not enough occurrences of Tree D");
        }
        if (s.getIntVal(occFern) < minOcc) {
            throw new AssertionError("Not enough occurrences of Fern");
        }
    }

    public void validateCoveringSetAnimals(Solution s, int minOcc) throws AssertionError {
        if (s.getIntVal(occBird) < minOcc) {
            throw new AssertionError("Not enough occurrences of Bird");
        }
        if (s.getIntVal(occGecko) <  minOcc) {
            throw new AssertionError("Not enough occurrences of Gecko");
        }
    }

    public void validateCoveringSet(Solution s, int minOcc) throws AssertionError {
        validateCoveringSetPlants(s, minOcc);
        validateCoveringSetAnimals(s, minOcc);
    }

    private UndirectedGraphVar makeGraphVar() {
        UndirectedGraph LB = GraphFactory.makeStoredUndirectedGraph(
                model, Data.N, SetType.BIPARTITESET, SetType.BIPARTITESET
        );
        UndirectedGraph UB = GraphFactory.makeStoredAllNodesUndirectedGraph(model, Data.N, SetType.BIPARTITESET, SetType.BIPARTITESET, false);
        for (int i = 0; i < Data.N; i++) {
            for (int j : getNeighbors(i)) {
                UB.addEdge(i, j);
            }
        }
        UndirectedGraphVar g = model.nodeInducedGraphVar("G", LB, UB);
        model.nodesChanneling(g, selected).post();
        return g;
    }

    private int[] getNeighbors(int cellIndex) {
        int left = cellIndex % Data.WIDTH != 0 ? cellIndex - 1 : -1;
        int right = (cellIndex + 1) % Data.WIDTH != 0 ? cellIndex + 1 : -1;
        int top = cellIndex >= Data.WIDTH ? cellIndex - Data.WIDTH : -1;
        int bottom = cellIndex < Data.WIDTH * (Data.HEIGHT - 1) ? cellIndex + Data.WIDTH : -1;
        return IntStream.of(left, right, top, bottom).filter(x -> x >= 0).toArray();
    }

}
