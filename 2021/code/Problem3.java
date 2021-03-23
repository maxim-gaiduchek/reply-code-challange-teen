import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Dobrodeev Ivan, Gaiduchek Maxim
 */

public class Problem3 {

    private static final String INPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/2021/inputs/input-antennas-927c.txt";
    private static final String OUTPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/2021/output.txt";

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
            int N = scan.nextInt();
            int K = scan.nextInt();
            List<Long> A = new ArrayList<>(N);
            List<Long> B = new ArrayList<>(N);
            long min, max;

            for (int i = 0; i < N; i++) A.add(scan.nextLong());
            for (int i = 0; i < N; i++) B.add(scan.nextLong());

            // min

            List<Long> minMultiplying = new ArrayList<>(N);

            A.sort((o1, o2) -> -Long.compare(o1, o2));
            B.sort(Long::compare);

            for (int i = 0; i < N; i++) minMultiplying.add(A.get(i) * B.get(i));

            min = minMultiplying.stream()
                    .sorted(Long::compare)
                    .limit(K)
                    .mapToLong(l -> l)
                    .sum();

            // max

            List<Long> maxMultiplying = new ArrayList<>(N);

            A.sort((o1, o2) -> -Long.compare(o1, o2));
            B.sort((o1, o2) -> -Long.compare(o1, o2));

            for (int i = 0; i < N; i++) maxMultiplying.add(A.get(i) * B.get(i));

            max = maxMultiplying.stream()
                    .sorted((o1, o2) -> -Long.compare(o1, o2))
                    .limit(K)
                    .mapToLong(l -> l)
                    .sum();

            sb.append("Case #").append(test).append(": ").append(min).append(" ").append(max).append("\n");
        }

        return sb.toString();
    }
}
