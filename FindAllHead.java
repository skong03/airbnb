import java.util.*;

class Solution16 {
 public static void main(String[] args){
   FindAllHead f=new FindAllHead();
   int[][] input=new int[][]{{2,9},{3,3},{3,5},{3,7},{4,8},{5,8},{6,6},{7,4},{8,7},{9,3},{9,6}};
   HashSet<Integer> re=f.getAllHead(input, 10);
   for(int i:re){
    System.out.println(i);
   }
 }
}


class FindAllHead{
  // n means n nodes
  boolean isHead=false;
  public HashSet<Integer> getAllHead(int[][] edges, int n){
    HashMap<Integer, List<Integer>> map=new HashMap<>();
    HashSet<Integer> res=new HashSet<>();
    for(int i=0;i<n;i++){
      res.add(i);
      map.put(i, new ArrayList<>());
    }
    
    for(int[] pair:edges){
      int l=pair[0];
      map.get(l).add(pair[1]);
    }
    
    for(int i=0;i<n;i++){
      isHead=true;
      dfs(map, new HashSet<Integer>(), i, res);
    }
    return res;
  }
  
  private void dfs(HashMap<Integer, List<Integer>> map, HashSet<Integer> visited, int node, HashSet<Integer> res){
    if(visited.contains(node)){
      return;
    }
    visited.add(node);
    if(isHead){
      isHead=false;
    }else{
      res.remove(node);
    }
    
    for(int i: map.get(node)){
      dfs(map, visited, i, res);
    }
  }
}