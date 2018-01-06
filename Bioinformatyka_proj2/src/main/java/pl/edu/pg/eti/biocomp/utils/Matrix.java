package pl.edu.pg.eti.biocomp.utils;

import pl.edu.pg.eti.biocomp.models.Point;

import java.util.Arrays;
import java.util.logging.Logger;

public class Matrix {
    private String[] header;
    private double[][] data;

    public Matrix(String[] header, String[][] data) {
        this.header = header;
        this.data = format(data);
    }

    public String[] getHeader() {
        return header;
    }

    public double[][] getData() {
        return data;
    }

    public String getLastColumnName() {
        return header[header.length - 1];
    }

    public void removeTwoAddOne(int remove1, int remove2, String newLabel) {
        int min = Math.min(remove1, remove2);
        int max = Math.max(remove1, remove2);

        String[] tmpHeader = header;
        header = new String[header.length - 1];
        System.arraycopy(tmpHeader, 0, header, 0, min);
        System.arraycopy(tmpHeader, min + 1, header, min, max - min - 1);
        System.arraycopy(tmpHeader, max + 1, header, max - 1, tmpHeader.length - max - 1);
        header[header.length - 1] = newLabel;

        double[][] tmpData = data;
        data = initQuadraticWithValue(tmpData.length - 1, Double.NaN);
        for (int i = 0; i < tmpData.length; i++) {
            for (int j = 0; j < tmpData.length; j++) {
                if (i != min && i != max || j != min && j != max) {
                    if (i > max && j > max) {
                        data[i - 2][j - 2] = tmpData[i][j];
                    } else if (i > max) {
                        data[i - 2][j] = tmpData[i][j];
                    } else if (j > max) {
                        data[i][j - 2] = tmpData[i][j];
                    } else if (i > min && j > min) {
                        data[i - 1][j - 1] = tmpData[i][j];
                    } else if (i > min) {
                        data[i - 1][j] = tmpData[i][j];
                    } else if (j > min) {
                        data[i][j - 1] = tmpData[i][j];
                    } else if (i < min && j < min) {
                        data[i][j] = tmpData[i][j];
                    }
                }
            }
        }
    }

    private double[][] format(String[][] matrix) {
        double[][] doubleMatrix = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                doubleMatrix[i][j] = Double.parseDouble(matrix[i][j]);
            }
        }
        return doubleMatrix;
    }

    // Inicjalizacja macierzy maksymalną wartościa doubla
    public static double[][] initQuadraticWithValue(int size, double value) {
        double[][] matrix = new double[size][size];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = value;
            }
        }
        return matrix;
    }

    public static Point findLowestValuePoint(double[][] matrix) {
        int n = matrix.length;
        Point minPoint = new Point(0, 0, Double.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i != j) {
                    double distance = matrix[i][j];
                    if (distance < minPoint.getValue()) {
                        minPoint = new Point(i, j, distance);
                    }
                }
            }
        }
        return minPoint;
    }
}
