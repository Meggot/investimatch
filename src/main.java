import Controller.InvestmentMatcher;
import Controller.MatchingFacade;
import Controller.MatchingRules.*;
import Model.InvalidMatcherSetupException;
import Util.InvestmentCsvLoader;
import Util.LoanCsvLoader;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
public class main {
    private final static String resourcesFile = "src/resources";
    public static void main(String[] args) {

        //Investment Matchers
        List<InvestmentMatchingRule> investmentMatchingRuleList = new ArrayList<>();
        investmentMatchingRuleList.add(new InvestmentTermMatcher());
        investmentMatchingRuleList.add(new InvestmentTypeMatcher());
        //Investment Link Matcher
        List<InvestmentLinkMatchingRule> investmentLinkMatchingRules = new ArrayList<>();
        investmentLinkMatchingRules.add(new MatchExactlyMatcher());

        InvestmentMatcher matcher = new InvestmentMatcher();
        matcher.setInvestmentMatchingRules(investmentMatchingRuleList);
        matcher.setInvestmentLinkMatchingRules(investmentLinkMatchingRules);

        MatchingFacade matchingFacade = new MatchingFacade();
        matchingFacade.setMatcher(matcher);

        try {
            matchingFacade.setAllLoans(LoanCsvLoader.loadLoansFromCsv(resourcesFile + "/loans.csv"));
            matchingFacade.setAllInvestments(InvestmentCsvLoader.loadInvestmentsFromFile(resourcesFile + "/investments.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            matchingFacade.matchAllLoans();
        } catch (InvalidMatcherSetupException e) {
            e.printStackTrace();
        }
        //Convert and display JSON
        Gson gson = new Gson();
        System.out.println(gson.toJson(matchingFacade));
    }
}
