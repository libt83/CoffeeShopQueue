import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Reads data in from an input file and then runs the
 * simulation of the Coffee Shop.
 * 
 * @author Brandon Semba
 * @version 1.15.15
 */
public class Main
{
    public static void main(final String[] theArgs) throws FileNotFoundException
    {
        final CoffeeShop shop = new CoffeeShop();
        final File file = new File("customer.txt");
        final Scanner scan = new Scanner(file);
        Student student;
        
        int elapsedTime = 0;
        String studentType = "";
        
        while(scan.hasNextLine())
        {
            if(scan.hasNextInt())
            {
                elapsedTime = scan.nextInt();
                shop.poll();
                System.out.println("Processing Input: " + elapsedTime);
                shop.displayShop();
                System.out.println();
            }
            else if(scan.hasNext())
            {
                studentType = scan.next() + " " + scan.nextInt();
                CharSequence its = "ITS";
                CharSequence css = "CSS";
                
                if(studentType.contains(its))
                {
                    student = new ITSStudent(studentType);
                    tillCheck(student, shop);
                }
                else if(studentType.contains(css))
                {
                    student = new CSSStudent(studentType);
                    tillCheck(student, shop);
                }
                else
                {
                    student = new CESStudent(studentType);
                    tillCheck(student, shop);
                }
                
                System.out.println("Processing Input: " + studentType);
                shop.displayShop();
                System.out.println();
            }
        }
        scan.close();
    }
    
    /**
     * Private helper method used to determine if the Student
     * goes to the door queue or if the line needs split.
     * 
     * @param theStudent - the Student entering the CoffeeShop
     * @param theShop - the CoffeeShop in use
     */
    private static void tillCheck(final Student theStudent, final CoffeeShop theShop)
    {
        MyQueue subQ;
        MyQueue selectedQ = theStudent.selectTill(theShop);
        
        if(selectedQ.size() > 4 && theShop.tillsOpen() > 4)
        {
            theShop.addToDoorQ(theStudent);
        }
        else 
        {
            selectedQ.offer(theStudent);
            if(selectedQ.size() > 5)
            {
                subQ = selectedQ.split();
                List<MyQueue> counter = theShop.getCashiers();
            
                for(int i = 0; i < counter.size(); i++)
                {
                    if(counter.get(i).isEmpty())
                    {
                        while(!subQ.isEmpty())
                        {
                            counter.get(i).offer(subQ.poll());
                        }
                    }
                }
            }
        }
    }
}
