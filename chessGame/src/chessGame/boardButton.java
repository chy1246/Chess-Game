package chessGame;

import javax.swing.JButton;

public class boardButton extends JButton {
   private String piece = "";
   private int x, y;
   public String getPiece(){
	   return piece;
   }
   
   public void setPiece(String name){
	   piece = name;
   }
   //get the current button has white or black or no piece
   public String WorBorN(){
	   String[] temp = piece.split("_");
	   return temp[0];
   }

   public int getX() {
	return x;
   }

   public void setX(int x) {
	this.x = x;
   }

   public int getY() {
	return y;
   }

   public void setY(int y) {
	this.y = y;
   }
}
