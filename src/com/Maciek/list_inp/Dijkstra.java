package com.Maciek.list_inp;

import com.Maciek.ShortestPath;


/**
 * class is using to calculate shortest path using dijkstra alg
 * @author Maciej Golebiowski
 */
public class Dijkstra implements ShortestPath {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private PQ pq;
    private EdgeWeightedDiGraph graph;

    /**
     * Initializes Dijkstra
     *
     * @param G - Directed edge-weight graph
     * @param s - index of source vertex
     * @author Maciej Golebiowski
     */

    public Dijkstra(EdgeWeightedDiGraph G,int s) {
        this.graph = G;
        for (DirectedEdge e : this.graph.edges()) {
            if (e.getWeight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
        }

        distTo = new double[this.graph.getV()];
        edgeTo = new DirectedEdge[this.graph.getV()];
        pq = new PQ<Double>(this.graph.getV());
        validateVertex(s);

        for (int v = 0; v < this.graph.getV(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

    }


    /**
     * function returns distance to v - vertex
     *
     * @param v v>0 && v<vertex number
     * @return infitiny if there is no path and distance if path exist
     */
    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * relax - if shortest way to some V would be founded function change dist in distTo[]
     * and in priority queque
     *
     * @param G edge-weigted digraph
     * @param V - vertex
     */
    private void relax(EdgeWeightedDiGraph G, int V) {
        for (DirectedEdge e :
                G.adj(V)) {
            int w = e.to();
            if (distTo[w] > distTo[V] + e.getWeight()) {
                distTo[w] = distTo[V] + e.getWeight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.changeWeight(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    /**********************************
     *           help function
     *********************************/
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    public PQ getPq() {
        return pq;
    }

    @Override
    public void getMinDistance(int sourceVertex) {
        pq.insert(sourceVertex, distTo[sourceVertex]);
        while (!pq.isEmpty()) {
            relax(this.graph,pq.delMin());
        }
    }
}
