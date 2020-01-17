/*
Edgar Martinez Martinez
EXM18001
*/


public abstract class Creature {
    protected int column;
    protected int row;
    protected int breedCntr;
    protected boolean hasMoved;
    
    
    Creature(){};
    
    Creature(int r, int c)
    {
        row = r;
        column = c;
        breedCntr = 0;
        hasMoved = false;
    };
    
    //These functions are expanded upon in the child classes Ant and Beetle
    
    public abstract void movement(Creature Grid [][]);
    public abstract void breed(Creature Grid [][]);
}
