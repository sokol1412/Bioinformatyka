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
            int currIdx = 0;
            int idx = 0;
            int totalChecks = 0;
            String matching = "1";
            int matchIdx = i;

            while (matching != "0") //until all queries match
            {
                matching = "0";
                if (matchIdx < sequence.count())
                    matching = sequence.Find(matchIdx, queries.get(currIdx));
                if (matching.length() > 1) //in case of x(2,4), x(4), G(2,4), G(4)...
                {
                    for (int m = 0; m < matching.length(); m++) {
                        matched[currIdx + m] = matching.charAt(m);
                    }
                    idx += matching.length();
                    matchIdx += matching.length();
                } else if (matching.length() == 1 && matching != "0") //all other cases
                {
                    matched[idx] = matching.toCharArray()[0];
                    matchIdx++;
                    idx++;
                }
                totalChecks++;
                currIdx++;
                if (totalChecks == queries.size()) //all queries checked
                {
                    return matched;
                }
            }
        }
        return matched;
    }
}
