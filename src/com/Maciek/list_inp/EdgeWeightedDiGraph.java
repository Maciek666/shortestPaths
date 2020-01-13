package com.Maciek.list_inp;

import java.util.Scanner;

/**
 * class represent graph based on neighbor lists
 * @author Maciej Golebiowski
 */
public class EdgeWeightedDiGraph {
    private final int V; //number of vertex
    private int E; //number of Edges
    private Bag<DirectedEdge>[] adj;//list of neighbors

    /**
     * constructor
     *
     * @param v number of vertex
     */
    public EdgeWeightedDiGraph(int v) {
        this.V = v;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int i = 0; i < this.V; i++) {
            adj[i] = new Bag<DirectedEdge>();
        }
    }

    /**
     * constructor
     *
     * @param in open file in which is graph represetnation in form:
     *           V
     *           E
     *           from to weight
     *           .     .    .
     *           .     .    .
     */
    public EdgeWeightedDiGraph(Scanner in) {

        this(in.nextInt());
        this.E = in.nextInt();
        while (in.hasNextInt()) {
            int v = (int) in.nextDouble();
            int w = (int) in.nextDouble();
            double weight = (double) in.nextDouble();
            addEdge(new DirectedEdge(v, w, weight));
            //undrirected:
            addEdge(new DirectedEdge(w, v, weight));
        }
    }

    /**
     * function adding directed edge
     * @param dE directed edge
     */
    public void addEdge(DirectedEdge dE) {
        this.adj[dE.from()].add(dE);
    }

    /**
     * function returning iterable neighbor list
     * @param v - vertex for which list is returning
     * @return adj
     */
    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    /**
     * function returning all edges
     * @return iterable list
     */
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> b = new Bag<DirectedEdge>();
        for (int i = 0; i < this.V; i++) {
            for (DirectedEdge e :
                    adj[i]) {
                b.add(e);
            }
        }
        return b;
    }

    /**
     * #######################
     * help functions
     * #######################
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.V + "\n");
        for (int i = 0; i < this.V; ++i) {
            for (DirectedEdge de :
                    adj[i]) {
                sb.append("{" + de.toString() + "} ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }
}
