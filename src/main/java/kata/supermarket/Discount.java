package kata.supermarket;

import java.util.List;

public interface Discount {

    public AppliedOffer applyDiscount(Offer offer, List<Item> items);
}
