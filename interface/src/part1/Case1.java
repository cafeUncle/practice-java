package part1;

public class Case1 {
    public static void main(String[] args) {
        DemoExtend demoExtend = new DemoExtend();

        demoExtend.hello();

        TestDefaultInterface testDefaultInterface = new TestDefaultInterface() {};

        testDefaultInterface.hello();

        TestDefaultInterface testDefaultInterface1 = new DemoExtend();

        testDefaultInterface1.hello();

        demoExtend.hello2();
        testDefaultInterface.hello2();
        testDefaultInterface1.hello2();
    }
}
