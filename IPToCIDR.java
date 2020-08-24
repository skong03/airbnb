import java.util.*;

public class IPToCIDR {
    public List<String> getCIDR(String startIP, String endIP){
        long start = ipToInt(startIP);
        long end = ipToInt(endIP);
        List<String> result= new ArrayList<String>();
        System.out.println(start+"->"+end);
        System.out.println(start<=end);
        while(start<=end){
          int mask=getMask(start, end);
          result.add(intToIP(start)+"/"+mask);
          start=start+(long)Math.pow(2,32-mask);
        }
        return result;
      }
      
      // Based on startIP, find available mask not excced endIP 
      // if startIP end with 1, return 32; range 1
      // if startIP end with 10, return 31; range 2. 
      // if startIP end with 100, return 30; range is 4
      // of course, there is limit, startIP + range should smaller than endIP. 
      private int getMask(long startIP, long endIP){
        int tailZero=0;
        long temp=startIP;
        for(int i=0;i<32;i++){
          if(temp%2==0){
            tailZero++;
            temp=temp/2;
          }
        }
        System.out.println(startIP+"->"+endIP);
        System.out.println("tailZero: "+tailZero);
        
        int result=0;
        for(int i=0;i<tailZero;i++){
          if(startIP+Math.pow(2,i)<=endIP){
            result=i;
          }else{
            break;
          }
        }
        
        return 32-result;
      }
      
      private static long ipToInt(String strIP) {         
        long[] ip = new long[4];         
        String[] ipSec = strIP.split("\\.");         
        for (int k = 0; k < 4; k++) {             
            ip[k] = Long.valueOf(ipSec[k]);         
        }         
    
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];     
      }       
      
      private static String intToIP(long longIP) {         
          StringBuffer sb = new StringBuffer("");         
          sb.append(String.valueOf(longIP >>> 24));         
          sb.append(".");         
          sb.append(String.valueOf((longIP & 0x00FFFFFF) >>> 16));         
          sb.append(".");         
          sb.append(String.valueOf((longIP & 0x0000FFFF) >>> 8));         
          sb.append(".");         
          sb.append(String.valueOf(longIP & 0x000000FF));   
    
          return sb.toString();     
      }  
}

class Solution4 {
    public static void main(String[] args) {
      IPToCIDR test=new IPToCIDR();
      
      // String binaryString = Integer.toBinaryString(Integer.MAX_VALUE);
      // System.out.println(binaryString);
      // String z=Integer.toBinaryString(-1);
      // System.out.println(z);
      //List<String> result = test.getCIDR("0.0.0.0", "255.255.255.255");
      //List<String> result = test.getCIDR("0.0.0.0", "255.255.255.254");
      List<String> result = test.getCIDR("0.0.0.0", "0.0.0.0");
      //List<String> result = test.getCIDR("0.0.0.111", "0.0.0.120");
      for(String s: result){
        System.out.println(s);
      }
    }
  }