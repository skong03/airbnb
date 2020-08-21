
import java.util.*;
public class DisplayPage {
    public static void main(String[] args) {
        List<Integer> input = new ArrayList<Integer>(Arrays.asList(1,2,3,4,1,5,1,2,3,1,3));
        Helper h=new Helper();
        List<Integer> result =  h.displayPage(input, 5);
        for(int i=0;i<result.size();i++){
          System.out.println(result.get(i));
        }
      }
}

class Helper {
    //12341512313 5
   // 12345/12311/3
   public List<Integer> displayPage(List<Integer> input, int pageSize){
     //Can always provider next index Node
     PriorityQueue<Node> minHeap = new PriorityQueue<Node>(new Comparator<Node>(){
       public int compare(Node n1, Node n2){
         return n1.index-n2.index;
       }
     });
     //Quick find the Node
     HashMap<Integer, Node> map=new HashMap<Integer, Node>();
     //build Node list and heap
     for(int i=0;i<input.size();i++){
       int v=input.get(i);
       Node n= new Node(v, i);
       if(!map.containsKey(v)){
         map.put(v, n);
         minHeap.add(n);
         continue;
       }
       // already saw before
       Node head=map.get(v);
       head.tail.next=n;
       head.tail=n;
     }
     
     List<Integer> result = new ArrayList<Integer>();
     while(minHeap.size()>0){
       fillPage(minHeap, pageSize, result);
       result.add(-1);
     }
     
     return result;
   }
   
   public List<Integer> fillPage(PriorityQueue<Node> minHeap, int pageSize, List<Integer> temp){
     if(pageSize==0){
       return temp;
     }
     
     List<Node> cache = new ArrayList<Node>();
     if(minHeap.size()>=pageSize){
       while(pageSize>0){
         Node n=minHeap.poll();
         cache.add(n);
         temp.add(n.value);
         pageSize--;
       }
       
       for(Node n: cache){
         if(n.next!=null){
           minHeap.add(n.next);
         }
       }
       return temp;
     }else{
       while(minHeap.size()>0){
         Node n=minHeap.poll();
         cache.add(n);
         temp.add(n.value);
         pageSize--;
       }
       
       for(Node n: cache){
         if(n.next!=null){
           minHeap.add(n.next);
         }
       }
       
       if(minHeap.size()>0){
         fillPage(minHeap, pageSize, temp);
       }
       return temp;
     }
       
   }
 }
 
 class Node {
   int value;
   int index;
   public Node next;
   public Node tail;
   
   public Node(int v, int i){
     value=v;
     index=i;
     tail=this;
   }
 }
 