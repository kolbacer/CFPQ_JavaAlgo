package ru.spbu.cfpq.algorithm;

import ru.spbu.cfpq.grammar.symbol.impl.Variable;
import ru.spbu.cfpq.matrix.BoolMatrix;

import java.util.HashMap;
import java.util.List;

public final class Utils {
    public static void printReachabilityMatrix(HashMap<Variable, ? extends BoolMatrix> reachability) {
        if (reachability.isEmpty()) return;

        List<Variable> variables = reachability.keySet().stream().toList();
        List<? extends BoolMatrix> matrices = reachability.values().stream().toList();
        int n = matrices.get(0).getRowDimension();

        String[][] reachabilityStr = new String[n][n];

        for (Variable variable : variables) {
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < n; ++j)
                    if (reachability.get(variable).getEntry(i, j).value)
                        if (reachabilityStr[i][j] == null)
                            reachabilityStr[i][j] = "{" + variable;
                        else
                            reachabilityStr[i][j] += "," + variable;
        }

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                if (reachabilityStr[i][j] != null)
                    reachabilityStr[i][j] += "}";

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j)
                System.out.print(reachabilityStr[i][j] + " ");
            System.out.println();
        }
    }
}
