import java.util.*;
public class TagValidator {
    Stack<String> stack= new Stack<String>();
    boolean alreadyHave =false;
    public boolean isValid(String code) {
        if(code.charAt(0)!='<'||code.charAt(code.length()-1)!='>'){
            return false;
        }
        
        for(int i=0;i<code.length();i++){
            if(stack.size()==0 && alreadyHave){
                return false;
            }
            if(code.charAt(i)=='<'){
                int closeIndex=-1;
                if(code.charAt(i+1)=='!'){
                    closeIndex=code.indexOf("]]>", i);
                    if(stack.size()<=0 ||closeIndex<0|| !isValidCdata(code.substring(i+2, closeIndex))){
                        return false;
                    }
                }else if(code.charAt(i+1)=='/'){
                    closeIndex=code.indexOf('>', i);
                    if(closeIndex<0 || !isValidTag(code.substring(i+2, closeIndex), true)){
                        return false;
                    }
                }else{
                    closeIndex=code.indexOf('>', i);
                    if(closeIndex<0|| !isValidTag(code.substring(i+1, closeIndex), false)){
                        return false;
                    }
                }
                i=closeIndex;
            }
            
        }
        
        return stack.size()==0;
    }
    
    private boolean isValidCdata(String data){
        return data.indexOf("[CDATA[")==0;
    }
    
    private boolean isValidTag(String tag, boolean isEnding){
        if(tag.length()<1||tag.length()>9){
            return false;
        }
        for(char c: tag.toCharArray()){
            if(c<'A'||c>'Z'){
                return false;
            }
        }
        if(!isEnding){
            stack.push(tag);
            alreadyHave=true;
        }else{
            if(stack.size()<=0){
                return false;
            }
            String preTag=stack.pop();
            if(!preTag.equals(tag)){
                return false;
            }
        }
        
        return true;
    }
}