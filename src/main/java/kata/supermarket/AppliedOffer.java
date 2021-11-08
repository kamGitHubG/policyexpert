package kata.supermarket;

import java.math.BigDecimal;

public class AppliedOffer {
    private Offer offer;
    private BigDecimal discountAmount;

    public AppliedOffer() {
        super();
    }

    public AppliedOffer(Offer offer, BigDecimal discountAmount) {
        super();
        this.offer = offer;
        this.discountAmount = discountAmount;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }
}
