package Model;

public class Investment extends Product {

    private String investor;
    private int investmentId;

    public static int lastInvestmentId = 0;

    public Investment(String investorName, Long investmentAmount, ProductType investmentType, double investmentTerm)
    {
        super(investmentType, investmentAmount, investmentTerm);
        this.investor = investorName;
        this.investmentId = lastInvestmentId++;
    }

    public String getInvestor() {
        return investor;
    }

    public void setInvestor(String investor) {
        this.investor = investor;
    }

    public int getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(int investmentId) {
        this.investmentId = investmentId;
    }

    public static int getLastInvestmentId() {
        return lastInvestmentId;
    }

    public static void setLastInvestmentId(int lastInvestmentId) {
        Investment.lastInvestmentId = lastInvestmentId;
    }
}
