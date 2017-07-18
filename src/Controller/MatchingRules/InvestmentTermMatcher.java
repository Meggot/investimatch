package Controller.MatchingRules;

import Model.Investment;
import Model.Loan;

import java.util.List;
import java.util.stream.Collectors;


public class InvestmentTermMatcher implements InvestmentMatchingRule{
    String matchingPrettyName;

    public InvestmentTermMatcher () {
        this.matchingPrettyName = "Term Matcher";
    }

    @Override
    public List<Investment> matchInvestments(List<Investment> investmentsToMatch, Loan loan) {
        return investmentsToMatch.stream()
                .filter(investment -> investment.getTerm() > loan.getTerm())
                .collect(Collectors.toList());
    }
}
