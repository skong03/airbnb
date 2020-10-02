

class Solution7 {
  public static void main(String[] args) {
    WaterDrop wd=new WaterDrop();
    int[] height= new int[]{1,2,0,2,1, 2};
    for(int i=0;i<30;i++){
      wd.addWater(height, i, 1);
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
      // if left is valid, and we should move water to left. 
      while(left>=0 && getHeight(left)<=getHeight(location) 
      && left-1>=0 && getHeight(left-1)<=getHeight(left)){
        left--;
      }

      //>0 consider no wall, >=0 mean allow wall. 
      if(left>0 && getHeight(left)<getHeight(location)){
        waters[left]++;
        continue;
      }

      int right=location+1;
      while(right<height.length && getHeight(right)<=getHeight(location)
      && right+1<height.length && getHeight(right+1)<=getHeight(right)){
        right++;
      }

      if(right<heights.length-1 && getHeight(right)<getHeight(location)){
        waters[right]++;
        continue;
      }

      // for no wall, if location small than both peek, add water. 
      if(right<heights.length && getHeight(location)<getHeight(right) 
      && left>=0 && getHeight(location)<getHeight(left)){
        waters[location]++;
        continue;
      }
    }
  }

  private int getHeight(int i){
    return height[i]+waters[i];
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

    System.out.println();
  }
}
