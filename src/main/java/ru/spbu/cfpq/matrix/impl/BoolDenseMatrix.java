package ru.spbu.cfpq.matrix.impl;

import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import ru.spbu.cfpq.matrix.BoolField;
import ru.spbu.cfpq.matrix.BoolFieldElement;

public class BoolDenseMatrix extends Array2DRowFieldMatrix<BoolFieldElement> {
    public BoolDenseMatrix() {
        super(BoolField.getInstance());
    }

    public BoolDenseMatrix(int rowDimension, int columnDimension) {
        super(BoolField.getInstance(), rowDimension, columnDimension);
    }

    public BoolDenseMatrix(BoolFieldElement[][] d) {
        super(BoolField.getInstance(), d);
    }

    public BoolDenseMatrix(BoolFieldElement[][] d, boolean copyArray) {
        super(BoolField.getInstance(), d, copyArray);
    }

    public BoolDenseMatrix(BoolFieldElement[] v) {
        super(BoolField.getInstance(), v);
    }
}
