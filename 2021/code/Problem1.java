import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Gaiduchek Maxim
 */

public class Problem1 {

    private static final String INPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/2021/inputs/input-kits-f3aa.txt";
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
            List<Integer> G = new ArrayList<>(N);

            for (int i = 0; i < N; i++) G.add(scan.nextInt());

            int min = G.stream().min(Integer::compareTo).orElse(-1);
            int counter = 1;

            for (float i = 2; i <= min; i++) {
                boolean isBroken = false;

                for (int gadget : G) {
                    if ((int) (gadget / i) != (gadget / i)) {
                        isBroken = true;
                        break;
                    }
                }
                if (!isBroken) counter++;
            }

            sb.append("Case #").append(test).append(": ").append(counter).append("\n");
        }

        return sb.toString();
    }
}