import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static Pathfinder readPaths(BufferedReader in) throws IOException {
        int nLines = Integer.parseInt(in.readLine());

        Pathfinder pf = new Pathfinder(nLines);

        for(int i = 0; i < nLines; i++){      
            String line = in.readLine();
            
            for(char c : line.toCharArray())
                pf.nextStep(c);
        }

        return pf;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Pathfinder pf = readPaths(in);

    }
    
}