import java.util.*;

public class TravelBuddy {
    List<Buddy> buddyList=new ArrayList<Buddy>();
    public TravelBuddy(Set<String> myList, Map<String, Set<String>> friendLists){
      for(String key: friendLists.keySet()){
        Buddy buddy=getBuddy(myList, friendLists.get(key));
        if(buddy!=null){
          buddyList.add(buddy);
        }
      }
      
      Collections.sort(buddyList);
      //System.out.println(buddyList.size());
    }
  
    public List<String> getRecomend(int num){
        HashSet<String> result = new HashSet<String>();
      for(int i=0;i< buddyList.size();i++){
        Buddy b=buddyList.get(i);
        for(String city: b.recommends){
          result.add(city);
          if(result.size()==num){
            return new ArrayList<String>(result);
          }
        }
      }
      
      return new ArrayList<String>(result);
    }
  
  //Only sameCityCount> 1/2 * myList.size(), return Buddy, otherwise null.
    private Buddy getBuddy(Set<String> myList, Set<String> friendList){
      // printCollection(myList);
      // System.out.println();
      // printCollection(friendList);
      List<String> recommends=new ArrayList<String>();
      
      for(String city: friendList){
        if(myList.contains(city)){
          continue;
        }
        recommends.add(city);
      }
     // printCollection(recommends);
      int sameCityCount = friendList.size()-recommends.size();
      if(myList.size()<=sameCityCount*2){
        return new Buddy(recommends, sameCityCount);
      }
      return null;
    }
  
  private void printCollection(Collection<String> c){
    for(String s: c){
      System.out.print(s+", ");
    }
  }
}

class Buddy implements Comparable<Buddy>{
  List<String> recommends;
  int sameCityCount;
  
  public Buddy(List<String> recommends,int sameCityCount ){
    this.recommends = recommends;
    this.sameCityCount = sameCityCount;
  }
  
  @Override 
  public int compareTo(Buddy b){
    //higher sameCityCount are ahead. 
    
    return b.sameCityCount- this.sameCityCount;
  }
}



class Solution {
  
  public static void main(String[] args) {
    HashSet<String> myList = new HashSet<String>();
    myList.add("a");
    myList.add("b");
    myList.add("c");
    myList.add("d");
    
    HashMap<String, Set<String>> map =  new HashMap<>();
    HashSet<String> b1 = new HashSet<String>();
    b1.add("a");
    b1.add("b");
    b1.add("e");
    b1.add("f");
    map.put("b1", b1);
    
    HashSet<String> b2 = new HashSet<String>();
    b2.add("a");
    b2.add("b");
    b2.add("d");
    b2.add("g");
    map.put("b2", b2);
    
    TravelBuddy tb = new TravelBuddy(myList, map);
    
    List<String> result=tb.getRecomend(2);
    for(String s: result){
      System.out.println(s);
    }
  }
 
}
