/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gardenorganizer;

import static org.junit.Assert.*;

import java.awt.Point;
import org.junit.Test;

/**
 *
 * @author Yannis
 */
public class CalculatorTest {

    private Calculator c = new Calculator(4, 5, 6, 7);

    public CalculatorTest() {
    }

    @Test
    public void testCalculateTRForCoordinates_first() {
        Point p = c.calculateTRForCoordinates(1, 1);
        assertEquals(6, p.x);
        assertEquals(0, p.y);
    }

    @Test
    public void testCalculateTRForCoordinates_middle() {
        Point p = c.calculateTRForCoordinates(7, 12);
        assertEquals(12, p.x);
        assertEquals(7, p.y);
    }

    @Test
    public void testCalculateTRForCoordinates_last() {
        Point p = c.calculateTRForCoordinates(16, 19);
        assertEquals(18, p.x);
        assertEquals(14, p.y);
    }

    @Test
    public void testCalculateTLForCoordinates_first() {
        Point p = c.calculateTLForCoordinates(1, 1);
        assertEquals(0, p.x);
        assertEquals(0, p.y);
    }

    @Test
    public void testCalculateTLForCoordinates_middle() {
        Point p = c.calculateTLForCoordinates(7, 12);
        assertEquals(6, p.x);
        assertEquals(7, p.y);
    }

    @Test
    public void testCalculateTLForCoordinates_last() {
        Point p = c.calculateTLForCoordinates(16, 19);
        assertEquals(12, p.x);
        assertEquals(14, p.y);
    }

    @Test
    public void testCalculateLRForCoordinates_first() {
        Point p = c.calculateLRForCoordinates(1, 1);
        assertEquals(6, p.x);
        assertEquals(7, p.y);
    }

    @Test
    public void testCalculateLRForCoordinates_middle() {
        Point p = c.calculateLRForCoordinates(7, 12);
        assertEquals(12, p.x);
        assertEquals(14, p.y);
    }

    @Test
    public void testCalculateLRForCoordinates_last() {
        Point p = c.calculateLRForCoordinates(16, 19);
        assertEquals(18, p.x);
        assertEquals(21, p.y);
    }

    @Test
    public void testCalculateLLForCoordinates() {
        Point p = c.calculateLLForCoordinates(1, 1);
        assertEquals(0, p.x);
        assertEquals(7, p.y);
    }

    @Test
    public void testCalculateLLForCoordinates_middle() {
        Point p = c.calculateLLForCoordinates(7, 12);
        assertEquals(6, p.x);
        assertEquals(14, p.y);
    }

    @Test
    public void testCalculateLLForCoordinates_last() {
        Point p = c.calculateLLForCoordinates(16, 19);
        assertEquals(12, p.x);
        assertEquals(21, p.y);
    }

    @Test
    public void testGetColumnNumberForCoordinates() {
        assertEquals(0, c.getColumnNumberForCoordinates(2, 2));
        assertEquals(0, c.getColumnNumberForCoordinates(5, 5));
        assertEquals(1, c.getColumnNumberForCoordinates(7, 12));
        assertEquals(1, c.getColumnNumberForCoordinates(12, 7));
        assertEquals(3, c.getColumnNumberForCoordinates(19, 19));
    }

    @Test
    public void testGetRowNumberForCoordinates() {
        assertEquals(-1, c.getRowNumberForCoordinates(0, 0));
        assertEquals(0, c.getRowNumberForCoordinates(12, 2));
        assertEquals(0, c.getRowNumberForCoordinates(1, 1));
        assertEquals(0, c.getRowNumberForCoordinates(1, 5));
        assertEquals(0, c.getRowNumberForCoordinates(5, 5));
        assertEquals(0, c.getRowNumberForCoordinates(1, 6));
        assertEquals(2, c.getRowNumberForCoordinates(20, 15));
        assertEquals(3, c.getRowNumberForCoordinates(20, 26));
    }

    @Test
    public void testGetBoxNumberForCoordinates() {
        assertEquals(0, c.getBoxNumberForCoordinates(1, 1));
        assertEquals(3, c.getBoxNumberForCoordinates(19, 1));
        assertEquals(5, c.getBoxNumberForCoordinates(7, 12));
        assertEquals(10, c.getBoxNumberForCoordinates(15, 15));
        assertEquals(11, c.getBoxNumberForCoordinates(20, 20));
    }

    @Test
    public void testHasTopNeighbor() {
        assertTrue(c.hasTopNeighbor(4));
        assertTrue(c.hasTopNeighbor(7));
        assertTrue(c.hasTopNeighbor(10));
        assertTrue(c.hasTopNeighbor(11));
        assertTrue(c.hasTopNeighbor(15));
        assertFalse(c.hasTopNeighbor(1));
        assertFalse(c.hasTopNeighbor(0));
        assertFalse(c.hasTopNeighbor(3));
    }

    @Test
    public void testHasBottomNeighbor() {
        assertTrue(c.hasBottomNeighbor(0));
        assertTrue(c.hasBottomNeighbor(3));
        assertTrue(c.hasBottomNeighbor(9));
        assertTrue(c.hasBottomNeighbor(11));
        assertFalse(c.hasBottomNeighbor(16));
        assertFalse(c.hasBottomNeighbor(19));
    }

    @Test
    public void testHasLeftNeighbor() {
        assertTrue(c.hasLeftNeighbor(1));
        assertTrue(c.hasLeftNeighbor(15));
        assertTrue(c.hasLeftNeighbor(3));
        assertTrue(c.hasLeftNeighbor(9));
        assertFalse(c.hasLeftNeighbor(0));
        assertFalse(c.hasLeftNeighbor(12));
    }

    @Test
    public void testHasRightNeighbor() {
        assertTrue(c.hasRightNeighbor(0));
        assertTrue(c.hasRightNeighbor(6));
        assertTrue(c.hasRightNeighbor(14));
        assertTrue(c.hasRightNeighbor(1));
        assertFalse(c.hasRightNeighbor(3));
        assertFalse(c.hasRightNeighbor(15));
        assertFalse(c.hasRightNeighbor(7));
    }

    @Test
    public void testGetColumnNumberFromBoxNumber() {
        assertEquals(1, c.getColumnNumberFromBoxNumber(1));
        assertEquals(1, c.getColumnNumberFromBoxNumber(5));
        assertEquals(1, c.getColumnNumberFromBoxNumber(9));
        assertEquals(1, c.getColumnNumberFromBoxNumber(13));
        assertEquals(0, c.getColumnNumberFromBoxNumber(8));
        assertEquals(0, c.getColumnNumberFromBoxNumber(0));
        assertEquals(0, c.getColumnNumberFromBoxNumber(4));
        assertEquals(0, c.getColumnNumberFromBoxNumber(12));
        assertEquals(2, c.getColumnNumberFromBoxNumber(2));
        assertEquals(2, c.getColumnNumberFromBoxNumber(6));
        assertEquals(2, c.getColumnNumberFromBoxNumber(10));
        assertEquals(2, c.getColumnNumberFromBoxNumber(14));
        assertEquals(3, c.getColumnNumberFromBoxNumber(15));
        assertEquals(3, c.getColumnNumberFromBoxNumber(3));
        assertEquals(3, c.getColumnNumberFromBoxNumber(11));
        assertEquals(3, c.getColumnNumberFromBoxNumber(7));
    }

    @Test
    public void testGetRowNumberFromBoxNumber() {
        assertEquals(0, c.getRowNumberFromBoxNumber(2));
        assertEquals(0, c.getRowNumberFromBoxNumber(1));
        assertEquals(0, c.getRowNumberFromBoxNumber(3));
        assertEquals(0, c.getRowNumberFromBoxNumber(0));
        assertEquals(1, c.getRowNumberFromBoxNumber(4));
        assertEquals(1, c.getRowNumberFromBoxNumber(5));
        assertEquals(1, c.getRowNumberFromBoxNumber(6));
        assertEquals(1, c.getRowNumberFromBoxNumber(7));
        assertEquals(2, c.getRowNumberFromBoxNumber(8));
        assertEquals(2, c.getRowNumberFromBoxNumber(9));
        assertEquals(2, c.getRowNumberFromBoxNumber(10));
        assertEquals(2, c.getRowNumberFromBoxNumber(11));
        assertEquals(3, c.getRowNumberFromBoxNumber(15));
        assertEquals(3, c.getRowNumberFromBoxNumber(12));
        assertEquals(3, c.getRowNumberFromBoxNumber(13));
        assertEquals(3, c.getRowNumberFromBoxNumber(14));
    }

    @Test
    public void testCalculateTRForBoxNumber() {
        assertEquals(6, c.calculateTRForBoxNumber(0).x);
        assertEquals(0, c.calculateTRForBoxNumber(0).y);

        assertEquals(12, c.calculateTRForBoxNumber(5).x);
        assertEquals(7, c.calculateTRForBoxNumber(5).y);

        assertEquals(24, c.calculateTRForBoxNumber(15).x);
        assertEquals(21, c.calculateTRForBoxNumber(15).y);
    }

    @Test
    public void testCalculateLRForBoxNumber() {
        assertEquals(6, c.calculateLRForBoxNumber(0).x);
        assertEquals(7, c.calculateLRForBoxNumber(0).y);

        assertEquals(12, c.calculateLRForBoxNumber(5).x);
        assertEquals(14, c.calculateLRForBoxNumber(5).y);

        assertEquals(24, c.calculateLRForBoxNumber(15).x);
        assertEquals(28, c.calculateLRForBoxNumber(15).y);
    }

    @Test
    public void testCalculateLLForBoxNumber() {
        assertEquals(0, c.calculateLLForBoxNumber(0).x);
        assertEquals(7, c.calculateLLForBoxNumber(0).y);

        assertEquals(6, c.calculateLLForBoxNumber(5).x);
        assertEquals(14, c.calculateLLForBoxNumber(5).y);

        assertEquals(18, c.calculateLLForBoxNumber(15).x);
        assertEquals(28, c.calculateLLForBoxNumber(15).y);
    }

    @Test
    public void testCalculateTLForBoxNumber() {
        assertEquals(0, c.calculateTLForBoxNumber(0).x);
        assertEquals(0, c.calculateTLForBoxNumber(0).y);

        assertEquals(6, c.calculateTLForBoxNumber(5).x);
        assertEquals(7, c.calculateTLForBoxNumber(5).y);

        assertEquals(18, c.calculateTLForBoxNumber(15).x);
        assertEquals(21, c.calculateTLForBoxNumber(15).y);
    }

    @Test
    public void testGetPointInBoxNumber(){
        int x = c.getPointInBoxNumber(0).x;
        int y = c.getPointInBoxNumber(0).y;
        assertEquals(0,c.getBoxNumberForCoordinates(x, y));
    }
}
