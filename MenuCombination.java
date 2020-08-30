import java.util.*;
public class MenuCombination {
    public List<List<Double>> getCombos(double[] prices, double target){
        List<List<Double>> result=new ArrayList<>();
        List<Double> temp=new ArrayList<>();
        
        dfs(result, temp, prices, 0,target);
        return result;
      }
      
      private void dfs(List<List<Double>> result, List<Double> temp, double[] prices, int index, double target){
        //System.out.println(temp+", taget: "+target);
        if(Math.abs(target) < 0.00000001){
          result.add(new ArrayList<Double>(temp));
          return;
        }
        
        if(target<0){
          return;
        }
        
        for(int i=index;i<prices.length;i++){
          temp.add(prices[i]);
          dfs(result, temp, prices, i, target-prices[i]);
          temp.remove(temp.size()-1);
        }
      }
}

class Solution10 {
    public static void main(String[] args){
      MenuCombination mc=new MenuCombination();
      double[] prices = {10.02, 1.11, 2.22, 3.01, 4.02, 2.00, 5.03};
      List<List<Double>> combos=mc.getCombos(prices, 7.03);
      System.out.println(combos);
    }
   }