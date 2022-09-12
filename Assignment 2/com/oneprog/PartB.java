package oneprog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class PartB {


    public static void main(String[] args) throws Exception {

        BufferedReader buffer = new BufferedReader(new FileReader(Library.readFile()));
        String string;

        PQ queue = new PQ();
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
                queue.insert(new CityImpl(Integer.parseInt(words[0]), words[1], Integer.parseInt(words[i]), Integer.parseInt(words[i + 1])));

            }
            
        }


        
        Scanner a = new Scanner(System.in);
        System.out.println("Give k: ");
        int k =a.nextInt();
        Library.printResultPQ(queue, k);




    }
}

