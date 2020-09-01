import java.util.*;

class Solution18 {
 public static void main(String[] args){
//    Simulater s=new Simulater();
//    print(s.myNumber);
   
   int[] myGuss=new GuessNumber().guess();
   System.out.println("guess number");
   print(myGuss);
 }
  
  private static void print(int[] arr){
    for(int i:arr){
      System.out.print(i+",");
    }
    System.out.println();
  }
}


class GuessNumber{
  Simulater s=new Simulater();
  List<Integer> resSet=new ArrayList<>();
  int none=0;
  //int[] res=new int[4];
  public int[] guess(){
      System.out.println("generate number:");
    print(s.myNumber);
    for(int i=1;i<=5;i++){
      int[] res=s.guss(new int[]{i,i,i,i});
      if(res[0]==1){
        resSet.add(i);
      }else{
        none=i;
      }
    }
    if(resSet.size()==3){
      resSet.add(6);
    }
    
    int[] dummy=new int[]{none, none, none, none};
    for(int i=0;i<2;i++){
      for(int j=0;j<resSet.size()-1;j++){
        int[] temp=dummy.clone();
        temp[i]=resSet.get(j);
        int[] res=s.guss(temp);
        if(res[1] ==i+1){
          dummy[i]=temp[i];
          resSet.remove(j);
          break;
        }
      }
      if(dummy[i]==none){
        dummy[i]=resSet.get(resSet.size()-1);
        resSet.remove(resSet.size()-1);
      }
    }
    dummy[2]=resSet.get(0);
    dummy[3]=resSet.get(1);
    
    int[] res=s.guss(dummy);
    if(res[1]==4){
      return dummy;
    }else{
      dummy[2]=resSet.get(1);
      dummy[3]=resSet.get(0);
      return dummy;
    }
    
  }

  private static void print(int[] arr){
    for(int i:arr){
      System.out.print(i+",");
    }
    System.out.println();
  }
}

class Simulater{
  public int[] myNumber=new int[4];
  public Simulater(){
    List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
    
    for(int i=0;i<4;i++){
      int random=(int)(Math.random()*list.size());
      myNumber[i]=list.get(random);
      list.remove(random);
    }
  }
  
  public int[] guss(int[] input){
    //print(input);
    int t=0;
    int c=0;
    HashSet<Integer> local=new HashSet<Integer>();
    for(int i=0;i<4;i++){
      if(input[i]==myNumber[i]){
        c++;
      }
      local.add(input[i]);
    }
    for(int i:myNumber){
      if(local.contains(i)){
        t++;
      }
    }
    //System.out.println("correct Number: "+t+", postion Correct: "+c);
    return new int[]{t,c};
  }
  
  private static void print(int[] arr){
    for(int i:arr){
      System.out.print(i+",");
    }
    System.out.println();
  }
}