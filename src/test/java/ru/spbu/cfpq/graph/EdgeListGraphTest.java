package ru.spbu.cfpq.graph;

import org.junit.jupiter.api.Test;
import ru.spbu.cfpq.graph.impl.EdgeListGraph;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EdgeListGraphTest {

    @Test
    public void givenEdges_whenBuildGraph_thenReturnCorrectGraph() {
        List<Integer> vertices = Arrays.asList(1, 2, 3, 4);
        List<Edge<Integer, String>> edges = Arrays.asList(
                new Edge<>(1, 2, "a"),
                new Edge<>(1, 3, "b"),
                new Edge<>(1, 4, "c"),
                new Edge<>(3, 4, "d")
        );

        EdgeListGraph<Integer, String> graph = new EdgeListGraph<>(edges);

        Collection<Integer> graph_vertices = graph.vertices;
        Collection<Edge<Integer, String>> graph_edges = graph.edges;

        assertTrue(vertices.containsAll(graph_vertices) && graph_vertices.containsAll(vertices));
        assertTrue(edges.containsAll(graph_edges) && graph_edges.containsAll(edges));
    }

}
