package ru.spbu.cfpq.grammar.symbol;

import java.util.Objects;

public abstract class Symbol extends Word {
    public abstract SymbolType getType();
    public abstract String getValue();

    @Override
    public String toString() {
        if (this.getType() == SymbolType.EPSILON)
            return "[eps]";
        else
            return this.getValue();
    }

    @Override
    public int hashCode() {
        if (this.getType() == SymbolType.EPSILON)
            return 0;
        return this.getValue().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Symbol other))
            return false;

        if (this.getType() != other.getType())
            return false;

        if (other.getType() == SymbolType.EPSILON)
            return true;
        else
            return Objects.equals(this.getValue(), other.getValue());
    }
}
