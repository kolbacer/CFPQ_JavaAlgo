package ru.spbu.cfpq.graph;

import java.util.Objects;

public class Edge<V, E> {
    public V v1, v2;
    public E e;

    public Edge(V v1, V v2, E e) {
        this.v1 = v1;
        this.v2 = v2;
        this.e = e;
    }

    public boolean hasVertex(V vertex) {
        return Objects.equals(vertex, v1) || Objects.equals(vertex, v2);
    }

    @Override
    public String toString() {
        return v1.toString() + " " + v2.toString() + " " + e.toString();
    }
}
