package math.basestrucut;

import java.util.*;

public class RandomCollection {
    /**
     * 实现一种数据结构：往集合里添加元素和删除元素时间复杂度都是O（1）：
     * 还要实现一个getRandom方法，等概率返回其中的一个值
     */
    private Map<String, List<Integer>> map = new HashMap<>();
    private List<String> list = new ArrayList<>();

    private int size = 0;
    public void add(String val){
        if(map.containsKey(val)){
            List list1 = map.get(val);
            list1.add(size);
        }else{
            map.put(val, Arrays.asList(size));
        }
        list.add(size,val);
        size++;
    }

    public void remove(String val){
        if(!map.containsKey(val)){
            return;
        }
        List<Integer> indexs = map.get(val);
        Integer remove = indexs.remove(0);
        if(remove==size-1){

        }
        list.remove(remove);
        list.set(remove,list.get(--size));
    }

    public String getRandonEle(){
        return list.get((int)Math.random()*size);
    }


}
