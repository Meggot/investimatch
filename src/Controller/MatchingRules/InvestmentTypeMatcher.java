package Controller.MatchingRules;

import Model.Investment;
import Model.Loan;

import java.util.List;
import java.util.stream.Collectors;

public class InvestmentTypeMatcher implements InvestmentMatchingRule{
    String matchingPrettyName;
    public InvestmentTypeMatcher(){
        matchingPrettyName = "Type Matcher";
    }

    @Override
    public List<Investment> matchInvestments(List<Investment> investmentsToMatch, Loan loan) {
        return investmentsToMatch.stream()
                .filter(investment -> investment.getType().equals(loan.getType()))
                .collect(Collectors.toList());
    }
}
