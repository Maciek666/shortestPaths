package com.Maciek;

import com.Maciek.matrix.DijktrsaMatrix;
import com.Maciek.matrix.Graph;
import com.Maciek.service.GraphGenerator;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final int N_TEST = 100;

    static double[] proc = {0.25, 0.5, 0.75, 1};
    static int[] vert = {10, 20, 50, 80, 100, 200};

    /**
     * before use in Graph in addEdge comment turnBack
     */
    public static void generateGraph() {
        for (int i = 0; i < vert.length; i++) {
            for (int j = 0; j < proc.length; j++) {
                GraphGenerator.createDiGraphs(vert[i], proc[j]);
            }
        }
    }

    public static void result() throws IOException {
        double[][] pomiary = new double[3][vert.length * proc.length];
        ShortestPath[] sp = new DijktrsaMatrix[N_TEST];
        int iter = 0;
        for (int v = 0; v < vert.length; v++) {
            for (int p = 0; p < proc.length; p++) {

                for (int i = 0; i < sp.length; i++) {
                    File file = new File("D:\\4sem\\PAMSI\\JELEN\\PROJEKT2\\shortestPaths\\src\\" +
                            "com\\Maciek\\graphs\\graph_no" + (i + 1) + "_v=" + vert[v] + "_%=" + proc[p] + ".txt");
                    Scanner scanner = new Scanner(file);
                    Graph G = new Graph(scanner);
                    sp[i] = new DijktrsaMatrix(G, 0);
                }

                double czasRozpoczecia = System.currentTimeMillis();

                for (int i = 0; i < N_TEST; i++)
                    sp[i].getMinDistance(0);

                double czasZakonczenia = System.currentTimeMillis();
                double czasTrwania = czasZakonczenia - czasRozpoczecia;
                System.out.println(czasTrwania);
                pomiary[0][iter] = vert[v];
                pomiary[1][iter] = proc[p];
                pomiary[2][iter] = (double) czasTrwania;
                iter++;
                System.gc();
            }
        }
        for (int i = 0; i < vert.length * proc.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(pomiary[j][i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //generateGraph();
        try {
            result();
        } catch (IOException e) {
            System.out.println("IOE");
        }

    }
}
