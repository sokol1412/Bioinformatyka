package pl.edu.pg.eti.biocomp.utils;


import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSV {

    public static Matrix load(String fileName) {
        try {
            CSVReader reader = new CSVReader(new FileReader(fileName));
            String[] header = reader.readNext();
            List<String[]> entries = reader.readAll();
            String[][] matrix = new String[entries.size()][];
            matrix = entries.toArray(matrix);
            return new Matrix(header, matrix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
