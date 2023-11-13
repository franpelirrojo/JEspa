import JEspa.corrector.Corrector;
import JEspa.util.CsvManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {
        CsvManager csvManager = new CsvManager("./data/frecuenciaFormas.csv");

        csvManager.constructDictionary(0);
    }
}
