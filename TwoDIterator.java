import java.util.*;

public class TwoDIterator {
    public static void main(String[] args) {
        ArrayList<List<Integer>> lists = new ArrayList<>();
        ArrayList<Integer> list1=new ArrayList<Integer>(Arrays.asList(1, 2));
        lists.add(list1);
         MyIterator my= new MyIterator(lists);
        System.out.println(my.hasNext());
        System.out.println(my.next());
        my.remove();
        System.out.println("list1 size"+list1.size() +", list1 first item:"+list1.get(0));
      }
      
}

//[[], [1, 2], [4]]
class MyIterator implements Iterator<Integer>{
    List<List<Integer>> cache;
    int row=0;
    int col=0;
    
    int curR=0;
    int curC=0;
    
    public MyIterator(List<List<Integer>> i){
      this.cache = i;
    }
    
    @Override
    public Integer next(){
      if(!hasNext()){
        return -1; 
      }
      int result=cache.get(row).get(col);
      curR=row;
      curC=col;
      col++;
      return result;
    }
    
    @Override
    public boolean hasNext(){
      //empty list or col is out of bound
      while(row< cache.size() && col == cache.get(row).size()){
        row++;
        col=0;
      }
      return row<cache.size();
    }
    
    @Override
    public void remove(){
      cache.get(curR).remove(curC);
    }
  }