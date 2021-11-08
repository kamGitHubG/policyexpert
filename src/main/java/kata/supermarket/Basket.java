package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Basket {
    private final List<Item> items;

    public Basket() {
        this.items = new ArrayList<>();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        /**
         * TODO: This could be a good place to apply the results of
         *  the discount calculations.
         *  It is not likely to be the best place to do those calculations.
         *  Think about how Basket could interact with something
         *  which provides that functionality.
         */
        private BigDecimal discounts() {
            Map<BaseProduct, Set<Offer>> productOffers = new HashMap<>();
            for(Item item : items) {
                Set<Offer> offers = DiscountFinder.retrieveCurrentOffersForProduct(item.getProduct());
                if(offers != null && !offers.isEmpty()) {
                    Set<Offer> currentOffers = productOffers.get(item.getProduct());
                    if (currentOffers == null) {
                        currentOffers = new HashSet<>();
                    }
                    currentOffers.addAll(offers);
                    productOffers.put(item.getProduct(), currentOffers);
                }
            }

            List<AppliedOffer> appliedOffers = new ArrayList<>();
            for(BaseProduct product : productOffers.keySet())
            {
                Set<Offer> offers = productOffers.get(product);
                for(Offer offer : offers)
                {
                    AppliedOffer appliedOffer = offer.getDiscount().applyDiscount(offer, items);
                    appliedOffers.add(appliedOffer);
                }
            }

            BigDecimal totalDiscount = appliedOffers.stream()
                                                 .map(AppliedOffer::getDiscountAmount)
                                                 .reduce(BigDecimal::add)
                                                 .orElse(BigDecimal.ZERO)
                                                 .setScale(2, RoundingMode.HALF_UP);

            return totalDiscount;
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }
}
