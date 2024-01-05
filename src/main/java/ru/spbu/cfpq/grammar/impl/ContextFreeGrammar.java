package ru.spbu.cfpq.grammar.impl;

import ru.spbu.cfpq.grammar.FormalGrammar;
import ru.spbu.cfpq.grammar.Production;
import ru.spbu.cfpq.grammar.symbol.Symbol;
import ru.spbu.cfpq.grammar.symbol.SymbolType;
import ru.spbu.cfpq.grammar.symbol.Word;
import ru.spbu.cfpq.grammar.symbol.impl.Terminal;
import ru.spbu.cfpq.grammar.symbol.impl.Variable;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class ContextFreeGrammar extends FormalGrammar<Variable, Word> {
    public ContextFreeGrammar(HashSet<Variable> variables, HashSet<Terminal> terminals, Variable start, HashSet<Production<Variable, Word>> productions) {
        super(variables, terminals, start, productions);
    }

    public ContextFreeGrammar(Variable[] variables, Terminal[] terminals, Variable start, Production<Variable, Word>[] productions) {
        this(new HashSet<>(Arrays.asList(variables)), new HashSet<>(Arrays.asList(terminals)), start, new HashSet<>(Arrays.asList(productions)));
    }

    public boolean isInChomskyNormalForm() {
        for (Production<Variable, Word> production : productions)
            if (!(  checkProductionType1(production) ||
                    checkProductionType3(production) ||
                    checkProductionType4(production) ))
                return false;
        return true;
    }

    public boolean isInWeakChomskyNormalForm() {
        for (Production<Variable, Word> production : productions)
            if (!(  checkProductionType2(production) ||
                    checkProductionType3(production) ||
                    checkProductionType5(production) ))
                return false;
        return true;
    }

    /**
     * Checks if production is of type A -> BC, where B and C are variables and are not start
     */
    public boolean checkProductionType1(Production<Variable, Word> production) {
        Word body = production.body;
        return body.length() == 2 && body.symbols.stream().allMatch(symbol ->
                symbol.getType() == SymbolType.VARIABLE && symbol != start);
    }

    /**
     * Checks if production is of type A -> BC, where B and C are variables
     */
    public boolean checkProductionType2(Production<Variable, Word> production) {
        Word body = production.body;
        return body.length() == 2 && body.symbols.stream().allMatch(symbol ->
                symbol.getType() == SymbolType.VARIABLE);
    }

    /**
     * Checks if production is of type A -> a, where a is a terminal
     */
    public boolean checkProductionType3(Production<Variable, Word> production) {
        Word body = production.body;
        return body.length() == 1 && body.symbols.get(0).getType() == SymbolType.TERMINAL;
    }

    /**
     * Checks if production is of type S -> eps, where S is a start symbol
     */
    public boolean checkProductionType4(Production<Variable, Word> production) {
        Word body = production.body;
        return production.head == start && body.length() == 1 && body.symbols.get(0).getType() == SymbolType.EPSILON;
    }

    /**
     * Checks if production is of type A -> eps
     */
    public boolean checkProductionType5(Production<Variable, Word> production) {
        Word body = production.body;
        return body.length() == 1 && body.symbols.get(0).getType() == SymbolType.EPSILON;
    }

    /**
     * Reads grammar line by line in format "s a b ...", where first symbol is head of production and other symbols are body.
     * @param stream input stream, e.g. file
     * @param isTerminalsUppercase if true, uppercase symbols are considered terminals and the rest are variables. Otherwise - vise versa.
     * @return new ContextFreeGrammar
     */
    public static ContextFreeGrammar readFromStream(InputStream stream, boolean isTerminalsUppercase) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new InputStreamReader(stream));
        } catch (Exception e) {
            return null;
        }

        String str;
        HashSet<Variable> variables = new HashSet<>();
        HashSet<Terminal> terminals = new HashSet<>();
        HashSet<Production<Variable, Word>> productions = new HashSet<>();
        Variable start = null;

        try {
            while ((str = reader.readLine()) != null) {
                String[] tokens = str.split(" ");

                Variable headSymbol = new Variable(tokens[0]);
                variables.add(headSymbol);
                if (start == null)
                    start = headSymbol;
                LinkedList<Symbol> bodySymbols = new LinkedList<>();
                for (int i = 1; i < tokens.length; ++i) {
                    String token = tokens[i];
                    if (token.equals("->")) continue;

                    Symbol symbol;
                    if (isTerminalsUppercase == isAllUpper(token)) {
                        symbol = new Terminal(token);
                        terminals.add((Terminal)symbol);
                    }
                    else {
                        symbol = new Variable(token);
                        variables.add((Variable)symbol);
                    }

                    bodySymbols.add(symbol);
                }

                Production<Variable, Word> production = new Production<>(headSymbol, new Word(bodySymbols));
                productions.add(production);
            }
            stream.close();
        } catch (IOException e) {
            return null;
        }

        return new ContextFreeGrammar(variables, terminals, start, productions);
    }

    /**
     * Reads grammar line by line in format "s a b ...", where first symbol is head of production and other symbols are body.
     * Uppercase symbols are considered variables, otherwise terminals.
     * @param stream input stream, e.g. file
     * @return new ContextFreeGrammar
     */
    public static ContextFreeGrammar readFromStream(InputStream stream) {
        return readFromStream(stream, false);
    }

    /**
     * Reads grammar line by line in format "s a b ...", where first symbol is head of production and other symbols are body.
     * @param grammar_string string, representing grammar
     * @param isTerminalsUppercase if true, uppercase symbols are considered terminals and the rest are variables. Otherwise - vise versa.
     * @return new ContextFreeGrammar
     */
    public static ContextFreeGrammar readFromString(String grammar_string, boolean isTerminalsUppercase) {
        return readFromStream(new ByteArrayInputStream(grammar_string.getBytes()), isTerminalsUppercase);
    }

    /**
     * Reads grammar line by line in format "s a b ...", where first symbol is head of production and other symbols are body.
     * Uppercase symbols are considered variables, otherwise terminals.
     * @param grammar_string string, representing grammar
     * @return new ContextFreeGrammar
     */
    public static ContextFreeGrammar readFromString(String grammar_string) {
        return readFromStream(new ByteArrayInputStream(grammar_string.getBytes()));
    }

    static boolean isAllUpper(String str) {
        return str.equals(str.toUpperCase());
    }

    static boolean isAllLower(String str) {
        return str.equals(str.toLowerCase());
    }

}
