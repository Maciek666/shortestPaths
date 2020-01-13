package com.Maciek.list_inp;

import com.Maciek.ShortestPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("D:\\4sem\\PAMSI\\JELEN\\PROJEKT2\\shortestPaths\\src\\com\\Maciek\\graf_testowy.txt");
        Scanner scanner = new Scanner(file);
        EdgeWeightedDiGraph G = new EdgeWeightedDiGraph(scanner);
        Dijkstra dijkstra = new Dijkstra(G, 0);
        dijkstra.getMinDistance(0);
        for (int i = 0; i <G.getV() ; i++) {
            System.out.println("FROM "+i+"to source dist =" +dijkstra.distTo(i));
        }
    }
}
