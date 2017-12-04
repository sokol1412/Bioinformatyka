import java.util.Arrays;
import java.util.List;

class Matcher {
    protected AASequence sequence;
    protected char[] matched;
    protected List<String> queries;

    public Matcher(String aminoAcids, String regex) {
        sequence = new AASequence(aminoAcids);
        queries = Arrays.asList(regex.split("-"));
        matched = new char[20];
    }

    public char[] findMatch() {
        for (int i = 0; i < sequence.count(); i++) {
            int q = 0;
            int idx = 0;
            int totalChecks = 0;
            String matching = "1";
            int l = i;

            while (matching != "0") //until all queries match
            {
                matching = "0";
                if (l < sequence.count())
                    matching = sequence.Find(l, queries.get(q));
                if (matching.length() > 1) //in case of x(2,4), x(4), G(2,4), G(4)...
                {
                    for (int m = 0; m < matching.length(); m++) {
                        matched[q + m] = matching.charAt(m);
                        idx++;
                    }
                    l += matching.length();
                } else if (matching.length() == 1 && matching != "0") //all other cases
                {
                    matched[idx] = matching.toCharArray()[0];
                    l++;
                    idx++;
                }
                totalChecks++;
                q++;
                if (totalChecks == queries.size()) //all queries checked
                {
                    return matched;
                }
            }
        }
        return matched;
    }
}
