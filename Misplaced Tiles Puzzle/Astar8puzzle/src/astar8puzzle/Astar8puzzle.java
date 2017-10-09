
//Program 1
//Submitted by Venkatesh Bonageri(800964302)
package astar8puzzle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Astar8puzzle {

   public static int[][] goalstate; 
   public static LinkedList<Node> nodes; //contains the nodes that are not expanded yet
   public static LinkedList<Node> visited; //contains the expanded nodes
  public static int nodesgenerated=0; //to keep the count of nodes generated
  public static long nodesexpanded=0; //to keep the count of nodes expanded
  public static long heuristictype=0;
   public static int size;
    
    public static void main(String[] args) {
        // TODO code application logic here
        
      int[][] temp;
         Scanner scan = new Scanner(System.in);
         
         nodes = new LinkedList<Node>();
         visited = new LinkedList<Node>();
         
         System.out.println("Enter the size of the matrix (enter 3 if it is 3x3)");
         size=scan.nextInt();
         
         if(size<0)
         {
             System.out.println("Wrong size choosen....Try again");
            System.exit(1);
         }
         
         goalstate=new int[size][size];
         temp = new int[size][size];
        System.out.println("Enter the initial state of the puzzle in the matrix format(represent space by 0)");
         
        
        //takes the initial state of the puzzle
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {          
                    temp[i][j]=scan.nextInt();
            }
        }
        
        
        //takes the goal state of the puzzle
         System.out.println("Enter the goal state of the puzzle in the matrix format");
         
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {          
                    goalstate[i][j]=scan.nextInt();
            }
        }
        
        //choices to choose heuristics function
        System.out.println("Select heuristics");
        System.out.println("Press 1 to select misplaced tiles as heuristics");
        System.out.println("Press 2 to select manhaton distance as heuristics");
        heuristictype=scan.nextInt();
        
        if(heuristictype>2 || heuristictype<1)
        {
            System.out.println("Wrong heuristics choosen....Try again");
            System.exit(1);
        }
                
        Node initial= new Node(temp,0);
        
        nodes.add(initial);
        
        int gotresult=0;
        
        System.out.println("The Solution steps are as below\n");
        
        //continues the search for the goal state till the goal state is found or all nodes are expanded
        while(gotresult!=1 && nodes.size()>0)
        {
            gotresult=mysolution();
        }
        
        if(gotresult==0)
            System.out.println("Couldnt find the solution");
        else
        {
            System.out.println("The number of nodes generated to reach the solution are "+nodesgenerated);
            System.out.println("The number of nodes expanded to reach the solution are "+nodesexpanded);
        }
        
    }
    
    //finds the best f(n) node expands its all possible child nodes and checks if it found the goal state
    public static int mysolution()
    {
        Node v= new Node(nodes.get(getbestf()));
        nodes.remove(getbestf());
        Node.printnode(v);
        
        System.out.println("f="+v.ftotal+" ("+v.gcost+" + "+v.heuristic+")");
        System.out.println();
        
        visited.add(v);
        createchildnodes(v);
        nodesexpanded++;
         
       
        return isgoal(v);
    }
    
    //creates all child nodes 
    public static void createchildnodes(Node n)
    {
        
        int i,j,expanded=0;
        
        for(i=0;i<size;i++)
        {
            for(j=0;j<size && expanded==0;j++)
            {
                if(n.data[i][j]==0)
                {
                    if(j!=(size-1))
                    {
                        moveright(n,i,j);
                    }
                    
                    if(i!=(size-1))
                    {
                        movedown(n,i,j);
                    }
                    
                    if(j!=0)
                    {
                        moveleft(n,i,j);
                    }
                    
                    if(i!=0)
                    {
                        moveup(n,i,j);
                    }
                    
                    expanded=1;
                }
            }
        }
    }
    
    
    
    
    //operator move up i.e, to move the space up in the puzzle
    public static void moveup(Node nn,int i,int j)
    {
        Node n =new Node(nn);
        n.data[i][j]=n.data[i-1][j];
        n.data[i-1][j]=0;
        n.gcost=n.gcost+1;//increments the g(n)
        
        if(sameasancestor(n)==0) //checks if the generated node is same as any ancestor node if so then it skips adding it
        {
            
          if(Astar8puzzle.heuristictype==1)    
            n.heuristic=n.getheuricticmis();
          else
            n.heuristic=n.getheuricticman();
          
            
            n.ftotal=n.heuristic+n.gcost; 
            nodes.add(n);
            nodesgenerated++;
            
        }
            
    }


        //operator move down i.e, to move the space down in the puzzle
    public static void movedown(Node nn,int i,int j)
    {
        Node n =new Node(nn);
        n.data[i][j]=n.data[i+1][j];
        n.data[i+1][j]=0;
        n.gcost=n.gcost+1; //increments the g(n)
        
        if(sameasancestor(n)==0) //checks if the generated node is same as any ancestor node if so then it skips adding it
        {
          if(Astar8puzzle.heuristictype==1)    
            n.heuristic=n.getheuricticmis();
          else
            n.heuristic=n.getheuricticman();
          
            n.ftotal=n.heuristic+n.gcost;
            nodes.add(n);
            nodesgenerated++;
        }
    }
    
    
    //operator move left i.e, to move the space left in the puzzle
    public static void moveleft(Node nn,int i,int j)
    {
        Node n =new Node(nn);
        n.data[i][j]=n.data[i][j-1];
        n.data[i][j-1]=0;
        n.gcost=n.gcost+1; //increments the g(n)
        
        if(sameasancestor(n)==0) //checks if the generated node is same as any ancestor node if so then it skips adding it
        {
           if(Astar8puzzle.heuristictype==1)    
            n.heuristic=n.getheuricticmis();
          else
            n.heuristic=n.getheuricticman();
            
           
            n.ftotal=n.heuristic+n.gcost;
            nodes.add(n);
            nodesgenerated++;
        }
    }
    
    
   //operator move right i.e, to move the space right in the puzzle
    public static void moveright(Node nn,int i,int j)
    {
        Node n =new Node(nn);
        n.data[i][j]=n.data[i][j+1];
        n.data[i][j+1]=0;
        n.gcost=n.gcost+1;//increments the g(n)
        
         if(sameasancestor(n)==0) //checks if the generated node is same as any ancestor node if so then it skips adding it
        {
         if(Astar8puzzle.heuristictype==1)    
            n.heuristic=n.getheuricticmis();
          else
            n.heuristic=n.getheuricticman();
         
            n.ftotal=n.heuristic+n.gcost;
            nodes.add(n);
            nodesgenerated++;
        }
    }
    
    //checks if the node is same as goal state
    public static int isgoal(Node n)
    {
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                if(goalstate[i][j]!=n.data[i][j])
                    return 0;
            }
        }
        return 1;
    }
    
    //function to get the best f(n) in the nodes available to expand
    public static int getbestf()
    {
        int least=nodes.get(0).ftotal,index=0;
        for(int i=0;i<nodes.size();i++)
        {
            if(least>nodes.get(i).ftotal)
            {
                least=nodes.get(i).ftotal;
                index=i;
            }
        }
        
        return index;
    }
    
    
    //function to check if two nodes are having same data to avoid the duplicate nodes of anscestor nodes
    public static int checksamedata(int[][] d4,int[][] d5)
    {
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                if(d4[i][j]!=d5[i][j])
                    return 0;
            }
        }
        
        return 1;
    }
    
    //function to check if the node is same as one of the ancestor nodes 
    public static int sameasancestor (Node n)
    {
        
        for(int i=0;i<visited.size();i++)
        {
            
            if(checksamedata(visited.get(i).data, n.data)==1)
        return 1;
        }
        
        return 0;
    
    }
    
}
