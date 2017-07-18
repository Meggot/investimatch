package Controller.MatchingRules;

import Model.Investment;
import Model.Loan;
import Model.ProductLink;

import java.util.List;

public interface InvestmentLinkMatchingRule {

    public List<ProductLink> matchInvestmentsToLoan(List<Investment> investments, Loan loan);
}
