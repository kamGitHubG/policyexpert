package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct extends BaseProduct {

    private final BigDecimal pricePerKilo;

    public WeighedProduct(final BigDecimal pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
    }

    public WeighedProduct(final String productCode, final String description, final BigDecimal pricePerKilo) {
        super(productCode, description);
        this.pricePerKilo = pricePerKilo;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
