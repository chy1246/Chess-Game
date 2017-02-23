package chessGame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChessUI extends JFrame {
	private Container container;
    private boardButton[][] board = new boardButton[8][8];
    JButton hint = new JButton("hint");
    ImageIcon black_bishop, black_king, black_knight, black_pawn, black_queen, black_rook, white_bishop
    ,white_king,white_knight, white_pawn, white_queen, white_rook;
    String bbishopURL = "resources/black_bishop.png", bkingURL = "resources/black_king.png", 
    	   bknightURL = "resources/black_knight.png", bpawnURL = "resources/black_pawn.png",
    	   bqueenURL = "resources/black_queen.png", brookURL = "resources/black_rook.png",
    	   wbishopURL = "resources/white_bishop.png", wkingURL = "resources/white_king.png",
    	   wknightURL = "resources/white_knight.png", wpawnURL = "resources/white_pawn.png",
    	   wqueenURL = "resources/white_queen.png", wrookURL = "resources/white_rook.png";
    String turn = "white"; //white = true, white first
    String state = "choose"; //track the move state, first of all choose one white piece
    boardButton pre = null;
    ArrayList<boardButton> possibleSteps = new ArrayList<>(); //record the possible steps for chosen piece
    calculator cal;
    public ChessUI(){
      initializePiece();
      cal = new calculator();
	  this.setBounds(400, 400, 900, 500);
	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  this.setVisible(true);
	  JPanel panel = new JPanel();
	  panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	  container = getContentPane();
	  container.add(panel);
	  //container.setLayout(new GridLayout(1,2));;
	  JPanel boardPanel = new JPanel();
	  panel.add(boardPanel);
	  boardPanel.setPreferredSize(new Dimension(600, 280));
	  boardPanel.setLayout(new GridLayout(8, 8, 5, 5));
	  //boardPanel.add(new JButton("1"));
	  //boardPanel.add(new JButton("1"));
	  //boardPanel.add(new JButton("1"));
	  //boardPanel.add(new JButton("1"));
		for(int i = 0; i < 8; i++){
			  for(int j = 0; j < 8; j++){
				  board[i][j] = new boardButton();
				  board[i][j].setX(i);
				  board[i][j].setY(j);
				  if((i + j) % 2 == 0){
					  //board[i][j].setForeground(Color.green);
					  board[i][j].setBackground(Color.green);
				  }else{
					  //board[i][j].setForeground(Color.white);
					  board[i][j].setBackground(Color.white);
				  }
				  board[i][j].setOpaque(true);
				  board[i][j].setBorderPainted(false);
				  boardPanel.add(board[i][j]);
				  //System.out.println();
			  }
			}
		//brook
		  board[0][0].setMyicon(black_rook);
		  board[0][0].setPiece("black_rook");
		  board[0][7].setMyicon(black_rook);
		  board[0][7].setPiece("black_rook");
		//bknight
		  board[0][1].setMyicon(black_knight);
		  board[0][1].setPiece("black_knight");
		  board[0][6].setMyicon(black_knight);
		  board[0][6].setPiece("black_knight");
		//bpawn
		  board[0][2].setMyicon(black_bishop);
		  board[0][2].setPiece("black_bishop");
		  board[0][5].setMyicon(black_bishop);
		  board[0][5].setPiece("black_bishop");
		//bqueen
		  board[0][3].setMyicon(black_queen);
		  board[0][3].setPiece("black_queen");
		//bking
		  board[0][4].setMyicon(black_king);
		  board[0][4].setPiece("black_king");
		 //bbishop
		 for(int i = 0; i < 8; i++){
			 board[1][i].setMyicon(black_pawn);
			 board[1][i].setPiece("black_pawn");
		 }
		 
		//wrook
		  board[7][0].setMyicon(white_rook);
		  board[7][0].setPiece("white_rook");
		  board[7][7].setMyicon(white_rook);
		  board[7][7].setPiece("white_rook");
		//wknight
		  board[7][1].setMyicon(white_knight);
		  board[7][1].setPiece("white_knight");
		  board[7][6].setMyicon(white_knight);
		  board[7][6].setPiece("white_knight");
		//wpawn
		  board[7][2].setMyicon(white_bishop);
		  board[7][2].setPiece("white_bishop");
		  board[7][5].setMyicon(white_bishop);
		  board[7][5].setPiece("white_bishop");
		//wqueen
		  board[7][3].setMyicon(white_queen);
		  board[7][3].setPiece("white_queen");
		//wking
		  board[7][4].setMyicon(white_king);
		  board[7][4].setPiece("white_king");
		 //wbishop
		 for(int i = 0; i < 8; i++){
			 board[6][i].setMyicon(white_pawn);
			 board[6][i].setPiece("white_pawn");
		 }
		 
		 //Hint Button
		 JPanel hintPanel = new JPanel();
		 JButton hint = new JButton("hint");
		 hintPanel.add(hint);
		 panel.add(hintPanel);
		 //Board ActionListener
		 
		 
		 for(int i = 0; i < 8; i++){
			 for(int j = 0; j < 8; j++){
				 final int I =i;
				 final int J = j;
				 board[i][j].addActionListener(new ActionListener() {
					 public void actionPerformed(ActionEvent e) {
						 //System.out.println(I);
						 if(state.equals("choose")){
						 showPossibleSteps(I, J);
						 }
						 else{
						 MovetoTarget(I, J);
						 }
					 }}
				 );
			 }
		 }
         JOptionPane.showMessageDialog( null,
                 "Welcome to My Chess Game");
    }
    public void initializePiece(){
    	//ImageIO.read(getClass().getResource("resources/water.bmp"))
    	try{
    	 black_bishop = new ImageIcon(bbishopURL);
         black_king = new ImageIcon(bkingURL);
         black_knight = new ImageIcon(bknightURL);
         black_pawn = new ImageIcon(bpawnURL);
         black_queen = new ImageIcon(bqueenURL);
         black_rook = new ImageIcon(brookURL);
         white_bishop = new ImageIcon(wbishopURL);
         white_king = new ImageIcon(wkingURL);
         white_knight = new ImageIcon(wknightURL);
         white_pawn = new ImageIcon(wpawnURL);
         white_queen = new ImageIcon(wqueenURL);
         white_rook = new ImageIcon(wrookURL);
    	}catch (Exception e){
    		JOptionPane.showMessageDialog( null,
					  "Miss Icon Image","Wrong File Path", JOptionPane.WARNING_MESSAGE); 
    	}
    }
    public void showPossibleSteps(int x, int y){
    	String piece = board[x][y].getPiece();
    	System.out.println(piece);
    	String[] temp = piece.split("_");
    	System.out.println(temp[0].equals("white"));
    	if(piece.equals("")){
    		JOptionPane.showMessageDialog( null,
    				"Pick the " + turn + " Piece","Wrong Pick", JOptionPane.WARNING_MESSAGE);
    	}else if(!temp[0].equals(turn)){
    		JOptionPane.showMessageDialog( null,
			"You can not move same side twice times consecutivly","Wrong Pick", JOptionPane.WARNING_MESSAGE); 
    	}else {
    		possibleSteps = cal.getPossibleSteps(piece, x, y, board);
    		System.out.println("size" + possibleSteps.size());
    		for(boardButton cur : possibleSteps){
    			int i = cur.getX();
                int j = cur.getY();
                System.out.println(i);
                board[i][j].setBackground(Color.yellow);
                state = "move";
                pre = board[x][y];
                //board[i][j].setOpaque(true);
    		}
    	}
    }
    public void MovetoTarget(int x, int y){
    	String piece = board[x][y].getPiece();
    	System.out.println(piece);
    	String[] temp = piece.split("_");
    	System.out.println(temp[0].equals("white"));
    	if(!possibleSteps.contains(board[x][y])){
    		removeIcon();
    	}else{
    	   ImageIcon icon = pre.getMyicon();
    	   String name = pre.getPiece();
    	   pre.setMyicon(null);
    	   pre.setPiece("");
    	   board[x][y].setMyicon(icon);
    	   board[x][y].setPiece(name);
    	   removeIcon();
    	   possibleSteps.clear();
    	   if(turn.equals("white")){
				 turn = "black";
			 }else{
				 turn = "white";
			 }
    	}
    	state = "choose";
    }
    public void removeIcon(){
		if(possibleSteps.size() != 0){
			for(boardButton cur : possibleSteps){
              int i = cur.getX();
              int j = cur.getY();
              if((i + j) % 2 == 0){
				  board[i][j].setBackground(Color.green);
			  }else{
				  board[i][j].setBackground(Color.white);
			  }
			  board[i][j].setOpaque(true);
			  board[i][j].setBorderPainted(false);
			}
		}
    }
}
