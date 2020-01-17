/*
Edgar Martinez Martinez
EXM18001
This Beetle class stores the counter for breeding and the starve Counter which is
used to make sure the Beetle dies if it hasn't eaten anything within a certain amount
of time
 */

public class Beetle extends Creature {
    int starveCntr;
    Beetle(){};
    
    Beetle(int r, int c, int s)
    {
        super(r, c);
        starveCntr = s;
    };
    
    /*
    This overriden movement function moves a Beetle towards the nearest Ant and 
    if it's not able to then it doesn't move. This section of code first checks where
    the closest ant instance is then find if the Beetle is able to move in that direction.
    */
    
    @Override
    public void movement(Creature grid [][])
    {

        int antCounter = 0;
        int moveR = ++column;
        int moveL = --column;
        int moveU = --row;
        int moveD = ++row;
        hasMoved = false;
        boolean turnMoved = false;
        boolean hasEaten = false;
        Ant aClose [] = new Ant[4];
        int distance [] = new int [4];
        distance[0] = -1;
        distance[1] = -1;
        distance[2] = -1;
        distance[3] = -1;        
        for(int i = moveU; i >= 0; i--)
        {
            if(grid[i][column] instanceof Ant)
            {
                aClose[0] = (Ant)grid[i][column];
                distance[0] = java.lang.Math.abs(row - grid[i][column].row);
                i = -1;
                antCounter++;
            }
        }
        if(distance[0] == -1)
            distance[0] = 11;
        
        for(int i = moveD; i < 10; i++)
        {
            if(grid[i][column] instanceof Ant)
            {
                aClose[2] = (Ant)grid[i][column];
                distance[2] = java.lang.Math.abs(row - grid[i][column].row);
                i = 11;
                antCounter++;
            }
            
        }
        if(distance[2] == -1)
            distance[2] = 11;

        for(int i = moveR; i < 10; i++)
        {
            if(grid[row][i] instanceof Ant)
            {
                aClose[1] = (Ant)grid[row][i];
                distance[1] = java.lang.Math.abs(column - grid[row][i].column);
                i = 11;
                antCounter++;
            }
        }   
        if(distance[1] == -1)
            distance[1] = 11;
        
        for(int i = moveL; i >= 0; i--)
        {
            if(grid[row][i] instanceof Ant)
            {
                aClose[3] = (Ant)grid[row][i];
                distance[3] = java.lang.Math.abs(column - grid[row][i].column);
                i = -1;
                antCounter++;
            }
        } 
        if(distance[3] == -1)
            distance[3] = 11;
        
        
        
        
        
        int smallDist = distance[0];
        int numEquidistant = 0;
        int numOfAnts [] = new int [4];
        for(int i = 1; i < 4; i++)
        {
            if(smallDist > distance[i])
                smallDist = distance[i];
            else if (smallDist == distance[i])
                numEquidistant++;
        }
        if(numEquidistant > 0)
        {
            for(int i = 0; i < 3; i++)
            {
                if(aClose[i] instanceof Ant)
                {
                    numEquidistant = 0;
                    if((aClose[i].row) - 1 >= 0)
                    {
                        if(grid[aClose[i].row - 1][aClose[i].column] instanceof Ant)
                            numEquidistant++;
                    }
                    if((aClose[i].column) + 1 < 10)
                    {
                        if(grid[aClose[i].row][aClose[i].column + 1] instanceof Ant)
                            numEquidistant++;
                    }
                    if((aClose[i].column) + 1 < 10 && (aClose[i].row) - 1 >= 0)
                    {
                        if(grid[aClose[i].row - 1][aClose[i].column + 1] instanceof Ant)
                            numEquidistant++;
                    }
                    if((aClose[i].column) + 1 < 10)
                    {
                        if(grid[aClose[i].row][aClose[i].column + 1] instanceof Ant)
                            numEquidistant++;
                    }
                    if((aClose[i].column - 1) >= 0)
                    {
                        if(grid[aClose[i].row][aClose[i].column - 1] instanceof Ant)
                            numEquidistant++;
                    }
                    if(((aClose[i].column) + 1) < 10 && ((aClose[i].row) - 1 >= 10))
                    {
                        if(grid[aClose[i].row - 1][aClose[i].column + 1] instanceof Ant)
                            numEquidistant++;
                    }
                    if(((aClose[i].column) + 1) < 10 && ((aClose[i].row) + 1 < 10))
                    {
                        if(grid[aClose[i].row + 1][aClose[i].column + 1] instanceof Ant)
                            numEquidistant++;
                    }
                    if((aClose[i].column) - 1 >= 0 && (aClose[i].row) - 1 >= 0)
                    {
                        if(grid[aClose[i].row - 1][aClose[i].column - 1] instanceof Ant)
                            numEquidistant++;
                    }
                    numOfAnts[i] = numEquidistant;
                }
            }
        }
        
        int bigAnts = numOfAnts[0];
        for(int i = 1; i < 4; i++)
        {
            if(bigAnts < numOfAnts[i])
                bigAnts = numOfAnts[i]; 
        }
        

        if(antCounter >= 1 && !(numEquidistant >= 1))
        {
            if(aClose[0] instanceof Ant)
            {
                if(distance[0] == smallDist && (row - 1 >= 0))
                {
                    if(!(grid[row - 1][column] instanceof Creature))
                    {
                        grid[row - 1][column] = this;
                        grid[row][column] = null;
                        row--;
                        starveCntr++;
                    }
                    else if(grid[row - 1][column] instanceof Ant)
                    {
                        grid[row - 1][column] = this;
                        grid[row][column] = null;
                        hasEaten = true;
                        row--;
                        starveCntr = 0;
                    }
                    turnMoved = false;
                }
            }
            if(aClose[1] instanceof Ant && !turnMoved)
            {
                if(distance[1] == smallDist && (column + 1 < 10))
                {
                    if(!(grid[row][column + 1] instanceof Creature))
                    {
                        grid[row][column + 1] = this;
                        grid[row][column] = null;
                        starveCntr++;
                        column++;
                    }
                    else if(grid[row][column + 1] instanceof Ant)
                    {
                        grid[row][column + 1] = this;
                        grid[row][column] = null;
                        hasEaten = true;
                        starveCntr = 0;
                        column++;
                    }
                    turnMoved = true;
                }
            }
            if(aClose[2] instanceof Ant && !turnMoved)
            {
                if(distance[2] == smallDist && (row + 1 < 10))
                {
                    if(!(grid[row + 1][column] instanceof Creature))
                    {
                        grid[row + 1][column] = this;
                        grid[row][column] = null;
                        starveCntr++;
                        row++;
                    }
                    else if(grid[row + 1][column] instanceof Ant)
                    {
                        grid[row + 1][column] = this;
                        grid[row][column] = null;
                        hasEaten = true;
                        starveCntr = 0;
                        row++;
                    }
                    turnMoved = true;
                }
            }
            if(aClose[3] instanceof Ant && !turnMoved)
            {
                if(distance[3] == smallDist && (column - 1 >= 0))
                {
                    if(!(grid[row][column - 1] instanceof Creature))
                    {
                        grid[row][column - 1] = this;
                        grid[row][column] = null;
                        starveCntr++;
                        column--;
                    }
                    else if(grid[row][column - 1] instanceof Ant)
                    {
                        grid[row][column - 1] = this;
                        grid[row][column] = null;
                        hasEaten = true;
                        starveCntr = 0;
                        column--;
                    }
                    turnMoved = true;
                }
            } 
        }
        
        
        
        
        else if(numEquidistant >= 1)
        {
            if(aClose[0] instanceof Ant)
            {
                if(distance[0] == smallDist && (row - 1 >= 0) && numOfAnts[0] == bigAnts)
                {
                    if(!(grid[row - 1][column] instanceof Creature))
                    {
                        grid[row - 1][column] = this;
                        grid[row][column] = null;
                        row--;
                        starveCntr++;
                    }
                    else if(grid[row - 1][column] instanceof Ant)
                    {
                        grid[row - 1][column] = this;
                        grid[row][column] = null;
                        hasEaten = true;
                        row--;
                        starveCntr = 0;
                    }
                    turnMoved = false;
                }
            }
            if(aClose[1] instanceof Ant && !turnMoved && numOfAnts[1] == bigAnts)
            {
                if(distance[1] == smallDist && (column + 1 < 10))
                {
                    if(!(grid[row][column + 1] instanceof Creature))
                    {
                        grid[row][column + 1] = this;
                        grid[row][column] = null;
                        starveCntr++;
                        column++;
                    }
                    else if(grid[row][column + 1] instanceof Ant)
                    {
                        grid[row][column + 1] = this;
                        grid[row][column] = null;
                        hasEaten = true;
                        starveCntr = 0;
                        column++;
                    }
                    turnMoved = true;
                }
            }
            if(aClose[2] instanceof Ant && !turnMoved && numOfAnts[2] == bigAnts)
            {
                if(distance[2] == smallDist && (row + 1 < 10))
                {
                    if(!(grid[row + 1][column] instanceof Creature))
                    {
                        grid[row + 1][column] = this;
                        grid[row][column] = null;
                        starveCntr++;
                        row++;
                    }
                    else if(grid[row + 1][column] instanceof Ant)
                    {
                        grid[row + 1][column] = this;
                        grid[row][column] = null;
                        hasEaten = true;
                        starveCntr = 0;
                        row++;
                    }
                    turnMoved = true;
                }
            }
            if(aClose[3] instanceof Ant && !turnMoved && numOfAnts[3] == bigAnts)
            {
                if(distance[3] == smallDist && (column - 1 >= 0))
                {
                    if(!(grid[row][column - 1] instanceof Creature))
                    {
                        grid[row][column - 1] = this;
                        grid[row][column] = null;
                        starveCntr++;
                        column--;
                    }
                    else if(grid[row][column - 1] instanceof Ant)
                    {
                        grid[row][column - 1] = this;
                        grid[row][column] = null;
                        hasEaten = true;
                        starveCntr = 0;
                        column--;
                    }
                    turnMoved = true;
                }
            } 
        }
        else 
            starveCntr++;
        hasMoved = true;
    };
    
    /*
    This overriden breed function checks for any available space orthogonal 
    to the current beetle in the grid that is passed through and creates a new Beetle
    in the first available space if the breed counter is = to 8 and the nearest 
    orthogonal space doesn't have a Creature.
     */
    
    @Override
    public void breed(Creature grid[][])
    {
        int counterR = row;
        int counterC = column;
        boolean hasBreeded = false;
        if(breedCntr < 8)
        {
            breedCntr++;
        }
        if(breedCntr >= 8)
        {
            breedCntr = 0;
            if(row - 1 >= 0)
            {
                if(!(grid[row - 1][column] instanceof Creature))
                {
                    counterR = row - 1;
                    counterC = column;
                    grid[counterR][counterC] = new Beetle(row - 1, column, 0);
                    hasBreeded = true;
                }
            }
            if(column + 1 < 10)
            {
                if(!(grid[row][column + 1] instanceof Creature) && !hasBreeded)
                {
                    counterR = row;
                    counterC =  column + 1;
                    grid[counterR][counterC] = new Beetle(row, column + 1, 0);
                    hasBreeded = true;
                }
            }
            if(row + 1 < 10)
            {
                if(!(grid[row + 1][column] instanceof Creature) && !hasBreeded)
                {
                    counterR = row + 1;
                    counterC = column;
                    grid[counterR][counterC] = new Beetle(row + 1, column, 0);
                    hasBreeded = true;
                }
            }
            if(column - 1 >= 0)
            {
                if(!(grid[row][column - 1] instanceof Creature) && !hasBreeded)
                {
                    counterR = row;
                    counterC = column - 1;
                    grid[counterR][counterC] = new Beetle(row, column - 1, 0);
                    hasBreeded = true;
                }
            }
        }
    }
}
