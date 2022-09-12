import java.io.IOException;

public interface MafiaInterface {
    void insert(Suspect item);
    void load(String filename) throws IOException;
    void updateSavings(int AFM, double savings);
    Suspect searchByAFM(int AFM);
    List searchByLastName(String last_name);
    void remove(int AFM);
    double getMeanSavings();
    void printTopSuspects(int k);
    void printByAFM();
}
