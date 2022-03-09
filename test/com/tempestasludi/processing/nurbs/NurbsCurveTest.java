package com.tempestasludi.processing.nurbs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.*;

class NurbsCurveTest {

    NurbsCurve weightedLinear;
    NurbsCurve weightedQuadratic;

    @BeforeEach
    void setup() {
        weightedLinear = new NurbsCurve(
                new PVector[]{new PVector(0, 0), new PVector(1, 1), new PVector(2, 0)},
                new float[] {1, 2, 3},
                new float[] {0, 0, 2, 5, 5}
        );

        weightedQuadratic = new NurbsCurve(
                new PVector[]{new PVector(0, 0), new PVector(1, 1), new PVector(2, 0), new PVector(3, 1)},
                new float[] {1, 2, 3, 4},
                new float[] {0, 0, 0, 2, 5, 5, 5}
        );
    }

    /**
     * Test case 1: A weighted linear nurbs with three points.
     */

    @Test
    void weightedLinearFirstMiddle() {
        assertArrayEquals(
                new PVector(2/3f, 2/3f).array(),
                weightedLinear.evaluate(1).array(),
                1e-5f
        );
    }

    @Test
    void weightedLinearSecondMiddle1() {
        assertArrayEquals(
                new PVector(10/7f, 4/7f).array(),
                weightedLinear.evaluate(3).array(),
                1e-5f
        );
    }

    @Test
    void weightedLinearSecondMiddle2() {
        assertArrayEquals(
                new PVector(7/4f, 1/4f).array(),
                weightedLinear.evaluate(4).array(),
                1e-5f
        );
    }

    /**
     * Test case 2: A weighted quadratic nurbs with four points.
     */

    @Test
    void weightedQuadraticFirstMiddle() {
        assertArrayEquals(
                new PVector(38/37f, 26/37f).array(),
                weightedQuadratic.evaluate(1).array(),
                1e-5f
        );
    }

    @Test
    void weightedQuadraticSecondMiddle() {
        assertArrayEquals(
                new PVector(63/32f, 11/32f).array(),
                weightedQuadratic.evaluate(3).array(),
                1e-5f
        );
    }

}