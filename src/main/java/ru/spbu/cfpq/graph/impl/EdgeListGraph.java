package ru.spbu.cfpq.graph.impl;

import ru.spbu.cfpq.grammar.symbol.impl.Terminal;
import ru.spbu.cfpq.graph.Edge;
import ru.spbu.cfpq.graph.Graph;

import java.io.*;
import java.util.*;

public class EdgeListGraph<V, E> implements Graph<V, E> {
    public HashSet<V> vertices;
    public LinkedList<Edge<V, E>> edges;

    public boolean isOriented = true;

    public EdgeListGraph() {
        this.vertices = new HashSet<>();
        this.edges = new LinkedList<>();
    }

    public EdgeListGraph(Collection<V> vertices, Collection<Edge<V, E>> edges) {
        this();
        vertices.forEach(this::addVertex);
        edges.forEach(this::addEdge);
    }

    public EdgeListGraph(Collection<Edge<V, E>> edges) {
        this();
        edges.forEach(this::addEdge);
    }

    public EdgeListGraph(Edge<V, E>[] edges) {
        this();
        Arrays.stream(edges).toList().forEach(this::addEdge);
    }

    @Override
    public int getVerticesCount() {
        return vertices.size();
    }

    @Override
    public int getEdgesCount() {
        return edges.size();
    }

    public void addVertex(V vertex) {
        this.vertices.add(vertex);
    }

    public void addEdge(Edge<V, E> e) {
        this.vertices.add(e.v1);
        this.vertices.add(e.v2);
        this.edges.add(e);
    }

    public void addEdge(V v1, V v2, E e) {
        Edge<V, E> edge = new Edge<>(v1, v2, e);
        this.vertices.add(edge.v1);
        this.vertices.add(edge.v2);
        this.edges.add(edge);
    }

    public void removeVertex(V vertex) {
        if (!this.vertices.contains(vertex)) return;

        edges.removeIf(edge -> edge.hasVertex(vertex));
        this.vertices.remove(vertex);
    }

    public void removeEdge(Edge<V, E> edge) {
        this.edges.remove(edge);
    }

    public void print() {
        System.out.println("Vertices:");
        printVertices();
        System.out.println("Edges:");
        printEdges();
    }

    public void printVertices() {
        for (V vertex : this.vertices) {
            System.out.print(vertex.toString() + " ");
        }
        System.out.println();
    }

    public void printEdges() {
        for (Edge<V, E> edge : this.edges) {
            System.out.println(edge);
        }
    }

    /**
     * Read graph line by line in format "1 a 2", where 1 and 2 are integer vertices and 'a' is an edge label (grammar terminal).
     * @param stream input stream, e.g. file
     * @return new EdgeListGraph<Integer, Terminal>
     */
    public static EdgeListGraph<Integer, Terminal> readFromStream(InputStream stream) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new InputStreamReader(stream));
        } catch (Exception e) {
            return null;
        }

        String str;
        EdgeListGraph<Integer, Terminal> graph = new EdgeListGraph<>();

        try {
            while ((str = reader.readLine()) != null) {
                String[] tokens = str.split(" ");
                graph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[2]), new Terminal(tokens[1]));
            }
            stream.close();
        } catch (IOException e) {
            return null;
        }

        return graph;
    }

    /**
     * Read graph line by line in format "1 a 2", where 1 and 2 are integer vertices and 'a' is an edge label (grammar terminal).
     * @param graph_string string, representing graph
     * @return new EdgeListGraph<Integer, Terminal>
     */
    public static EdgeListGraph<Integer, Terminal> readFromString(String graph_string) {
        return readFromStream(new ByteArrayInputStream(graph_string.getBytes()));
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Vertices:\n");
        for (V vertex : this.vertices) {
            s.append(vertex.toString()).append(" ");
        }
        s.append("\nEdges:\n");
        for (Edge<V, E> edge : this.edges) {
            s.append(edge).append("\n");
        }
        return s.toString();
    }
}
