package teen.year2019;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Gaiduchek Maxim
 */

public class Problem5 {

    private static final String INPUT_PATH = "C:/Users/Xiaomi/Desktop/input-password-10ed.txt";
    private static final String OUTPUT_PATH = "C:/Users/Xiaomi/Desktop/output.txt";

    public static void main(String[] args) throws IOException {
        String res = result();

        //new FileOutputStream(OUTPUT_PATH).write(result().getBytes());
        new FileOutputStream(OUTPUT_PATH).write(res.getBytes());
        System.out.println("\n" + res + "\n");
    }

    private static String result() throws FileNotFoundException {
        Scanner scan = new Scanner(new FileInputStream(INPUT_PATH));
        //Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int test = 1; test <= T; test++) {
            double count = 0.0D;
            int N = scan.nextInt(), M = scan.nextInt();
            String S = scan.next();

            if (N < M) {
                count = Math.pow(2, N);
            } else if (N == M) {
                count = Math.pow(2, N) - 1;
            } else {
                try {
                /*long dS = Long.parseLong(S, 2);
                count = dS;
                //System.out.println(dS);
                //System.out.println(Long.parseLong("1".repeat(N + 1), 2));
                //System.exit(2);

                for (long i = dS + 1; i < Long.MAX_VALUE; i++) {
                    String binary = Long.toBinaryString(i);

                    if (binary.length() < N) binary = "0".repeat(N - binary.length()).concat(binary);
                    else if (binary.length() > N) break;

                    if (!binary.contains(S)) count++;
                }*/

                    Set<String> set = new HashSet<>();

                    for (int l = 0; l <= N - M; l++) {
                        int r = N - M - l;

                        boolean toBreak = false;
                        for (long lS = 0; lS < Long.MAX_VALUE; lS++) {
                            String lStr = Long.toBinaryString(lS);

                            if (l != 0) {
                                if (lStr.length() < l) lStr = "0".repeat(l - lStr.length()).concat(lStr);
                                else if (lStr.length() > l) break;
                            } else lStr = "";

                            if (r != 0) {
                                for (long rS = 0; rS < Long.MAX_VALUE; rS++) {
                                    String rStr = Long.toBinaryString(rS);

                                    if (rStr.length() < r) rStr = "0".repeat(r - rStr.length()).concat(rStr);
                                    else if (rStr.length() > r) {
                                        toBreak = true;
                                        break;
                                    }

                                    String res = lStr + S + rStr;
                                    set.add(res);
                                    //System.out.println(set + " " + res + "=" + (lStr.equals("") ? "\"\"" : lStr) + "+" + S + "+" + rStr);
                                }
                            } else {
                                String res = lStr + S;

                                set.add(res);
                                //System.out.println(set + " " + res + "=" + lStr + "+" + S + "+" + "\"\"");
                            }

                            if (toBreak && l == 0) break;
                        }
                    }
                    count = Math.pow(2, N) - set.size();
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                }
            }

            sb.append("Case #").append(test).append(": ").append((int) count % 1000000007).append("\n");
        }

        return sb.toString();
    }
}
