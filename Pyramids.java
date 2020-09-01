import java.util.*;

class Solution13 {
 public static void main(String[] args){
   Pyramids p=new Pyramids(new HashMap<String, String[]>());
   // List<String> nextString=new ArrayList<>();
   // List<String[]> strList=new ArrayList<>();
   // strList.add(new String[]{"A", "B"});
   // strList.add( new String[] {"C"});
   // strList.add(new String[]{"D","A"});
   // p.dfs(nextString, strList, 0, "");
   // for(String s: nextString){
   //  System.out.println(s);
   // }
   System.out.println(p.canAchieve("ABCD","B"));
   
 }
}

public class Pyramids {
    HashMap<String, String[]> map;
  public Pyramids(){}
  
  public Pyramids(HashMap<String, String[]> map){
    this.map=map;
    
    this.map.put("AA", new String[]{"B"});
    this.map.put("AB", new String[]{"A", "C"});
    this.map.put("AC", new String[]{"D"});
    this.map.put("AD", new String[]{"A"});
    
    this.map.put("BA", new String[]{"D"});
    this.map.put("BB", new String[]{"B","C"});
    this.map.put("BC", new String[]{"A"});
    this.map.put("BD", new String[]{"B"});
    
    this.map.put("CA", new String[]{"B"});
    this.map.put("CB", new String[]{"A"});
    this.map.put("CC", new String[]{"A"});
    this.map.put("CD", new String[]{"B"});
    
    this.map.put("DA", new String[]{"A"});
    this.map.put("DB", new String[]{"A"});
    this.map.put("DC", new String[]{"A"});
    this.map.put("DD", new String[]{"B"});
    
  }
  
  public boolean canAchieve(String source, String target){
    if(source.equals(target)){
      return true;
    }
    
    if(source.length()<=target.length()){
      return false;
    }
    
    List<String[]> nextCombin=new ArrayList<>();
    
    for(int i=1;i<source.length();i++){
      String key=source.substring(i-1,i+1);
      
      nextCombin.add(this.map.get(key));
    }
    
    List<String> nextString=new ArrayList<>();
    dfs(nextString, nextCombin, 0, "");
    
    for(String str: nextString){
      System.out.println(str);
    }
    System.out.println();
    
    for(String next:nextString){
      if(canAchieve(next, target)){
        return true;
      }
    }
    return false;
  }
  
  
  public void dfs(List<String> res, List<String[]> combin, int i, String str){
    if(i==combin.size()){
      res.add(str);
      return;
    }
    
    String[] com=combin.get(i);
    for(int j=0;j<com.length;j++){
      dfs(res, combin, i+1, str+com[j]);
    }
  }
}