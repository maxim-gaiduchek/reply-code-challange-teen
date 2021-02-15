import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Gaiduchek Maxim
 */

public class Problem2 {

    private static final String INPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/training/week 1/inputs/input-xray-e1ca.txt";
    private static final String OUTPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/training/week 1/output.txt";

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
            int[] bone = new int[N];
            int count = 0;

            for (int i = 0; i < N; i++) bone[i] = scan.nextInt();

            while (!isZero(bone)) {
                boolean isLastZero = bone[0] == 0;

                for (int i = 0; i < N; i++) {
                    if (bone[i] == 0) {
                        if (!isLastZero) {
                            count++;
                            isLastZero = true;
                        }
                    } else {
                        bone[i]--;
                        isLastZero = false;
                    }
                }

                if (!isLastZero) count++;
            }

            sb.append("Case #").append(test).append(": ").append(count).append("\n");
        }

        return sb.toString();
    }

    private static boolean isZero(int[] arr) {
        for (int i : arr) {
            if (i != 0) return false;
        }
        return true;
    }
}