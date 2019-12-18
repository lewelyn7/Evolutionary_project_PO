package main;

import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Map;
import java.util.Set;
/**
 * A simple implementation of a MultiMap. This implementation allows duplicate elements in the the
 * values. (I know classes like this are out there but the ones available to me didn't work).
 */
public class MultiMap<K, V> extends HashMap<K, PriorityQueue<V>> implements Iterable{

    /**
     * Looks for a list that is mapped to the given key. If there is not one then a new one is created
     * mapped and has the value added to it.
     *
     * @param key
     * @param value
     * @return true if the list has already been created, false if a new list is created.
     */
    public boolean putOne(K key, V value) {
        if (this.containsKey(key)) {
            this.get(key).add(value);
            return true;
        } else {
            PriorityQueue<V> values = new PriorityQueue<V>();
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

    @Override
    public Iterator<V> iterator(){
        throw new UnsupportedOperationException("multipmap iterator");
        //return (new MultiMapIterator<K,V>(this));
    }


}

class MultiMapIterator<K, V> implements Iterator<V>{

    MultiMap<K, V> map;
    Set<Map.Entry<K, PriorityQueue<V>>> mapSet;
    Iterator<Map.Entry<K,PriorityQueue<V>>> setIterator;
    Iterator<V> queueIterator;
    V toReturn;
    PriorityQueue<V> queue;
    boolean endOfQueue = true;

    public MultiMapIterator(MultiMap map) {
        this.map = map;
        mapSet = map.entrySet();
        setIterator = mapSet.iterator();
        if(setIterator.hasNext()) {
            queueIterator = setIterator.next().getValue().iterator();
            if (queueIterator.hasNext()) endOfQueue = false;
            else endOfQueue = true;
        }

    }




    @Override
    public boolean hasNext() {
        if(endOfQueue) {
            if (!setIterator.hasNext()) return false;
            queue = setIterator.next().getValue();
            queueIterator = queue.iterator();
            endOfQueue = false;
            return (queueIterator.hasNext());
        }else{
            if(queueIterator.hasNext()){
                return true;
            }else{
                return false;
            }
        }
    }

    @Override
    public V next() {
            toReturn = queueIterator.next();
            if(!queueIterator.hasNext()) endOfQueue = true;
            return toReturn;
    }
    @Override
    public void remove(){
        queue.remove(toReturn);
    }
}