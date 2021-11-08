package kata.supermarket;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BOGOFDiscountTest {
    static Product digestives = new Product("Product-1", "SM Digestives", new BigDecimal("1.55"));
    static Product pintOfMilk = new Product("Product-3", "SM Milk", new BigDecimal("0.49"));
    Discount bogofDiscount = null;
    Offer bogofOffer = null;

    @BeforeEach
    void init() {
        bogofDiscount = new BOGOFDiscount();
        bogofOffer = new Offer("Offer-1", digestives, bogofDiscount);
    }

    @DisplayName("Buy One Get One Free Discount offer test..")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void testApplyDiscount(String description, String expectedDiscount, List<Item> items) {
        AppliedOffer appliedDiscounts = bogofDiscount.applyDiscount(bogofOffer, items);

        assertNotNull(appliedDiscounts);
        assertEquals(new BigDecimal(expectedDiscount), appliedDiscounts.getDiscountAmount());
    }

    static Stream<Arguments> testApplyDiscount() {
        return Stream.of(
                multipleItemsPricedPerUnit()
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "1.55",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk(), aPackOfDigestives()));
    }

    private static Item aPackOfDigestives() {
        return digestives.oneOf();
    }

    private static Item aPintOfMilk() {
        return pintOfMilk.oneOf();
    }
}
