import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static void readPaths(BufferedReader in, Pathfinder pf, int nLines) throws IOException {
        for(int i = 0; i < nLines; i++){      
            String line = in.readLine();
            
            for(char c : line.toCharArray())
                pf.nextStep(c);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int nLines = Integer.parseInt(in.readLine());
        Pathfinder pf = new Pathfinder(nLines);
        readPaths(in, pf, nLines);
        System.out.println(pf.solve());
    }
    
}