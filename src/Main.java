import jdk.nashorn.internal.parser.JSONParser;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] ints = topKFrequent(new int[]{3,0,0,1}, 1);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]+",");
        }
        System.out.println();
        int[] ints2 = topKFrequent(new int[]{1,1,1,2,2,3}, 2);
        for (int i = 0; i < ints2.length; i++) {
            System.out.print(ints2[i]+",");
        }
        System.out.println();
        int[] ints3 = topKFrequent(new int[]{4,1,-1,2,-1,2,3}, 2);
        for (int i = 0; i < ints3.length; i++) {
            System.out.print(ints3[i]+",");
        }
    }



    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer,List<Integer>> map = new HashMap<>();
        Map<Integer,Integer> cuntMap = new HashMap<>();
        int max = 0;
        for(int i=0;i<nums.length;i++){
            int key = nums[i];
            Integer orDefault = cuntMap.getOrDefault(key, 0)+1;
            max = Math.max(orDefault,max);
            cuntMap.put(key,orDefault);
            if(map.containsKey(orDefault)){
                map.get(orDefault).add(nums[i]);
            }else{
                List<Integer> list = new ArrayList<>();
                list.add(nums[i]);
                map.put(orDefault,list);
            }
        }
        return map.get(max<k).stream().mapToInt(Integer::intValue).toArray();
    }
}