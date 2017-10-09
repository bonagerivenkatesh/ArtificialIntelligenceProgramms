/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queenrandomhillclimb;

/**
 *
 * @author Venkatesh
 */
public class Queen {
    
    int row;
    int column;

    public Queen(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    public int checkAttack(Queen q){ //checks whether the two queens have either have the same row or column or dialogonal
		int flag=0;
		
		if(row==q.getRow() || column==q.getColumn())
			flag=1;
		
		else if(Math.abs(column-q.getColumn()) == Math.abs(row-q.getRow()))
			flag=1;
			
		return flag;
	}
    
}
