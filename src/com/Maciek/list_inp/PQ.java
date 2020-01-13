package com.Maciek.list_inp;

import java.util.NoSuchElementException;

/**
 * PQ uses binary heap
 *
 * @param <Key>
 */
public class PQ <Key extends Comparable<Key>>  {
    private int maxN;        // maximum number of elements on PQ
    private int n;           // number of elements on PQ
    private int[] pq;        // binary heap using 1-based indexing
    private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys;      // keys[i] = priority of i


    /**
     * constructor
     *
     * @param maxN - maximum number of element in queue
     */
    public PQ(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        this.maxN = maxN;
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];    // make this of length maxN??
        pq   = new int[maxN + 1];
        qp   = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }

    /**
     * function insert into priority queue
     *
     * @param V - index
     * @param key - object
     */
    public void insert(int V, Key key) {
        if (V < 0 || V >= maxN) throw new IllegalArgumentException();
        if (contains(V)) throw new IllegalArgumentException("index is already in the priority queue");
        n++;
        qp[V] = n;
        pq[n] = V;
        keys[V] = key;
        swim(n);
    }

    /**
     * isEmpty
     * @return true if is,if not false
     */
    public boolean isEmpty() {
        return n==0;
    }

    /**
     * function check is index in queue
     *
     * @param V index
     * @return boolean
     */
    public boolean contains(int V) {
        if (V < 0 || V >= maxN) throw new IllegalArgumentException();
        return qp[V] != -1;

    }

    /**
     * function change weight- put key in {@param V - index}
     *
     * @param V - index
     * @param key param of priority
     */
    public void changeWeight(int V, Key key) {

        if (V < 0 || V >= maxN) throw new IllegalArgumentException();
        if (!contains(V)) throw new NoSuchElementException("index is not in the priority queue");
        keys[V] = key;
        swim(qp[V]);
        sink(qp[V]);

    }

    /**
     * function delete smallest elem
     *
     * @return
     */
    public int delMin() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        int min = pq[1];
        exch(1, n--);
        sink(1);
        assert min == pq[n+1];
        qp[min] = -1;        // delete
        keys[min] = null;    // to help with garbage collection
        pq[n+1] = -1;        // not needed
        return min;
    }

/************************************************

                help function

 ***********************************************/
    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }


    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    public void showKeyses(){
        for (int i = 0; i <keys.length ; i++) {
            System.out.println(keys[i]);
        }
    }
}
