package Utils;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import DomainObjects.Coordinates;

public class NavigationUtilTest {

    @Test
    public void TestMoveUtil() {

        //(1,1) to (4,4)
        ArrayList<Coordinates> stepsExpected = new ArrayList<Coordinates>();
        stepsExpected.add(new Coordinates(2, 1));
        stepsExpected.add(new Coordinates(3, 1));
        stepsExpected.add(new Coordinates(4, 1));
        stepsExpected.add(new Coordinates(4, 2));
        stepsExpected.add(new Coordinates(4, 3));
        stepsExpected.add(new Coordinates(4, 4));
        
        ArrayList<Coordinates> stepsActual = NavigationUtil.moveUtil(1, 1, 4, 4);

        for (int i=0; i < 6; i++) {
            assertTrue("Step " + i + " does not match.", (stepsExpected.get(i).getX() == stepsActual.get(i).getX()) && (stepsExpected.get(i).getY() == stepsActual.get(i).getY()));
        }
        assertTrue("Not the same size", stepsExpected.size() == stepsActual.size());


        //(4,4) to (1,1)
        stepsExpected = new ArrayList<Coordinates>();
        stepsExpected.add(new Coordinates(3, 4));
        stepsExpected.add(new Coordinates(2, 4));
        stepsExpected.add(new Coordinates(1, 4));
        stepsExpected.add(new Coordinates(1, 3));
        stepsExpected.add(new Coordinates(1, 2));
        stepsExpected.add(new Coordinates(1, 1));
        
        stepsActual = NavigationUtil.moveUtil(4, 4, 1, 1);
        for (int i=0; i < 6; i++) {
            assertTrue("Step " + i + " does not match.", (stepsExpected.get(i).getX() == stepsActual.get(i).getX()) && (stepsExpected.get(i).getY() == stepsActual.get(i).getY()));
        }
        assertTrue("Not the same size", stepsExpected.size() == stepsActual.size());



        //(2,1) to (4,1)
        stepsExpected = new ArrayList<Coordinates>();
        stepsExpected.add(new Coordinates(3, 1));
        stepsExpected.add(new Coordinates(4, 1));

        stepsActual = NavigationUtil.moveUtil(2, 1, 4, 1);
        for (int i=0; i < 2; i++) {
    //        assertTrue("Step " + i + " does not match.", (stepsExpected.get(i).getX() == stepsActual.get(i).getX()) && (stepsExpected.get(i).getY() == stepsActual.get(i).getY()));
        }
    //    assertTrue("Not the same size", stepsExpected.size() == stepsActual.size());

    
    
    
        //(2,1) to (2,4)
        stepsExpected = new ArrayList<Coordinates>();
        stepsExpected.add(new Coordinates(2, 2));
        stepsExpected.add(new Coordinates(2, 3));
        stepsExpected.add(new Coordinates(2, 4));

        stepsActual = NavigationUtil.moveUtil(2, 1, 2, 4);
        for (int i=0; i < 3; i++) {
            assertTrue("Step " + i + " does not match.", (stepsExpected.get(i).getX() == stepsActual.get(i).getX()) && (stepsExpected.get(i).getY() == stepsActual.get(i).getY()));
        }
        assertTrue("Not the same size", stepsExpected.size() == stepsActual.size());

    }

}
