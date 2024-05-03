package other.cache;

import other.GateLock;

import java.util.concurrent.*;

public class Memoizer<A,V> implements Computable<A,V>{
    //缓存集合
    private final ConcurrentHashMap<A, Future<V>>  cache = new ConcurrentHashMap();
    //需要执行任务的实现
    private Computable<A,V> c;

    public Memoizer(Computable<A,V> c){
        this.c = c;
    }

    /**
     *   从缓存获取
     * @param arg
     * @return
     * @throws InterruptedException
     */
    @Override
    public V compute(A arg) throws InterruptedException {
        while(true){
            Future<V> res = cache.get(arg);
            if(res == null){
                Callable<V> callable = new Callable<V>(){
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<>(callable);
                cache.putIfAbsent(arg,ft);
                if(res == null){
                    res = ft;
                    ft.run(); //异步的
                }
            }
            try {
                return res.get(); //阻塞，等待计算结果
            }catch (CancellationException e){
                cache.remove(arg,res);
            } catch (ExecutionException e){
                throw GateLock.launderThrowable(e.getCause());
            }
        }
    }
}
