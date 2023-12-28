package ru.spbu.cfpq.grammar.symbol.impl;

import ru.spbu.cfpq.grammar.symbol.Symbol;
import ru.spbu.cfpq.grammar.symbol.SymbolType;

import java.util.Collections;

public class Variable extends Symbol {
    public static final SymbolType TYPE = SymbolType.VARIABLE;
    private final String value;

    public Variable(String value) {
        if (value == null || value.isEmpty())
            throw new IllegalArgumentException("Value must not be null or empty");

        this.value = value;
        this.symbols = Collections.singletonList(this);
    }

    public SymbolType getType() {
        return TYPE;
    }

    public String getValue() {
        return this.value;
    }

}