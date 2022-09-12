import java.io.File;
import java.util.Scanner;

public class Library {
    public static int readAFM(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter AFM");
        return myObj.nextInt();
    }
    public static double readSavings(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter Savings");
        return myObj.nextDouble();
    }
    public static int readKSuspects(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter How many Suspects do you want to check:    ");
        return myObj.nextInt();
    }
    public static String readLastName(){
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter Last Name");
        return myObj.nextLine();
    }
    public static Suspect readSuspect(){
        Scanner myObj = new Scanner(System.in);
        int AFM;
        String firstName;
        String lastName;
        double savings;
        double taxedIncome;
        System.out.println("Enter AFM");
        AFM = Integer.parseInt(myObj.nextLine());
        System.out.println("Enter first name");
        firstName = myObj.nextLine();
        System.out.println("Enter last name");
        lastName = myObj.nextLine();
        System.out.println("Enter savings");
        savings = myObj.nextDouble();
        System.out.println("Enter taxedIncome");
        taxedIncome = myObj.nextDouble();
        return new Suspect(AFM, firstName, lastName, savings, taxedIncome);
    }

    public static String readFile(){
        File file;
        do {
            System.out.println("Please add the txt file name (only the name, without .txt) ");
            Scanner myObj = new Scanner(System.in);
            String fileName = myObj.nextLine();
            file = new File(fileName +".txt");
        }while(!file.exists());
        return file.toString();
    }

    public static String[] splitSentenceByWords(String str) {
        if (str == null || str.equals(""))
            return new String[0];

        return str.split(" ");
    }

    public static int log2(int N)
    {
        return (int)(Math.log(N) / Math.log(2));
    }

    public static void printResultPQ(PQ queue, int k){
        int size = queue.size();
        if (k<=size) {
            System.out.println("The top " + k + " suspects are: ");
            for (int i = 0; i < k; ++i) {
                Suspect print = queue.getMax();
                System.out.println(print.toString());
            }
        }else{
            System.out.println("max k you can give: " + size);
        }
    }

}
