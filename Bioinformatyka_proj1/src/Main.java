
public class Main {

    private static char[] scanProsite(String str) {
        String regex = "[RK]-G-{EDRKHPCG}-[AGSCI]-[FY]-[LIVA]-x-[FYM]";
//        String regex = "[AC]-x-V-x(4)-{ED}";
//        String regex = "RGQAF";
        if (str.contains(regex))
            return regex.toCharArray();
        Matcher m = new Matcher(str, regex);
        char[] result = m.findMatch();
        boolean empty = true;
        for (int i = 0; i < result.length; i++) {
            if (result[i] != '\u0000') { //check if result contains empty cells
                empty = false;
                break;
            }
        }
        if (empty)
            return "No hit!".toCharArray();
        return result;
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
