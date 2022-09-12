package oneprog;

import java.io.BufferedReader;
import java.io.FileReader;

public class DynamicCovid_k_withPQ {
        public static void main(String[] args) throws Exception {

            BufferedReader buffer = new BufferedReader(new FileReader(Library.readFile()));
            String string;

            int k = Library.readIntFromUser("Give k: ");

            PQ queue = new PQ(k);
            Dynamic_Median median =new Dynamic_Median();
            int count = 0;

            while ((string = buffer.readLine()) != null) {
                count++;
                String[] words = Library.splitSentenceByWords(string);
                int i = 2;


                while (words[i].compareTo("A") >= 1 || words[i].equals("A")) {

                    words[1] = words[1] + " " + words[i];
                    ++i;

                }

                if(Library.checkData(words, i)){
                    System.out.println("Invalid data");
                }else {
                    if(queue.size() <=k) {
                        CityImpl toadd= new CityImpl(Integer.parseInt(words[0]), words[1], Integer.parseInt(words[i]), Integer.parseInt(words[i + 1]));
                        queue.insert(toadd);
                    }else{
                        CityImpl toadd= new CityImpl(Integer.parseInt(words[0]), words[1], Integer.parseInt(words[i]), Integer.parseInt(words[i + 1]));
                        if(toadd.getCovidCasesPerFiftyThousands()>queue.min().getCovidCasesPerFiftyThousands()){
                            queue.remove(queue.min().getID());
                            queue.insert(toadd);
                        }
                    }
                }
                median.insert(new CityImpl(Integer.parseInt(words[0]), words[1], Integer.parseInt(words[i]), Integer.parseInt(words[i + 1])));
                if(count%5==0)
                    median.print();

            }



            Library.printResultPQ(queue, k);
            

        }
}


