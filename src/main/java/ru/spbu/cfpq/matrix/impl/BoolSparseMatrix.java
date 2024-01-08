package ru.spbu.cfpq.matrix.impl;

import org.apache.commons.math3.linear.SparseFieldMatrix;
import ru.spbu.cfpq.matrix.BoolField;
import ru.spbu.cfpq.matrix.BoolFieldElement;
import ru.spbu.cfpq.matrix.BoolMatrix;

public class BoolSparseMatrix extends SparseFieldMatrix<BoolFieldElement> implements BoolMatrix {
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

    @Override
    public BoolSparseMatrix createMatrix(int rowDimension, int columnDimension) {
        return new BoolSparseMatrix(rowDimension, columnDimension);
    }

    public int NumberOfNonzeros() {
        int nnz = 0;
        BoolFieldElement[][] data = getData();
        for (BoolFieldElement[] row : data)
            for (BoolFieldElement element : row)
                if (element != BoolFieldElement.ZERO)
                    ++nnz;
        return nnz;
    }

    public BoolSparseMatrix add(BoolSparseMatrix m) {
        return (BoolSparseMatrix)super.add(m);
    }

    public BoolSparseMatrix multiply(BoolSparseMatrix m) {
        return (BoolSparseMatrix)super.multiply(m);
    }

}
