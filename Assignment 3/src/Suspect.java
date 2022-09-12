class Suspect {
    private int AFM;
    private String firstName;
    private String lastName;
    private double savings;
    private double taxedIncome;

    public Suspect(int AFM, String firstName, String lastName, double savings, double taxedIncome){
        this.AFM = AFM;
        this.firstName = firstName;
        this.lastName = lastName;
        this.savings = savings;
        this.taxedIncome = taxedIncome;
    }

    int key() {
        return AFM;
    }

    String getLastName(){
        return this.lastName;
    }
    String getFullName(){
        return this.firstName + " " + this.lastName;
    }
    double getSavings(){
        return this.savings;
    }
    double getTaxedIncome(){
        return this.taxedIncome;
    }
    public void setSavings(double savings){
        this.savings = savings;
    }
    public String toString(){
        return "AFM: " + key() + "\n" + "Full name: " + getFullName() + "\n" + "Savings: " + getSavings();
    }
}
