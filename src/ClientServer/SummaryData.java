package ClientServer;

public class SummaryData {
    private double count;
    private double totalPop;
    private double avgPop;

    public SummaryData(double count, double totalPop, double avgPop) {
        this.count = count;
        this.totalPop = totalPop;
        this.avgPop = avgPop;
    }

    public double getCount() {
        return count;
    }

    public double getTotalPop() {
        return totalPop;
    }

    public double getAvgPop() {
        return avgPop;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public void setTotalPop(double totalPop) {
        this.totalPop = totalPop;
    }

    public void setAvgPop(double avgPop) {
        this.avgPop = avgPop;
    }

    public void display()
    {
        System.out.println("SUMMARY DATA OF COUNTRIES");
        System.out.println("==================================");
        System.out.println("Number of Countries: " + this.count);
        System.out.println("Total Population   : " + this.totalPop);
        System.out.println("Average Population : " + this.avgPop);
        System.out.println("==================================");
    }
}
