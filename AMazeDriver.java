/*
References: http://math.hws.edu/javanotes/source/chapter9/Maze.java
The maze is randomly created. The black areas represent the walls/obstructions and the white area represent the pathways area. The algorithm performs depth first search to search for 
path from (0,0) to (n,n). The green path represents the correct path. The yellow area represents the incorrect path that the algorithm traces, to ultimately find the correct one.
This is a graph traversal problem.
*/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

class AMazeDriver{
	public static void main(String args[]){
		//AMaze a=new AMaze();
	JFrame window = new JFrame("AMaze");
        window.setContentPane(new AMaze());
        window.pack();
        window.setLocation(120, 80);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        AMaze a=(AMaze)window.getContentPane();
        
        try{
        Thread.sleep(1000);}
        catch(Exception e){
        	System.out.println(e);
        }
        
        a.solveMaze(0,0);
		

	}
}

class AMaze extends JPanel{
	int no_of_rows=40;
	int no_of_columns=30;

	int maze_arr[][]=new int[no_of_rows][no_of_columns];
	final int currentpath=0;
	final int visited=1;
	final int clearpath=2;
	final int obstruction=3;
	Color color[];

	
	AMaze(){
		color = new Color[] {
           
            Color.GREEN,
            Color.YELLOW,
            Color.WHITE,
            Color.BLACK
        };
        System.out.println(clearpath);
        setBackground(Color.WHITE);
        for(int i=0;i<no_of_columns;i++)
        	for(int j=0;j<no_of_rows;j++){
        		maze_arr[j][i]=clearpath;
        	}
        	repaint();
        System.out.println(color[clearpath]==Color.WHITE);
        setPreferredSize(new Dimension(12*40, 12*40));
        createRandomObstuctions();
        

	}


	//creating maze by randomly selecting pathways between the vertical walls
	 void createRandomObstuctions(){
	 	
		for(int i=0;i<no_of_rows;i=i+2){
			for(int j=0;j<no_of_columns;j=j+1)
				maze_arr[i][j]=obstruction;

		Random rand = new Random();
		int  n = rand.nextInt(no_of_columns-1) ;
		System.out.println(n);
		maze_arr[i][n]=clearpath;
		}
		maze_arr[0][0]=clearpath;


	for(int i=1;i<no_of_rows;i=i+2){
		for(int j=0;j<no_of_columns;j++)
		maze_arr[i][j]=clearpath;
		
		

		}
		maze_arr[no_of_rows-1][no_of_columns-1]=clearpath;

			repaint();
	}

	boolean solveMaze(int row,int col){
		if(row==no_of_rows-1&&col==no_of_columns-1){
			maze_arr[row][col]=currentpath;
			return true;
		}
		if(row>no_of_rows-1||col>no_of_columns-1||(row<0)||(col<0)){return false;}
		if(maze_arr[row][col]==clearpath){
			maze_arr[row][col]=currentpath;
			try{
	        Thread.sleep(300);}
	        catch(Exception e){
	        	System.out.println(e);
	        }	
			repaint();
			
			boolean result= /*solveMaze(row+1, col+1) ||*/ solveMaze(row+1, col) || solveMaze(row, col+1)||solveMaze(row-1,col)||solveMaze(row, col-1)|| solveMaze(row+1, col-1);
			if(!result){maze_arr[row][col]=visited; repaint();}
			return result;
			}
		else{
			
			return false;
		}
	
	}

	synchronized protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        painter(g);
	    	     
     }
     void painter(Graphics g){
     	for(int i=0;i<no_of_columns;i++)
     		for(int j=0;j<no_of_rows;j++){
     			g.setColor(color[maze_arr[j][i]]);
     			g.fillRect(j*12, i*12,12,12);
     		}

     }
	

}