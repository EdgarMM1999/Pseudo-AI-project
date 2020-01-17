/*
Edgar Martinez Martinez
EXM18001
This program is meant to take a file in as input and using the file and two letters
to represent an ant and a beetle and the last input from the user is the amount
of turns needed. The program will use a pseudo AI to make beetles attempt to eat
ants and the ants will run away from the beetles if possible and for the specified
amount of turns.
 */

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Creature [][] var = new Creature [10][10];
        Scanner scan = new Scanner(System.in);
        String FileIn;
        FileIn = scan.next();
        File in = new File(FileIn);
        Scanner inputFile = new Scanner(in);
        int turns;
        char aChar, bChar;
        char character = ' ';
        String line = "";
        
        line = scan.next();
        aChar = line.charAt(0);
        line = scan.next();
        bChar = line.charAt(0);
        turns = scan.nextInt();
        

        for (int i = 0; i < 10; i++)
        {
            if(inputFile.hasNextLine())
            {
                line = inputFile.nextLine();
            }
            else
                line = "          ";
            for(int j = 0; j < 10; j++)
            {
                character = line.charAt(j);
                
                if(character == 'B')
                {
                    var[i][j] = new Beetle(i, j, 0);
                }
                else if(character == 'a')
                {
                    var[i][j] = new Ant(i, j);
                }
            }
        }
        
        for(int i = 1; i <= turns; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                for(int k = 0; k < 10; k++)
                {
                    switch(j)
                    {
                        case 0:
                        {
                            for(int l = 0; l < 10; l++)
                            {
                                if(var[l][k] instanceof Beetle && var[l][k].hasMoved == false)
                                {
                                    var[l][k].movement(var);
                                }
                                else if(var[l][k] instanceof Beetle)
                                    var[l][k].hasMoved = true;
                            }
                            break;
                        }
                        case 1:
                        {
                            for(int l = 0; l < 10; l++)
                            {
                                if(var[l][k] instanceof Ant && var[l][k].hasMoved == false)
                                {
                                    var[l][k].movement(var);
                                }
                                else if(var[l][k] instanceof Ant)
                                    var[l][k].hasMoved = true;
                            }
                            break;
                        }
                        case 2:
                        {
                            for(int l = 0; l < 10; l++)
                            {
                                if(var[l][k] instanceof Beetle && var[l][k].hasMoved == false)
                                {
                                    if(((Beetle)var[l][k]).starveCntr >= 5)
                                        var[l][k] = null;
                                    else if(var[l][k] instanceof Creature)
                                        var[l][k].hasMoved = true;
                                }
                            }
                            break;
                        }
                        case 3:
                        {
                            for(int l = 0; l < 10; l++)
                            {
                                if(var[l][k] instanceof Ant && var[l][k].hasMoved == false)
                                    var[l][k].breed(var);
                                else if(var[l][k] instanceof Creature)
                                    var[l][k].hasMoved = true;
                            }
                            break;
                        }
                        case 4:
                        {
                            for(int l = 0; l < 10; l++)
                            {
                                if(var[l][k] instanceof Beetle && var[l][k].hasMoved == false)
                                {
                                    var[l][k].breed(var);
                                }
                                else if(var[l][k] instanceof Creature)
                                    var[l][k].hasMoved = true;
                            }
                            break;
                        }
                    }
                }
                for(int k = 0; k < 10; k++)
                {
                    for(int l = 0; l < 10; l++)
                    {
                        if(var[l][k] instanceof Creature)
                            var[l][k].hasMoved = false;
                    }
                }
            }
            printGrid(var, i, aChar, bChar);
        }
    }
    
    
    
    /*
    This function prints out the grid with their respective character the user inputted
    for ants and beetles. 
    */
    
    public static void printGrid(Creature grid[][], int turn, char aChar, char bChar)
    {
        System.out.println("TURN " + turn);
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j< 10; j++)
            {
                if(grid[i][j] instanceof Ant)
                    System.out.print(aChar);
                else if(grid[i][j] instanceof Beetle)
                    System.out.print(bChar);
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
}
