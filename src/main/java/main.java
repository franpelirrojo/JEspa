import JEspa.util.CsvManager;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) throws IOException {
        CsvManager csvManager = new CsvManager("./data/frecuenciaFormas.csv");
        ArrayList<String> words = csvManager.getOneColum(0);

    }
}
