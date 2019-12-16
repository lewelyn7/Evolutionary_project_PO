package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * A simple implementation of a MultiMap. This implementation allows duplicate elements in the the
 * values. (I know classes like this are out there but the ones available to me didn't work).
 */
public class MultiMap<K, V> extends HashMap<K, PriorityQueue<V>> {

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
    }
}