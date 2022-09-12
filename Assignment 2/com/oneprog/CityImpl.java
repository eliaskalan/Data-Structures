package oneprog;

public class CityImpl implements City,Comparable{

    private int ID;
    private String name;
    private int population;
    private int covidCases;
    private float covidCasesPerFiftyThousends;

    public CityImpl(int ID,String name,int population,int covidCases){
        setCovidCases(covidCases);
        setPopulation(population);
        setId(ID);
        setName(name);
        calculateDensity(covidCases, population);
    }


    public int getID() {
        return ID;
    }


    public String getName() {
        return name;
    }


    public int getPopulation() {
        return population;
    }


    public int getCovidCases() {
        return covidCases;
    }


    public void setName(String name) {
        this.name=name;
    }



    public void setId(int ID) {
        this.ID=ID;

    }


    public void setPopulation(int population) {
        this.population=population;
    }

    public void calculateDensity(int covidCases, int population){
        this.covidCasesPerFiftyThousends =  Library.round((float) covidCases * 50000 / population, 2);
    }
    public void setCovidCases(int covidCases) {
        this.covidCases=covidCases;
    }
    public float getCovidCasesPerFiftyThousands(){
        return covidCasesPerFiftyThousends;
    }


    public float compare(CityImpl otherCity,int opcode) {
            if (this.getCovidCasesPerFiftyThousands() == otherCity.getCovidCasesPerFiftyThousands()) {
                if (this.getName().compareTo(otherCity.getName()) < 0) {
                    return 1;
                }
                if (this.getName().equals(otherCity.getName())) {
                    if (this.getID() < otherCity.getID()) {
                        return 1;
                    }
                }
            }
        return (this.getCovidCasesPerFiftyThousands()-otherCity.getCovidCasesPerFiftyThousands())*opcode;
    }

    public String toString() {
        return this.getName();
    }
}
