public class FillOcean {
    public void fillOcean(char[][] map, int r, int c){
        if(r<0 ||r>=map.length || c<0 || c >= map[0].length || map[r][c]!='W'){
          return;
        }
        //map[r][c]=="W"
        map[r][c]='O';
        
        fillOcean(map, r-1, c);
        fillOcean(map, r+1, c);
        fillOcean(map, r, c-1);
        fillOcean(map, r, c+1);
      }
}

class Solution14 {
    public static void main(String[] args){
      char[][] map = new char[][] {
        {'W','W'}
      };
      
      FillOcean f=new FillOcean();
      f.fillOcean(map, 0, 0);
      for(char[] ca:map){
      System.out.println(ca);
      }
      
    }
   }