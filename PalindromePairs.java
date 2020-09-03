import java.util.*;
public class PalindromePairs {

    //O(k^2*n)
    //my thought O(k^2+n^2)
    public List<List<Integer>> palindromePairs(String[] words) {
        HashMap<String, Integer> map=new HashMap<String, Integer>();
        for(int i=0;i<words.length;i++){
            map.put(words[i], i);
        }
        
        //space:O(n^2)
        List<List<Integer>> result=new ArrayList<>();
        //time: O(n)
        for(String word: words){
            //case 1: reverse. 
            String reverse=new StringBuilder(word).reverse().toString();
            if(map.containsKey(reverse)&&map.get(reverse)!=map.get(word)){
                result.add(Arrays.asList(map.get(word), map.get(reverse)));
            }
            
            //case2: 
            for(String str: getAllSuffix(word)){
                String revStr=new StringBuilder(str).reverse().toString();
                if(map.containsKey(revStr)){
                    result.add(Arrays.asList(map.get(word), map.get(revStr)));
                }
            }
            
            for(String str: getAllPrefix(word)){
                String revStr=new StringBuilder(str).reverse().toString();
                if(map.containsKey(revStr)){
                    result.add(Arrays.asList( map.get(revStr), map.get(word)));
                }
            }
        }
        return result;
    }
    
    private List<String> getAllSuffix(String s){
        //time: O(k^2)
        //space: O(k^2)
        List<String> result=new ArrayList<String>();
        for(int i=0;i<s.length();i++){
            if(isPal(s.substring(i, s.length()))){
                result.add(s.substring(0,i));
            }
        }
        return result;
    }
    
    private List<String> getAllPrefix(String s){
        List<String> result= new ArrayList<String>();
        for(int i=s.length();i>0;i--){
            if(isPal(s.substring(0,i))){
                result.add(s.substring(i,s.length()));
            }
        }
        return result;
    }
    
    private boolean isPal(String s){
        String reverse=new StringBuilder(s).reverse().toString();
        return reverse.equals(s);
    }
}