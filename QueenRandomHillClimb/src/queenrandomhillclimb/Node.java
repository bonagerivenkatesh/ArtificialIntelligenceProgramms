/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queenrandomhillclimb;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Venkatesh
 */
public class Node {
    
    Queen[] state; 
    
    int heuristics;
    int sizeN;
    
    int[][] conflictsMatrix;

    public Node() {
        
        sizeN=QueenRandomHillClimb.N;
        heuristics=0;
    
        state = new Queen[sizeN];
        
    }
    
    public Node(int x)
    {
        sizeN=QueenRandomHillClimb.N;
        heuristics=0;
    
        state = new Queen[sizeN];
        
        Random random = new Random();
            for(int i=0; i<sizeN; i++){
		state[i] = new Queen(random.nextInt(sizeN), i);	
            }
    }
    
    public Node(Node n)
    {
        sizeN=QueenRandomHillClimb.N;
        heuristics=0;
    
        state = new Queen[sizeN];
        
        for(int i=0; i<sizeN; i++){
		state[i] = new Queen(n.state[i].row,n.state[i].column);	
            }
    }
    
    
    public int moveToMinConflict(int q) //moves the queen q to its minimum conflicting row in its column
    {
       int []conflicts = new int[sizeN];
        Queen tempq;  
        int min= sizeN;
        int pos = state[q].row;
        
        for(int i=0;i<sizeN;i++)
        {
            tempq = new Queen(i, state[q].column);
            conflicts[i]=getHeuristicforMin(tempq);
            
            if(conflicts[i]<min)
            {
                min=conflicts[i];
                pos=i;
            }
        }
        
        if(state[q].row!=pos)
        {
            state[q].row=pos;
            return 1;
        }
        
        return 0;
    }
    
    public int getHeuristicforMin(Queen q){ //it is the heuristics for minimum conflicts algorithm.
	
                heuristics=0;               //No of attacking queens for queen q is taken as the heuristics
                
                for(int i=0;i<sizeN;i++)
                {
                    if(q.column!=state[i].column)
                    {
                        if(q.checkAttack(state[i])==1)
                            heuristics++;
                    }
                    
                }
		
		return heuristics;
	}
    
    public int getHeuristic(){ // this is the heuristics for Hill Climbing
	
                heuristics=0; //Number of attacking pair of queens in the board is taken as the heurisctics
		for(int i=0; i<sizeN-1; i++){
			for(int j=i+1; j<sizeN; j++){
				if(state[i].checkAttack(state[j])==1){
						heuristics++;
				}
			}
		}
		
		return heuristics;
	}
    
    
    
    
    public Node getNextNeighbour(Node initialState) //the function returns the best neightbour available (node with better heuristics)
    {
        
        Node nextNeighbour = new Node(initialState);
        
        int minHeuristics = nextNeighbour.getHeuristic();
        
        int [] pos = new int[2];
        pos[0]=-1;
        pos[1]=-1;
        
        for(int i=0;i<sizeN;i++)
        {
            copynode(nextNeighbour, initialState);
            for(int j=0;j<sizeN;j++)
            {
                nextNeighbour.state[i].row=j;
              
                if(minHeuristics>nextNeighbour.getHeuristic())
                {
                    minHeuristics=nextNeighbour.getHeuristic();
                    pos[1]=i;
                    pos[0]=j;
                }
            }
        }
        
        if(pos[0]==-1)
        {
            return initialState;
        }
        else
        {
            copynode(nextNeighbour,initialState);
            nextNeighbour.state[pos[1]].row=pos[0];
            return nextNeighbour;
        }
        
    }
    

    
    public void copynode(Node n1, Node n2) //copies the details of one Node to another
    {
        for(int i=0;i<sizeN;i++)
        {
            n1.state[i] = new Queen(n2.state[i].row, n2.state[i].column);
           
        }
    }
    
}
