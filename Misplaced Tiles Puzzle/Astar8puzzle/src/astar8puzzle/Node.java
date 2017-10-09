
package astar8puzzle;


public class Node {
    
    int [][] data; //contains the elements and space will be represented by 0
    int heuristic; //contains the heuristic function value i.e., h(n)
    int gcost; //contains the g(n)
    int ftotal; //contains f(n)
    
    Node (Node n)
    {
        data = new int[Astar8puzzle.size][Astar8puzzle.size];
        
        copydata(this, n);
        this.heuristic=n.heuristic;
        this.gcost=n.gcost;
        this.ftotal=n.ftotal;
    }
   
     Node(int[][] d, int g)
    {
        data = new int[Astar8puzzle.size][Astar8puzzle.size];
        copydata(this, d);
        if(Astar8puzzle.heuristictype==1)    
          this.heuristic=this.getheuricticmis(d);
        else
            this.heuristic=this.getheuricticman(d);
        this.gcost=g;
        this.ftotal=g+this.heuristic;
    }
     
     Node()
     {
         
     }
    
     
     public void copydata(Node n, Node nn)
     {
         for(int i=0;i<Astar8puzzle.size;i++)
         {
             for(int j=0;j<Astar8puzzle.size;j++)
                 n.data[i][j]=nn.data[i][j];
         }
     }
     
     public void copydata(Node n, int[][] d)
     {
         for(int i=0;i<Astar8puzzle.size;i++)
         {
             for(int j=0;j<Astar8puzzle.size;j++)
                 n.data[i][j]=d[i][j];
         }
     }
     
    public static void printnode(Node n)
    {
        for(int i=0;i<Astar8puzzle.size;i++)
        {
            for (int j=0;j<Astar8puzzle.size;j++)
                System.out.print(n.data[i][j]+" ");
            System.out.println("");
        }
        
    }
    
    
    //heuristics function based on manhaton distance
    
    public int getheuricticman(Node n)
    {
        int h=0;
        
        for(int i=0;i<Astar8puzzle.size;i++)
        {
            for(int j=0;j<Astar8puzzle.size;j++)
            {
                for(int k=0;k<Astar8puzzle.size;k++)
                {
                    for(int l=0;l<Astar8puzzle.size;l++)
                    {
                        if(n.data[i][j]==Astar8puzzle.goalstate[k][l])
                        {
                            if(n.data[i][j]!=0)
                            {
                               h=h+Math.abs(i-k)+Math.abs(j-l);
                               
                            }
                        }
                    }
                }
            }
        }
        
        
        
        return h;
    }
    

    
    public int getheuricticman()
    {
        int h=0;
        
        for(int i=0;i<Astar8puzzle.size;i++)
        {
            for(int j=0;j<Astar8puzzle.size;j++)
            {
                for(int k=0;k<Astar8puzzle.size;k++)
                {
                    for(int l=0;l<Astar8puzzle.size;l++)
                    {
                        if(this.data[i][j]==Astar8puzzle.goalstate[k][l])
                        {
                            if(this.data[i][j]!=0)
                            {
                               h=h+Math.abs(i-k)+Math.abs(j-l);
        
                            }
                            
                        }
                    }
                }
            }
        }
        
        
        return h;
    }
    

    
    
      public int getheuricticman(int[][] n)
    {
        int h=0;
        
        for(int i=0;i<Astar8puzzle.size;i++)
        {
            for(int j=0;j<Astar8puzzle.size;j++)
            {
                for(int k=0;k<Astar8puzzle.size;k++)
                {
                    for(int l=0;l<Astar8puzzle.size;l++)
                    {
                        if(n[i][j]==Astar8puzzle.goalstate[k][l])
                        {
                            if(n[i][j]!=0)
                            {
                               h=h+Math.abs(i-k)+Math.abs(j-l);
        
                            }
                                
                        }
                    }
                }
            }
        }
        
        
        return h;
    }
      
      
      //Heuristic functions considering misplaced tiles are as below

    public int getheuricticmis(Node n)
    {
        int h=0;
        
        for(int i=0;i<Astar8puzzle.size;i++)
        {
            for(int j=0;j<Astar8puzzle.size;j++)
            {
                if(n.data[i][j]!=Astar8puzzle.goalstate[i][j])
                {
                    if(n.data[i][j]!=0)
                    h++;
                }
            }       
        }
        
        return h;
    }
      
            

      
   public int getheuricticmis()
    {
        int h=0;
        
        for(int i=0;i<Astar8puzzle.size;i++)
        {
            for(int j=0;j<Astar8puzzle.size;j++)
            {
                if(this.data[i][j]!=Astar8puzzle.goalstate[i][j])
                {
                    if(this.data[i][j]!=0)
                    h++;
                    
                }
            }       
        }
        
        return h;
    }      

      
    public int getheuricticmis(int[][] n)
    {
        int h=0;
        
        for(int i=0;i<Astar8puzzle.size;i++)
        {
            for(int j=0;j<Astar8puzzle.size;j++)
            {
                if(n[i][j]!=Astar8puzzle.goalstate[i][j])
                {
                    if(n[i][j]!=0)
                    h++;
                }
            }       
        }
        
        return h;
}


}
