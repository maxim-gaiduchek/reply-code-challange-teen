import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author Gaiduchek Maxim
 */

public class Problem3 {

    private static final String INPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/2019/inputs/input-t9-bcb4.txt";
    private static final String OUTPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/2019/output.txt";

    public static void main(String[] args) throws IOException {
        new FileOutputStream(OUTPUT_PATH).write(result().getBytes());
        //System.out.println(result());
    }

    private static String result() throws FileNotFoundException {
        Scanner scan = new Scanner(new FileInputStream(INPUT_PATH));
        //Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int test = 1; test <= T; test++) {
            int L = scan.nextInt(), K = scan.nextInt();
            int[][] P = new int[26][26];

            for (int[] row : P) {
                for (int i = 0; i < 26; i++) {
                    row[i] = scan.nextInt();
                }
            }

            String clicks = scan.next();
            List<Combination> combinations = new ArrayList<>();

            fill(combinations, clicks, 0, "", P);

            combinations.sort((o1, o2) -> o1.score != o2.score ? o1.score - o2.score : o1.comb.compareTo(o2.comb));

            sb.append("Case #").append(test).append(": ").append(combinations.get(K - 1).comb).append("\n");
        }

        return sb.toString();
    }

    private static void fill(List<Combination> combinations, String clicks, int click, String comb, int[][] P) {
        if (click < clicks.length() - 1) {
            char[] chars;

            switch (clicks.toCharArray()[click]) {
                case '2' -> chars = new char[]{'A', 'B', 'C'};
                case '3' -> chars = new char[]{'D', 'E', 'F'};
                case '4' -> chars = new char[]{'G', 'H', 'I'};
                case '5' -> chars = new char[]{'J', 'K', 'L'};
                case '6' -> chars = new char[]{'M', 'N', 'O'};
                case '7' -> chars = new char[]{'P', 'Q', 'R', 'S'};
                case '8' -> chars = new char[]{'T', 'U', 'V'};
                case '9' -> chars = new char[]{'W', 'X', 'Y', 'Z'};
                default -> {
                    chars = new char[]{};
                    System.exit(1111111111);
                }
            }

            for (char ch : chars) {
                fill(combinations, clicks, click + 1, comb + ch, P);
            }
        } else {
            Combination combination = new Combination();

            combinations.add(new Combination());
        }
    }

    private static class Combination {

        private String comb = "";
        private int score = 0;

        void addChar(String ch, int points) {
            comb += ch;
            score += points;
        }
    }
}
