/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queenrandomhillclimb;

import java.util.Random;

/**
 *
 * @author Venkatesh
 */
public class minConflictRandomRestart {
    
    long nodesGenerated;
        long noOfRestarts;
	long noOfStateChanges;
        Node currentNode;
        Node initialNode;
        int sizeN;
        int maxsteps;
        
        public minConflictRandomRestart(){
		
                noOfStateChanges=0;
                noOfRestarts=0;
		nodesGenerated = 0;
                sizeN=QueenRandomHillClimb.N;
                maxsteps = QueenRandomHillClimb.maxsteps;
                
                currentNode = new Node(2);
                
                initialNode = new Node(currentNode);
                
	}

        public long getNodesGenerated() {
            return nodesGenerated;
        }
        
        
        public Node randomRestart(){ //the function checks if the solution state is available using the minimum conflicts algo
                                    //the algotithm for minimum conflicts will run for maximum  allowed steps and if it cannot find the solution in those steps
                                    // it will restart the board and continue rhe algorithm
            int heuristic = currentNode.getHeuristic();
				
		while(heuristic!=0){
                    
                    for(int v=0;v<maxsteps && heuristic!=0;v++)
                    {
                        checkForSolution(currentNode);
                        heuristic = currentNode.getHeuristic();
                    }
            		
			if(heuristic!=0){ 
                            noOfRestarts++;
                            noOfStateChanges=0;
                            currentNode = new Node(2);
                            heuristic=currentNode.getHeuristic();
                            
			}
		}
		return currentNode;
	}
        
        public void checkForSolution(Node currentNode) //for the randomly selected Queen it will apply the minimum conflict algorithm
        {
            Random random = new Random();
            int flag=currentNode.moveToMinConflict(random.nextInt(sizeN));
                        
                        if(flag==1)
                            noOfStateChanges++;
                        
			
        }
    
}
