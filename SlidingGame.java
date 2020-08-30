
import java.util.*;

/*
123
456
870

A* ????
*/
class Solution8 {
 public static void main(String[] args){
    SlidingGame game=new SlidingGame();
   boolean result= game.canSolve(new int[]{1,2,3,4,5,6,7,8,0});
   System.out.println(result);
 }
}

class SlidingGame {
    private static final int[][] steps=new int[][]{{1,3},{2,4,0},{5,1},{6,4,0},{7,5,3,1},{8,4,2},{3,7},{8,6,4},{7,5}};
    private HashSet<Integer> saw;
    private int target=123456780;
    public boolean canSolve(int[] matrix){
      saw=new HashSet<>();
      int index=0;
      for(int i=0;i< matrix.length;i++){
        if(matrix[i]==0){
          index=i;
        }
      }
      
      return canSolve(matrix, index);
    }
    
    private boolean canSolve(int[] matrix, int i){
      int sign=signiture(matrix);
      System.out.println(sign+", i:"+i);
      if(sign==target){
        return true;
      }
      if(saw.contains(sign)){
        return false;
      }
      
      saw.add(sign);
      
      int[] nextIs=steps[i];
      for(int nexti: nextIs){
        swap(matrix, i, nexti);
    
        if(canSolve(matrix,nexti)){
          return true;
        }
        swap(matrix, i, nexti);
      }
      
      return false;
    }
    
    private void swap(int[] matrix, int i, int j){
      int temp=matrix[i];
      matrix[i]=matrix[j];
      matrix[j]=temp;
    }
    
    public int signiture(int[] matrix){
      int sign=0;
      for(int i=0;i<matrix.length;i++){
        sign=sign*10+matrix[i];
      }
      return sign;
    }
  }