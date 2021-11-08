package kata.supermarket;

import java.math.BigDecimal;

public class Product extends BaseProduct {

    private final BigDecimal pricePerUnit;

    public Product(final BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Product(final String productCode, final String description, final BigDecimal pricePerUnit) {
        super(productCode, description);
        this.pricePerUnit = pricePerUnit;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
