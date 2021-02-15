import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author Gaiduchek Maxim and Koliakova Margarita
 */

public class Problem3 {

    private static final String INPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/2020/inputs/input-scheduler-a91e.txt";
    private static final String OUTPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/2020/output.txt";

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
            int N = scan.nextInt(), K = scan.nextInt(), M = scan.nextInt();
            List<Server> servers = new ArrayList<>();

            for (int i = 0; i < N; i++) servers.add(new Server(scan.nextInt(), scan.nextInt()));

            int average = M / K, remainder = M - average * K;
            System.out.println(N + " " + K + " " + M + " " + average + " " + remainder);
            System.out.println(servers);

            if (K > 0) {
                servers.sort(Comparator.comparingInt(s0 -> s0.getTime(average)));
                System.out.println(servers);

                List<Server> bestServers = servers.subList(0, K);
                System.out.println(bestServers);

                for (Server server : bestServers) server.addTime(average);
            }

            if (remainder > 0) {
                if (K > 0) {
                    servers.sort(Comparator.comparingInt(s0 -> s0.S));
                } else {
                    servers.sort(Comparator.comparingInt(s0 -> s0.getTime(1)));
                }
                System.out.println(servers);

                List<Server> bestServers = servers.subList(0, remainder);
                System.out.println(bestServers);

                if (K > 0) {
                    for (Server server : bestServers) server.addTime(1);
                } else {
                    for (Server server : bestServers) server.addSpeedTime(1);
                }
            }

            int totalTime = servers.get(0).executingTime;
            for (int i = 1; i < servers.size(); i++) {
                Server server = servers.get(i);
                int time = server.executingTime;

                if (time > totalTime) totalTime = time;
            }

            sb.append("Case #").append(test).append(": ").append(totalTime).append("\n");
        }

        return sb.toString();
    }

    static class Server {

        final int P, S;
        int executingTime = 0;

        public Server(int p, int s) {
            P = p;
            S = s;
        }

        int getTime(int tasks) {
            return P + getSpeedTime(tasks);
        }

        int getSpeedTime(int tasks) {
            return S * tasks;
        }

        void addTime(int tasks) {
            executingTime += getTime(tasks);
        }

        void addSpeedTime(int tasks) {
            executingTime += getSpeedTime(tasks);
        }

        @Override
        public String toString() {
            return "Server{" +
                    "P=" + P +
                    ", S=" + S +
                    '}';
        }
    }
}