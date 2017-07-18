package Controller;

import Controller.MatchingRules.InvestmentLinkMatchingRule;
import Controller.MatchingRules.InvestmentMatchingRule;
import Model.InvalidMatcherSetupException;
import Model.Investment;
import Model.Loan;
import Model.ProductLink;

import java.util.ArrayList;
import java.util.List;

public class InvestmentMatcher {

    private List<InvestmentMatchingRule> investmentMatchingRules = new ArrayList<>();
    private List<InvestmentLinkMatchingRule> investmentLinkMatchingRules = new ArrayList<>();

    public InvestmentMatcher() {

    }

    public List<ProductLink> matchInvestmentsToLoan(List<Investment> investments, Loan loan) throws InvalidMatcherSetupException {
        if (getInvestmentMatchingRules().isEmpty()) {
            throw new InvalidMatcherSetupException("There are no investment matching rules");
        }
        if (getInvestmentLinkMatchingRules().isEmpty()) {
            throw new InvalidMatcherSetupException("There are no investment link matching rules");
        }
        List<Investment> matchingInvestments = investments;
        for (InvestmentMatchingRule matchingRule : getInvestmentMatchingRules()) {
            matchingInvestments = matchingRule.matchInvestments(matchingInvestments, loan);
            if (matchingInvestments.isEmpty()) {
                throw new InvalidMatcherSetupException("There are no investments that meet the critera for loan: " + loan);
            }
        }
        List<ProductLink> matchingInvestmentLinks = null;
        for (InvestmentLinkMatchingRule linkMatchingRule : getInvestmentLinkMatchingRules()) {
            matchingInvestmentLinks = linkMatchingRule.matchInvestmentsToLoan(matchingInvestments, loan);
            if (matchingInvestmentLinks == null) {
                throw new InvalidMatcherSetupException("Loan could not be funded with available investments: " + loan);
            }
        }
        return matchingInvestmentLinks;
    }

    public List<InvestmentMatchingRule> getInvestmentMatchingRules() {
        return investmentMatchingRules;
    }

    public void setInvestmentMatchingRules(List<InvestmentMatchingRule> investmentMatchingRules) {
        this.investmentMatchingRules = investmentMatchingRules;
    }

    public List<InvestmentLinkMatchingRule> getInvestmentLinkMatchingRules() {
        return investmentLinkMatchingRules;
    }

    public void setInvestmentLinkMatchingRules(List<InvestmentLinkMatchingRule> investmentLinkMatchingRules) {
        this.investmentLinkMatchingRules = investmentLinkMatchingRules;
    }
}