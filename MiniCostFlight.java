import java.util.*;

class Solution12 {
 public static void main(String[] args){
   MiniCostFlight m=new MiniCostFlight();
   
   List<Flight> flights=new ArrayList<Flight>();
    flights.add(new Flight("A", "B", 100));
    flights.add(new Flight("B", "C", 100));
    flights.add(new Flight("A", "C", 500));
   
   int cost=m.minCost(flights, "A", "C", 10);
   System.out.println(cost);
   
 }
}

public class MiniCostFlight {
    int minCost=Integer.MAX_VALUE;
  HashMap<String, Integer> cache= new HashMap<String, Integer>();
  public int minCost(List<Flight> flights, String source, String target, int stops){
    HashMap<String, List<Flight>> map=new HashMap<String, List<Flight>>();
    
    for(Flight f: flights){
      if(!map.containsKey(f.source)){
        map.put(f.source, new ArrayList<Flight>());
      }
      map.get(f.source).add(f);
    }
    
    dfs(map, source, target, stops, 0, new HashSet<String>());
    if(minCost!=Integer.MAX_VALUE){
      return minCost;
    }
    return -1;
  }
  
  public void dfs(HashMap<String, List<Flight>> map, String source, String target, int stops, int cost, HashSet<String> visit){
    if(stops+1<0||visit.contains(source)){
      return;
    }
    if(source==target){
      minCost=Math.min(cost, minCost);
      return;
    }
    if(!cache.containsKey(source)){
      cache.put(source, cost);
    }else{
      if(cache.get(source)<cost){
        return;
      }else{
        cache.put(source, cost);
      }
    }
    
    visit.add(source);
    List<Flight> flights=map.get(source);
    for(Flight f: flights){
      dfs(map, f.target, target, stops-1, cost+f.cost, visit);
    }
    visit.remove(source);
  }
}

class Flight{
  public String source;
  public String target;
  public int cost;
  public Flight(String s, String t, int c){
    source=s;
    target=t;
    cost=c;
  }
}