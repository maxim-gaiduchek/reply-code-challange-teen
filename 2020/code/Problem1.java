import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Gaiduchek Maxim, Viarvelskii Lev and Chigarev Dmitrii
 */

public class Problem1 {

    private static final String INPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/2020/inputs/input-scoreboard-5a62.txt";
    private static final String OUTPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/2020/output.txt";

    public static void main(String[] args) throws IOException {
        new FileOutputStream(OUTPUT_PATH).write(result().getBytes());
        //System.out.println(result());
    }

    private static String result() throws FileNotFoundException {
        Scanner scan = new Scanner(new FileInputStream(INPUT_PATH));
        int T = scan.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int test = 1; test <= T; test++) {
            int N = scan.nextInt();
            int L = scan.nextInt();
            List<Team> teams = new ArrayList<>(N);

            for (int i = 0; i < N; i++) {
                teams.add(new Team(i + 1));
            }

            for (int i = 0; i < L; i++) {
                int timestamp = scan.nextInt();
                int id = scan.nextInt();
                int problem = scan.nextInt();
                int input = scan.nextInt();
                int score = scan.nextInt();

                teams.get(id - 1).addToScore(problem, input, score, timestamp);
            }

            teams.sort((t0, t1) -> {
                int score0 = t0.getScore(), score1 = t1.getScore();

                if (score0 != score1) {
                    return score1 - score0;
                } else {
                    if (t0.timestamp != t1.timestamp) {
                        return t0.timestamp - t1.timestamp;
                    } else {
                        return t0.id - t1.id;
                    }
                }
            });

            sb.append("Case #").append(test).append(": ");
            teams.forEach((team) -> sb.append(team.id).append(" "));
            sb.append("\n");
        }

        return sb.toString();
    }

    static class Team {

        int id;
        int[][] scores = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        };
        int timestamp = 0;

        public Team(int id) {
            this.id = id;
        }

        void addToScore(int problem, int input, int scored, int timestamp) {
            if (scores[problem - 1][input - 1] == 0) {
                int score = 100 * input * scored;

                scores[problem - 1][input - 1] = score;

                if (score != 0) {
                    this.timestamp += timestamp;
                }
            }
        }

        int getScore() {
            return Arrays.stream(scores).mapToInt(arr -> Arrays.stream(arr).sum()).sum();
        }

        @Override
        public String toString() {
            return "Team{" +
                   "id=" + id +
                   ", scores=" + Arrays.deepToString(scores) + " = " + getScore() +
                   ", timestamp=" + timestamp +
                   '}';
        }
    }
}