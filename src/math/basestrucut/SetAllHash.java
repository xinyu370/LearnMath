package math.basestrucut;

import java.util.HashMap;
import java.util.Map;

public class SetAllHash {
    /**
     * 给hashmap提供一个setAll方法，要求时间复杂度O（1）
     */
    Map<String,int[]> map = new HashMap<>();
    private int setAllVal = 0;
    private int setAllTime = 0;
    private int cnt;

    public void put(String key,int val){
        map.put(key,new int[]{val,cnt});
        cnt++;
    }

    public int get(String key){
        int[] ints = map.get(key);
        if(ints!=null){
            return ints[1]<setAllTime?setAllVal:ints[0];
        }
        return -1;
    }

    public void setAll(int val){
        setAllTime = cnt++;
        setAllVal = val;
    }
}
