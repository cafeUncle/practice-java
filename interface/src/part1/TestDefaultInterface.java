package part1;

public interface TestDefaultInterface {
    default String hello() {
        System.out.println("Hello default method in interface");
        return "Hello default method in interface";
    }

    default String hello2() {
        System.out.println("Hello default method in interface2");
        return "Hello default method in interface2";
    }
}
