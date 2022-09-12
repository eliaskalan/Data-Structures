package oneprog;

import java.io.File;
import java.util.Scanner;

public class Library {
    public static float round(float value, int places) {
       return  Math.round(value * 100)/100.0f;
    }

    public static String[] splitSentenceByWords(String str) {

        //if string is empty or null, return empty array
        if (str == null || str.equals(""))
            return new String[0];

        String[] words = str.split(" ");
        return words;
    }

    public static Integer readIntFromUser(String message){
        Scanner a = new Scanner(System.in);
        System.out.println(message);
        return a.nextInt();
    }
    public static int log2(int N)
    {
        int result = (int)(Math.log(N) / Math.log(2));

        return result;
    }
    public static File readFile(){
        File file;
        do {
            System.out.println("Please add the txt file in the 'text_file' folder and enter the name of the file (Without .txt)");
            Scanner myObj = new Scanner(System.in);
            String fileName = myObj.nextLine();
            file = new File("./text_file/" + fileName +".txt");
        }while(!file.exists());
        return file;
    }
    public static Boolean checkData(String[] words, int i){
        return Integer.parseInt(words[0])>9999||Integer.parseInt(words[0])<=0||Integer.parseInt(words[i])>10000000
                ||Integer.parseInt(words[i])<=0||words[1].length()>50||Integer.parseInt(words[i])<Integer.parseInt(words[i + 1]);
    }
    public static void printResultPQ(PQ queue, int k){
        if (k<=queue.size()) {
            System.out.println("The top " + k + " cites are: ");
            for (int i = 0; i < k; ++i) {
                CityImpl print = queue.getMax();
                System.out.println(print.toString());
            }
        }else{
            System.out.println("Invalid k");
        }
    }

}
