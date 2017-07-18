package Controller.MatchingRules;

import Model.InvalidMatcherSetupException;
import Model.Investment;
import Model.Loan;
import Model.ProductLink;

import java.util.ArrayList;
import java.util.List;

public class MatchExactlyMatcher implements InvestmentLinkMatchingRule{

    String matchingPrettyName;

    public MatchExactlyMatcher() {
        matchingPrettyName = "Exactly Matcher";
    }

    @Override
    public List<ProductLink> matchInvestmentsToLoan(List<Investment> investments, Loan loan) {
        List<ProductLink> proposedInvestmentLinks = new ArrayList<>();
        Long totalInvestmentTicker = 0L;
        for (Investment investment : investments)
        {
            if (loan.getAmount()>totalInvestmentTicker)
            {
                Long outstandingInvestment = loan.getAmount() - totalInvestmentTicker;
                Long available = investment.getOutstandingAmount();
                Long linkLong = outstandingInvestment - available;
                if (linkLong < 0L)
                {
                    available += linkLong;
                }
                ProductLink newInvestmentLink = new ProductLink(investment, loan, available);
                proposedInvestmentLinks.add(newInvestmentLink);
                totalInvestmentTicker += available;
            } else {
                break;
            }
        }
        if (loan.getAmount()>totalInvestmentTicker)
        {
            proposedInvestmentLinks = null;
        }
        return proposedInvestmentLinks;
    }
}
