import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Gaiduchek Maxim
 */

public class Problem1 {

    private static final String INPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/2019/inputs/input-teleportation-8eb1.txt";
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
            long count = 0;
            int gridX = scan.nextInt(), gridY = scan.nextInt();
            int x = scan.nextInt(), y = scan.nextInt(); // qubit coords
            int N = scan.nextInt();
            List<Teleport> teleports = new ArrayList<>(N);

            for (int i = 0; i < N; i++) {
                int xin = scan.nextInt(), yin = scan.nextInt();
                int xout = scan.nextInt(), yout = scan.nextInt();

                teleports.add(new Teleport(xin, yin, xout, yout));
            }

            while (teleports.size() != 0) {
                Teleport teleport = getAimTeleport(teleports, x, y);

                count += calcDist(teleport, x, y);
                x = teleport.xout;
                y = teleport.yout;

                teleports.remove(teleport);
            }

            sb.append("Case #").append(test).append(": ").append(count % 100003).append("\n");
        }

        return sb.toString();
    }

    private static int calcDist(Teleport teleport, int x, int y) {
        return Math.abs(teleport.xin - x) + Math.abs(teleport.yin - y);
    }

    private static Teleport getAimTeleport(List<Teleport> teleports, int x, int y) {
        Teleport min = null;
        int minDist = 0;

        for (int i = 0; i < teleports.size(); i++) {
            Teleport teleport = teleports.get(i);
            int dist = calcDist(teleport, x, y);

            if (i == 0) {
                min = teleport;
                minDist = dist;
            } else {
                if (dist < minDist) {
                    min = teleport;
                    minDist = dist;
                } else if (dist == minDist) {
                    if (teleport.xin < min.xin || (teleport.xin == min.xin && teleport.yin < min.yin)) {
                        min = teleport;
                        minDist = dist;
                    }
                }
            }
        }

        return min;
    }

    static class Teleport {

        int xin, yin;
        int xout, yout;

        private Teleport(int xin, int yin, int xout, int yout) {
            this.xin = xin;
            this.yin = yin;
            this.xout = xout;
            this.yout = yout;
        }

        @Override
        public String toString() {
            return "Teleport{" +
                    "xin=" + xin +
                    ", yin=" + yin +
                    ", xout=" + xout +
                    ", yout=" + yout +
                    '}';
        }
    }
}