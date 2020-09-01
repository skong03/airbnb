import java.util.*;

class Solution17 {
 public static void main(String[] args){
   TenWizards f=new TenWizards();
   int[][] input=new int[][]{{0,1},{0,4},{0,5},{4,9}};
   List<Integer> re=f.getShortPath(input, 0, 9);
   for(int i:re){
    System.out.println(i);
   }
 }
}


class TenWizards{
  int maxPath=Integer.MAX_VALUE;
  List<Integer> res;
  public List<Integer> getShortPath(int[][] widzards, int source, int target){
    HashMap<Integer, List<Path>> map =new HashMap<>();
    for(int[] w:widzards){
      int key=w[0];
      if(!map.containsKey(key)){
        map.put(key, new ArrayList<>());
      }
      if(!map.containsKey(w[1])){
        map.put(w[1], new ArrayList<>());
      }
      Path p=new Path(w[1], (w[1]-w[0])*(w[1]-w[0]));
      map.get(key).add(p);
    }
    
    List<Integer> temp=new ArrayList<>();
    dfs(map, temp, source, target, 0);
    
    System.out.println("minPath:"+maxPath);
    res.add(target);
    return res;
  }
  
  private void dfs(HashMap<Integer, List<Path>> map, List<Integer> temp, int source, int target, int cost){
    if(source ==target){
      if(cost<maxPath){
        res=new ArrayList<>(temp);
        maxPath=cost;
      }
      return;
    }
    
    temp.add(source);
    for(Path path: map.get(source)){
      dfs(map, temp, path.target, target, cost+path.cost);
    }
    
    temp.remove(temp.size()-1);
  }
}

class Path{
  public int target;
  public int cost;
  public Path(int t, int c){
    target=t;
    cost=c;
  }
}