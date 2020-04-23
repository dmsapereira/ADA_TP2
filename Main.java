import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static void readPaths(BufferedReader in, Pathfinder pf) throws IOException {
        int nLines = Integer.parseInt(in.readLine());

        for(int i = 0; i < nLines; i++){      
            String line = in.readLine();
            
            for(char c : line.toCharArray())
                pf.nextStep(c);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Pathfinder pf = new Pathfinder();
        readPaths(in, pf);
        long start = System.currentTimeMillis();
        System.out.println(pf.solve());
        System.out.println("Solved in " + (System.currentTimeMillis() - start) +"ms");
    }
    
}