/**
 * Created by Xiang on 10/4/2016.
 */
import java.util.*;
import java.util.concurrent.*;

class Person{
    private String name;
    private List<Person> followers;
    private String secret;

    Person(){}

    public void setName(String n){
        this.name = n;
    }

    public void setFollowers(List<Person> l){
        this.followers = l;
    }

    public void setSecret(String s){
        this.secret = s;
    }

    public String getName(){
        return this.name;
    }

    public String getSecret(){
        return this.secret;
    }

    public Iterable<Person> getFollowers(){
        return this.followers;
    }

}

public class ThreadDemo extends Thread{
    private Thread t;
    private static Set<Person> s = Collections.newSetFromMap(new ConcurrentHashMap<Person, Boolean>());
    private Person person;
    public static String secretHolder = "";
    private Set<Thread> threadpool;

    ThreadDemo(){
        this.threadpool = new HashSet<>();
    }

    public void PersonSetter(Person p){
        this.person = p;
    }

    public static synchronized void setSecretHolder(String s) {
        secretHolder = s;
    }

    public void run() {
        if (!secretHolder.equals("")) {
            for (Thread t : this.threadpool) t.interrupt();
        }
        if (person.getSecret().equals("Target")) {
            System.out.println("Found in " + person.getName());
            setSecretHolder(person.getName());
            return;
        }
        for (Person curp : person.getFollowers()) {
            if (!s.contains(curp)) {
                s.add(curp);
                ThreadDemo nt = new ThreadDemo();
                nt.PersonSetter(curp);
                threadpool.add(nt);
            }
        }
        for (Thread t : threadpool){
            t.start();
        }
        secretHolder = "";
    }

    public void start(){
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    public static void main(String[] args){
        String[] names = {"A","B","C","D","E","F","G","H"};
        String[] secret = {"H","F","D","S","Target","I","O","P"};
        String[][] following = {{"B","C","G"},{"D","A"}, {"A","F"}, {"G","C"},{"D"},{"D","H"},{"B"},{"C","B"}};
        HashMap<String, Person> hs = new HashMap<>();
        for (int i =0; i < names.length; i ++){
            Person tmp = new Person();
            tmp.setName(names[i]);
            tmp.setSecret(secret[i]);
            hs.put(names[i], tmp);
        }
        for (int i = 0; i < names.length; i ++){
            List<Person> l = new ArrayList<>();
            for (String s : following[i]){
                l.add(hs.get(s));
            }
            hs.get(names[i]).setFollowers(l);
        }
        ThreadDemo th = new ThreadDemo();
        th.PersonSetter(hs.get("H"));
        th.start();
        String res = ThreadDemo.secretHolder;
        System.out.println(res);
    }
}
