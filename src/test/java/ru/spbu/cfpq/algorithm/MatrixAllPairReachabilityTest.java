package ru.spbu.cfpq.algorithm;

import org.junit.jupiter.api.Test;
import ru.spbu.cfpq.grammar.Production;
import ru.spbu.cfpq.grammar.impl.ContextFreeGrammar;
import ru.spbu.cfpq.grammar.symbol.Word;
import ru.spbu.cfpq.grammar.symbol.impl.Terminal;
import ru.spbu.cfpq.grammar.symbol.impl.Variable;
import ru.spbu.cfpq.graph.impl.EdgeListGraph;
import ru.spbu.cfpq.matrix.BoolFieldElement;
import ru.spbu.cfpq.matrix.impl.BoolDenseMatrix;
import ru.spbu.cfpq.matrix.impl.BoolSparseMatrix;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.spbu.cfpq.algorithm.CFReachabilityAlgorithms.matrixAllPairReachability;

public class MatrixAllPairReachabilityTest {
    final BoolFieldElement TRUE = BoolFieldElement.ONE;
    final BoolFieldElement FALSE = BoolFieldElement.ZERO;


    @Test
    public void givenGraphAndGrammar_whenCalculateReachability_thenReturnCorrectReachabilityMatrices() {
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

        BoolFieldElement[][] S_reach_data = new BoolFieldElement[][] {
                {FALSE, FALSE, TRUE, TRUE},   // 0 0 1 1
                {FALSE, FALSE, TRUE, TRUE},   // 0 0 1 1
                {FALSE, FALSE, TRUE, TRUE},   // 0 0 1 1
                {FALSE, FALSE, FALSE, FALSE}, // 0 0 0 0
        };

        BoolFieldElement[][] S1_reach_data = new BoolFieldElement[][] {
                {FALSE, FALSE, TRUE, TRUE},   // 0 0 1 1
                {FALSE, FALSE, TRUE, TRUE},   // 0 0 1 1
                {FALSE, FALSE, TRUE, TRUE},   // 0 0 1 1
                {FALSE, FALSE, FALSE, FALSE}, // 0 0 0 0
        };

        BoolFieldElement[][] A_reach_data = new BoolFieldElement[][] {
                {FALSE, TRUE, FALSE, FALSE},  // 0 1 0 0
                {FALSE, FALSE, TRUE, FALSE},  // 0 0 1 0
                {TRUE, FALSE, FALSE, FALSE},  // 1 0 0 0
                {FALSE, FALSE, FALSE, FALSE}, // 0 0 0 0
        };

        BoolFieldElement[][] B_reach_data = new BoolFieldElement[][] {
                {FALSE, FALSE, FALSE, FALSE}, // 0 0 0 0
                {FALSE, FALSE, FALSE, FALSE}, // 0 0 0 0
                {FALSE, FALSE, FALSE, TRUE},  // 0 0 0 1
                {FALSE, FALSE, TRUE, FALSE},  // 0 0 1 0
        };

        BoolSparseMatrix S_reach = new BoolSparseMatrix(new BoolDenseMatrix(S_reach_data));
        BoolSparseMatrix S1_reach = new BoolSparseMatrix(new BoolDenseMatrix(S1_reach_data));
        BoolSparseMatrix A_reach = new BoolSparseMatrix(new BoolDenseMatrix(A_reach_data));
        BoolSparseMatrix B_reach = new BoolSparseMatrix(new BoolDenseMatrix(B_reach_data));

        HashMap<Variable, BoolSparseMatrix> reachability = matrixAllPairReachability(graph, grammar);

        assertEquals(S_reach, reachability.get(S));
        assertEquals(S1_reach, reachability.get(S1));
        assertEquals(A_reach, reachability.get(A));
        assertEquals(B_reach, reachability.get(B));
    }

    @Test
    public void givenTestCase1_whenCalculateReachability_thenHaveCorrectNumberOfReachablePairs() {
        final int REACHABLE_PAIRS = 20;

        // binary_tree
        String grammar_string = """
                S A B
                S A S1
                S1 S B
                A a
                B b""";

        // binary_tree
        String graph_string = """
                0 a 2
                1 a 2
                3 a 5
                4 a 5
                2 a 6
                5 a 6
                2 b 0
                2 b 1
                5 b 3
                5 b 4
                6 b 2
                6 b 5""";

        ContextFreeGrammar grammar = ContextFreeGrammar.readFromString(grammar_string);
        EdgeListGraph<Integer, Terminal> graph = EdgeListGraph.readFromString(graph_string);

        HashMap<Variable, BoolSparseMatrix> reachability = matrixAllPairReachability(graph, grammar);
        BoolSparseMatrix S_reach = reachability.get(new Variable("S"));

        assertEquals(REACHABLE_PAIRS, S_reach.NumberOfNonzeros());
    }

    @Test
    public void givenTestCase2_whenCalculateReachability_thenHaveCorrectNumberOfReachablePairs() {
        final int REACHABLE_PAIRS = 9;

        // cycle
        String grammar_string = """
                S -> S1 S
                S -> a
                S1 -> a""";

        // cycle
        String graph_string = """
                0 a 1
                1 a 2
                2 a 0""";

        ContextFreeGrammar grammar = ContextFreeGrammar.readFromString(grammar_string);
        EdgeListGraph<Integer, Terminal> graph = EdgeListGraph.readFromString(graph_string);

        HashMap<Variable, BoolSparseMatrix> reachability = matrixAllPairReachability(graph, grammar);
        BoolSparseMatrix S_reach = reachability.get(new Variable("S"));

        assertEquals(REACHABLE_PAIRS, S_reach.NumberOfNonzeros());
    }

    @Test
    public void givenTestCase3_whenCalculateReachability_thenHaveCorrectNumberOfReachablePairs() {
        final int REACHABLE_PAIRS = 2;

        // line
        String grammar_string = """
                S -> H1 H0
                S -> H2 H0
                H0 -> b
                H1 -> H2 S
                H2 -> a""";

        // line
        String graph_string = """
                0 a 1
                1 a 2
                2 b 3
                3 b 4""";

        ContextFreeGrammar grammar = ContextFreeGrammar.readFromString(grammar_string);
        EdgeListGraph<Integer, Terminal> graph = EdgeListGraph.readFromString(graph_string);

        HashMap<Variable, BoolSparseMatrix> reachability = matrixAllPairReachability(graph, grammar);
        BoolSparseMatrix S_reach = reachability.get(new Variable("S"));

        assertEquals(REACHABLE_PAIRS, S_reach.NumberOfNonzeros());
    }

    @Test
    public void givenTestCase4_whenCalculateReachability_thenHaveCorrectNumberOfReachablePairs() {
        final int REACHABLE_PAIRS = 1;

        // loop
        String grammar_string = """
                S -> S1 S
                S -> a
                S1 -> a""";

        // loop
        String graph_string = """
                0 a 0
                0 b 1
                1 c 2""";

        ContextFreeGrammar grammar = ContextFreeGrammar.readFromString(grammar_string);
        EdgeListGraph<Integer, Terminal> graph = EdgeListGraph.readFromString(graph_string);

        HashMap<Variable, BoolSparseMatrix> reachability = matrixAllPairReachability(graph, grammar);
        BoolSparseMatrix S_reach = reachability.get(new Variable("S"));

        assertEquals(REACHABLE_PAIRS, S_reach.NumberOfNonzeros());
    }

    @Test
    public void givenTestCase5_whenCalculateReachability_thenHaveCorrectNumberOfReachablePairs() {
        final int REACHABLE_PAIRS = 6;

        // two_cycles
        String grammar_string = """
                S -> H1 H0
                S -> H2 H0
                H0 -> b
                H1 -> H2 S
                H2 -> a""";

        // two_cycles
        String graph_string = """
                0 a 1
                1 a 2
                2 a 0
                2 b 3
                3 b 2""";

        ContextFreeGrammar grammar = ContextFreeGrammar.readFromString(grammar_string);
        EdgeListGraph<Integer, Terminal> graph = EdgeListGraph.readFromString(graph_string);

        HashMap<Variable, BoolSparseMatrix> reachability = matrixAllPairReachability(graph, grammar);
        BoolSparseMatrix S_reach = reachability.get(new Variable("S"));

        assertEquals(REACHABLE_PAIRS, S_reach.NumberOfNonzeros());
    }

    @Test
    public void givenTestCase6_whenCalculateReachability_thenHaveCorrectNumberOfReachablePairs() {
        final int REACHABLE_PAIRS = 2;

        // single_vs_shortest
        String grammar_string = """
                S -> S1 S2
                S -> S15 S16
                S1 -> S3 S4
                S2 -> S5 S6
                S3 -> S7 S8
                S4 -> S9 S10
                S5 -> S11 S12
                S6 -> S13 S14
                S16 -> S17 S18
                S17 -> S19 S20
                S18 -> S21 S22
                S22 -> S23 S24
                S7 -> a
                S8 -> a
                S9 -> a
                S10 -> a
                S11 -> b
                S12 -> b
                S13 -> b
                S14 -> b
                S15 -> a
                S19 -> a
                S20 -> a
                S21 -> b
                S23 -> b
                S24 -> b""";

        // single_vs_shortest
        String graph_string = """
                0 a 1
                1 a 2
                2 a 3
                3 a 4
                3 b 5
                4 b 3
                5 b 6
                6 b 7""";

        ContextFreeGrammar grammar = ContextFreeGrammar.readFromString(grammar_string);
        EdgeListGraph<Integer, Terminal> graph = EdgeListGraph.readFromString(graph_string);

        HashMap<Variable, BoolSparseMatrix> reachability = matrixAllPairReachability(graph, grammar);
        BoolSparseMatrix S_reach = reachability.get(new Variable("S"));

        assertEquals(REACHABLE_PAIRS, S_reach.NumberOfNonzeros());
    }

}
