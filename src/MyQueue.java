import java.util.NoSuchElementException;

/**
 * A Queue data structure implemented using a Linked List
 * data structure.<br>
 * 
 * The following source was used to implement some of this
 * data structures features: http://algs4.cs.princeton.edu/43mst/Queue.java.html
 * 
 * @author Brandon Semba
 * @version 01.15.15
 */
public class MyQueue
{
    /** The minimum MyQueue size that can be split. */
    private static final int QUEUE_MIN = 2;
    
    /** The beginning of MyQueue. */
    private Node first;
    
    /** The end of the MyQueue. */
    private Node last;
    
    /** The size of the MyQueue. */
    private int size;
    
    /**
     * Constructor that initializes an empty MyQueue.
     */
    public MyQueue()
    {
        first = null;
        last = null;
        size = 0;
    }
    
    /**
     * Returns true if MyQueue is empty.
     * 
     * @return whether the queue is empty or not.
     */
    public boolean isEmpty()
    {
        return first == null;      
    }
    
    /**
     * Adds the Student to the back of MyQueue.
     * 
     * @param theStudent - the Student being added to the queue
     */
    public void offer(final Student theStudent)
    {
        Node oldlast = last;
        last = new Node();
        last.student = theStudent;
        last.next = null;
        
        if(isEmpty())
        {
            first = last;
        }
        else
        {
            oldlast.next = last;
        }
        size++;  
    }
    
    /**
     * Removes and returns the Student at the front of MyQueue.
     * 
     * @return the Student at the front of the queue.
     */
    public Student poll()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException("The queue is empty");
        }
        Student student = first.student;
        first = first.next;
        size--;
        return student;
    }
    
    /**
     * Returns the Student at the front of MyQueue.<br>
     * Does not remove the Student from the queue.
     * 
     * @return the Student at the front of the queue.
     */
    public Student peek()
    {
        if(isEmpty())
        {
            throw new NoSuchElementException("MyQueue is empty");
        }
        return first.student;
    }
    
    /**
     * Splits the MyQueue into two MyQueues.<br>
     * Returns the odd-numbered Students as a sub queue.
     * 
     * @return a MyQueue with the even numbered Students.
     */
    public MyQueue split()
    {
        final MyQueue subQ = new MyQueue();
        Node current;
        Node ahead;
        int adjustedSize;
        
        current = first;
        ahead = first;
        
        if(this.size() % 2 == 0)
        {
            adjustedSize = this.size() / 2;
        }
        else
        {
            adjustedSize = (this.size() / 2) + 1;
        }
        
        if(this.size() < QUEUE_MIN)
        {
            throw new IllegalArgumentException("Cannot split a queue with less than 2 Students");
        }
        else
        {
            while(current.next != null || ahead.next != null)
            {
                if(this.size() == QUEUE_MIN)
                {
                    ahead = current.next;
                    current.next = current.next.next;
                    subQ.offer(ahead.student);
                    last = first;
                }
                else
                {
                    ahead = current.next;
                    current.next = current.next.next;
                    subQ.offer(ahead.student);
                    
                    if (ahead.next != null)
                    {
                        current = ahead.next;
                        ahead.next = null;
                        ahead = current;
                        last = current;
                    }
                }
            }
        }
        this.size = adjustedSize;
        return subQ;
    }

    /**
     * Returns the number of Students that are in MyQueue.
     * 
     * @return the number of Students in the queue.
     */
    public int size()
    {
        return size;
    }
    
    /**
     * Creates a String representation of the contents of the MyQueue.
     * 
     * @return the contents of MyQueue as a String representation.
     */
    public String toString()
    {
        Node current = first;
        StringBuilder sb = new StringBuilder();
        if(this.isEmpty())
        {
            sb.append("[ ]");
        }
        else
        {
            sb.append("[");
            sb.append(current.student);
            while(current.next != null)
            {
                sb.append(", ");
                current = current.next;
                sb.append(current.student);
            }
            sb.append("]");
        }
        return sb.toString();
    }
    
    /**
     * Private helper class for the Linked List implementation of the
     * MyQueue data structure.
     */
    private class Node
    {
        private Student student;
        private Node next;
    }
}
