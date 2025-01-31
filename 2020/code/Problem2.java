import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author Gaiduchek Maxim, Samoilenko Marta and Chigarev Dmitrii
 */

public class Problem2 {

    private static final String INPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/2020/inputs/input-server-64b1.txt";
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
            // S - P -> min
            int N = scan.nextInt();
            int P = scan.nextInt();
            ArrayList<Integer> arr = new ArrayList<>(N);

            for (int i = 0; i < N; i++) arr.add(scan.nextInt());

            int min = arr.stream().min(Integer::compare).orElse(0);
            int max = arr.stream().max(Integer::compare).orElse(0);
            int minLength = P / max + 1;
            int maxLength = P / min + 1;

            int l = 0, r = N - 1;
            long minDelta = Integer.MAX_VALUE;

            for (int L = 0; L < N - 1; L++) {
                boolean toBreak = false;
                long S = arr.subList(L, Math.min(L + minLength - 1, N)).stream().mapToInt(i -> i).sum();

                for (int R = L + minLength; R <= L + maxLength && R <= N; R++) {
                    S += arr.get(R - 1);
                    long delta = S - P;

                    if (delta > 0) {
                        if (delta < minDelta) {
                            l = L;
                            r = R - 1;
                            minDelta = delta;
                        } else if (delta == minDelta) {
                            if (l > L || (l == L && r > R)) {
                                l = L;
                                r = R - 1;
                            }
                        }
                        break;
                    } else if (delta == 0) {
                        l = L;
                        r = R - 1;
                        toBreak = true;
                        break;
                    }
                }

                if (toBreak) break;
            }

            sb.append("Case #").append(test).append(": ").append(l).append(" ").append(r).append("\n");
        }

        return sb.toString();
    }
}