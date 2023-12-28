package ru.spbu.cfpq.matrix;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;

public class BoolFieldElement implements FieldElement<BoolFieldElement> {
    public static final BoolFieldElement ZERO = new BoolFieldElement(false);
    public static final BoolFieldElement ONE = new BoolFieldElement(true);

    public Boolean value = false;

    public BoolFieldElement() {}

    public BoolFieldElement(Boolean value) {
        this.value = value;
    }

    public BoolFieldElement add(BoolFieldElement var1) {
        return new BoolFieldElement(this.value || var1.value);
    }

    public BoolFieldElement subtract(BoolFieldElement var1) {
        throw new UnsupportedOperationException("Can't subtract boolean values");
    }

    public BoolFieldElement negate() {
        return new BoolFieldElement(!this.value);
    }

    public BoolFieldElement multiply(int var1) {
        throw new UnsupportedOperationException("Can't multiply boolean by int");
    }

    public BoolFieldElement multiply(BoolFieldElement var1) {
        return new BoolFieldElement(this.value && var1.value);
    }

    public BoolFieldElement divide(BoolFieldElement var1) {
        throw new UnsupportedOperationException("Can't divide boolean values");
    }

    public BoolFieldElement reciprocal() {
        throw new UnsupportedOperationException("Can't get reciprocal of boolean");
    }

    public Field<BoolFieldElement> getField() {
        return BoolField.getInstance();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else {
            return other instanceof BoolFieldElement && this.value.equals(((BoolFieldElement) other).value);
        }
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public String toString() {
        return this.value ? "1" : "0";
    }
}
