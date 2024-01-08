package ru.spbu.cfpq.matrix;

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

        BoolDenseMatrix M1 = new BoolDenseMatrix(M1_data);
        BoolDenseMatrix M2 = new BoolDenseMatrix(M2_data);
        BoolDenseMatrix M = new BoolDenseMatrix(M_data);

        BoolDenseMatrix SUM = M1.add(M2);

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

        BoolSparseMatrix M1 = new BoolSparseMatrix(new BoolDenseMatrix(M1_data));
        BoolSparseMatrix M2 = new BoolSparseMatrix(new BoolDenseMatrix(M2_data));
        BoolSparseMatrix M = new BoolSparseMatrix(new BoolDenseMatrix(M_data));

        BoolSparseMatrix SUM = M1.add(M2);

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

        BoolDenseMatrix M1 = new BoolDenseMatrix(M1_data);
        BoolDenseMatrix M2 = new BoolDenseMatrix(M2_data);
        BoolDenseMatrix M = new BoolDenseMatrix(M_data);

        BoolDenseMatrix PROD = M1.multiply(M2);

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

        BoolSparseMatrix M1 = new BoolSparseMatrix(new BoolDenseMatrix(M1_data));
        BoolSparseMatrix M2 = new BoolSparseMatrix(new BoolDenseMatrix(M2_data));
        BoolSparseMatrix M = new BoolSparseMatrix(new BoolDenseMatrix(M_data));

        BoolSparseMatrix PROD = M1.multiply(M2);

        assertEquals(M, PROD);
    }
}
