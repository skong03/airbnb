
public class CSVParser {
    public String parser(String s){
        String[] fields = s.split(",");
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<fields.length;i++){
          String field = fields[i];
          sb.append(removeQuote(field));
          if(i!=fields.length-1){
            sb.append("|");
          }
          
        }
        return sb.toString();
      }
      
      // mutiple quote to one quote, one quote to null.
      private String removeQuote(String str){
        boolean sawLeft = false;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str.length();i++){
          if(str.charAt(i)=='\"'){
            if(sawLeft){
              if(i+1<str.length() && str.charAt(i+1)=='\"'){
                sb.append('\"');
                i++;
              }else{
                sawLeft=false;
              }
            }else{
              sawLeft=true;
            }
          }else{
            sb.append(str.charAt(i));
          }
          
        }
        
        return sb.toString();
        
      }
}

class Solution5 {
    public static void main(String[] args) {
      //String test1="John,Smith,jo@gmai.com,Los Angeles,1";
      String test1="\"John \"\"Alex\"\"\",Smith,jo@gmai.com,Los Angeles,1 \"\"\"Alex\"\" \"";
      //String test1="Jane,RO,jo@gmai.com,\"Los Angeles\",1";
      
      CSVParser c= new CSVParser();
      
      System.out.println(c.parser(test1));
    }
  }
