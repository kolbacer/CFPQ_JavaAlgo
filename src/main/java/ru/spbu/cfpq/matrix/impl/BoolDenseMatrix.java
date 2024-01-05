package ru.spbu.cfpq.matrix.impl;

import org.apache.commons.math3.exception.DimensionMismatchException;
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
        BoolDenseMatrix out = new BoolDenseMatrix(rowCount, columnCount);

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
        BoolDenseMatrix out = new BoolDenseMatrix(nRows, nCols);

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
