import java.util.ArrayList;
import java.util.List;

/**
 * Creates an empty Coffee Shop implemented 
 * by using the Queue data structure.
 * 
 * @author Brandon Semba
 * @version 1.14.15
 */
public class CoffeeShop
{
    /** The max amount of patrons allowed to be in line. */
    private final static int MAX_IN_LINE = 25;
    
    /** A List containing all MyQueues. */
    private final List<MyQueue> cashiers;
    
    /** The first MyQueue in the CoffeeShop. */
    private final MyQueue till1;
    
    /** The second MyQueue in the CoffeeShop. */
    private final MyQueue till2;
    
    /** The third MyQueue in the CoffeeShop. */
    private final MyQueue till3;
    
    /** The fourth MyQueue in the CoffeeShop. */
    private final MyQueue till4;
    
    /** The fifth MyQueue in the CoffeeShop. */
    private final MyQueue till5;
    
    /** The door MyQueue. */
    private final MyQueue doorQ;
    
    /** One-minute wait MyQueue. */
    private MyQueue exitQ1;
    
    /** Two-minute wait MyQueue. */
    private MyQueue exitQ2;
   
    /**
     * Constructs a CoffeeShop.
     */
    public CoffeeShop()
    {
        cashiers = new ArrayList<MyQueue>(5);
        cashiers.add(till1 = new MyQueue());
        cashiers.add(till2 = new MyQueue());
        cashiers.add(till3 = new MyQueue());
        cashiers.add(till4 = new MyQueue());
        cashiers.add(till5 = new MyQueue());
        doorQ = new MyQueue();
        exitQ1 = new MyQueue();
        exitQ2 = new MyQueue();   
    }
    
    /**
     * Returns the specified MyQueue by its index within a List.
     * 
     * @param theTillIndex - the index of the target queue
     * @return the queue at the given index of the List.
     */
    public MyQueue getTill(int theTillIndex)
    {
        return cashiers.get(theTillIndex);
    }
    
    /**
     * Returns a List of MyQueues from the CoffeeShop.
     * 
     * @return a List of queues. 
     */
    public List<MyQueue> getCashiers()
    {
        return cashiers;
    }
    
    /**
     * Checks to see how many MyQueues are not empty.
     * 
     * @return a count of queues that are not empty.
     */
    public int tillsOpen()
    {
        int openTills = 0;
        for(int i = 0; i < cashiers.size(); i++)
        {
            if(!cashiers.get(i).isEmpty())
            {
                openTills++;
            }
        }
        return openTills;
    }
    
    /**
     * Adds a Student to the door MyQueue.
     * 
     * @param theStudent - a Student
     */
    public void addToDoorQ(final Student theStudent)
    {
        doorQ.offer(theStudent);
    }
    
    /**
     * Returns the number of patrons in line at the
     * CoffeeShop.
     * 
     * @return the number of patrons in the CoffeeShop.
     */
    public int patronsAtTills()
    {
        int total = 0;
        for(int i = 0; i < cashiers.size(); i++)
        {
            total += cashiers.get(i).size();
        } 
        return total;
    }
    
    /**
     * Returns a sub MyQueue containing Students
     * who have been served this minute.<br>
     * Displays Students who are currently in the exit MyQueues.<br>
     * Handles Students who are waiting in the door MyQueue.
     * 
     * @return a sub queue of Students served this minute.
     */
    public MyQueue poll()
    {
        MyQueue exit;
        final MyQueue temp = new MyQueue();
        MyQueue reEntry;
        
        if(!(exitQ1.isEmpty() && exitQ2.isEmpty()))
        {
            exitQ1 = exitQ2;
            exitQ2 = temp;
        }
        else if(!exitQ2.isEmpty() && exitQ1.isEmpty())
        {
            exitQ1 = exitQ2;
            exitQ2 = temp;
        }
        
        for(int i = 0; i < 5; i++)
        {
            exit = cashiers.get(i);
            if(!exit.isEmpty())
            {
                temp.offer(exit.poll());
            }
        }
        
        while(this.patronsAtTills() < MAX_IN_LINE && !doorQ.isEmpty())
        {
            Student exitQStudent = doorQ.poll();
            reEntry = exitQStudent.selectTill(this);
            reEntry.offer(exitQStudent);
        }
        exitQ2 = temp;
        return exitQ2;
    }
    
    /**
     * Generates console output, displaying
     * the contents of all MyQueues in the CoffeeShop.
     */
    public void displayShop()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Door Queue: ");
        sb.append(doorQ);
        sb.append(System.getProperty("line.separator"));
        sb.append("Till 0: ");
        sb.append(till1);
        sb.append(System.getProperty("line.separator"));
        sb.append("Till 1: ");
        sb.append(till2);
        sb.append(System.getProperty("line.separator"));
        sb.append("Till 2: ");
        sb.append(till3);
        sb.append(System.getProperty("line.separator"));
        sb.append("Till 3: ");
        sb.append(till4);
        sb.append(System.getProperty("line.separator"));
        sb.append("Till 4: ");
        sb.append(till5);
        sb.append(System.getProperty("line.separator"));
        sb.append("Exit Queue 2 min: ");
        sb.append(exitQ2);
        sb.append(System.getProperty("line.separator"));
        sb.append("Exit Queue 1 min: ");
        sb.append(exitQ1);
        sb.append(System.getProperty("line.separator"));
        System.out.println(sb.toString());    
    }
}
