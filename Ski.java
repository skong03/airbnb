import java.util.*;
public class Ski {
    int MaxScore=0;
    HashMap<String, Integer> pointsMap;
    HashMap<String, Edge[]> dir;
    List<String> result;
    public List<String> getPath(String start, HashMap<String, Integer> pointsMap, HashMap<String, Edge[]> dir){
        this.pointsMap = pointsMap;
        this.dir = dir;
        List<String> temp=new ArrayList<>();
        temp.add(start);
        getPath(temp, start, pointsMap.get(start));

        return result;
    }

    public void getPath(List<String> temp, String cur, int curScore){
        //reach end
        if(dir.get(cur).length==0){
            if(curScore>MaxScore){
                MaxScore=curScore;
                result=new ArrayList<String>(temp);
            }
            return;
        }

        for(Edge edge: dir.get(cur)){
            temp.add(edge.des);
            getPath(temp, edge.des, curScore+edge.score+pointsMap.get(edge.des)*2);
            temp.remove(temp.size()-1);
        }
    }

     public static void main(String[] args) {
        Ski s=new Ski();
        HashMap<String, Integer> pMap=new HashMap<>();
        pMap.put("A", 5);    
        pMap.put("B", 7);
        pMap.put("C", 6);
        pMap.put("D", 2);
        pMap.put("E", 4);
        pMap.put("F", 7);
        pMap.put("H", 7);
        pMap.put("I", 3);
        pMap.put("J", 2);

        HashMap<String, Edge[]> dir= new HashMap<>();
        dir.put("A", new Edge[]{new Edge("B", 2), new Edge("C", 3)});
        dir.put("B", new Edge[]{new Edge("D", 5), new Edge("E", 6)});
        dir.put("C", new Edge[]{new Edge("E", 4), new Edge("F", 4)});
        dir.put("D", new Edge[]{new Edge("H", 7)});
        dir.put("E", new Edge[]{new Edge("H", 6)});
        dir.put("F", new Edge[]{new Edge("J", 3)});
        dir.put("H", new Edge[]{new Edge("I", 1), new Edge("J", 2)});
        dir.put("I", new Edge[]{});
        dir.put("J", new Edge[]{});
        List<String> r=s.getPath("A", pMap, dir);
        for (String string : r) {
            System.out.print(string+"->");
        }

    }
}

class Edge{
    String des;
    int score;
    public Edge(String d, int s){
        des=d;
        score=s;
    }
}