package Model;

import java.util.ArrayList;
import java.util.List;

public abstract class Product {

    ProductType type;
    private Long amount;
    private double term;
    private List<ProductLink> links;
    private MatchingStatus status;

    public Product(ProductType prodType, Long prodAmount, double prodTerm)
    {
        this.status = MatchingStatus.UNMATCHED;
        this.type = prodType;
        this.amount = prodAmount;
        this.term = prodTerm;
        setLinks(new ArrayList<>());
    }

    public MatchingStatus getStatus() {
        return status;
    }

    public void setStatus(MatchingStatus status) {
        this.status = status;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public double getTerm() {
        return term;
    }

    public void setTerm(double term) {
        this.term = term;    }

    public Long getOutstandingAmount() {
        Long returnValue = amount;
        for (ProductLink investment : this.getLinks())
        {
            returnValue -= investment.getAmount();
        }
        return returnValue;
    }

    public void addLink(ProductLink link)
    {
        this.links.add(link);
        if (getOutstandingAmount() == 0)
        {
            status = MatchingStatus.MATCHED;
        }
    }

    public List<ProductLink> getLinks() {
        return links;
    }

    public void setLinks(List<ProductLink> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Product{" +
                "type=" + type +
                ", amount=" + amount +
                ", outstanding amount: " + getOutstandingAmount() +
                ", term=" + term +
                ", links=" + links +
                ", status=" + status +
                '}';
    }
}