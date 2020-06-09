package edu.vt.cs5044;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.vt.cs5044.tetris.AI;
import edu.vt.cs5044.tetris.Board;
import edu.vt.cs5044.tetris.Placement;
import edu.vt.cs5044.tetris.Rotation;
import edu.vt.cs5044.tetris.Shape;

/**
 * 
 * This test to verify all methods work correctly in TetrisAI
 *
 * @author yijing
 * @version Mar 9, 2019
 *
 */
public class TetrisAITest
{
    private AI tetrisAI;
    private Board emptyBoard;
    private Board simpleBoard;
    private Board lessSimpleBoard;
    private Board fullBoard;
    
    @Before
    public void setup() 
    {
        tetrisAI = new TetrisAI();
        emptyBoard = new Board();
        simpleBoard = new Board("   # # # #");
        lessSimpleBoard = new Board(
                "   # # # #",
                "   # # # #",
                "  ## # # #"
                );
        fullBoard = new Board(
                "## ##    #",
                "# ##### ##",
                "#### #####",
                "# ##### ##",
                "## #######",
                "######### ",
                " #########",
                " #########",
                "###  #####",
                "####### ##",
                "######## #",
                " #### ####"
                );
    }

    /**
     * Test all methods with empty board
     */
    @Test
    public void testEmptyBoard()
    {
        assertEquals(0,tetrisAI.getAverageColumnBlocks(emptyBoard));
        assertEquals(0,tetrisAI.getColumnHeightRange(emptyBoard));
        assertEquals(0,tetrisAI.getColumnHeightVariability(emptyBoard));
        assertEquals(0,tetrisAI.getTotalGapCount(emptyBoard));
    }
    
    /**
     * Test all methods with simple board
     */
    @Test
    public void testSimpleBoard()
    {
        assertEquals(0,tetrisAI.getAverageColumnBlocks(simpleBoard));
        assertEquals(1,tetrisAI.getColumnHeightRange(simpleBoard));
        assertEquals(7,tetrisAI.getColumnHeightVariability(simpleBoard));
        assertEquals(0,tetrisAI.getTotalGapCount(simpleBoard));
    }
    
    /**
     * Test all methods with less simple board
     */
    @Test
    public void testlessSimpleBoard()
    {
        assertEquals(0,tetrisAI.getAverageColumnBlocks(simpleBoard));
        assertEquals(1,tetrisAI.getColumnHeightRange(simpleBoard));
        assertEquals(7,tetrisAI.getColumnHeightVariability(simpleBoard));
        assertEquals(0,tetrisAI.getTotalGapCount(simpleBoard));
        
    }
    
    /**
     * Test all methods with full board
     */
    @Test 
    public void testFullBoard()
    {
       
        assertEquals(10,tetrisAI.getAverageColumnBlocks(fullBoard));
        assertEquals(2,tetrisAI.getColumnHeightRange(fullBoard));
        assertEquals(6,tetrisAI.getColumnHeightVariability(fullBoard));
        assertEquals(14,tetrisAI.getTotalGapCount(fullBoard));
       
    }
    
    /**
     * Test best position for I shape on full board
     */
    @Test
    public void testBestPlacePositionIShape()
    {
        Placement bestPlace = tetrisAI.findBestPlacement(fullBoard, Shape.I);
        assertEquals(new Placement(Rotation.CCW_90, 5), bestPlace);
        assertEquals(Rotation.CCW_90, bestPlace.getRotation());
        
    }
    
    
    /**
     * Test best position for T shape on full board
     */
    @Test
    public void testBestPlacePositionTShape()
    {
        Placement bestPlace = tetrisAI.findBestPlacement(fullBoard, Shape.T);
        assertEquals(new Placement(Rotation.CCW_180, 6), bestPlace);
        assertEquals(Rotation.CCW_180, bestPlace.getRotation());
        
    }
    
    /**
     * Test best position for O shape on full board
     */
    @Test
    public void testBestPlacePositionOShape()
    {
        Placement bestPlace = tetrisAI.findBestPlacement(fullBoard, Shape.O);
        assertEquals(new Placement(Rotation.NONE, 0), bestPlace);
        assertEquals(Rotation.NONE, bestPlace.getRotation());   
    }
    
    /**
     * Test best position for Z shape on full board
     */
    @Test
    public void testBestPlacePositionZShape()
    {
        Placement bestPlace = tetrisAI.findBestPlacement(fullBoard, Shape.Z);
        assertEquals(new Placement(Rotation.CCW_90, 7), bestPlace);
        assertEquals(Rotation.CCW_90, bestPlace.getRotation());
        
    }
    
    /**
     * Test best position for J shape on full board
     */
    @Test
    public void testBestPlacePositionJShape()
    {
        Placement bestPlace = tetrisAI.findBestPlacement(fullBoard, Shape.J);
        assertEquals(new Placement(Rotation.CCW_90, 5), bestPlace);
        assertEquals(Rotation.CCW_90, bestPlace.getRotation());
        
    }
    
    /**
     * Test best position for L shape on full board
     */
    @Test
    public void testBestPlacePositionLShape()
    {
        Placement bestPlace = tetrisAI.findBestPlacement(fullBoard, Shape.L);
        assertEquals(new Placement(Rotation.CCW_270, 2), bestPlace);
        assertEquals(Rotation.CCW_270, bestPlace.getRotation());
        
    }
    
    /**
     * Test best position for S shape on full board
     */
    @Test
    public void testBestPlacePositionSShape()
    {
        Placement bestPlace = tetrisAI.findBestPlacement(fullBoard, Shape.S);
        assertEquals(new Placement(Rotation.NONE, 7), bestPlace);
        assertEquals(Rotation.NONE, bestPlace.getRotation());
        
    }
}
