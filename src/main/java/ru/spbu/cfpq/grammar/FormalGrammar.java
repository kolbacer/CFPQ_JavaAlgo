package ru.spbu.cfpq.grammar;

import ru.spbu.cfpq.grammar.symbol.Symbol;
import ru.spbu.cfpq.grammar.symbol.SymbolType;
import ru.spbu.cfpq.grammar.symbol.Word;
import ru.spbu.cfpq.grammar.symbol.impl.Terminal;
import ru.spbu.cfpq.grammar.symbol.impl.Variable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

/**
 * Base formal grammar abstract class
 * @param <H> Type of production head
 * @param <B> Type of production body
 */
public abstract class FormalGrammar<H extends Word, B extends Word> {
    public HashSet<Variable> variables;
    public HashSet<Terminal> terminals;
    public Variable start;
    public HashSet<Production<H, B>> productions;

    public FormalGrammar(HashSet<Variable> variables, HashSet<Terminal> terminals, Variable start, HashSet<Production<H, B>> productions) {
        if (!variables.contains(start))
            throw new IllegalArgumentException("Variables must contain start");

        for (Variable variable : variables)
            for (Terminal terminal : terminals)
                if (Objects.equals(variable.getValue(), terminal.getValue()))
                    throw new IllegalArgumentException("Variables and terminals contain equal values");

        for (Production<H, B> production : productions)
            if (!checkProduction(production, variables, terminals))
                throw new IllegalArgumentException("All production symbols must be within either variables or terminals");

        this.variables = variables;
        this.terminals = terminals;
        this.start = start;
        this.productions = productions;
    }

    public FormalGrammar(Variable[] variables, Terminal[] terminals, Variable start, Production<H, B>[] productions) {
        this(new HashSet<>(Arrays.asList(variables)), new HashSet<>(Arrays.asList(terminals)), start, new HashSet<>(Arrays.asList(productions)));
    }

    public void addProduction(Production<H, B> production) {
        if (!checkProduction(production, this.variables, this.terminals))
            throw new IllegalArgumentException("All production symbols must be within either variables or terminals, epsilon can't be in head");

        this.productions.add(production);
    }

    public void removeProduction(Production<H, B> production) {
        this.productions.remove(production);
    }

    private boolean checkProduction(Production<H, B> production, HashSet<Variable> variables, HashSet<Terminal> terminals) {
        for (Symbol symbol : production.head.symbols)
            if (!(variables.contains(symbol) || terminals.contains(symbol)))
                return false;
        for (Symbol symbol : production.body.symbols)
            if (!(variables.contains(symbol) || terminals.contains(symbol) || symbol.getType() == SymbolType.EPSILON))
                return false;

        return true;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Production<H,B> production : productions)
            s.append(production.toString()).append("\n");
        return s.toString();
    }

}
