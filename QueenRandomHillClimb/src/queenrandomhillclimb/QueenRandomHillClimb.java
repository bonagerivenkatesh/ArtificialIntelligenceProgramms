/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queenrandomhillclimb;

import java.util.Scanner;

/**
 *
 * @author Venkatesh
 */
public class QueenRandomHillClimb {

    /**
     * @param args the command line arguments
     */
    
    public static int N; //its the size of the board
    public static int maxsteps; //the maximum attempts allowed for minimum conflict before restarting the board
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
    
    
    
    
    System.out.println("Enter the number of Queens");
    Scanner scan = new Scanner(System.in);
    N = scan.nextInt(); //takes the size of the board from the user
    
    
    
    System.out.println("Enter the max steps for min conflict algorithm");
    maxsteps = scan.nextInt();//takes the maximum attempts allowed for minimum conflict before restarting the board from the user
    
    int noOfExecutions, hillClimbSuccess=0, minConflictSuccess=0;
    long minConflictStateChanges=0,hillClimbStateChanges=0;
    long minConflictTotalRestars=0,hillClimbTotalRestars=0;
    
    double avgHillClimbStateChange, avgMinConflictStateChange;
    double avgHillClimbRestarts, avgMinConflictRestarts;
    
    System.out.println("Enter the number of executions that you want to try");
    noOfExecutions = scan.nextInt();
    
    for(int v=0;v<noOfExecutions;v++)
    {
        minConflictRandomRestart solveQueenmin = new minConflictRandomRestart();
        Node solution1 = solveQueenmin.randomRestart();
    
        if(solution1.getHeuristic()==0)
        {
            System.out.println();
            System.out.println();
            System.out.println("Min conflicts with Random restarts for Execution number "+(v+1));
        
        
            System.out.println("Number of restarts to solve are "+solveQueenmin.noOfRestarts);
        
            System.out.println("Number of state changes to solve are "+solveQueenmin.noOfStateChanges);
            minConflictSuccess++;
            minConflictStateChanges=minConflictStateChanges+solveQueenmin.noOfStateChanges;
            minConflictTotalRestars=minConflictTotalRestars+solveQueenmin.noOfRestarts;
            printBoard(solution1);
        }
        
        else
            System.out.println("Couldnt solve the problem");
     
        
    


    
        RandomRestart solveQueen = new RandomRestart();
        Node solution = solveQueen.randomRestartHillClimb(solveQueenmin.initialNode);
        //Node solution = solveQueen.randomRestartHillClimb();
    
        if(solution.getHeuristic()==0)
        {
            System.out.println();
            System.out.println();
            System.out.println("Hill Climbing with Random restarts for Execution number "+(v+1));
     
            System.out.println("Number of restarts to solve are "+solveQueen.noOfRestarts);
        
            System.out.println("Number of state changes to solve are "+solveQueen.noOfStateChanges);
        
            hillClimbSuccess++;
            hillClimbStateChanges=hillClimbStateChanges+solveQueen.noOfStateChanges;
            hillClimbTotalRestars=hillClimbTotalRestars+solveQueen.noOfRestarts;        
            printBoard(solution);
        }
        else
            System.out.println("Couldnt solve the problem");
        
        }
    
        avgHillClimbStateChange = hillClimbStateChanges/hillClimbSuccess;
        avgMinConflictStateChange = minConflictStateChanges/minConflictSuccess;
        
        avgHillClimbRestarts= hillClimbTotalRestars/hillClimbSuccess;
        avgMinConflictRestarts = minConflictTotalRestars/minConflictSuccess;
      
        System.out.println();
        System.out.println();
        System.out.println("Total no of Executions are "+noOfExecutions);
        
        System.out.println("Min Conflict was successfull in "+minConflictSuccess+" Executions with the average State Changes of "+avgMinConflictStateChange+" with "+avgMinConflictRestarts+" Restarts in average");
        
        System.out.println("Hill Climb with Random Restart was successfull in "+hillClimbSuccess+" Executions with the average State Changes of "+avgHillClimbStateChange+" with "+avgHillClimbRestarts+" Restarts in average");
    
    }
    
    public static void printBoard(Node n) //prints the board state
    {
        System.out.println("The final board look as below");
        
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                if(n.state[j].row==i)
                {
                    if(n.state[j].column==j)
                        System.out.print(" X");
                    else
                        System.out.print(" O");
                }
                else
                    System.out.print(" O");
            }
            System.out.println();
        }
    }
    
}
