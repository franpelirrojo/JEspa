package JEspa.corrector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

public class Corrector {
    private static final String DICTIONARY = null;
    /*
    La función corregir y la clase JEspa.corrector.Corrector están nombradas en castellano por coherencia con su uso
    El resto del código está comentado en ingles.

    We use UTF-8 because of manners
    */
    public Corrector() {
    }

    /*
    All simple edits at one distance. A simple edit can be: deletion, transposition, replacement or an insertion.
    For a word of length n, there will be n deletions, n-1 transpositions, 27n replacements, and 27(n+1) insertions,
    for a total of 56n+26. For example "leche" (n = 5, n-1 = 4, 27n = 135, 27(n+1) = 162) is equal to 306 possibilities.
     */

    public List<String> editsOne(String word){ //TODO why is a list, make it array
        String letters = "abcdefghijklmnñopqrstuvwxyz";
        List<String[]> combinations = new ArrayList<>();
        List<String> deletes = new ArrayList<>();
        List<String> transposes = new ArrayList<>();
        List<String> remplaces = new ArrayList<>();
        List<String> inserts = new ArrayList<>();

        for (int i = 0; i < word.length() + 1; i++){
            String prefix = word.substring(0,i);
            String sufix = word.substring(i);
            String[] splitted = {prefix, sufix};
            combinations.add(splitted);
        }

        //Deletions
        int count = 0;
        for (String[] tuple : combinations){
            if (!tuple[1].equals("")){
                deletes.add(tuple[0] + tuple[1].substring(1));
            }
        }

        //Transpositions
        for (String[] tuple : combinations ){
            if (1 < tuple[1].length()){
                transposes.add(tuple[0] +
                        tuple[1].charAt(1) +
                        tuple[1].charAt(0) +
                        tuple[1].substring(2));
            }
        }

        //Replacements
        for (String[] tuple : combinations ){
           for (char character : letters.toCharArray()) {
               if (!tuple[1].equals("")){
                   remplaces.add(tuple[0] +
                           character +
                           tuple[1].substring(1));
               }
           }
        }

        //Insertions
        for (String[] tuple : combinations ){
            for (char character : letters.toCharArray()) {
                inserts.add(tuple[0] +
                        character +
                        tuple[1]);
            }
        }

        return Stream.of(deletes.stream(), transposes.stream(),
                remplaces.stream(), inserts.stream()).flatMap(Function.identity())
                .map(Object::toString).toList();
    }

    void known(ArrayList<String> words){

    }

}
