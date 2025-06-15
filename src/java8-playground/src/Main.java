import sun.font.TrueTypeFont;

public class Main {
    public static void main(String[] args) {
        String s = "Chirp!";
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        System.out.println(sb);
    }
}