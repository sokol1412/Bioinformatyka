package pl.edu.pg.eti.biocomp.algorithms;

import pl.edu.pg.eti.biocomp.models.Point;
import pl.edu.pg.eti.biocomp.models.Tree;
import pl.edu.pg.eti.biocomp.utils.Matrix;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class UPGMA {
    private final Matrix distances;

    public UPGMA(Matrix initialDistances) {
        this.distances = initialDistances;
    }

    public Tree run() {
        int n = distances.getData().length;
        Tree[] clusters = new Tree[n];
        for (int i = 0; i < n; i++) {
            clusters[i] = new Tree(String.valueOf((char) ('a' + i)));
        }

        while (clusters.length > 1) {
            double[][] clusterDistances = getClusterDistances(clusters);
            Point minDistancePoint = Matrix.findLowestValuePoint(clusterDistances);
            clusters = mergeClusters(clusters, minDistancePoint);
        }
        assert clusters.length == 1;
        return clusters[0];
    }


    private double[][] getClusterDistances(Tree[] clusters) {
        int n = clusters.length;
        double[][] clusterDistances = Matrix.initQuadraticWithValue(n, Double.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                clusterDistances[i][j] = distance(clusters[i], clusters[j]);
            }
        }
        return clusterDistances;
    }


    private double distance(Tree a, Tree b) {
        int aCount = a.getSize();
        int bCount = b.getSize();
        double distance = 0;
        for (String aLabel : a.getLabels()) {
            for (String bLabel : b.getLabels()) {
                distance += distances.getData()[labelToPosition(aLabel)][labelToPosition(bLabel)];
            }
        }
        distance /= (aCount * bCount);
        return distance;

    }


    private int labelToPosition(String label) {
        String[] header = distances.getHeader();
        for (int i = 0; i < header.length; i++) {
            String colName = header[i];
            if (colName.equals(label)) {
                return i;
            }
        }
        throw new RuntimeException("Something went wrong with header");
    }

    private Tree[] mergeClusters(Tree[] clusters, Point minPoint) {
        int n = clusters.length;
        List<Integer> toMerge = minPoint.positionAsList();
        Tree[] newClusters = new Tree[n - 1];
        newClusters[0] = clusters[toMerge.get(0)].merge(clusters[toMerge.get(1)], minPoint.getValue());
        for (int i = 0, j = 1; i < n; i++) {
            if (!toMerge.contains(i)) {
                newClusters[j++] = clusters[i];
            }
        }
        return newClusters;
    }
}
