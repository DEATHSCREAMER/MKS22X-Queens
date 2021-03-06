public class QueenBoard{
  private int[][] board;
  private int count;

  public QueenBoard(int size){
    board = new int[size][size];
    for (int r = 0 ; r < board.length ; r++){
      for (int c = 0 ; c < board[r].length ; c++){
        board[r][c] = 0;
      }
    }
  }

  private boolean addQueen(int r, int c){
    if (r < board.length){
      if (c < board[r].length){
        if (board[r][c] == 0){
          board[r][c] = -1;
          int row = r;
          int col = c;
          for (row = r + 1 , col = c + 1; row < board.length && col < board[row].length ; row++ , col++){
            if (board[row][col] > -1)
            board[row][col] += 1;
          }
          for (row = r , col = c + 1 ; row > 0 && col < board[row].length ; row-- , col++){
            if (board[row-1][col] > -1)
            board[row - 1][col] += 1;
          }
          for (col = c + 1; col < board[row].length; col++){
            if (board[r][col] > -1)
            board[r][col] += 1;
          }
          return true;
        }
      }
    }
    return false;
  }
  private boolean removeQueen(int r, int c){
    if (r < board.length){
      if (c < board[r].length){
        if (board[r][c] == -1){
          board[r][c] = 0;
          int row = r;
          int col = c;
          for (row = r + 1 , col = c + 1; row < board.length && col < board[row].length ; row++ , col++){
            if (board[row][col] > 0)
            board[row][col] -= 1;
          }
          for (row = r , col = c + 1 ; row > 0 && col < board[row].length ; row-- , col++){
            if (board[row-1][col] > 0)
            board[row - 1][col] -= 1;
          }
          for (col = c + 1; col < board[row].length; col++){
            if (board[r][col] > 0)
            board[r][col] -= 1;
          }
          return true;
        }
      }
    }
    return false;
  }

  public String toString(){
    String chessboard = "";
    for (int row = 0 ; row < board.length ; row++){
      for (int col = 0 ; col < board[row].length ; col++){
        if (board[row][col] == -1){
          chessboard += "Q ";
        }
        else{
          chessboard += "_ ";
        }
      }
      chessboard += "\n";
    }
    return chessboard;
  }

  public boolean solve(){
      check();
      if(solve(0,0)){
        return true;
      }
      else{
        clear();
        return false;
      }
  }

  private boolean solve(int row, int c){
    if (c >= board.length){
      return true;
    }
    for (int r = row ; r < board.length ; r++){
      if (addQueen(r,c)){
        if (solve(0,c+1)){
          return true;
        }
      }
      removeQueen(r,c);
    }
    return false;
  }

  public int countSolutions(){
      check();
      count = 0;
      solveMulti(0,0);
      clear();
      return count;
  }

  private boolean solveMulti(int row, int c){
    if (c >= board.length){
      count++;
      return true;
    }
    for (int r = row ; r < board.length ; r++){
      if (addQueen(r,c)){
        solveMulti(0,c+1);
      }
      removeQueen(r,c);
    }
    return false;
  }

  private void clear(){
    for (int r = 0 ; r < board.length ; r++){
      for (int c = 0 ; c < board[r].length ; c++){
        board[r][c] = 0;
      }
    }
  }

  private void check() throws IllegalStateException{
    for (int r = 0 ; r < board.length ; r++){
      for (int c = 0 ; c < board[r].length ; c++){
        if (board[r][c] != 0){
          throw new IllegalStateException("BOARD IS NOT EMPTY");
        }
      }
    }
  }

}
