package com.tempestasludi.processing.nurbs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;

import static org.junit.jupiter.api.Assertions.*;

class NurbsSurfaceTest {

    NurbsSurface linearQuadraticsurface;

    @BeforeEach
    void setup() {
        linearQuadraticsurface = new NurbsSurface(
                new PVector[][] {
                        {new PVector(0, 0, 0), new PVector(0, 1, 0), new PVector(0, 2, 1), new PVector(0, 3, 1)},
                        {new PVector(1, 0, 0), new PVector(1, 1, 1), new PVector(1, 2, 0), new PVector(1, 3, 1)},
                        {new PVector(2, 0, 0), new PVector(2, 1, 0), new PVector(2, 2, 1), new PVector(2, 3, 1)},
                },
                new float[][] {
                        {1, 1, 1, 1},
                        {1, 2, 2, 1},
                        {1, 1, 1, 1}
                },
                new float[] {0, 0, 2, 5, 5},
                new float[] {0, 0, 0, 2, 5, 5, 5}
        );
    }

    @Test
    void weightedLinear1() {
        assertArrayEquals(
                new PVector(7/11f, 51/55f, 28/55f).array(),
                linearQuadraticsurface.evaluate(1, 1).array(),
                1e-5f
        );
    }

    @Test
    void weightedLinear2() {
        assertArrayEquals(
                new PVector(17/26f, 9/5f, 31/65f).array(),
                linearQuadraticsurface.evaluate(1, 3).array(),
                1e-5f
        );
    }

    @Test
    void weightedLinear3() {
        assertArrayEquals(
                new PVector(23/15f, 68/75f, 2/5f).array(),
                linearQuadraticsurface.evaluate(4, 1).array(),
                1e-5f
        );
    }

    @Test
    void weightedLinear4() {
        assertArrayEquals(
                new PVector(46/37f, 83/37f, 94/185f).array(),
                linearQuadraticsurface.evaluate(3, 4).array(),
                1e-5f
        );
    }

}