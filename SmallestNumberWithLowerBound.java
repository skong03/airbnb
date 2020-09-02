public class SmallestNumberWithLowerBound {
    public static void main(String[] arg){
        SmallestNumberWithLowerBound test = new SmallestNumberWithLowerBound();
        int[] input=new int[]{3,3,3,3,3,3,3,3,1}; 
        String res= test.getSmallestBucket(input, "133333334");
        System.out.println("133333334");
        System.out.println(res);
    }

    public String getSmallestBucket(int[] digits, String boundary){
        int[] bucket=new int[10];
        for(int i:digits){
            bucket[i]++;
        }
        return dfs(bucket, boundary, 0);
    }

    private String dfs(int[] bucket, String boundary, int index){
        if(index==boundary.length()){
            //found the number equals boundary
            return boundary;
        }

        int i=(int)(boundary.charAt(index)-'0');

        if(bucket[i]>0){
            bucket[i]--;
            String res=dfs(bucket, boundary, index+1);
            if(res!=null){
                return res;
            }
            bucket[i]++;
        }
        //go next large number.
        i++;
        //doesn't exists a number at this level return null;
        while(i<10 && bucket[i]==0){
            i++;
            if(i==10){
                return null;
            }
        }

        return boundary.substring(0, index)+ bucketToString(bucket, i);
    }

    private String bucketToString(int[] bucket, int start){
        StringBuilder sb=new StringBuilder();
        for(int i=start;i<10;i++){
            if(bucket[i]>0){
                sb.append(""+i);
                bucket[i]--;
                break;
            }
        }
        for(int i=0;i<10;i++){
            for(int j=0;j<bucket[i];j++){
                sb.append(""+i);
            }
        }
        return sb.toString();
    }
}