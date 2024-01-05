package ru.spbu.cfpq.matrix.impl;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.FieldMatrix;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
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
    public FieldMatrix<BoolFieldElement> add(FieldMatrix<BoolFieldElement> m) throws MatrixDimensionMismatchException {
        this.checkAdditionCompatible(m);
        int rowCount = this.getRowDimension();
        int columnCount = this.getColumnDimension();
        BoolSparseMatrix out = new BoolSparseMatrix(rowCount, columnCount);

        for(int row = 0; row < rowCount; ++row) {
            for(int col = 0; col < columnCount; ++col) {
                out.setEntry(row, col, this.getEntry(row, col).add(m.getEntry(row, col)));
            }
        }

        return out;
    }

    @Override
    public FieldMatrix<BoolFieldElement> multiply(FieldMatrix<BoolFieldElement> m) throws DimensionMismatchException {
        this.checkMultiplicationCompatible(m);
        int nRows = this.getRowDimension();
        int nCols = m.getColumnDimension();
        int nSum = this.getColumnDimension();
        BoolSparseMatrix out = new BoolSparseMatrix(nRows, nCols);

        for(int row = 0; row < nRows; ++row) {
            for(int col = 0; col < nCols; ++col) {
                BoolFieldElement sum = this.getField().getZero();

                for(int i = 0; i < nSum; ++i) {
                    sum = sum.add(this.getEntry(row, i).multiply(m.getEntry(i, col)));
                }

                out.setEntry(row, col, sum);
            }
        }

        return out;
    }

}
