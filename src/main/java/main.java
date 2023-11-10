import java.util.List;

public class main {
    public static void main(String[] args) {
        List<String> list = new Corrector().editsOne("concatenación");

        for (String words : list){
            System.out.println(words);
        }

        System.out.println(list.size());

    }
}
