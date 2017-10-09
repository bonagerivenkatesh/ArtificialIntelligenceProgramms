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
public class RandomRestart {
    
        
        long noOfRestarts;
	long noOfStateChanges;
        int sizeN;
        Node initial;
        
        
        public RandomRestart(){
		
                sizeN=QueenRandomHillClimb.N;
                noOfStateChanges=0;
                noOfRestarts=0;
		
                
                initial= new Node();
        
        
            Random random = new Random();
            for(int i=0; i<sizeN; i++){
		initial.state[i] = new Queen(random.nextInt(sizeN), i);	
            }
	}
        
        public Node randomRestartHillClimb(){ //the function checks for the neighbour node with better heuristics(hill climbing)
                                               //if the hill climb fails then the function restarts with the new state
                
		Node currentNode = new Node(initial);
		
				int heuristic = currentNode.getHeuristic();
                
                Node nextNode = new Node();
                int flag=0;
		while(heuristic!=0){
                    
                        nextNode = currentNode.getNextNeighbour(currentNode);
                      
                        if(heuristic>nextNode.getHeuristic())
                        {
                            currentNode.copynode(currentNode, nextNode);
                            noOfStateChanges++;
                            heuristic=currentNode.getHeuristic();
                        }
                        else
                        {
                            noOfRestarts++;
                            noOfStateChanges=0;
                            currentNode = new Node(2);
                            heuristic=currentNode.getHeuristic();
                            flag=0;
                        }
                    
		}
		return currentNode;
	}
        
        public Node randomRestartHillClimb(Node n){ //the function checks for the neighbour node with better heuristics(hill climbing)
                                                    //if the hill climb fails then the function restarts with the new state
		Node currentNode = new Node(n);
		initial = n;
		int heuristic = currentNode.getHeuristic();
                
                Node nextNode = new Node();
                int flag=0;
		while(heuristic!=0){
                    
                        nextNode = currentNode.getNextNeighbour(currentNode);
                      
                        if(heuristic>nextNode.getHeuristic())
                        {
                            currentNode.copynode(currentNode, nextNode);
                            noOfStateChanges++;
                            heuristic=currentNode.getHeuristic();
                        }
                        else
                        {
                            noOfRestarts++;
                            noOfStateChanges=0;
                            currentNode = new Node(2);
                            heuristic=currentNode.getHeuristic();
                            flag=0;
                        }
                    
		}
		return currentNode;
	}
        
        
    
}
