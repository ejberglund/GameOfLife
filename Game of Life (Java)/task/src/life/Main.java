package life;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);



        int n = sc.nextInt(); // universe size
        long s = sc.nextInt(); // seed
        int m = sc.nextInt(); // number of generations to run

        Random random = new Random(s);

        String[][] previousUniverse = new String[n][n];
        String[][] nextUniverse = new String[n][n];

        // generate seed universe
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(random.nextBoolean()){
                    previousUniverse[i][j] = "O";
                } else {
                    previousUniverse[i][j] = " ";
                }
            }
        }

        // cycle through all generations and update arrays accordingly
        for (int g = 0; g < m; g++) {
            // generate next universe based off given algorithm
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    nextUniverse[i][j] = Algorithm.cellLives(previousUniverse, i, j, n);
                }
            }

            // use nextUniverse as input for next iteration
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    previousUniverse[i][j] = nextUniverse[i][j];
                }
            }
        }

        // account for case where m = 0 and generation loop doesn't run
        String[][] output = nextUniverse;
        if (m==0) output = previousUniverse;

        //print last generation
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(output[i][j]);
            }
            if (i<n-1) System.out.println("");
        }
    }
}

class Algorithm {

    static String cellLives(String[][] input, int i, int j, int n) {

        int numNeighbors = 0;
        String result = " ";

        // used to find coordinates of neighbors
        int N = Math.floorMod(i - 1, n);
        int E = Math.floorMod(j + 1, n);
        int S = Math.floorMod(i + 1, n);
        int W = Math.floorMod(j - 1, n);

        // count the number of living neighbors
        if (input[N][j].equals("O")) numNeighbors++; // North
        if (input[N][E].equals("O")) numNeighbors++; // Northeast
        if (input[i][E].equals("O")) numNeighbors++; // East
        if (input[S][E].equals("O")) numNeighbors++; // Southeast
        if (input[S][j].equals("O")) numNeighbors++; // South
        if (input[S][W].equals("O")) numNeighbors++; // Southwest
        if (input[i][W].equals("O")) numNeighbors++; // West
        if (input[N][W].equals("O")) numNeighbors++; // Northwest

        if (input[i][j].equals("O") && (numNeighbors == 2 || numNeighbors == 3)) {
            result = "O";
        }
        else if (input[i][j].equals(" ") && numNeighbors == 3) {
            result = "O";
        }
        return result;
    }
}

