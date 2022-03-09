package com.tempestasludi.processing.nurbs;

import java.util.Arrays;
import java.util.function.BiFunction;

/**
 * Base class of all nurbs-related classes. Implements the overarching functionality of calculating base functions and default values.
 */
public class Nurbs {
    /**
     * Calculates the basis function values for knot span {@code k} at time {@code t}.
     *
     * @param k The knot span to calculate for
     * @param t The time to calculate the function values for
     * @param degree The degree of the functions to calculate
     * @param knotVector The knot vector to calculate for
     * @return The values of basis functions {@code k - degree} till {@code k} (so {@code degree + 1} values) at time {@code t}
     */
    protected static float[] calcBasisFunctionValues(int k, float t, int degree, float[] knotVector) {
        if (k < 0 || knotVector.length <= k + 1) {
            throw new IllegalArgumentException("Knot out of bounds.");
        }
        if (knotVector[k + 1] <= knotVector[k]) {
            throw new IllegalArgumentException("The knot interval has nonpositive length.");
        }

        float[][] basisValues = new float[degree + 1][degree + 1];

        BiFunction<Integer, Integer, Float> getF = (n, i) ->
                (knotVector[i + n] <= knotVector[i])
                        ? 1
                        : (t - knotVector[i]) / (knotVector[i + n] - knotVector[i]);

        for (int n = 0; n <= degree; n++) {
            for (int i = Math.max(degree - k, degree - n); i <= degree && i + k + n - degree < knotVector.length - 1; i++) {
                if (n == 0) {
                    basisValues[n][i] = 1;
                    continue;
                }

                float v1 = (i <= degree - n) ? 0 : basisValues[n - 1][i];
                float v2 = (degree < i + 1) ? 0 : basisValues[n - 1][i + 1];

                basisValues[n][i] = v1 * getF.apply(n, i + k - degree) + v2 * (1 - getF.apply(n, i + k - degree + 1));
            }
        }
        return basisValues[degree];
    }

    /**
     * Gives a default value for the weights vector.
     *
     * @param length The number of weights that are to be generated
     * @return An array of length {@code length}, filled with ones
     */
    protected static float[] getWeights(int length) {
        float[] result = new float[length];
        Arrays.fill(result, 1);
        return result;
    }

    /**
     * Gives a default value for the two-dimensional weights array.
     *
     * @param length The range of the first parameter of the array
     * @param width The range of the second parameter of the array
     * @return A two-dimensional array of size {@code length} x {@code width}, filled with ones
     */
    protected static float[][] getWeights(int length, int width) {
        float[][] result = new float[length][width];
        for (float[] row: result) {
            Arrays.fill(row, 1);
        }
        return result;
    }

    /**
     * Gives a default value for the knot vector, in which every nontrivial knot span has length 1 and with {@code degree} trivial knot spans at the start and end
     *
     * @param pointCount The number of points that the knot vector should describe
     * @param degree The degree of the curve that the knot vector should describe
     * @return A knot vector that describes a nurbs of degree {@code degree}, with {@code pointCount} points
     */
    protected static float[] getKnots(int pointCount, int degree) {
        float[] result = new float[pointCount + degree + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.max(Math.min(i, pointCount) - degree, 0);
        }
        return result;
    }
}
