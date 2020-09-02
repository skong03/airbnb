
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
    int[] dp=new int[nights.length];
    for(int i=0;i<nights.length;i++){
        int max1=0;
        if(i-1>=0){
            max1=dp[i-1];
        }
        int max2=nights[i];
        if(i-2>=0){
            max2+=dp[i-2];
        }
        dp[i]=Math.max(max1, max2);
    }
    return dp[nights.length-1];
  }
}