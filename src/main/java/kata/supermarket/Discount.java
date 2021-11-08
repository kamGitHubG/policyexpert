package kata.supermarket;

import java.util.List;

public interface Discount {

    public List<AppliedOffer> applyDiscount(List<Item> items);
}
