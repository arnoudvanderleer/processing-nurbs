package com.tempestasludi.processing.nurbs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NurbsTest {

    /**
     * Test case 1: Linear interpolation between two points.
     */
    private static void singleLinear(float t, float[] expected) {
        assertArrayEquals(
                expected,
                Nurbs.calcBasisFunctionValues(1, t, 1, new float[]{0, 0, 1, 1}),
                (float) 1e-5
        );
    }

    @Test
    public void singleLinearStart() {
        singleLinear(0, new float[]{1, 0});
    }

    @Test
    public void singleLinearMiddle() {
        singleLinear(0.31f, new float[]{0.69f, 0.31f});
    }

    @Test
    public void singleLinearEnd() {
        singleLinear(1, new float[]{0, 1});
    }

    /**
     * Test case 2: Linear interpolation between 5 points.
     */
    private static void multipleLinear(int k, float t, float[] expected) {
        assertArrayEquals(
                expected,
                Nurbs.calcBasisFunctionValues(k, t, 1, new float[]{-1, -1, 1, 2, 4, 5, 5}),
                (float) 1e-5
        );
    }

    @Test
    public void multipleLinearStart() {
        multipleLinear(
                1,
                -1,
                new float[]{1, 0}
        );
    }

    @Test
    public void multipleLinearFirstMiddle() {
        multipleLinear(
                1,
                .2f,
                new float[]{.4f, .6f}
        );
    }

    @Test
    public void multipleLinearFirstEnd() {
        multipleLinear(
                1,
                1,
                new float[]{0, 1}
        );
    }

    @Test
    public void multipleLinearSecondStart() {
        multipleLinear(
                2,
                1,
                new float[]{1, 0}
        );
    }

    @Test
    public void multipleLinearThirdMiddle() {
        multipleLinear(
                3,
                3.5f,
                new float[]{.25f, .75f}
        );
    }

    @Test
    public void multipleLinearFourthEnd() {
        multipleLinear(
                4,
                5,
                new float[]{0, 1}
        );
    }

    /**
     * Test case 3: Quadratic interpolation between 3 points.
     */
    private static void singleQuadratic(float t, float[] expected) {
        assertArrayEquals(
                expected,
                Nurbs.calcBasisFunctionValues(2, t, 2, new float[]{0, 0, 0, 1, 1, 1}),
                (float) 1e-5
        );
    }

    @Test
    public void singleQuadraticStart() {
        singleQuadratic(
                0, new float[]{1, 0, 0}
        );
    }

    @Test
    public void singleQuadraticMiddle1() {
        singleQuadratic(
                0.3f, new float[]{.49f, .6f - .18f, 0.09f}
        );
    }

    @Test
    public void singleQuadraticMiddle2() {
        singleQuadratic(
                0.5f, new float[]{.25f, 1 - .5f, .25f}
        );
    }

    @Test
    public void singleQuadraticMiddle3() {
        singleQuadratic(
                0.9f, new float[]{.01f, 1.8f - 1.62f, .81f}
        );
    }

    @Test
    public void singleQuadraticEnd() {
        singleQuadratic(
                1, new float[]{0, 0, 1}
        );
    }

    /**
     * Then the ultimate test: Cubic interpolation of 8 points.
     */
    private static void multipleCubic(int k, float t, float[] expected) {
        assertArrayEquals(
                expected,
                Nurbs.calcBasisFunctionValues(k, t, 3, new float[]{-1, -1, -1, -1, 1, 3, 3, 6, 7, 7, 7, 7}),
                (float) 1e-5
        );
    }

    @Test
    public void multipleCubicStart() {
        multipleCubic(3, -1, new float[] {1, 0, 0, 0});
    }

    @Test
    public void multipleCubicSecondMiddle() {
        multipleCubic(4, 2, new float[] {1/32f, 1/4f, -23/20f + 99/40f - 69/80f + 33/160f, 1/20f});
    }

    @Test
    public void multipleCubicFourthMiddle() {
        multipleCubic(6, 4, new float[] {8/45f, 149/240f, 13/72f, 1/48f});
    }

}