import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Pathfinder {

    public static final int STEPS_PER_LINE = 80;
    public static final int MAX_POS = 250001;
    private Map<Long, Integer> nodes;
    private List<Integer>[] connected;
    private long lastNode;
    private int counter, lastNodeIndex;

    public Pathfinder(int nLines){
        this.nodes = new HashMap<>();
        this.buildArray(nLines);
        this.counter = 0;
        this.lastNode = 0;
        this.lastNodeIndex = 0;
        this.nodes.put(0L, this.counter++);
    }

    @SuppressWarnings("unchecked")
    private void buildArray(int nLines){
        this.connected = new List[nLines * STEPS_PER_LINE + 1];

        for(int i = 0; i < this.connected.length; i++)
            this.connected[i] = new LinkedList<>();
    }

    public int solve(){
        boolean[] found = new boolean[this.nodes.size()];
        
        boolean listFlag = true;
        List<Integer> waiting1 = new LinkedList<>();
        List<Integer> waiting2 = new LinkedList<>();

        int steps = 0;
        
        waiting1.add(this.lastNodeIndex);
        found[this.lastNodeIndex] = true;

        do{
            int node = listFlag ? waiting1.remove(0) : waiting2.remove(0);  
            
            if(node == 0)
                return steps;
           
            for(int outgoing : this.connected[node]){
                

                if(!found[outgoing]){
                    if (listFlag)
                        waiting2.add(outgoing);
                    else 
                        waiting1.add(outgoing);
                    found[outgoing] = true;
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

        int nextNodeIndex;

        if(!this.nodes.containsKey(nextNode)){
            nextNodeIndex = this.counter++;
            this.nodes.put(nextNode, nextNodeIndex);
        }else 
            nextNodeIndex = this.nodes.get(nextNode);
        
        
            this.connected[this.lastNodeIndex].add(nextNodeIndex);
            this.connected[nextNodeIndex].add(this.lastNodeIndex);

        this.lastNode = nextNode; 
        this.lastNodeIndex = nextNodeIndex;
    }
}