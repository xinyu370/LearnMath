package math.basestrucut;

import java.util.*;

public class AllO1 {

    /**
     * 一个词频数据结构：
     * 往结构加入或者移除一个字符串，它出现的次数加一或者减一，还要获取最大或者最小的词频的字符串
     */
    private Bucket head;
    private Bucket tail;
    private HashMap<String,Bucket> map;

    public AllO1(){
        head = new Bucket(Integer.MIN_VALUE,"");
        tail = new Bucket(Integer.MAX_VALUE,"");
        head.next = tail;
        tail.pre = head;
        map = new HashMap<>();
    }

    public void incre(String key){
        if(map.containsKey(key)){
            Bucket bucket = map.get(key);//链表中的这个桶
            bucket.set.remove(key);
            if(bucket.next.baseHead == bucket.baseHead+1){//词频加一的桶存在
                bucket.next.set.add(key);//集合新加一个字符串
                map.put(key,bucket.next);
            }else{ //新建一个桶
                Bucket newB = new Bucket(bucket.baseHead+1,key);
                insert(bucket,newB);
                map.put(key,newB);
            }
            if(bucket.set.isEmpty()){
                remove(bucket);
            }
        }else{//第一次加进来
            if(head.next.baseHead==1){ //有1这个桶
                head.next.set.add(key);
                map.put(key,head.next);
            }else{//创建一个新桶
                Bucket newB = new Bucket(1,key);
                insert(head,newB);
                map.put(key,newB);
            }
        }
    }

    public void decre(String key){
        if(map.containsKey(key)){
            Bucket bucket = map.get(key);
            bucket.set.remove(key);
            if(bucket.baseHead==1){
                map.remove(key);
            }else {
                if (bucket.pre.baseHead == bucket.baseHead - 1) {//有这个桶
                    bucket.pre.set.add(key);
                    map.put(key, bucket.pre);
                } else {//不存在，新建
                    Bucket newB = new Bucket(bucket.baseHead - 1, key);
                    insert(bucket.pre, newB);
                    map.put(key, newB);
                }
            }
            if(bucket.set.isEmpty()){
                remove(bucket);
            }
        }
    }

    public String getMin(){
        HashSet<String> set = head.next.set;
        return set.iterator().next();
    }

    public String getMax(){
        HashSet<String> set = tail.pre.set;
        return set.iterator().next();
    }



    /**
     * 在当前节点后面插入一个新的桶
     * @param curr
     * @param newBucket
     */
    public void insert(Bucket curr,Bucket newBucket){
       if(curr!=null && newBucket !=null){
           newBucket.next = curr.next;
           curr.next.pre = newBucket;
           curr.next = newBucket;
           newBucket.pre=curr;
       }
    }

    /**
     * 删除一个桶
     * @param curr
     */
    public void remove(Bucket curr){
        if(curr != null){
            curr.pre.next = curr.next;
            curr.next.pre = curr.pre;
        }
    }




    class Bucket{
        public HashSet<String> set;
        public Integer baseHead;
        public Bucket pre;
        public Bucket next;

        public Bucket(Integer baseHead,String value){
            this.baseHead = baseHead;
            set = new HashSet<>();
            set.add(value);
        }

    }


}
