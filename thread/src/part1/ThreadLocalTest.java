package part1;

public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();


        String s = stringThreadLocal.get();

        String s1 = stringThreadLocal.get();

        System.out.println(s);
        System.out.println(s1);

        stringThreadLocal.set("131231231");

        System.out.println(stringThreadLocal.get());
    }
}
