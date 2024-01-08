package ru.spbu.cfpq.matrix.impl;

import org.apache.commons.math3.linear.Array2DRowFieldMatrix;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import ru.spbu.cfpq.matrix.BoolField;
import ru.spbu.cfpq.matrix.BoolFieldElement;
import ru.spbu.cfpq.matrix.BoolMatrix;

public class BoolDenseMatrix extends Array2DRowFieldMatrix<BoolFieldElement> implements BoolMatrix {
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

    @Override
    public FieldMatrix<BoolFieldElement> createMatrix(int rowDimension, int columnDimension) {
        return new BoolDenseMatrix(rowDimension, columnDimension);
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

    @Override
    public Array2DRowFieldMatrix<BoolFieldElement> add(Array2DRowFieldMatrix<BoolFieldElement> m) throws MatrixDimensionMismatchException {
        return new BoolDenseMatrix(super.add(m).getDataRef(), false);
    }

    public BoolDenseMatrix add(BoolDenseMatrix m) {
        return (BoolDenseMatrix)add((Array2DRowFieldMatrix<BoolFieldElement>)m);
    }

    @Override
    public Array2DRowFieldMatrix<BoolFieldElement> multiply(Array2DRowFieldMatrix<BoolFieldElement> m) throws MatrixDimensionMismatchException {
        return new BoolDenseMatrix(super.multiply(m).getDataRef(), false);
    }

    public BoolDenseMatrix multiply(BoolDenseMatrix m) {
        return (BoolDenseMatrix)multiply((Array2DRowFieldMatrix<BoolFieldElement>)m);
    }

}
