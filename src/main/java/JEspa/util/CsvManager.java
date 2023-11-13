package JEspa.util;

import java.io.*;
import java.util.*;

public class CsvManager {
    private final String CSV;
    public CsvManager(String csv) {
        CSV = csv;
    }

    public static void spacebasedToCSV(String input, String output) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(input));
            BufferedWriter writer = new BufferedWriter(new FileWriter(output));

            String[] row;
            StringBuilder line = new StringBuilder();
            while (reader.ready()){
                line.delete(0, line.length());
                row = reader.readLine().split("\\s");;

                for (int i = 0; i < row.length-1; i++){
                    line.append(row[i]).append(",");
                }
                line.append(row[row.length - 1]).append("\n");

                writer.write(line.toString());
            }
        } catch (IOException e) {throw new RuntimeException(e);
        }
    }

    /*
    Return the column that corresponds wih the passed index
     */
    public ArrayList<String> getOneColum(int column){
        ArrayList<String> words = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(CSV));

            String[] row;
            while (reader.ready()){
                row = reader.readLine().split(",([^,*])"); //Allow have "," in a column
                words.add(row[column]);
            }
        } catch (IOException e) {throw new RuntimeException(e);
        }

        return words;
    }


}
