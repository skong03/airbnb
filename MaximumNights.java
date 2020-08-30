
/*
123
456
870
*/
class Solution9 {
 public static void main(String[] args){
   MaximumNights mn=new MaximumNights();
   int[][] inputs=new int[][]{{5,1,1,5},{3,6,4},{4,10,3,1,5}};
   for(int[] input : inputs){
    int v=mn.getMaxNights(input);
     System.out.println(v);
   }
   
 }
}

class MaximumNights{
  public int getMaxNights(int[] nights){
    int max=0;
    for(int i:nights){
      max+=i;
    }
    
    int[] dp=new int[max+1];
    int index=0;
    for(int i=0;i<nights.length;i++){
      index+=nights[i];
      dp[index]=nights[i];
    }
    
    for(int i=1;i<dp.length;i++){
      this.print(dp);
    System.out.println();
      if(dp[i]==0){
        dp[i]=dp[i-1];
        continue;
      }
      
      int cur=dp[i];
      if(i-dp[i]-1>=0){
        cur+=dp[i-dp[i]-1];
      }
      
      dp[i]=Math.max(dp[i-1], cur);
    }
    
    return dp[dp.length-1];
  }
  
  private void print(int[] c){
    for(int i=0;i<c.length;i++){
      System.out.print(c[i]+",");
    }
  }
}