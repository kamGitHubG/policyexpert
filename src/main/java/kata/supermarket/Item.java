package kata.supermarket;

import java.math.BigDecimal;

public interface Item {
    BigDecimal price();

    public BaseProduct getProduct();
}
