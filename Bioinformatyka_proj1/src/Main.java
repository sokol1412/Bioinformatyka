
public class Main {

    public static void main(String[] args) {
        String regex = "[RK]-G-{EDRKHPCG}-[AGSCI]-[FY]-[LIVA]-x-[FYM]";
        String str1 = "SRSLKMRGQAFVIFKEVSSAT";
        String str2 = "KLTGRPRGVAFVRYNKREEAQ";
        String str3 = "VGCSVHKGFAFVQYVNERNAR";

        Match m1 = new Match(str1, regex);
        Match m2 = new Match(str2, regex);
        Match m3 = new Match(str3, regex);

        char[] result1 = m1.findMatch();
        char[] result2 = m2.findMatch();
        char[] result3 = m3.findMatch();

        System.out.println(str1);
        System.out.println(result1);
        System.out.println();

        System.out.println(str2);
        System.out.println(result2);
        System.out.println();

        System.out.println(str3);
        System.out.println(result3);
    }
}
