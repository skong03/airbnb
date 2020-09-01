import java.util.*;

class Solution15 {
 public static void main(String[] args){
    pregerenceList plist=new pregerenceList();
   List<List<Integer>> input=new ArrayList<>();
   input.add(new ArrayList<>(Arrays.asList(3,5,7,9)));
   input.add(new ArrayList<>(Arrays.asList(2,3,8)));
   input.add(new ArrayList<>(Arrays.asList(5,8)));
   List<Integer> result=plist.getList(input);
   for(int i=0;i<result.size();i++){
    System.out.println(result.get(i));
   }
 }
}

public class pregerenceList {
    boolean hasLoop=false;
  @SuppressWarnings( "deprecation" )
  public List<Integer> getList(List<List<Integer>> preferences){
    Map<Integer, List<Integer>> map=new HashMap<>();
    List<Integer> heads=new ArrayList<Integer>();
    for(List<Integer> list:preferences){
      for(int i=0;i<list.size()-1;i++){
        int l=list.get(i);
        int r=list.get(i+1);
        
        if(!map.containsKey(l)){
          map.put(l, new ArrayList<>());
          heads.add(l);
        }
        if(heads.contains(r)){
          heads.remove(new Integer(r));
        }
        if(!map.containsKey(r)){
          map.put(r, new ArrayList<>());
        }
        map.get(l).add(r);
      }
    }
    
    Set<Integer> visited=new HashSet<Integer>();
    Set<Integer> visiting=new HashSet<Integer>();
    List<Integer> result=new ArrayList<>();
    
    for(int head:heads){
      getResult(result, visiting, visited, map,head);
    }
    return result;
  } 
  
  private void getResult(List<Integer> result, Set<Integer> visiting, Set<Integer> visited,Map<Integer, List<Integer>> map, int node ){
    if(visited.contains(node)){
      return;
    }
    if(visiting.contains(node)){
      hasLoop=true;
      return;
    }
    visiting.add(node);
    result.add(node);
    List<Integer> nextNode=map.get(node);
    
    for(int i=0;i<nextNode.size();i++){
      getResult(result, visiting, visited, map, nextNode.get(i));
    }
    
    
    visiting.remove(node);
    visited.add(node);
  }
}