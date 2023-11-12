import JEspa.util.CsvManager;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        CsvManager.spacebasedToCSV("./data/frecuencia_formas_ortograficas_1_0.txt",
                "./data/frecuenciaFormas.csv");

    }
}
