import java.util.HashSet;
import java.util.Iterator;

public class Ex1_3 {
    public static void main(String[] args) {
        DuplicateEliminator.run(args);
    }

    public static class DuplicateEliminator {
        public static void run(String[] args){
            HashSet<String> set = new HashSet<>();

            for (String s : args){
                set.add(s);
            }

            Iterator<String> it = set.iterator();
            while (it.hasNext()){
                System.out.println(it.next());
            }
        }
    }
}