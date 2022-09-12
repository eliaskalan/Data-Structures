import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        final String menu = "1. Read from a file\n"+"2. Add a Suspect\n" +
                "3. Remove a suspect\n"+"4. Search by AFM\n"+"5. Search by Last Name\n"
                +"6. Get mean savings\n"+"7. Print top suspect\n"+
                "8. Update savings\n"+"9. print by AFM\n"+"10. exit;\n";
        RandomizedBST dome = new RandomizedBST();
        while (true){
            System.out.println(menu);
            Scanner myObj = new Scanner(System.in);
            System.out.print("Enter code: ");
            int code = myObj.nextInt();
            if(code == 1){
                dome.load(Library.readFile());
            }else if(code == 2){
                dome.insert(Library.readSuspect());
            }else if(code==3){
                dome.remove(Library.readAFM());
            } else if(code == 4){
                Suspect suspect = dome.searchByAFM(Library.readAFM());
                if(suspect == null){
                    System.out.println("There is no suspect with this AFM");
                }else {
                    System.out.println(suspect);
                }
            }else if(code ==5){
               System.out.println(dome.searchByLastName(Library.readLastName()));
            }else if(code==6){
                System.out.println(dome.getMeanSavings());
            }else if(code ==7){
                dome.printTopSuspects(Library.readKSuspects());
            }else if(code == 8){
                dome.updateSavings(Library.readAFM(), Library.readSavings());
            }else if(code == 9){
               dome.printByAFM();
            }else if(code == 10){
                System.out.println("See you later!");
                break;
            }else{
                System.out.println("Wrong Input");
            }
        }
    }
}
