import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author Gaiduchek Maxim and Koliakova Margarita
 */

public class Problem4 {

    private static final String INPUT_PATH = "C:/Users/Xiaomi/Desktop/input-ropchain-c7fb.txt";
    private static final String OUTPUT_PATH = "C:/Users/Xiaomi/Desktop/output.txt";

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
            String S = scan.next();
            String[] G = new String[N];

            for (int i = 0; i < N; i++) {
                G[i] = scan.next();
            }

            Set<String> gadgetsSet = new HashSet<>();
            for (String g : G) {
                for (int i = 0; i < g.length(); i++) {
                    gadgetsSet.add(g.substring(i));
                }
            }

            List<String> gadgets = new ArrayList<>(gadgetsSet);
            gadgets.sort((s0, s1) -> s1.length() - s0.length());

            Counter.method(S, gadgets);

            sb.append("Case #").append(test).append(": ").append(Counter.count == 0 ? -1 : Counter.count).append("\n");
            Counter.count = 0;
        }

        return sb.toString();
    }


    static class Counter {

        private static int count = 0;

        private static String method(String S, List<String> G) {
            if (S.equals("")) return "";
            for (String g : G) {
                if (S.startsWith(g)) {
                    count++;
                    if (method(S.substring(g.length()), G).length() == 0) return "";
                }
            }

            return "";
        }
    }
}