package ru.spbu.cfpq.algorithm;

import ru.spbu.cfpq.grammar.Production;
import ru.spbu.cfpq.grammar.impl.ContextFreeGrammar;
import ru.spbu.cfpq.grammar.symbol.Word;
import ru.spbu.cfpq.grammar.symbol.impl.Terminal;
import ru.spbu.cfpq.grammar.symbol.impl.Variable;
import ru.spbu.cfpq.graph.Edge;
import ru.spbu.cfpq.graph.impl.EdgeListGraph;
import ru.spbu.cfpq.matrix.BoolFieldElement;
import ru.spbu.cfpq.matrix.impl.BoolSparseMatrix;

import java.util.*;

public final class CFReachabilityAlgorithms {
    private CFReachabilityAlgorithms() {}

    public static HashMap<Variable, BoolSparseMatrix> matrixAllPairReachability(EdgeListGraph<Integer, Terminal> graph, ContextFreeGrammar grammar) {
        if (!grammar.isInWeakChomskyNormalForm())
            throw new IllegalArgumentException("Grammar must be in weak chomsky normal form");

        LinkedList<Production<Variable, Word>> terminalProductions = new LinkedList<>();
        LinkedList<Production<Variable, Word>> epsilonProductions = new LinkedList<>();
        LinkedList<Production<Variable, Word>> variableProductions = new LinkedList<>();

        for (Production<Variable, Word> production : grammar.productions) {
            if (grammar.checkProductionType3(production))
                terminalProductions.add(production);
            else if (grammar.checkProductionType5(production))
                epsilonProductions.add(production);
            else
                variableProductions.add(production);
        }

        final BoolFieldElement TRUE = BoolFieldElement.ONE;
        final BoolFieldElement FALSE = BoolFieldElement.ZERO;

        HashSet<Variable> variables = grammar.variables;
        int n = graph.getVerticesCount();
        List<Edge<Integer, Terminal>> edges = graph.edges;

        HashMap<Variable, BoolSparseMatrix> T = new HashMap<>();

        for (Variable variable: variables) {
            T.put(variable, new BoolSparseMatrix(n, n));
        }

        // terminal productions
        for (Production<Variable, Word> production : terminalProductions) {
            BoolSparseMatrix Tv = T.get(production.head);
            for (Edge<Integer, Terminal> edge : edges) {
                if (production.body.equals(edge.e))
                    Tv.setEntry(edge.v1, edge.v2, TRUE);
            }
        }

        // epsilon productions
        for (Production<Variable, Word> production : epsilonProductions) {
            BoolSparseMatrix Tv = T.get(production.head);
            for (int i = 0; i < n; ++i)
                Tv.setEntry(i, i, TRUE);
        }

        // variable productions
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Production<Variable, Word> production : variableProductions) {
                BoolSparseMatrix Tv = T.get(production.head);
                BoolSparseMatrix T1 = T.get((Variable)production.body.symbols.get(0));
                BoolSparseMatrix T2 = T.get((Variable)production.body.symbols.get(1));
                BoolSparseMatrix newTv = (BoolSparseMatrix)Tv.add(T1.multiply(T2));
                if (!Tv.equals(newTv)) {
                    T.replace(production.head, newTv);
                    changed = true;
                }
            }
        }

        return T;
    }
}
