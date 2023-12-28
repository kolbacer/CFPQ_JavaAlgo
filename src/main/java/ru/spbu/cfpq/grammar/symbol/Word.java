package ru.spbu.cfpq.grammar.symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Word {
    public List<Symbol> symbols;

    public int length() {
        return symbols.size();
    }

    public boolean isEmpty() {
        return this.length() == 0;
    }

    public Word() {}

    public Word(List<Symbol> symbols) {
        this.symbols = symbols;
    }

    public Word(Symbol... symbols) {
        this.symbols = new ArrayList<>(Arrays.asList(symbols));
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.length(); i++)
            s.append(this.symbols.get(i).toString()).append(" ");
        return s.toString();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (Symbol symbol : symbols)
            hash += symbol.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Word other))
            return false;

        if (this.length() != other.length())
            return false;

        for (int i = 0; i < this.length(); i++)
            if (!Objects.equals(this.symbols.get(i), other.symbols.get(i)))
                return false;

        return true;
    }
}
