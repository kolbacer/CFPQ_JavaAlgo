package ru.spbu.cfpq.graph;

/**
 * Base Graph interface
 * @param <V> Type of vertices
 * @param <E> Type of edges
 */
public interface Graph<V, E> {
    boolean isOriented = true;

    int getVerticesCount();
    int getEdgesCount();
}
