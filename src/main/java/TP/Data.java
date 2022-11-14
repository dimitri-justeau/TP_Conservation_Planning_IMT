package TP;

import java.util.Arrays;

public class Data {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    public static final int N = WIDTH * HEIGHT;

    public static final int[] OCC_TREE_A = new int[] {
            0,1,0,0,0,0,0,0,1,1,
            0,0,0,0,0,0,0,0,0,0,
            0,1,1,1,1,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,1,0,0,0,0,
            0,0,0,0,0,0,1,1,0,0,
            0,0,0,0,0,0,0,0,1,1,
            0,0,0,0,0,0,0,1,0,0,
            0,0,0,1,0,1,0,0,0,1,
    };

    public static final int[] OCC_TREE_B = new int[] {
            0,0,0,1,0,0,0,0,0,0,
            0,0,0,0,0,0,1,1,1,0,
            0,0,0,0,1,0,1,0,0,0,
            0,0,0,0,0,0,0,1,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,1,0,0,0,0,0,0,
            0,0,0,0,0,1,0,0,1,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
    };

    public static final int[] OCC_TREE_C = new int[] {
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,1,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,1,
            0,0,0,0,0,0,0,0,1,1,
            0,1,0,0,0,0,0,0,1,1,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,1,0,0,0,0,0,0,0,
            0,1,1,0,1,0,0,0,0,0,
    };

    public static final int[] OCC_TREE_D = new int[] {
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,1,0,0,0,0,0,
            0,0,1,0,1,1,0,1,0,0,
            1,0,1,1,1,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            1,1,1,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,1,0,0,0,0,0,0,0,0,
            0,0,0,1,0,0,0,0,0,0,
    };

    public static final int[] OCC_FERN = new int[] {
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,1,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,1,0,1,1,1,1,0,0,0,
            0,0,0,0,0,0,0,1,0,0,
            0,0,0,1,0,0,0,0,0,0,
    };

    public static final int[] OCC_BIRD = new int[] {
            0,1,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,1,0,0,0,0,0,1,0,0,
            0,0,0,1,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,1,0,0,0,0,0,
            0,1,0,0,0,0,0,0,0,0,
            0,0,0,0,1,0,1,0,0,0,
    };

    public static final int[] OCC_GECKO = new int[] {
            0,0,0,0,0,0,0,0,0,1,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,1,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,1,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,1,0,0,0,
    };

    public static final int NB_OCC_TREE_A = Arrays.stream(Data.OCC_TREE_A).sum();
    public static final int NB_OCC_TREE_B = Arrays.stream(Data.OCC_TREE_B).sum();
    public static final int NB_OCC_TREE_C = Arrays.stream(Data.OCC_TREE_C).sum();
    public static final int NB_OCC_TREE_D = Arrays.stream(Data.OCC_TREE_D).sum();
    public static final int NB_OCC_FERN = Arrays.stream(Data.OCC_TREE_D).sum();

    public static final int NB_OCC_BIRD = Arrays.stream(Data.OCC_BIRD).sum();
    public static final int NB_OCC_GECKO = Arrays.stream(Data.OCC_GECKO).sum();
}
