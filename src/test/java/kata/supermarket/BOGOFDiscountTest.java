package kata.supermarket;

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

    Discount bogofDiscount = null;

    @BeforeEach
    void init() {
        bogofDiscount = new BOGOFDiscount();
    }

    @DisplayName("Buy One Get One Free Discount offer test..")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void testApplyDiscount(String description, String expectedDiscount, List<Item> items) {
        List<AppliedOffer> appliedDiscounts = bogofDiscount.applyDiscount(items);

        assertEquals(1, appliedDiscounts.size());
        assertEquals(new BigDecimal(expectedDiscount), appliedDiscounts.get(0).getDiscountAmount());
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
        return new Product(new BigDecimal("1.55")).oneOf();
    }

    private static Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49")).oneOf();
    }
}
