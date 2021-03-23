import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Samoilenko Marta, Gaiduchek Maxim (a bit)
 */

public class Problem2 {

    private static final String INPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/2021/inputs/input-seats-2d11.txt";
    private static final String OUTPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/2021/output.txt";

    public static void main(String[] args) throws IOException {
        //new FileOutputStream(OUTPUT_PATH).write(result().getBytes());
        System.out.println(result());
    }

    private static String result() throws FileNotFoundException {
        Scanner scan = new Scanner(new FileInputStream(INPUT_PATH));
        //Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int test = 1; test <= T; test++) {
            int N = scan.nextInt();
            int K = scan.nextInt();
            List<Integer> seatOfCurrentReplier = new ArrayList<>(N);
            List<Integer> nextSeat = new ArrayList<>(N);

            for (int j = 0; j < N; j++) {
                seatOfCurrentReplier.add(j);
                nextSeat.add(scan.nextInt());
            }

            System.out.println(N + " " + K);
            System.out.println(nextSeat);

            for (int i = 0; i < K; i++) {
                for (int j = 0; j < N; j++) {
                    seatOfCurrentReplier.set(j, nextSeat.get(seatOfCurrentReplier.get(j)));
                }
                System.out.println(seatOfCurrentReplier);
            }

            StringBuilder s = new StringBuilder();

            seatOfCurrentReplier.forEach(i -> s.append(" ").append(i));
            sb.append("Case #").append(test).append(":").append(s.toString()).append("\n");
        }

        return sb.toString();
    }
}