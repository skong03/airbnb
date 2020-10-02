import java.util.*;
public class MaxProfitsInJobScheduling1235 {
    // total Time O(n)+O(logn), space O(n)
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        Node[] nodes=new Node[startTime.length];
        for(int i=0;i<startTime.length;i++){
            nodes[i]=new Node(startTime[i], endTime[i], profit[i]);
        }
        
        Arrays.sort(nodes, new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                return n1.end-n2.end;
            }
        });
        
        // storage O(n), look up(logn)
        TreeMap<Integer, Node> treeMap=new TreeMap<Integer, Node>();
        treeMap.put(nodes[0].end, nodes[0]);
        
        for(int i=1;i<nodes.length;i++){
            Node node=nodes[i];
            // remember this interface.
            Map.Entry<Integer, Node> entry = treeMap.floorEntry(node.start);
            int preMax=0;
            if(entry!=null){
                preMax=entry.getValue().maxProfit;
            }
            node.maxProfit=Math.max(nodes[i-1].maxProfit, node.maxProfit+preMax);
            treeMap.put(node.end, node);
        }
        
        return nodes[nodes.length-1].maxProfit;
    }
}

class Node{
    public int start;
    public int end;
    public int profit;
    public int maxProfit;
    public Node(int s, int e, int p){
        start=s;
        end=e;
        profit=p;
        maxProfit=p;
    }
}
