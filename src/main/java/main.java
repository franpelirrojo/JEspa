import JEspa.corrector.Corrector;
import JEspa.util.CsvManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {
        CsvManager csvManager = new CsvManager("./data/frecuenciaFormas.csv");
        ArrayList<String> words = csvManager.getOneColum(0);

        Corrector corrector = new Corrector();
        List<String> instancias = corrector.editsOne("estigma");

        System.out.println(instancias.size());
    }
}
