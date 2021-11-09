# Notes

Here is my thought process and assumptions I made while building this application -

1. Product and Weighed Product did not have any unique attributes which could be use to identify the product.  So that is first change I made to the model and introduced BaseProduct class which is backwards compatible to existing code.
2. DiscountFinder is a helper class which in Production application will ideally initialise current live offers from external configuration which can be file, database query to call to external service.  
3. The relationship between Discount Offer and items is loosely coupled i.e. it designed on the principle, take these offers and apply them to the basket.  Thus giving us flexibility to introduce new discounts without making any changes to item datastructure or changes to Basket calculations.
4. BOGOFDiscount is just one implementation of Discount interface.  Similar other implementations for other type of discounts can be created.