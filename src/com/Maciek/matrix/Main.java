package com.Maciek.matrix;

import com.Maciek.list_inp.EdgeWeightedDiGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("D:\\4sem\\PAMSI\\JELEN\\PROJEKT2\\shortestPaths\\src\\com\\Maciek\\graf_testowy.txt");
        Scanner scanner = null;
        Graph graph;
        try {
            scanner = new Scanner(file);
            graph = new Graph(scanner);
            DijktrsaMatrix dm=new DijktrsaMatrix(graph,0);
            dm.getMinDistance(0);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
