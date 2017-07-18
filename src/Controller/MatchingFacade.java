package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MatchingFacade {

    private List<Investment> allInvestments;
    private List<Loan> allLoans;

    private InvestmentMatcher matcher;

    public MatchingFacade() {

    }

    public void matchAllLoans() throws InvalidMatcherSetupException{
        if (allLoans.isEmpty())
        {
            throw new InvalidMatcherSetupException("No Loans to Match");
        }
        if (allInvestments.isEmpty())
        {
            throw new InvalidMatcherSetupException("No investments to match");
        }
        allLoans.stream()
                .filter(loan -> loan.getStatus().equals(MatchingStatus.UNMATCHED))
                .sorted(Comparator.comparing(Loan::getCompletedDate))
                .forEach(unmatchedLoan -> {
                    try {
                        List<ProductLink> possibleLinks = matcher.matchInvestmentsToLoan(allInvestments.stream()
                                .filter(investment -> investment.getStatus().equals(MatchingStatus.UNMATCHED))
                                .collect(Collectors.toList()), unmatchedLoan);
                        if (possibleLinks!=null)
                        {
                            possibleLinks.forEach(possibleLink -> {
                                possibleLink.getInvestment().addLink(possibleLink);
                                possibleLink.getLoan().addLink(possibleLink);
                            });
                        }
                    } catch (InvalidMatcherSetupException err)
                    {
                        System.out.println(err);
                    }
                });
        System.out.println("All Loans Matched!");
    }

    public List<Investment> getAllInvestments() {
        return allInvestments;
    }

    public void setAllInvestments(List<Investment> allInvestments) {
        this.allInvestments = allInvestments;
    }

    public List<Loan> getAllLoans() {
        return allLoans;
    }

    public void setAllLoans(List<Loan> allLoans) {
        this.allLoans = allLoans;
    }

    public InvestmentMatcher getMatcher() {
        return matcher;
    }

    public void setMatcher(InvestmentMatcher matcher) {
        this.matcher = matcher;
    }
}
