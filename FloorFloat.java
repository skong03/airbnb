
import java.util.*;

class Solution6 {
 public static void main(String[] args){
    ArrayList<Float> test=new ArrayList<Float>();
     test.add(1.2f);
     test.add(2.3f);
     test.add(3.4f);
     FloorFloat c=new FloorFloat();

     ArrayList<Float> test1=new ArrayList<Float>();
     test.add(1.2f);
     test.add(2.9f);
     test.add(3.8f);

  int[] result = c.getList(test);
  for(int i: result){
    System.out.println(i);
  }

  System.out.println();

  result = c.getList(test1);
  for(int i: result){
    System.out.println(i);
  }
 }
}
public class FloorFloat {
    public int[] getList(ArrayList<Float> input){
        ArrayList<Float> loss=new ArrayList<>();
        ArrayList<Float> gain=new ArrayList<>();
        
        float sum=0;
        int intSum=0;
        for(float f: input){
          sum+=f;
          intSum +=Math.round(f);
          if(Math.round(f)<f){
            loss.add(f);
          }else{
            gain.add(f);
          }
        }
        
        //System.out.println("round sum:"+Math.round(sum)+", intSum:"+intSum);
        
        if(Math.round(sum)>=intSum){
          //sort loss; mark some number to top. 
          this.sortLoss(loss);
          
          for(int i=0;i<Math.round(sum)-intSum;i++){
            float f= loss.remove(0);
            gain.add(f+1f);
          }
          
          for(int i=0;i<loss.size();i++){
            gain.add(loss.get(i));
          }
          return getInt(gain);
        }else{
          this.sortGain(gain);
          
          for(int i=0;i<intSum-Math.round(sum);i++){
            float f= gain.remove(0);
            loss.add(f-1);
          }
          
          for(int i=0;i<gain.size();i++){
            loss.add(gain.get(i));
          }
          return getInt(loss);
        
        }
      }
      
      private <T> void print(Collection<T> c){
        for(T t: c){
          System.out.println(t);
        }
         System.out.println();
      }
      
      public int[] getInt(ArrayList<Float> input){
        int[] result =new int[input.size()];
        for(int i=0;i<input.size();i++){
          result[i]=Math.round(input.get(i));
        }
        return result;
      }
      
      public ArrayList<Float> sortLoss(ArrayList<Float> loss){
        
        Collections.sort(loss, new Comparator<Float>(){
            @Override
            public int compare(Float f1, Float f2){
              if(f1-Math.round(f1)>f2-Math.round(f2)){
                return -1;
              }else{
                return 1;
              }
            }
          });
        
        return loss;
      }
      
      public ArrayList<Float> sortGain(ArrayList<Float> gain){
        
        Collections.sort(gain, new Comparator<Float>(){
            @Override
            public int compare(Float f1, Float f2){
              if(Math.round(f1)-f1>Math.round(f2)-f2){
                return -1;
              }else{
                return 1;
              }
            }
          });
        
        return gain;
      }
}