package Controller.MatchingRules;

import Model.Investment;
import Model.Loan;

import java.util.List;

public interface InvestmentMatchingRule {
    public List<Investment> matchInvestments(List<Investment> investmentsToMatch, Loan loan);
}
