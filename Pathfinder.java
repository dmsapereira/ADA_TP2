import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    
    private int getCodedPosition(int x, int y){
        return (x * MAX_POS + y);
    }

    private int[] getPosition(int codedPosition){
        int[] position = new int[2];
        position[1] =  Math.floorMod(codedPosition, MAX_POS);
        position[0] = (codedPosition - position[1]) / MAX_POS;

        return position;
    }

    public int solve(){
        Set<Integer> found = new HashSet<>();
        List<Integer> waiting1 = new LinkedList<>();
        List<Integer> waiting2 = new LinkedList<>();
        boolean listFlag = true;
        waiting1.add(this.lastNode);
        found.add(this.lastNode);

        int steps = 0;

        do{
            int node = listFlag ? waiting1.remove(0) : waiting2.remove(0);

            int[] position = this.getPosition(node);

            System.out.println("Current node is " + position[0] + ":" + position[1]);

            if(node == 0)
                return steps;

            for(int outgoing : this.nodes.get(node)){
                if(!found.contains(outgoing)){
                    if (listFlag)
                        waiting2.add(outgoing);
                    else 
                        waiting1.add(outgoing);
                    found.add(outgoing);
                }
            }

            if(waiting1.isEmpty()){
                listFlag = false;
                steps++;
            }else if(waiting2.isEmpty()){
                listFlag = true;
                steps++;
            }
        }while(!waiting1.isEmpty() || !waiting2.isEmpty());

        return -1;
    }

    public void nextStep(char direction){

        int[] position = this.getPosition(this.lastNode);

        int nextY = position[1];
        int nextX = position[0];

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

        int nextNode = this.getCodedPosition(nextX, nextY);

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