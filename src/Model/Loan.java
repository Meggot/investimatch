package Model;

import java.util.Date;


public class Loan extends Product implements Comparable<Loan>{

    private int id;
    private Date completedDate;

    public Loan(int loanId,Long loanAmount, ProductType loanType, double loanTerm, Date loanCompletedDate)
    {
        super(loanType, loanAmount, loanTerm);
        this.setId(loanId);
        this.setCompletedDate(loanCompletedDate);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    @Override
    public int compareTo(Loan o) {
        if (this.completedDate.before(o.getCompletedDate())){
            return 1;
        } else if (this.completedDate.after(o.getCompletedDate())){
            return -1;
        } else {
            return 0;
        }
    }
}
