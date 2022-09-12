package oneprog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
public class Covid_k {

    public static void main(String[] args) throws Exception {

        BufferedReader buffer = new BufferedReader(new FileReader(Library.readFile()));
        String string;

        int k = Library.readIntFromUser("Give k: ");

        PQ_PartA list = new PQ_PartA();

        while ((string = buffer.readLine()) != null) {
            String[] words = Library.splitSentenceByWords(string);
            int i = 2;
            while (words[i].compareTo("A") >= 1 || words[i].equals("A")) {
                words[1] = words[1] + " " + words[i];
                ++i;
            }
            if(Library.checkData(words, i)){
                System.out.println("Invalid data");
            }else {
                    list.insert(new CityImpl(Integer.parseInt(words[0]), words[1], Integer.parseInt(words[i]), Integer.parseInt(words[i + 1])));
            }

        }


        if (k<=list.size()) {
            list.sort();
            list.print(k);
        }else {
            System.out.println("Invalid k");
        }


    }
}
