package edu.vt.cs5044;

import edu.vt.cs5044.tetris.AI;
import edu.vt.cs5044.tetris.Board;
import edu.vt.cs5044.tetris.Placement;
import edu.vt.cs5044.tetris.Rotation;
import edu.vt.cs5044.tetris.Shape;

/**
 * 
 * This program is to learn about TetrisAI interface. 
 * It will be tested in Junit and a tetris window will open to play and test.
 *
 * @author yijing
 * @version Mar 8, 2019
 *
 */
public class TetrisAI implements AI
{ 


    @Override
    public Placement findBestPlacement(Board currentBoard, Shape shape)
    {
        int bestCost = Integer.MAX_VALUE;
        Placement bestPlace = null;

        for (Rotation rotation : shape.getRotationSet())
        {
            int shapeWidth = shape.getWidth(rotation);
            for (int col = 0; col <= Board.WIDTH - shapeWidth; col++)
            {
                Placement trailPlacement = new Placement(rotation, col);
                Board trailBoard = currentBoard.getResultBoard(shape, trailPlacement);
                int trailCost = 5 * getAverageColumnBlocks(trailBoard) +
                        0 * getColumnHeightRange(trailBoard) +
                        10 * getTotalGapCount(trailBoard) +
                        5 * getColumnHeightVariability(trailBoard);

                if (trailCost < bestCost)
                {
                    bestCost = trailCost;
                    bestPlace = trailPlacement;
                }

            }
        }

        return bestPlace;
    }



    /**
     * It gives the height of a block at certain row and column.
     *
     * @param board
     * @param col
     * @return colHeight
     */
    private int getColumnHeight(Board board, int col) {
        int colHeight = 0;
        for (int row = 0; row < Board.HEIGHT; row++) {
            if (board.isBlockAt(col, row)) {
                colHeight = row + 1;
            }
        }
        return colHeight;
    }

    /**
     * It gives the count of blocks at certain column
     *
     * @param board
     * @return totalCount
     */
    private int getColumnBlockCount(Board board, int col) {
        int totalCount = 0;
        for (int row = 0; row < Board.HEIGHT; row++) 
        {    
            if (board.isBlockAt(col, row)) 
            {
                totalCount++;
            }
        }
        return totalCount;
    }

    @Override
    public int getAverageColumnBlocks(Board board)
    {
      
        int totalBlocks = 0;
        for (int col = 0; col < Board.WIDTH; col++)
        {
            totalBlocks += getColumnBlockCount(board, col);
        }
        return totalBlocks / Board.WIDTH ;
    }


    @Override
    public int getColumnHeightRange(Board board)
    {             
        int tallest = Integer.MIN_VALUE;
        int shortest = Integer.MAX_VALUE;
        for (int col = 0; col < Board.WIDTH; col++) {
            int currentHeight = getColumnHeight(board, col);
            if (currentHeight > tallest)
            {
                tallest = currentHeight;
            }
            if (currentHeight < shortest)
            {
                shortest = currentHeight;
            }
        }
        return (tallest - shortest);
    }

    @Override
    public int getColumnHeightVariability(Board board)
    {
        int heightVar = 0;
        for (int col = 0; col < Board.WIDTH - 1; col++) {
            int colDiff = Math.abs(getColumnHeight(board, col) - getColumnHeight(board, col + 1));
            heightVar = heightVar + colDiff;
        }

        return heightVar;
    }

    @Override
    public int getTotalGapCount(Board board)
    {
        int count = 0;
        for (int col = 0; col < Board.WIDTH; col++) {
            boolean hitBlock = false;
            for (int row = Board.HEIGHT - 1; row >= 0; row--) {
                if (board.isBlockAt(col, row)) {
                    hitBlock = true;
                } else if (hitBlock) {
                    count++;
                }
            }
        }
        return count;
    }

}
