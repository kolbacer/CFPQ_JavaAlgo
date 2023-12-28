package ru.spbu.cfpq.grammar.symbol.impl;

import ru.spbu.cfpq.grammar.symbol.Symbol;
import ru.spbu.cfpq.grammar.symbol.SymbolType;

import java.util.Collections;

public class Epsilon extends Symbol {
    public static final SymbolType TYPE = SymbolType.EPSILON;

    public Epsilon() {
        this.symbols = Collections.singletonList(this);
    }

    public SymbolType getType() {
        return TYPE;
    }

    public String getValue() {
        return null;
    }

}
