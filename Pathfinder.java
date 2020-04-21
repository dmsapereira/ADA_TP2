import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Pathfinder {

    public static final int STEPS_PER_LINE = 80;
    public static final int MAX_POS = 250000;
    private Map<Integer, Set<Integer>> nodes;
    private int lastNode;

    
    public Pathfinder(){
        this.nodes = new HashMap<>();
        this.lastNode = 0;
        this.nodes.put(0, new HashSet<>());
    }
    
    private int getPosition(int x, int y){
        return (x * MAX_POS + y);
    }

    private int bfsExplore(){
        Set<Integer> found = new HashSet<>();
        List<Integer> waiting = new LinkedList<>();
        waiting.add(this.lastNode);
        found.add(this.lastNode);

        int steps = 1;

        do{
            int node = waiting.remove(0);

            if(node == 0)
                return steps;

            for(int outgoing : this.nodes.get(node)){
                if(!found.contains(outgoing)){
                    waiting.add(outgoing);
                    found.add(outgoing);
                }
            }
        }while(!waiting.isEmpty());

        return -1;
    }

    public void nextStep(char direction){

        int nextX = this.lastNode / MAX_POS;
        int nextY = this.lastNode % MAX_POS;

        switch(direction){
            case 'N':
                nextY++;
                break;
            case 'E':
                nextX++;
                break;
            case 'S':
                nextY--;
                break;
            case 'W':
                nextX--;
                break;
        }
        int nextNode = this.getPosition(nextX, nextY);

        Set<Integer> connected;

        if(!this.nodes.containsKey(nextNode)){
            connected = new HashSet<>();
            this.nodes.put(nextNode, connected);
        }else
            connected = this.nodes.get(nextNode);

        this.nodes.get(this.lastNode).add(nextNode);

        connected.add(this.lastNode);

        this.lastNode = nextNode; 
    }
}