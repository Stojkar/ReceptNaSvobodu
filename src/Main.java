public class Main {
    public static void main(String[] args) {


        Hra hra = new Hra();
        hra.test();
        System.out.println(hra);
        hra.getHrac().posun("zapad");
        System.out.println(hra.getHrac());


    }
}