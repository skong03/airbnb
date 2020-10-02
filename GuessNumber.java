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
    //find 4 matched number
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
   
    // find first two position, 3*2 worse case;
    // try resSet[1,2,3] on position 1.
    // try res[1,2] on posision 2.
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
      // the previouly is not right, the last one must be. 
      if(dummy[i]==none){
        dummy[i]=resSet.get(resSet.size()-1);
        resSet.remove(resSet.size()-1);
      }
    }

    // last two case. 1
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

// the approach I used in the code:
// 1111, 2222 ... - 5 steps to find 4 numbers
// 1xxx, 2xxx, 3xxx - 3 steps to find position 1. 
// 12xx, 13xx 2 steps to find position 2. 
// 1234 to find position 3,4 
// total 5+3+2+1 = 11 worse case

// a better strategy. 
//1. got all four number: 1122, 3344 might know which 4 is correct or (know which 2 sets has 1 correct, use extra 2 move to get all 4 numbers.)
//2. after get 4 numbers: use 1222 will return (2,0)2 is first, continue to step 4;  
//    (2,1) 1 is not first, and 2 is not first, need step 3 (2,2) 1 is first, continue to step 4.
//3. guess 3444, because both 1 and 2 is not first, so it can only return (2,0) means 4 is first, or (2,2) means 3 is first. 
//4. assume 1 is at first step, so 1-233 will return(3, 1) means 3 is second, (3,2) means second is not 2 or 3, then it's 4 (3,3) second is 2. 
//5. extra step to find rest 2. 
// worse case： 4+2+1+1 = 8

// Another improvement, the step 3, how to find first 1 position digit can be done in step 1. 
// after 1222, 3444 guess, worse case is these two only return 1 hit.
// we can guess 5111 to know if first is 5, and 6333 to know if first is 6.  it improves to 4+1+1+1=7 

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
  
  @SuppressWarnings("unused")
  private static void print(int[] arr){
    for(int i:arr){
      System.out.print(i+",");
    }
    System.out.println();
  }
}