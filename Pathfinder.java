import java.util.HashMap;
import java.util.Map;

import dataStructures.UnionFind;
import dataStructures.UnionFindInArray;

public class Pathfinder {

    public static final int STEPS_PER_LINE = 80;
    private Map<Integer, Node> nodes;
    private UnionFind union;

    public Pathfinder(int nLines){
        this.nodes = new HashMap<>();
        this.union = new UnionFindInArray(STEPS_PER_LINE * nLines);
    }


    public void nextStep(char direction){

    }
}