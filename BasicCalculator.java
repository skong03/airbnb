
class BasicCalculator {
  
  public static void main(String[] args) {
    Calculator h= new Calculator();
    int result = h.calculate("(1+ (4+ 5+2) -3)+(6+8)");
    
    System.out.println(result);
  }
 
}

// Input: "(1+(4+5+2)-3)+(6+8)"
// Output: 23
class Calculator {
    int i=0;
    public int calculate(String s) {
        int result=0;
        
        char sign='+';
        for(;i<s.length();i++){
            char c=s.charAt(i);
            
            if(Character.isDigit(c)){
              int temp = (int)(c-'0');
              while(i+1<s.length() && Character.isDigit(s.charAt(i+1))){
                temp=temp*10+(int)(s.charAt(i+1)-'0');
                i++;
              }
              if(sign=='+'){
                result += temp;
              }else{
                result =result-temp;
              }
            }else if(c=='('){
              i++;
              if(sign=='+'){
                result+=calculate(s);
              }else{
                result -= calculate(s);
              }
            }else if(c==')'){
              return result;
            }else if(c=='+'){
              sign='+';
            }else if(c=='-'){
              sign='-';
            }
        }
      
      return result;
    }
}