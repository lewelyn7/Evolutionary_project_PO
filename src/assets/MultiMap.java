package assets;

import java.util.HashMap;

import java.util.PriorityQueue;

public class MultiMap<K, V> extends HashMap<K, PriorityQueue<V>>{

    public boolean putOne(K key, V value) {
        if (this.containsKey(key)) {
            this.get(key).add(value);
            return true;
        } else {
            PriorityQueue<V> values = new PriorityQueue<>();
            values.add(value);
            this.put(key, values);
            return false;
        }
    }
    public void removeOne(K key, V value){
        this.get(key).remove(value);
        if(this.get(key).isEmpty()){
            this.remove(key);
        }
    }

}

