package com.Maciek.matrix;

import com.Maciek.ShortestPath;


/**
 * class using to find shortest paths using dijkstra alg
 * @author Maciej Golebiowski
 */
public class DijktrsaMatrix implements ShortestPath {

    private Graph graph;
    public double[] distance;

    public boolean[] spt;

    /**
     * constructor
     * @param graph
     * @param sourceVertex
     */
    public DijktrsaMatrix(Graph graph,int sourceVertex) {
        this.graph = graph;
        distance =  new double[graph.getVertices()];
        spt = new boolean[graph.getVertices()];

        for (int i = 0; i < graph.getVertices(); i++) {
            distance[i] = Double.POSITIVE_INFINITY;
        }
        distance[sourceVertex] = 0;

    }

    /**
     * get the vertex with minimum distance which is not included in SPT
     */
    int getMinimumVertex(boolean[] mst, double[] key) {
        double minKey = Double.POSITIVE_INFINITY;
        int vertex = -1;
        for (int i = 0; i < graph.getVertices(); i++) {
            if (mst[i] == false && minKey > key[i]) {
                minKey = key[i];
                vertex = i;
            }
        }
        return vertex;
    }

    /**
     *  function printing min value of path to source vertex
     *
     * @param sourceVertex
     * @param key
     */
    public void printDijkstra(int sourceVertex, double[] key) {
        System.out.println("Dijkstra Algorithm: (Adjacency Matrix)");
        for (int i = 0; i < graph.getVertices(); i++) {
            System.out.println("Source Vertex: " + sourceVertex + " to vertex " + +i +
                    " distance: " + key[i]);
        }
    }

    @Override
    public void getMinDistance(int sourceVertex) {
        //create SPT - shortest path tree
        for (int i = 0; i < graph.getVertices(); i++) {
            //get the vertex with the minimum distance
            int vertex_U = getMinimumVertex(spt, distance);
            //include this vertex in SPT
            spt[vertex_U] = true;
            //iterate through all the adjacent graph.getVertices() of above vertex and update the keys
            for (int vertex_V = 0; vertex_V < graph.getVertices(); vertex_V++) {
                //check of the edge between vertex_U and vertex_V
                if (graph.getMatrix()[vertex_U][vertex_V] > 0) {
                    //check if this vertex 'vertex_V' already in spt and
                    // if distance[vertex_V]!=Infinity
                    if (spt[vertex_V] == false && graph.getMatrix()[vertex_U][vertex_V] != Double.POSITIVE_INFINITY) {
                        //check if distance needs an update or not
                        //means check total weight from source to vertex_V is less than
                        //the current distance value, if yes then update the distance
                        double newKey = graph.getMatrix()[vertex_U][vertex_V] + distance[vertex_U];
                        if (newKey < distance[vertex_V])
                            distance[vertex_V] = newKey;
                    }
                }
            }
        }
        //print shortest path tree
        //printDijkstra(sourceVertex, distance);
    }

    public Graph getGraph() {
        return graph;
    }
}
