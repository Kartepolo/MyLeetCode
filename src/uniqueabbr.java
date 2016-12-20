/**
 * Created by Xiang on 11/15/2016.
 */
import sun.text.normalizer.CharacterIteratorWrapper;
import sun.text.normalizer.Trie;

import java.util.*;
public class uniqueabbr {
    class TrieNode{
        boolean isString;
        String prefix;
        Map<Character, TrieNode> subtree;
        TrieNode(String p){
            isString = false;
            subtree = new HashMap<Character, TrieNode>();
            prefix = p;
        }
    }

    class Pair{
        String abbr;
        int size;
        Pair(String s, int size){
            this.abbr = s;
            this.size = size;
        }
    }
    class TrieTree {
        TrieNode root;

        public TrieTree() {
            root = new TrieNode("");
        }

        public void insert(String s) {
            boolean flag = false;
            TrieNode now = root;
            int i = 0;
            while (i < s.length()) {
                char c = s.charAt(i);
                if (!now.subtree.containsKey(c)) {
                    // change every prefix in the subtree
                    now.subtree.put(c, new TrieNode(s.substring(0, i)));
                }
                now = now.subtree.get(c);
                i++;
            }
            now.isString = true;


        }
        public Pair getabbr(String s, TrieNode root){
            if (root == null) return new Pair("" ,0);
            TrieNode now = root;
            int i = 0;
            while (i < s.length()){
                char c = s.charAt(i);
                now = now.subtree.get(c);
                if (now.subtree.size() == 1){
                    if (i == 0) return new Pair(s.length() + "", 1);
                    Pair p1 = new Pair(now.prefix + Character.toString(c) + (s.length() - i), now.prefix.length() + 2);
                    Pair latter = this.getabbr(s.substring(i+1, s.length()), now);
                    Pair p2 = new Pair(now.prefix.length() + Character.toString(c) + latter.abbr, 2 + latter.size);
                    if (p1.size <= p2.size) return p1;
                    else return p2;
                }
                i++;
            }
            return new Pair(s, s.length());
        }
    }
    List<String> findUniqueAbbreviations( List<String> dict){
        TrieTree t = new TrieTree();
        for (String s : dict){
            t.insert(s);
        }
        List<String> abbr = new ArrayList<String>();
        for (String s : dict){
            Pair p = t.getabbr(s, t.root);
            abbr.add(p.abbr);
        }
        return abbr;
    }

    public static void main(String[] args){
        uniqueabbr u = new uniqueabbr();
        List<String> test = new ArrayList<>();
        test.add("dog");
        test.add("localization");
        test.add("accessibility");
        test.add("automatically");
        u.findUniqueAbbreviations(test);
    }
}
