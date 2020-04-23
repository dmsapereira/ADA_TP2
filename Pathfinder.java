import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Pathfinder {

    public static final int STEPS_PER_LINE = 80;
    public static final int MAX_POS = 250000;
    private Map<Long, Set<Long>> nodes;
    private long lastNode;

    
    public Pathfinder(){
        this.nodes = new HashMap<>();
        this.lastNode = 0;
        this.nodes.put(0L, new HashSet<>());
    }

    public int solve(){
        Set<Long> found = new HashSet<>();
        List<Long> waiting1 = new LinkedList<>();
        List<Long> waiting2 = new LinkedList<>();
        boolean listFlag = true;
        waiting1.add(this.lastNode);
        found.add(this.lastNode);

        int steps = 0;

        do{
            long node = listFlag ? waiting1.remove(0) : waiting2.remove(0);            
            
            for(long outgoing : this.nodes.get(node)){
                
                if(outgoing == 0)
                    return steps + 1;

                if(!found.contains(outgoing)){
                    if (listFlag)
                        waiting2.add(outgoing);
                    else 
                        waiting1.add(outgoing);
                    found.add(outgoing);
                }
            }

            if(listFlag && waiting1.isEmpty()){
                listFlag = false;
                steps++;
            }else if(!listFlag && waiting2.isEmpty()){
                listFlag = true;
                steps++;
            }
        }while(!waiting1.isEmpty() || !waiting2.isEmpty());

        return -1;
    }

    public void nextStep(char direction){

        long nextNode = this.lastNode;

        switch(direction){
            case 'N':
                nextNode++;
                break;
            case 'E':
                nextNode += MAX_POS;
                break;
            case 'S':
                nextNode--;
                break;
            case 'W':
                nextNode -= MAX_POS;
                break;
        }

        Set<Long> connected;

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