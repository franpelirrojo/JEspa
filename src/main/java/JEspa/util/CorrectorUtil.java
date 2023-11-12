package JEspa.util;

import java.io.*;
import java.util.*;

public class CorrectorUtil {
    public CorrectorUtil() {
    }

    public void spacebasedToCSV(String input, String output) {
        try{
            if (output == null){
                output = input;
                output.split("[\\/]");
            }
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


}
