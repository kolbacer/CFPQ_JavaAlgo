package ru.spbu.cfpq.matrix;

import org.apache.commons.math3.linear.FieldMatrix;

public interface BoolMatrix extends FieldMatrix<BoolFieldElement> {
    int NumberOfNonzeros();
}
