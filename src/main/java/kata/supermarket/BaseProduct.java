package kata.supermarket;

public abstract class BaseProduct {
    private String productCode;
    private String description;

    protected BaseProduct() {
        super();
    }

    protected BaseProduct(String productCode, String description) {
        super();
        this.productCode = productCode;
        this.description = description;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
