package chessGame;

import java.util.ArrayList;
import java.util.List;

public class calculator {
   public ArrayList<boardButton> getPossibleSteps(String piece, int x, int y, boardButton[][] board){
	   ArrayList<boardButton> res = new ArrayList<>();
	   int X = x;
	   int Y = y;
	   String[] temp = piece.split("_");
	   switch(temp[1]){
	   case "bishop":{
		   boolean NW = false;
		   boolean NE = false;
		   boolean SW = false;
		   boolean SE = false;
		   for(int i = 0; i < 8; i++){
			   if(!NE){
			   NE = helper(res, temp[0], X + i, Y + i, board);
			   }
			   if(!SE){
			   SE = helper(res, temp[0], X + i, Y - i, board);
			   }
			   if(!NW){
			   NW = helper(res, temp[0], X - i, Y + i, board);
			   }
			   if(!SW){
			   SW = helper(res, temp[0], X - i, Y - i, board);
			   }
		   }
		   break;
	   }
	   case "king":{
		   helper(res, temp[0], X + 1, Y + 1, board);
		   helper(res, temp[0], X + 1, Y - 1, board);
		   helper(res, temp[0], X - 1, Y + 1, board);
		   helper(res, temp[0], X - 1, Y - 1, board);
		   helper(res, temp[0], X + 1, Y, board);
		   helper(res, temp[0], X - 1, Y, board);
		   helper(res, temp[0], X, Y + 1, board);
		   helper(res, temp[0], X, Y - 1, board);
		   break;
	   }
	   case "knight":{
		 helper(res, temp[0], X + 2, Y + 1, board);
		 helper(res, temp[0], X + 2, Y - 1, board);
		 helper(res, temp[0], X - 2, Y + 1, board);
		 helper(res, temp[0], X - 2, Y - 1, board);
		 helper(res, temp[0], X + 1, Y + 2, board);
		 helper(res, temp[0], X + 1, Y - 2, board);
		 helper(res, temp[0], X - 1, Y + 2, board);
		 helper(res, temp[0], X - 1, Y - 2, board);
		 break;
	   }
	   case "pawn":{
		   //System.out.println(2);
		   if(temp[0].equals("white")){
			   helper(res, temp[0], X - 1, Y, board);
			   pawnCheck(piece,res, temp[0], X -1, Y - 1, board);
			   pawnCheck(piece,res, temp[0], X - 1, Y + 1, board);
			   if(X == 6){
				  helper(res, temp[0], X - 2, Y, board);
			   }
		   }else{
			   helper(res, temp[0], X + 1, Y, board);
			   pawnCheck(piece,res, temp[0], X + 1, Y + 1, board);
			   pawnCheck(piece,res, temp[0], X + 1, Y - 1, board);
			   if(X == 6){
				  helper(res, temp[0], X + 2, Y, board);
			   }
		   }
		   break;
	   }
	   case "queen":{
		   boolean NW = false;
		   boolean NE = false;
		   boolean SW = false;
		   boolean SE = false;
		   boolean N = false;
		   boolean W = false;
		   boolean S = false;
		   boolean E = false;
		 for(int i = 0; i < 8; i++){
			 if(!SE){
			 SE = helper(res, temp[0], X + i, Y + i, board);
			 }
			 if(!SW){
			 SW = helper(res, temp[0], X + i, Y - i, board);
			 }
			 if(!NE){
			 NE = helper(res, temp[0], X - i, Y + i, board);
			 }
			 if(!NW){
			 NW = helper(res, temp[0], X - i, Y - i, board);
			 }
			 if(!S){
			 S = helper(res, temp[0], X, Y + i, board);
			 }
			 if(!N){
			 N = helper(res, temp[0], X, Y - i, board);
			 }
			 if(!E){
			 E = helper(res, temp[0], X + i, Y, board);
			 }
			 if(!W){
			 W = helper(res, temp[0], X - i, Y, board);
			 }
		 }
		 break;
	   }
	   case "rook":{
		   boolean N = false;
		   boolean W = false;
		   boolean S = false;
		   boolean E = false;
		   for(int i = 0; i < 8; i++){
			 if(!S){
			 S = helper(res, temp[0], X + i, Y, board);
			 }
			 if(!N){
			 N = helper(res, temp[0], X - i, Y, board);
			 }
			 if(!E){
			 E = helper(res, temp[0], X, Y + i, board);
			 }
			 if(!W){
			 W= helper(res, temp[0], X, Y - i, board);
			 }
		   }
		   break;
	   }
	   }
	   return res;
   }
   public boolean helper( List<boardButton> res, String turn,  int X, int Y, boardButton[][] board){
	   if(X < 0 || X >= board.length || Y < 0 || Y >= board[0].length)
		   return false;
	   String name = board[X][Y].getPiece();
	   System.out.println(X);
	   System.out.println(name);
	   if(name.equals("")){
		   System.out.println(3);
		   res.add(board[X][Y]);
		   return false;
	   }else if(!turn.equals(name.split("_")[0])){
		   res.add(board[X][Y]);
		   return false;
	   }
	     return true;
   }
   public void pawnCheck(String name,List<boardButton> res, String turn,  int X, int Y, boardButton[][] board){
	   if(X < 0 || X >= board.length || Y < 0 || Y >= board[0].length)
		   return;
	   if(!(name.equals("") ||name.split("_")[0].equals(turn))){
		   res.add(board[X][Y]);
	   }
   }
}
