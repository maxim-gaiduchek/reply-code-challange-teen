import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author Gaiduchek Maxim
 */

public class Problem2 {

    private static final String INPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/2019/inputs/input-riceboard-d63d.txt";
    private static final String OUTPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/2019/output.txt";

    public static void main(String[] args) throws IOException {
        //new FileOutputStream(OUTPUT_PATH).write(result().getBytes());
        System.out.println(result());
    }

    private static String result() throws FileNotFoundException {
        Scanner scan = new Scanner(new FileInputStream(INPUT_PATH));
        int T = scan.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int test = 1; test <= T; test++) {
            BigInteger R = BigInteger.valueOf(scan.nextLong());
            BigInteger N = BigInteger.valueOf(scan.nextLong());
            BigInteger M = BigInteger.valueOf(scan.nextLong());
            System.out.println(R + " " + N + " " + M);
            BigInteger grainsCounter = BigInteger.valueOf(1);
            BigInteger lastMultiply = BigInteger.valueOf(1);

            //System.out.println(lastMultiply);
            for (BigInteger i = BigInteger.ONE; i.compareTo(N.multiply(N)) < 0; i = i.add(BigInteger.ONE)) {
                lastMultiply = lastMultiply.multiply(R);
                grainsCounter = grainsCounter.add(lastMultiply.mod(M));
                //System.out.println(lastMultiply + " " + lastMultiply.mod(M) + " " + grainsCounter);
            }

            sb.append("Case #").append(test).append(": ").append(grainsCounter.mod(M)).append("\n");
        }

        return sb.toString();
    }
}
