package ru.spbu.cfpq;

import ru.spbu.cfpq.grammar.Production;
import ru.spbu.cfpq.grammar.impl.ContextFreeGrammar;
import ru.spbu.cfpq.grammar.symbol.Word;
import ru.spbu.cfpq.grammar.symbol.impl.Terminal;
import ru.spbu.cfpq.grammar.symbol.impl.Variable;
import ru.spbu.cfpq.graph.impl.EdgeListGraph;
import ru.spbu.cfpq.matrix.impl.BoolSparseMatrix;

import java.util.HashMap;

import static ru.spbu.cfpq.algorithm.CFReachabilityAlgorithms.matrixAllPairReachability;
import static ru.spbu.cfpq.algorithm.Utils.printReachabilityMatrix;

public class Main {
    public static void main(String[] args) {

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
                new Variable[]{S, S1, A, B},           // variable (non-terminal) symbols
                new Terminal[]{a, b},                  // terminal symbols
                S,                                     // start symbol
                new Production[]{p1, p2, p3, p4, p5}); // production rules

        System.out.println("Grammar:");
        System.out.println(grammar);

        EdgeListGraph<Integer, Terminal> graph = new EdgeListGraph<>();
        graph.addEdge(0, 1, a);
        graph.addEdge(1, 2, a);
        graph.addEdge(2, 0, a);
        graph.addEdge(2, 3, b);
        graph.addEdge(3, 2, b);

        System.out.println("Graph:");
        System.out.println(graph);

        HashMap<Variable, BoolSparseMatrix> reachability = matrixAllPairReachability(graph, grammar);

        System.out.println("Reachability matrix:");
        printReachabilityMatrix(reachability);
    }
}