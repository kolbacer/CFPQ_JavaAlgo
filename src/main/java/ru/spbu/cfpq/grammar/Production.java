package ru.spbu.cfpq.grammar;

import ru.spbu.cfpq.grammar.symbol.Word;

import java.util.Objects;

/**
 * Production rule class
 * @param <H> Type of production head
 * @param <B> Type of production body
 */
public class Production<H extends Word, B extends Word> {
    public final H head;
    public final B body;

    public Production(H head, B body) {
        this.head = head;
        this.body = body;
    }

    @Override
    public String toString() {
        return head.toString() + " -> " + body.toString();
    }

    @Override
    public int hashCode() {
        return head.hashCode() + body.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Production other))
            return false;

        return Objects.equals(this.head, other.head) && Objects.equals(this.body, other.body);
    }
}
