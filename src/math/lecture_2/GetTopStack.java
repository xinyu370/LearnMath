package org.example.lecture_2;

import java.util.*;

public class GetTopStack {
    /**
     * 设置一个数据结构，获取词频最高的那个数，如果相等就拿到出现离stacktop最近的那个数
     */
    private Map<Integer, ArrayList<Integer>> stack;
    private Map<Integer,Integer> freqMap;
    private int topTimes;

    public GetTopStack(){
        stack = new HashMap<>();
        freqMap = new HashMap<>();
        topTimes=0;
    }
    public void push(int val){
       freqMap.put(val,freqMap.getOrDefault(val,0)+1);
       int curTopTimes = freqMap.get(val);
       if(!stack.containsKey(curTopTimes)){
           stack.put(curTopTimes,new ArrayList<>());
       }
       ArrayList<Integer> integers = stack.get(curTopTimes);
       integers.add(val);
       topTimes=Math.max(topTimes,curTopTimes);
    }

    public int getTopTimes(){
        ArrayList<Integer> integers = stack.get(topTimes);
        Integer remove = integers.remove(integers.size() - 1);
        if(integers.size()==0){
            freqMap.remove(topTimes--);
        }
        Integer i = freqMap.get(remove);
        if((i-1)==0){
            freqMap.remove(remove);
        }else{
            freqMap.put(remove,i-1);
        }
        return remove;
    }
}
