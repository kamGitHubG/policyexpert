package kata.supermarket;

public class Offer {
    private String offerId;
    private String description;
    private BaseProduct product;
    private Discount discount;

    public Offer(String offerId, BaseProduct product, Discount discount) {
        super();
        this.offerId = offerId;
        this.product = product;
        this.discount = discount;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public BaseProduct getProduct() {
        return product;
    }

    public void setProduct(BaseProduct product) {
        this.product = product;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
