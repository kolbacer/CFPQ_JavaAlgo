package ru.spbu.cfpq.matrix;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;

public class BoolField implements Field<BoolFieldElement> {
    private static final BoolField INSTANCE = new BoolField();

    private BoolField() {
    }

    public static BoolField getInstance() {
        return INSTANCE;
    }

    public BoolFieldElement getZero() {
        return BoolFieldElement.ZERO;
    }

    public BoolFieldElement getOne()  {
        return BoolFieldElement.ONE;
    }

    public Class<? extends FieldElement<BoolFieldElement>> getRuntimeClass() {
        return BoolFieldElement.class;
    }
}
