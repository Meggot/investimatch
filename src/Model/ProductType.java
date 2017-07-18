package Model;

public enum ProductType {
    FIXED("fixed"),
    TRACKER("tracker");

    private String displayString;
    ProductType(String idisplay)
    {
        this.displayString = idisplay;
    }
}
