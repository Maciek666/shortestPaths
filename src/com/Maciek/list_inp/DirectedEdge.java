package com.Maciek.list_inp;

import java.text.DecimalFormat;

/**
 * class represent edge in weighted digraph
 * @author Maciej Golebiowski
 */
public class DirectedEdge {
    private static DecimalFormat decimalFormat;
    private final int v;
    private final int w;
    private double weight;

    /**
     * static initialization block
     */
    static {
       decimalFormat = new DecimalFormat("#0.00");
    }

    /**
     * constructor
     *
     * @param v - from
     * @param w - to
     * @param weight
     */
    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * #####################################
     * Help functions
     * #####################################
     */
    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return v+" "+w+" "+ decimalFormat.format(weight);
    }
}
