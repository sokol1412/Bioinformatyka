package pl.edu.pg.eti.biocomp;

import pl.edu.pg.eti.biocomp.algorithms.NJ;
import pl.edu.pg.eti.biocomp.algorithms.UPGMA;
import pl.edu.pg.eti.biocomp.models.Tree;
import pl.edu.pg.eti.biocomp.utils.CSV;
import pl.edu.pg.eti.biocomp.utils.Matrix;
import pl.edu.pg.eti.biocomp.utils.TreePrinter;

class Main {

    public static void main(String[] args) {
        String fileName = "data/data1.matrix";
        Matrix matrix = CSV.load(fileName);

        UPGMA upgma = new UPGMA(matrix);
        Tree upgmaTree = upgma.generate();
        TreePrinter.print("\nUPGMA(" + fileName + ")", upgmaTree.getRootNode());

        NJ nj = new NJ(matrix);
        Tree njTree = nj.generate();
        TreePrinter.print("\nNJ(" + fileName + ")", njTree.getRootNode());
    }
}
