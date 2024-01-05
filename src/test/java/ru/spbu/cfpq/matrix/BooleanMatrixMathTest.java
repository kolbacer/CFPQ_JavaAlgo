package ru.spbu.cfpq.matrix;

import org.apache.commons.math3.linear.FieldMatrix;
import org.junit.jupiter.api.Test;
import ru.spbu.cfpq.matrix.impl.BoolDenseMatrix;
import ru.spbu.cfpq.matrix.impl.BoolSparseMatrix;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BooleanMatrixMathTest {

    final BoolFieldElement TRUE = BoolFieldElement.ONE;
    final BoolFieldElement FALSE = BoolFieldElement.ZERO;

    @Test
    public void givenBoolDenseMatrices_whenAdd_thenReturnCorrectSum() {
        BoolFieldElement[][] M1_data = new BoolFieldElement[][] {
                {TRUE, TRUE, FALSE},  // 1 1 0
                {FALSE, TRUE, FALSE}, // 0 1 0
                {FALSE, FALSE, TRUE}  // 0 0 1
        };

        BoolFieldElement[][] M2_data = new BoolFieldElement[][] {
                {FALSE, TRUE, FALSE}, // 0 1 0
                {TRUE, TRUE, FALSE},  // 1 1 0
                {TRUE, FALSE, FALSE}  // 1 0 0
        };

        BoolFieldElement[][] M_data = new BoolFieldElement[][] {
                {TRUE, TRUE, FALSE}, // 1 1 0
                {TRUE, TRUE, FALSE}, // 1 1 0
                {TRUE, FALSE, TRUE}  // 1 0 1
        };

        FieldMatrix<BoolFieldElement> M1 = new BoolDenseMatrix(M1_data);
        FieldMatrix<BoolFieldElement> M2 = new BoolDenseMatrix(M2_data);
        FieldMatrix<BoolFieldElement> M = new BoolDenseMatrix(M_data);

        FieldMatrix<BoolFieldElement> SUM = M1.add(M2);

        assertEquals(M, SUM);
    }

    @Test
    public void givenBoolSparseMatrices_whenAdd_thenReturnCorrectSum() {
        BoolFieldElement[][] M1_data = new BoolFieldElement[][] {
                {TRUE, TRUE, FALSE},  // 1 1 0
                {FALSE, TRUE, FALSE}, // 0 1 0
                {FALSE, FALSE, TRUE}  // 0 0 1
        };

        BoolFieldElement[][] M2_data = new BoolFieldElement[][] {
                {FALSE, TRUE, FALSE}, // 0 1 0
                {TRUE, TRUE, FALSE},  // 1 1 0
                {TRUE, FALSE, FALSE}  // 1 0 0
        };

        BoolFieldElement[][] M_data = new BoolFieldElement[][] {
                {TRUE, TRUE, FALSE}, // 1 1 0
                {TRUE, TRUE, FALSE}, // 1 1 0
                {TRUE, FALSE, TRUE}  // 1 0 1
        };

        FieldMatrix<BoolFieldElement> M1 = new BoolSparseMatrix(new BoolDenseMatrix(M1_data));
        FieldMatrix<BoolFieldElement> M2 = new BoolSparseMatrix(new BoolDenseMatrix(M2_data));
        FieldMatrix<BoolFieldElement> M = new BoolSparseMatrix(new BoolDenseMatrix(M_data));

        FieldMatrix<BoolFieldElement> SUM = M1.add(M2);

        assertEquals(M, SUM);
    }

    @Test
    public void givenBoolDenseMatrices_whenMultiply_thenReturnCorrectProduct() {
        BoolFieldElement[][] M1_data = new BoolFieldElement[][] {
                {TRUE, TRUE, FALSE},  // 1 1 0
                {FALSE, TRUE, FALSE}, // 0 1 0
                {FALSE, FALSE, TRUE}  // 0 0 1
        };

        BoolFieldElement[][] M2_data = new BoolFieldElement[][] {
                {FALSE, TRUE, FALSE}, // 0 1 0
                {TRUE, TRUE, FALSE},  // 1 1 0
                {TRUE, FALSE, FALSE}  // 1 0 0
        };

        BoolFieldElement[][] M_data = new BoolFieldElement[][] {
                {TRUE, TRUE, FALSE},  // 1 1 0
                {TRUE, TRUE, FALSE},  // 1 1 0
                {TRUE, FALSE, FALSE}  // 1 0 0
        };

        FieldMatrix<BoolFieldElement> M1 = new BoolDenseMatrix(M1_data);
        FieldMatrix<BoolFieldElement> M2 = new BoolDenseMatrix(M2_data);
        FieldMatrix<BoolFieldElement> M = new BoolDenseMatrix(M_data);

        FieldMatrix<BoolFieldElement> PROD = M1.multiply(M2);

        assertEquals(M, PROD);
    }

    @Test
    public void givenBoolSparseMatrices_whenMultiply_thenReturnCorrectProduct() {
        BoolFieldElement[][] M1_data = new BoolFieldElement[][] {
                {TRUE, TRUE, FALSE},  // 1 1 0
                {FALSE, TRUE, FALSE}, // 0 1 0
                {FALSE, FALSE, TRUE}  // 0 0 1
        };

        BoolFieldElement[][] M2_data = new BoolFieldElement[][] {
                {FALSE, TRUE, FALSE}, // 0 1 0
                {TRUE, TRUE, FALSE},  // 1 1 0
                {TRUE, FALSE, FALSE}  // 1 0 0
        };

        BoolFieldElement[][] M_data = new BoolFieldElement[][] {
                {TRUE, TRUE, FALSE},  // 1 1 0
                {TRUE, TRUE, FALSE},  // 1 1 0
                {TRUE, FALSE, FALSE}  // 1 0 0
        };

        FieldMatrix<BoolFieldElement> M1 = new BoolSparseMatrix(new BoolDenseMatrix(M1_data));
        FieldMatrix<BoolFieldElement> M2 = new BoolSparseMatrix(new BoolDenseMatrix(M2_data));
        FieldMatrix<BoolFieldElement> M = new BoolSparseMatrix(new BoolDenseMatrix(M_data));

        FieldMatrix<BoolFieldElement> PROD = M1.multiply(M2);

        assertEquals(M, PROD);
    }
}
