package ru.spbu.cfpq.algorithm;

import org.apache.commons.math3.linear.FieldMatrix;
import org.junit.jupiter.api.Test;
import ru.spbu.cfpq.grammar.Production;
import ru.spbu.cfpq.grammar.impl.ContextFreeGrammar;
import ru.spbu.cfpq.grammar.symbol.Word;
import ru.spbu.cfpq.grammar.symbol.impl.Terminal;
import ru.spbu.cfpq.grammar.symbol.impl.Variable;
import ru.spbu.cfpq.graph.impl.EdgeListGraph;
import ru.spbu.cfpq.matrix.BoolFieldElement;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.spbu.cfpq.algorithm.CFReachabilityAlgorithms.matrixAllPairReachability;

public class MatrixAllPairReachabilityTest {
    final BoolFieldElement TRUE = BoolFieldElement.ONE;
    final BoolFieldElement FALSE = BoolFieldElement.ZERO;


    @Test
    public void givenGraphAndGrammar_whenCalculateReachability_mustReachLastVertexFromFirst() {
        Variable S = new Variable("S");
        Variable S1 = new Variable("S1");
        Variable A = new Variable("A");
        Variable B = new Variable("B");

        Terminal a = new Terminal("a");
        Terminal b = new Terminal("b");

        Production<Variable, Word> p1 = new Production<>(S, new Word(A, B));
        Production<Variable, Word> p2 = new Production<>(S, new Word(A, S1));
        Production<Variable, Word> p3 = new Production<>(S1, new Word(S, B));
        Production<Variable, Terminal> p4 = new Production<>(A, a);
        Production<Variable, Terminal> p5 = new Production<>(B, b);

        ContextFreeGrammar grammar = new ContextFreeGrammar(
                new Variable[]{S, S1, A, B},
                new Terminal[]{a, b},
                S,
                new Production[]{p1, p2, p3, p4, p5});

        EdgeListGraph<Integer, Terminal> graph = new EdgeListGraph<>();
        graph.addEdge(0, 1, a);
        graph.addEdge(1, 2, a);
        graph.addEdge(2, 0, a);
        graph.addEdge(2, 3, b);
        graph.addEdge(3, 2, b);

        HashMap<Variable, FieldMatrix<BoolFieldElement>> reachability = matrixAllPairReachability(graph, grammar);

        assertEquals(reachability.get(grammar.start).getEntry(0, 3), TRUE);
    }
}
