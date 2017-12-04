
public class Main {

    private static char[] scanProsite(String str) {
        String regex = "[RK]-G-{EDRKHPCG}-[AGSCI]-[FY]-[LIVA]-x-[FYM]";
        //String regex = "[AC]-x-V-x(4)-{ED}";
        //String regex = "RGQAFVIFKE";
        if (str.contains(regex))
            return regex.toCharArray();
        Match m = new Match(str, regex);
        return new Match(str, regex).findMatch();
    }

    public static void main(String[] args) {
        String str1 = "SRSLKMRGQAFVIFKEVSSAT";
        String str2 = "KLTGRPRGVAFVRYNKREEAQ";
        String str3 = "VGCSVHKGFAFVQYVNERNAR";

        System.out.println(str1);
        System.out.println(scanProsite(str1));
        System.out.println();

        System.out.println(str2);
        System.out.println(scanProsite(str2));
        System.out.println();

        System.out.println(str3);
        System.out.println(scanProsite(str3));
    }
}
