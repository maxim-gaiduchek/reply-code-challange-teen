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

public class Problem5 {

    private static final String INPUT_PATH = "C:/Users/Xiaomi/Desktop/Projects/Reply Code Challenge/2021/inputs/input-kits-f3aa.txt";
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
            int M = scan.nextInt();
            int Z = scan.nextInt();
            int r = 0, z = 0, n = 0;
            List<Node> nodes = new ArrayList<>();

            for (int i = 0; i < N; i++) nodes.add(new Node(i));
            for (int i = 0; i < M; i++) {
                int start = scan.nextInt(), finish = scan.nextInt();

                nodes.get(start).edges.add(new Edge(nodes.get(start), nodes.get(finish)));
            }

            for (int rStart = 0; rStart < Z; rStart++) {
                for (int zStart = Z; zStart < N; zStart++) {
                    int rNodeCount = rStart, zNodeCount = zStart;

                    while (true) {
                        // r moves

                        Node rNode = nodes.get(rNodeCount);
                        boolean flag = false;

                        for (Edge edge : rNode.edges) {
                            if (rNode.edges.size() == 1 && edge.finish.id == zNodeCount) {
                                z++;
                                flag = true;
                                break;
                            }
                        }

                        if (flag) break;

                        // z moves

                        Node zNode = nodes.get(zNodeCount);

                        for (Edge edge : zNode.edges) {
                            if (zNode.edges.size() == 1 && edge.finish.id == rNodeCount) {
                                r++;
                                flag = true;
                                break;
                            }
                        }

                        if (flag) break;
                    }
                }
            }

            sb.append("Case #").append(test).append(": ").append(r).append(" ").append(z).append(" ").append(n).append(" ").append("\n");
        }

        return sb.toString();
    }

    private static class Node {

        private final int id;
        private final List<Edge> edges = new ArrayList<>();

        private Node(int id) {
            this.id = id;
        }
    }

    private static class Edge {

        private final Node start, finish;

        private Edge(Node start, Node finish) {
            this.start = start;
            this.finish = finish;
        }
    }
}
