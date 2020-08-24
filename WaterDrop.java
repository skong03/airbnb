import java.io.*;
import java.util.*;

class Solution7 {
  public static void main(String[] args) {
    WaterDrop wd=new WaterDrop();
    int[] height= new int[]{5, 4, 2, 1, 2, 3, 2, 1, 0, 1, 2, 4};
    for(int i=0;i<30;i++){
      wd.addWater(height, i, 5);
      wd.print();
    }
    
  }
}

class WaterDrop{
  int[] height;
  int[] waters;
  public void addWater(int[] heights, int water, int location){
    this.height=heights;
    waters =new int[height.length];
    for(int i=0;i<water; i++){
      int left=location-1;
      while(left>=0&& height[left]+ waters[left]<= height[location]+waters[location] 
            && left-1>=0 && height[left-1]+waters[left-1]<=heights[left]+waters[left]){
        left--;
      }
      if(height[left]+waters[left]<height[location]+waters[location]){
        waters[left]=waters[left]+1;
        continue;
      }
      
      int right=location+1;
      while(right+1<height.length && height[right]+waters[right]<=height[location]+waters[location] && height[right]+waters[right] >=height[right+1]+waters[right+1]){
        right++;
      }
      
      if(heights[right]+waters[right]< height[location]+waters[location]){
        waters[right]=waters[right]+1;
        continue;
      }
      
      waters[location]=waters[location]+1;
    }
    
  }
  
  public void print(){
    int max=0;
    for(int i=0;i<height.length;i++){
      max=Math.max(max, height[i]+waters[i]);
    }
    
    
    for(int i=max;i>=0;i--){
      StringBuilder sb=new StringBuilder();
      for(int j=0;j<height.length;j++){
        if(height[j]+waters[j]<i){
          sb.append(' ');
        }else if(height[j]< i){
          sb.append('w');
        }else{
          sb.append('+');
        }
      }
      System.out.println(sb.toString());
    }
  }
}
