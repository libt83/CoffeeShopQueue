/**
 * @author Brandon Semba
 * @version 1.15.15
 */
public interface Student
{
    /**
     * Used by implementing classes to determine which
     * queue to put in.
     * 
     * @param theCoffeeShop - a reference to the CoffeeShop.
     * @return the queue to be offered to.
     */
    MyQueue selectTill(final CoffeeShop theShop);
}
