import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Gaiduchek Maxim
 */

public class Problem3 {

    private static final String INPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/training/inputs/input-antivirus-2211.txt";
    private static final String OUTPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/training/output.txt";

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
            String[] names = new String[4];

            int minIndex = 0, min = 1002;
            for (int i = 0; i < 4; i++) {
                int num = scan.nextInt();

                if (num <= min) {
                    min = num;
                    minIndex = i;
                }
            }

            int length = scan.nextInt();
            for (int i = 0; i < 4; i++) names[i] = scan.next();

            String str = names[minIndex];
            for (int i = 0; i < str.length() - length + 1; i++) {
                String sub = str.substring(i, length + i);

                if (contains(names, sub)) {
                    sb.append("Case #").append(test).append(": ")
                            .append(names[0].indexOf(sub)).append(" ")
                            .append(names[1].indexOf(sub)).append(" ")
                            .append(names[2].indexOf(sub)).append(" ")
                            .append(names[3].indexOf(sub)).append(" ")
                            .append("\n");
                    break;
                }
            }
        }

        return sb.toString();
    }

    private static boolean contains(String[] arr, String str) {
        return arr[0].contains(str) && arr[1].contains(str) && arr[2].contains(str) && arr[3].contains(str);
    }
}