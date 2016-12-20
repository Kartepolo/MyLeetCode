/**
 * Created by Xiang on 11/18/2016.
 */
import java.util.*;
public class LRU {
    private class Node{
        Node prev;
        Node next;
        int key;
        int value;

        Node(int k, int v){
            this.key = k;
            this.value = v;
        }

    }

    private int capacity;
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;

    // @param capacity, an integer
    public LRU(int capacity) {
        // write your code here
        this.capacity = capacity;
        this.map = new HashMap<Integer, Node>();
        head= new Node(-1, -1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    // @return an integer
    public int get(int key) {
        // write your code here
        if (map.containsKey(key)){
            //put it to the head;
            Node tmp = map.get(key);
            Node p = tmp.prev;
            p.next = tmp.next;
            p.next.prev = p;
            tmp.next = head.next;
            tmp.next.prev = tmp;
            head.next = tmp;
            return tmp.value;
        }
        else return -1;
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        // write your code here
        if (map.containsKey(key)){
            Node old = map.get(key);
            old.value = value;
            this.get(key);
        }else{
            if (map.size() == capacity){
                Node todel = tail.prev;
                map.remove(todel.key);
                tail.prev = todel.prev;
                tail.prev.next = tail;
            }
            Node newNode = new Node(key, value);
            newNode.next = head.next;
            newNode.next.prev = newNode;
            head.next = newNode;
            newNode.prev = head;
            map.put(key, newNode);
        }
    }

    public static void main(String[] args){
        LRU l = new LRU(3);
        l.set(1,1);
        l.set(2,2);
        l.set(3,3);
        l.set(4,4);
        System.out.print(l.get(4));
    }
}
