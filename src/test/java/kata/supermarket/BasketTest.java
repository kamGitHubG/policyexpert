package kata.supermarket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BasketTest {
    static Product digestives = new Product("Product-1", "SM Digestives", new BigDecimal("1.55"));
    Discount bogofDiscount = null;
    Offer bogofOffer = null;

    @BeforeEach
    void init() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        bogofDiscount = new BOGOFDiscount();
        bogofOffer = new Offer("Offer-1", digestives, bogofDiscount);
        Set<Offer> currentOffers = new HashSet<>();
        currentOffers.add(bogofOffer);

        Field field = DiscountFinder.class.getDeclaredField("currentOffers");
        field.setAccessible(true);
        field.set(null, currentOffers);
    }

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight(),
                multipleItemsPricedPerUnitBOGOFF()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments multipleItemsPricedPerUnitBOGOFF() {
        return Arguments.of("multiple items priced per unit to avail BOGOF discount", "5.14",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk(), aPackOfDigestives(), aPackOfDigestives(),
                        aPackOfDigestives(), aPackOfDigestives()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new Product("Product-2", "SM Milk Pint", new BigDecimal("0.49")).oneOf();
    }

    private static Item aPackOfDigestives() {
        return digestives.oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct("Product-3", "American Sweets", new BigDecimal("4.99"));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct("Product-4", "Pick And Mix", new BigDecimal("2.99"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }
}