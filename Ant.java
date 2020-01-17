/*
Edgar Martinez Martinez
EXM18001
This section of code extends the Creature class and moves the child depeinding on the 
grid that was passed into each function
This also contains a breedCntr which counts the numbers of turns passed so that
the ant can breed.
 */

import java.lang.Math;
public class Ant extends Creature{
    int breedCntr;
    Ant(){};
    Ant(int r, int c)
    {
        super(r, c);
        breedCntr = 0;
    };
    
    /*
    This overriden function is meant to move the ant based on where the nearest beetle is and
    if there's a space available for the ant to move to
    */

    @Override
    public void movement(Creature grid [][])
    {
        hasMoved = false;
        int beetleCounter = 0;
        int moveR = ++column;
        int moveL = --column;
        int moveU = --row;
        int moveD = ++row;
        Beetle bClose [] = new Beetle[4];
        int distance [] = new int [4];
        distance[0] = -1;
        distance[1] = -1;
        distance[2] = -1;
        distance[3] = -1;
        
        for(int i = moveU; i >= 0; i--)
        {
            if(grid[i][column] instanceof Beetle)
            {
                bClose[0] = (Beetle)grid[i][column];
                distance[0] = java.lang.Math.abs(row - grid[i][column].row);
                beetleCounter++;
                i = -1;
            }
        }
        if(distance[0] == -1)
            distance[0] = 11;
        
        for(int i = moveD; i < 10; i++)
        {
            if(grid[i][column] instanceof Beetle)
            {
                bClose[2] = (Beetle)grid[i][column];
                distance[2] = java.lang.Math.abs(row - grid[i][column].row);
                beetleCounter++;
                i = 10;
            }  
        }
        if(distance[2] == -1)
            distance[2] = 11;

        for(int i = moveR; i < 10; i++)
        {
            if(grid[row][i] instanceof Beetle)
            {
                bClose[1] = (Beetle)grid[row][i];
                distance[1] = java.lang.Math.abs(column - grid[row][i].column);
                beetleCounter++;
                i = 10;
            }
        }   
        if(distance[1] == -1)
            distance[1] = 11;
        
        for(int i = moveL; i >= 0; i--)
        {
            if(grid[row][i] instanceof Beetle)
            {
                bClose[3] = (Beetle)grid[row][i];
                distance[3] = java.lang.Math.abs(column - grid[row][i].column);
                beetleCounter++;
                i = -1;
            }
        } 
        if(distance[3] == -1)
            distance[3] = 11;
        
        
        
        
        int smallDist = distance[0];
        for(int i = 1; i < 4; i++)
        {
            if(smallDist > distance[i])
                smallDist = distance[i];
        }
        

        boolean turnMoved = false;
        if(beetleCounter >= 1)
        {
            if(bClose[0] instanceof Beetle && distance[0] < 11)
            {
                if(distance[0] == smallDist && (row + 1 < 10))
                {
                    if(!(grid[row + 1][column] instanceof Creature))
                    {
                        grid[row + 1][column] = this;
                        grid[row][column] = null;
                        row++;
                    }
                    turnMoved = true;
                }
            }
            if((bClose[1] instanceof Beetle) && !turnMoved && distance[1] < 11)
            {
                if(distance[1] == smallDist && (column - 1 >= 0))
                {
                    if(!(grid[row][column - 1] instanceof Creature))
                    {
                        grid[row][column - 1] = this;
                        grid[row][column] = null;
                        column--;
                    }
                    turnMoved = true;
                }
            }
            if((bClose[2] instanceof Beetle) && !turnMoved && distance[2] < 11)
            {
                if(distance[2] == smallDist && (row - 1 >= 0))
                {
                    if(!(grid[row - 1][column] instanceof Creature))
                    {
                        grid[row - 1][column] = this;
                        grid[row][column] = null;
                        row--;
                    }
                    turnMoved = true;
                }
            }
            if((bClose[3] instanceof Beetle) && !turnMoved && distance[3] < 11)
            {
                if(distance[3] == smallDist && (column + 1 < 10))
                {
                    if(!(grid[row][column + 1] instanceof Creature))
                    {
                        grid[row][column + 1] = this;
                        grid[row][column] = null;
                        column++;
                    }
                    turnMoved = true;
                }
            } 
        }
        hasMoved = true;
    };
    
    /*
    This overriden function is used to breed the ants depending whether or not 5 turns have
    elapsed or not. Once 5 turns have elapsed the ant will find the first available space orthogonal
    to it and create a new ant instance at that location.
    */
    
    @Override
    public void breed(Creature grid [][])
    {
        int counterR = row;
        int counterC = column;
        boolean hasBreeded = false;
        if(breedCntr < 3)
        {
            breedCntr++;
        }
        if(breedCntr >= 3)
        {
            breedCntr = 0;
            if(row - 1 >= 0)
            {
                if(!(grid[row - 1][column] instanceof Creature))
                {
                    counterR = row - 1;
                    counterC = column;
                    grid[counterR][counterC] = new Ant(row - 1, column);
                    hasBreeded = true;
                }
            }
            if(column + 1 < 10)
            {
                if(!(grid[row][column + 1] instanceof Creature) && !hasBreeded)
                {
                    counterR = row;
                    counterC =  column + 1;
                    grid[counterR][counterC] = new Ant(row, column + 1);
                    hasBreeded = true;
                }
            }
            if(row + 1 < 10)
            {
                if(!(grid[row + 1][column] instanceof Creature) && !hasBreeded)
                {
                    counterR = row + 1;
                    counterC = column;
                    grid[counterR][counterC] = new Ant(row + 1, column);
                    hasBreeded = true;
                }
            }
            if(column - 1 >= 0)
            {
                if(!(grid[row][column - 1] instanceof Creature) && !hasBreeded)
                {
                    counterR = row;
                    counterC = column - 1;
                    grid[counterR][counterC] = new Ant(row, column - 1);
                }
            }
        }
    }
}
