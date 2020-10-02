import java.util.*;
class FractionToRecurringDecimal166 {
    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator==0){
            return "0";
        }
        //number and its index in res;
        HashMap<Long, Integer> map=new HashMap<>();
        StringBuilder res=new StringBuilder();
        boolean isNeg=(numerator>0 && denominator<0) || (numerator<0&&denominator>0);
        if(isNeg){
            res.append('-');
        }
        long num=Math.abs((long)numerator);
        long den=Math.abs((long)denominator);
        
        res.append(String.valueOf(num/den));
        long reminder = num%den;
        if(reminder==0){
            return res.toString();
        }
        res.append(".");
        
        while(reminder>0){
            if(map.containsKey(reminder)){
                res.insert(map.get(reminder), "(");
                res.append(")");
                return res.toString();
            }
            map.put(reminder, res.length());
            reminder*=10;
            res.append(String.valueOf(reminder/den));
            reminder%=den;
        }
        return res.toString();
    }
}