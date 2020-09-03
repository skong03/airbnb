//time: O(VK)
//space: O(VK+V^2)
import java.util.*;
public class MiniCostFlightDFS {
    private int[][] adjMatrix;
    private HashMap<String, Long> memo;
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
     
        this.adjMatrix = new int[n][n];
        this.memo = new HashMap<>();
        
        for (int[] flight: flights) {
            this.adjMatrix[flight[0]][flight[1]] = flight[2];
        }
            
        long ans = this.findShortest(src, K, dst, n);
        return ans >= Integer.MAX_VALUE ? -1 : (int)ans;
    }
    
    public long findShortest(int node, int stops, int dst, int n) {
        
        // No need to go any further if the destination is reached    
        if (node == dst) {
            return 0;
        }
            
        
        // Can't go any further if no stops left
        if (stops < 0) {
            return Integer.MAX_VALUE;
        }
            
        String key = node+","+stops;
    
    
        // If the result of this state is already cached, return it
        if (this.memo.containsKey(key)) {
            return this.memo.get(key);
        }
        
        // Recursive calls over all the neighbors
        long ans = Integer.MAX_VALUE;
        for (int neighbor = 0; neighbor < n; ++neighbor) {
            
            int weight = this.adjMatrix[node][neighbor];
            
            // 0 value means no edge
            if (weight > 0) {
                ans = Math.min(ans, this.findShortest(neighbor, stops - 1, dst, n) + weight);            
            }  
        } 
        
        // Cache the result
        this.memo.put(key, ans);
        return ans;
    }
}