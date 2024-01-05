package ru.spbu.cfpq.grammar;

import org.junit.jupiter.api.Test;
import ru.spbu.cfpq.grammar.impl.ContextFreeGrammar;
import ru.spbu.cfpq.grammar.symbol.Word;
import ru.spbu.cfpq.grammar.symbol.impl.Terminal;
import ru.spbu.cfpq.grammar.symbol.impl.Variable;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContextFreeGrammarTest {

    @Test
    public void givenSymbolsAndProductions_whenBuildGrammar_thenReturnCorrectGrammar() {
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

        Variable[] variables = {S, S1, A, B};
        Terminal[] terminals = {a, b};
        Production[] productions = {p1, p2, p3, p4, p5};

        ContextFreeGrammar grammar = new ContextFreeGrammar(
                variables,
                terminals,
                S,
                productions);

        assertEquals(S, grammar.start);
        assertTrue(Arrays.asList(variables).containsAll(grammar.variables) && grammar.variables.containsAll(Arrays.asList(variables)));
        assertTrue(Arrays.asList(terminals).containsAll(grammar.terminals) && grammar.terminals.containsAll(Arrays.asList(terminals)));
        assertTrue(Arrays.asList(productions).containsAll(grammar.productions) && grammar.productions.containsAll(Arrays.asList(productions)));
    }

}
