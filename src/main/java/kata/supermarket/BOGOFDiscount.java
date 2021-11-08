package kata.supermarket;

import java.util.ArrayList;
import java.util.List;

public class BOGOFDiscount implements Discount {

    @Override
    public List<AppliedOffer> applyDiscount(List<Item> items) {
        return new ArrayList<AppliedOffer>();
    }

}
