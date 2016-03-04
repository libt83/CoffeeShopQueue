import java.util.List;

/**
 * Creates a CSS student who can be identified by a name.<br>
 * Provides implementation of the Student interface.
 * 
 * @author Brandon Semba
 * @version 1.16.15
 */
public final class CSSStudent implements Student
{   
    /** The Student identifier. */
    private final String identifier;
    
    /**
     * Constructs a CSS Student.
     */
    public CSSStudent(final String theName)
    {
        identifier = theName;
    }

    /**
     * Selects the first open queue with the fewest
     * Students.
     * 
     * {@inheritDoc}
     */
    @Override
    public MyQueue selectTill(final CoffeeShop theCoffeeShop)
    {
        final CoffeeShop shop = theCoffeeShop;
        final List<MyQueue> counter = shop.getCashiers();
        MyQueue till = new MyQueue();
        
        int compare = 6;
        for(int i = 0; i < counter.size(); i++)
        {
            if(counter.get(0).isEmpty())
            {
                till = counter.get(0);
            }
            if(!counter.get(i).isEmpty())
            {
                if(counter.get(i).size() < compare)
                {
                    compare = counter.get(i).size();
                    till = counter.get(i);
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
