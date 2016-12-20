/**
 * Created by Xiang on 10/23/2016.
 */
import java.util.*;
public class skyline {

    class HashHeap {
        ArrayList<Integer> heap;
        String mode;
        int size_t;
        HashMap<Integer, Node> hash;

        class Node {
            public Integer id;
            public Integer num;

            Node(Node now) {
                id = now.id;
                num = now.num;
            }

            Node(Integer first, Integer second) {
                this.id = first;
                this.num = second;
            }
        }

        public HashHeap(String mod) {
            // TODO Auto-generated constructor stub
            heap = new ArrayList<Integer>();
            mode = mod;
            hash = new HashMap<Integer, Node>();
            size_t = 0;
        }

        int peek() {
            return heap.get(0);
        }

        int size() {
            return size_t;
        }

        Boolean empty() {
            return (heap.size() == 0);
        }

        int parent(int id) {
            if (id == 0) {
                return -1;
            }
            return (id - 1) / 2;
        }

        int lson(int id) {
            return id * 2 + 1;
        }

        int rson(int id) {
            return id * 2 + 2;
        }

        boolean comparesmall(int a, int b) {
            if (a <= b) {
                if (mode == "min")
                    return true;
                else
                    return false;
            } else {
                if (mode == "min")
                    return false;
                else
                    return true;
            }

        }

        void swap(int idA, int idB) {
            int valA = heap.get(idA);
            int valB = heap.get(idB);

            int numA = hash.get(valA).num;
            int numB = hash.get(valB).num;
            hash.put(valB, new Node(idA, numB));
            hash.put(valA, new Node(idB, numA));
            heap.set(idA, valB);
            heap.set(idB, valA);
        }

        Integer poll() {
            size_t--;
            Integer now = heap.get(0);
            Node hashnow = hash.get(now);
            if (hashnow.num == 1) {
                swap(0, heap.size() - 1);
                hash.remove(now);
                heap.remove(heap.size() - 1);
                if (heap.size() > 0) {
                    siftdown(0);
                }
            } else {
                hash.put(now, new Node(0, hashnow.num - 1));
            }
            return now;
        }

        void add(int now) {
            size_t++;
            if (hash.containsKey(now)) {
                Node hashnow = hash.get(now);
                hash.put(now, new Node(hashnow.id, hashnow.num + 1));

            } else {
                heap.add(now);
                hash.put(now, new Node(heap.size() - 1, 1));
            }

            siftup(heap.size() - 1);
        }

        void delete(int now) {
            size_t--;
            ;
            Node hashnow = hash.get(now);
            int id = hashnow.id;
            int num = hashnow.num;
            if (hashnow.num == 1) {

                swap(id, heap.size() - 1);
                hash.remove(now);
                heap.remove(heap.size() - 1);
                if (heap.size() > id) {
                    siftup(id);
                    siftdown(id);
                }
            } else {
                hash.put(now, new Node(id, num - 1));
            }
        }

        void siftup(int id) {
            while (parent(id) > -1) {
                int parentId = parent(id);
                if (comparesmall(heap.get(parentId), heap.get(id)) == true) {
                    break;
                } else {
                    swap(id, parentId);
                }
                id = parentId;
            }
        }

        void siftdown(int id) {
            while (lson(id) < heap.size()) {
                int leftId = lson(id);
                int rightId = rson(id);
                int son;
                if (rightId >= heap.size() || (comparesmall(heap.get(leftId), heap.get(rightId)) == true)) {
                    son = leftId;
                } else {
                    son = rightId;
                }
                if (comparesmall(heap.get(id), heap.get(son)) == true) {
                    break;
                } else {
                    swap(id, son);
                }
                id = son;
            }
        }
    }


    public ArrayList<ArrayList<Integer>> buildingOutline(int[][] buildings) {
        // write your code here
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(null == buildings || buildings.length == 0) return res;
        // Create a hashheap
        int TotalLength = 0;
        HashHeap h = new HashHeap("max");
        HashMap<Integer, ArrayList<Integer>> startmap = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> endmap = new HashMap<>();
        for (int i = 0; i < buildings.length; i ++){
            int start = buildings[i][0];
            int end = buildings[i][1];
            int height = buildings[i][2];
            if (!startmap.containsKey(start)) startmap.put(start, new ArrayList<Integer>());
            if (!endmap.containsKey(end)) endmap.put(end, new ArrayList<Integer>());
            startmap.get(start).add(height);
            endmap.get(end).add(height);
            TotalLength = Math.max(TotalLength, end);
        }


        ArrayList<Integer> subOutLine = new ArrayList<>();
        for (int j = 0; j <= TotalLength; j ++){
            if (startmap.containsKey(j)){
                ArrayList<Integer> heights = startmap.get(j);
                for (Integer p : heights) h.add(p);
            }
            if (endmap.containsKey(j)){
                ArrayList<Integer> heights = endmap.get(j);
                for (Integer p : heights) h.delete(p);
            }

            //When to terminate old outline: find a bigger outline, or there are no more buildings.
            if (subOutLine.size() > 0 && (h.empty() || (!h.empty() && h.peek() != subOutLine.get(2)))){
                subOutLine.set(1, j);
                res.add(subOutLine);
                subOutLine = new ArrayList<>();
            }
            //Otherwise, keep extending the outline.
            if (subOutLine.size() == 0){
                if (h.empty()) continue;
                subOutLine.add(j);
                subOutLine.add(j);
                subOutLine.add(h.peek());
            }else{
                subOutLine.set(1, j);
            }
        }
        return res;
    }
    public static void main(String[] args){
        int[][] b = {{1,10,3},{2,5,8},{7,9,8}};
        skyline s = new skyline();
        s.buildingOutline(b);
    }
}
