package kata.supermarket;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * DiscountFinder is a utility - helper class that would provide list of offers that are currently active.
 * Ideally this will be from either configuration file or from static data tables.
 */
public class DiscountFinder {

    private static Set<Offer> currentOffers = new HashSet<>();

    public static Set<Offer> retrieveCurrentOffers() {
        return currentOffers;
    }

    public static Set<Offer> retrieveCurrentOffersForProduct(BaseProduct product)
    {
        return currentOffers.stream().filter(offer -> offer.getProduct().getProductCode().equals(product.getProductCode()))
                .collect(Collectors.toSet());
    }
}
