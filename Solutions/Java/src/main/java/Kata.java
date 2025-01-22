
public class Kata {
    public String hello(String name) {
        return String.format("Hello %s", name);
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
        Kata kata = new Kata();
    }
}
