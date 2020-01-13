package com.Maciek.avl;

public class Main {
    public static void main(String[] args) {
        AVL tree = new AVL();

        System.out.println("Inserting values 1 to 10");
        for (int i = 1; i < 20; i++)
            tree.insert(i);

        System.out.print("Printing balance: ");
        //tree.printBalance();


        for (int i = 0; i < 6; i++)
            System.out.println(tree.delete_Min());
        //tree.printBalance();
       // tree.bal();
    }
}
