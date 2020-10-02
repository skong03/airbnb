import java.util.*;

class test{
    public static void main(String[] args){
        TireTreeShortestUnique tre=new TireTreeShortestUnique();
        String[] result =tre.getArray(new String[]{"I like to watch movie", "I love watching moives like Titanic"});
        for (String string : result) {
            System.out.println(string);   
        }
    }
}

public class TireTreeShortestUnique{
    TireNode root;

    public String[] getArray(String[] input){
        String[] result=new String[input.length];
        
        root=new TireNode(-1, -1, -1);
        for(int i=0;i<input.length;i++){
            //trim the string, trim() only trim head and tail.
            input[i]=input[i].replace(" ", "");
            //build prefix tree for each string
            for(int s=0;s<input[i].length();s++){     
                insert(root, input[i].substring(s,input[i].length()), 0, i, s, s+1);
            }
            
            result[i]=null;
        }

        //bfs the tree, if the result placehold is empty, fill it. 
        Queue<TireNode> queue=new LinkedList<>();
        queue.add(root);
        while(queue.size()!=0){
            TireNode node=queue.poll();
            if(node.isUnique){
                if(result[node.index]==null){
                    result[node.index]=input[node.index].substring(node.start, node.end);
                }
            }
            for (TireNode tireNode : node.next.values()) {
                queue.add(tireNode);
            }
        }
        return result;
    }

    private void insert(TireNode n, String word, int cp, int index, int start, int end){
        if(cp==word.length()){
            return;
        }
        //another word come here
        if(n.index!=index){
            n.isUnique=false;
        }

        char key = word.charAt(cp);
        if(!n.next.containsKey(key)){
            n.next.put(key, new TireNode(index, start, end));
        }
        insert(n.next.get(key), word, cp+1, index, start, end+1);
    }

}

 class TireNode {
    public HashMap<Character, TireNode> next;
    public boolean isUnique;
    public int index;
    public int start;
    public int end;
    
    public TireNode(int index, int s, int e){
        isUnique=true;
        this.next=new HashMap<>();
        this.index=index;
        start=s;
        end=e;
    }
}