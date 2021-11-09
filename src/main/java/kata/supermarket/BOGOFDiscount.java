package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class BOGOFDiscount implements Discount {

    @Override
    public AppliedOffer applyDiscount(Offer offer, List<Item> items) {
        List<Item> offerItemList = items.stream()
                .filter(item -> item.getProduct().getProductCode().equals(offer.getProduct().getProductCode()))
                .collect(Collectors.toList());

        AppliedOffer appliedOffer = new AppliedOffer(offer, BigDecimal.ZERO);
        if (offerItemList != null && offerItemList.size() < 2) {

            return appliedOffer;
        }

        int freeQuantity = offerItemList.size() / 2;
        BigDecimal discountAmount = offerItemList.get(0)
                                                 .price()
                                                 .multiply(new BigDecimal(freeQuantity))
                                                 .setScale(2,
                                                         RoundingMode.HALF_UP);
        appliedOffer.setDiscountAmount(discountAmount);
        return appliedOffer;
    }

}
