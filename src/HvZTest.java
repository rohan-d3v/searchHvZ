import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HvZTest {
    zCoordinates z = new zCoordinates(0,0);
    HvZ tester = new HvZ();

    @Before
    public void setup() {
        tester.insert(z, 1,1);
        tester.insert(z,2,0);
        tester.insert(z,2,3);
        tester.insert(z,5,2);
        tester.insert(z, 1,2);
        tester.insert(z,0,1);
        tester.insert(z,3,4);
    }

    /**
     * Code Coverage/nominal case where it checks the zombie method for standard use
     */
    @Test
    public void zombieCoverageCheck() {

        zCoordinates checkZ = new zCoordinates(3,4);
        zCoordinates testZ = tester.zombie(3,4);

        assertEquals(testZ.getX(), checkZ.getX());
        assertEquals(testZ.getY(), checkZ.getY());

    }

    /**
     * Edge check case where errors should be thrown for incorrect values
     */

    @Test
    public void zombieEdgeCheck() {

        try{
            tester.zombie(5,6);
            fail("Null Exception not thrown");
        }
        catch (NullPointerException e){
            assertTrue("Exception was caught", true);
        }

    }

    /**
     * Code coverage/Nominal Check for the insert method
     * Values are initialized in here cause initialization needsd to be checked
     */
    @Test
    public void insertCoverageCheck() {


        zCoordinates testZ1 = tester.zombie(3,4);
        zCoordinates testZ2 = tester.zombie(0,1);
        zCoordinates testZ3 = tester.zombie(1,2);

        assertEquals(new zCoordinates(3,4).getX(), testZ1.getX());
        assertEquals(new zCoordinates(0,1).getY(), testZ2.getY());
        assertEquals(new zCoordinates(1,2).getX(), testZ3.getX());

    }

    /**
     * Edge Check for the insert method
     * Values are initialized in here cause initialization needsd to be checked
     */
    @Test
    public void insertEdgeCheck() {

        try{
            tester.insert(z,1,2);
            fail("Argument Exception not thrown");
        }
        catch (IllegalArgumentException e){
            assertTrue("Exception was caught", true);
        }

        zCoordinates newTemp = null;

        try{
            tester.insert(newTemp,3,4);
            fail("Argument Exception not thrown");
        }
        catch (NullPointerException e){
            assertTrue("Exception was caught", true);
        }

    }

    /**
     * Code Coverage/Edge Check for the delete method, checks delete and move
     * Also edge case check cause they need to go together
     */
    @Test
    public void delete() {

        tester.insert(new zCoordinates(0,0),6,6);

        assertEquals(new zCoordinates(6,6).getX(), tester.delete(6,6).getX());

        try{
            tester.delete(6,6);
            fail("Argument Exception not thrown");
        }
        catch (NullPointerException e){
            assertTrue("Exception was caught", true);
        }
    }

    /**
     * Move for the delete method, checks delete and move
     * Also edge case check cause they need to go together
     */
    @Test
    public void deleteMoveCheck() {

        tester.insert(z,4,5);
        //Checks, the delete and move methods
        tester.insert(tester.delete(4,5),6,6);

        assertEquals(new zCoordinates(6,6).getCoordinates(), "6,6");

    }

    /**
     * Nominal/Code Coverage Check for arrow
     */
    @Test
    public void arrow() {
        assertEquals(tester.arrow("left"), "0,1");
        assertEquals(tester.arrow("right"), "5,2");
    }

    /**
     *
     */

    @Test
    public void javelin() {

        assertEquals(tester.javelin(1), "1,2");
        assertEquals(tester.javelin(2), "2,3");


    }

    /**
     * Nominal Code Coverage Check for bomb
     */
    @Test
    public void bomb() {

        assertEquals(tester.bomb(1,2), "3,4");
    }


    /**
     * Stress Test Code Coverage
     */
    @Test
    public void stressTest(){
        HvZ tester2 = new HvZ();
        zCoordinates z = new zCoordinates(0,0);

        Random x = new Random();
        Random y = new Random();
        int tempX, tempY, n = 0;

        while (n<100){
            tempX = x.nextInt();
            tempY = y.nextInt();
            tester2.insert(z,tempX, tempY);
            assertEquals(new zCoordinates(tempX, tempY).getCoordinates(), tester2.zombie(tempX,tempY).getCoordinates());
            n++;
        }
    }

}