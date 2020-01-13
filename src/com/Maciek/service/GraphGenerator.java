package com.Maciek.service;

import com.Maciek.matrix.Graph;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * class can generate random graphs which always would be connected
 */
public class GraphGenerator {
    /**
     * Help function
     * @param graph
     * @param first
     * @param second
     * @param noConnected
     * @param random
     */
    private static void addTwoWay(Graph graph,int first,int second,List<Integer>[] noConnected,Random random){
        int indexFisrt;
        int indexSecond;
        //add to ways
        graph.addEdge(first,second,random.nextDouble());
        indexSecond=noConnected[first].indexOf(second);
        indexFisrt=noConnected[second].indexOf(first);
        noConnected[first].remove(indexSecond);
        noConnected[second].remove(indexFisrt);
    }
    public static void createDiGraphs(int vertex, double conections) {
        Graph graph;
        /**
         * tablica list mozliwych połaczen
         * przetasowane, zapewnia przypadkowe polaczenia
         */
        List<Integer>[] edges;
        Random rand = new Random();
        double r;
        int E;
        int from;

        for (int i = 0; i < 100; i++) {
            edges = new List[vertex];
            for (int c = 0; c < vertex; c++) {
                edges[c] = new ArrayList<>();
                for (int j = 0; j < vertex; j++) {
                    if (j != c) {
                        edges[c].add(j);
                    }
                }
                Collections.shuffle(edges[c]);
            }
            try {
                graph = new Graph(vertex);
                List<Integer> vertexes = new ArrayList<>();
                for (int j = 0; j < vertex; j++) {
                    vertexes.add(Integer.valueOf(j));
                }
                Collections.shuffle(vertexes);
                addTwoWay(graph,vertexes.get(vertex-1),vertexes.get(0),edges,rand);
                for (int c = 0; c < vertexes.size() - 1; c++) {
                    while (true) {
                        r = rand.nextDouble();
                        if (r >= 0.02) break;
                    }
                    addTwoWay(graph,vertexes.get(c),vertexes.get(c+1),edges,rand);
                }
                E = (int) ((vertex * (vertex - 1)) * 0.5 * conections)-vertex;
                int numE=0;
                int j=1;
                while (numE<E){
                    from = j%vertex;
                    if(!edges[from].isEmpty()){
                       addTwoWay(graph,from,edges[from].get(0),edges,rand);
                        numE++;
                    }
                    ++j;
                }

                System.gc();

                PrintWriter printWriter =
                        new PrintWriter("D:\\4sem\\PAMSI\\JELEN\\PROJEKT2\\shortestPaths" +
                                "\\src\\com\\Maciek\\graphs\\graph_no"
                                + (i + 1) + "_v=" + vertex + "_%=" + conections + ".txt");
                printWriter.println(vertex);
                printWriter.println((E+vertex));
                graph.edges().forEach(printWriter::println);
                printWriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.gc();
    }

}
