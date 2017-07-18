package Model;

public class ProductLink {
    private transient Investment investment; //Prevent SOE
    private transient Loan loan;
    private Long amount;

    //JSON display fields
    private String investorName;
    private int loanId;

    public ProductLink(Investment investmentLoan, Loan linkLoan, Long linkAmount)
    {
        this.setInvestment(investmentLoan);
        this.setLoan(linkLoan);
        this.setAmount(linkAmount);
    }

    public Investment getInvestment() {
        return investment;
    }

    public void setInvestment(Investment investment) {
        this.investment = investment;
        this.investorName = investment.getInvestor();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
        this.loanId = loan.getId();
    }

    @Override
    public String toString() {
        return "ProductLink{" +
                "investment=" + investment.getInvestor() +
                ", amount=" + amount +
                ", loan=" + loan.getId() +
                '}';
    }
}
