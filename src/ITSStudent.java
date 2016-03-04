import java.util.List;

/**
 * Creates an ITS student who can be identified by a name.<br>
 * Provides implementation of the Student interface.
 * 
 * @author Brandon Semba
 * @version 1.20.15
 *
 */
public final class ITSStudent implements Student
{
    /** The Student's identifier. */
    private final String identifier;

    /**
     * Constructs a ITS Student.
     */
    public ITSStudent(final String theName)
    {
        identifier = theName;
    }

    /**
     * Selects the first open queue containing the
     * most Students that does not have five Students.<br>
     * 
     * If all open queues have five Students the
     * first open till is selected.
     * 
     * {@inheritDoc}
     */
    @Override
    public MyQueue selectTill(final CoffeeShop theCoffeeShop)
    {
        final CoffeeShop shop = theCoffeeShop;
        final List<MyQueue> counter = shop.getCashiers();
        MyQueue till = new MyQueue();
        
        int fullTillCounter = 0;
        int compare = 0;
        for(int i = 0; i < counter.size(); i++)
        {
            if(counter.get(0).isEmpty())
            {
                till = counter.get(0);
            }
            if(!counter.get(i).isEmpty() && counter.get(i).size() < 5)
            {            
                if(counter.get(i).size() > compare)
                {
                    compare = counter.get(i).size();
                    till = counter.get(i);
                }             
            }
            else
            {
                if(counter.get(i).size() == 5)
                {
                    fullTillCounter++;
                    if(fullTillCounter == shop.tillsOpen())
                    {
                        till = counter.get(0);
                    }
                }
            }
        }
        return till;
    }
    
    /**
     * Returns a String representation of the Student.
     * 
     * @return a String representation of the Student.
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.identifier);
        return sb.toString();
    }
}
