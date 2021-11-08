package kata.supermarket;

import java.util.ArrayList;
import java.util.List;

/*
 * DiscountFinder is a utility - helper class that would provide list of offers that are currently active.
 * Ideally this will be from either configuration file or from static data tables.
 */
public class DiscountFinder {

    private static List<Offer> currentOffers = new ArrayList<Offer>();

    public static List<Offer> retrieveCurrentOffers() {
        return currentOffers;
    }

    public static List<Offer> retrieveCurrentOffersForProduct(BaseProduct product)
    {
        return currentOffers;
    }
}
