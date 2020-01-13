package com.Maciek.matrix;

import com.Maciek.list_inp.Bag;
import com.Maciek.list_inp.DirectedEdge;

import java.util.Scanner;

public class Graph {
    private int vertices;
    private int edges;
    private double matrix[][];

    public Graph(int vertex) {
        this.vertices = vertex;
        matrix = new double[vertex][vertex];
    }

    public Graph(Scanner in) {
        this(in.nextInt());
        this.edges = in.nextInt();
        while (in.hasNextInt()) {
            int v = (int) in.nextDouble();
            int w = (int) in.nextDouble();
            double weight = in.nextDouble();
            addEdge(v, w, weight);
        }
    }

    public void addEdge(int source, int destination, double weight) {
        //add edge
        matrix[source][destination] = weight;
        //add back edge for undirected graph - graph genarator make 2ways
        matrix[destination][source] = weight;
    }

    public void printMatrix() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }

    public Iterable<DirectedEdge> edges() {
        /**
         * mozliwy blad w kolejnosci przejscia po tablicy
         */
        Bag<DirectedEdge> dE = new Bag<>();
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (matrix[i][j] > 0.001) {
                    dE.add(new DirectedEdge(i, j, matrix[i][j]));
                }
            }
        }
        return dE;
    }


    /**
     * ######################
     * help function
     * ######################
     */
    public int getVertices() {
        return vertices;
    }

}
