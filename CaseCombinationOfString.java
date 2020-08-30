import java.util.*;
public class CaseCombinationOfString {
    public List<String> getCombination(String str){
        List<String> result=new ArrayList<String>();
        dfs(result, "", str, 0);
        return result;
        
      }
      
      private void dfs(List<String> result, String temp, String str, int index){
        if(temp.length()==str.length()){
          result.add(temp);
          return;
        }
        
        if(index>=str.length()){
          return;
        }
        
        String c=""+str.charAt(index);
        dfs(result, temp+ c.toLowerCase(), str, index+1);
        dfs(result, temp+c.toUpperCase(), str, index+1);
      }
}



/*
123
456
870
*/
class Solution11 {
 public static void main(String[] args){
    CaseCombinationOfString c=new CaseCombinationOfString();
   List<String> re=c.getCombination("abc");
   System.out.println(re);
 }
}