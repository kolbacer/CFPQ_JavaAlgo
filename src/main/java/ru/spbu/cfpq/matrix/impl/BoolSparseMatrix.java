package ru.spbu.cfpq.matrix.impl;

import org.apache.commons.math3.linear.SparseFieldMatrix;
import ru.spbu.cfpq.matrix.BoolField;
import ru.spbu.cfpq.matrix.BoolFieldElement;
import ru.spbu.cfpq.matrix.BoolMatrix;

public class BoolSparseMatrix extends SparseFieldMatrix<BoolFieldElement> {
    public BoolSparseMatrix() {
        super(BoolField.getInstance());
    }

    public BoolSparseMatrix(int rowDimension, int columnDimension) {
        super(BoolField.getInstance(), rowDimension, columnDimension);
    }

    public BoolSparseMatrix(BoolSparseMatrix other) {
        super(other);
    }

    public BoolSparseMatrix(BoolMatrix other) {
        super(other);
    }
}
