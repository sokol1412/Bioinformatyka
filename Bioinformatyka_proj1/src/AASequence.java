class AASequence {
    protected String sequence;

    public AASequence(String s) {
        sequence = s;
    }

    public int count() {
        return sequence.length();
    }

    // idx - index of character in sequence
    // query - part of regex
    public String Find(int idx, String query) {
        if (query.startsWith("[")) {
            for (int s = 1; s < query.length() - 1; s++) {
                if (sequence.charAt(idx) == query.charAt(s)) return String.valueOf(sequence.charAt(idx));
            }
        }
        if (query.startsWith("{")) {
            int check = 0;
            for (int s = 1; s < query.length() - 1; s++) {
                if (sequence.charAt(idx) == query.charAt(s)) {
                    check = 1;
                    break;
                }
            }
            if (check == 0) return String.valueOf(sequence.charAt(idx));
        }
        if (query.startsWith("x")) {
            if (query.length() == 1) { //x
                return String.valueOf(sequence.charAt(idx));
            } else {
                if (query.contains(",")) //x(2,4)
                {
                    int counter = 0;
                    int minNumber = Integer.parseInt(String.valueOf(query.charAt(2)));
                    int maxNumber = Integer.parseInt(String.valueOf(query.charAt(4)));
                    String result = "";
                    for (int i = 0; i < maxNumber; i++) {
                        result += String.valueOf(sequence.charAt(idx + i));
                        counter++;
                    }
                    if (counter >= minNumber && counter <= maxNumber) return result;
                } else //x(3)
                {
                    String result = "";
                    int number = Integer.parseInt(String.valueOf(query.charAt(2)));
                    for (int i = 0; i < number; i++)
                        result += String.valueOf(sequence.charAt(idx + i));
                    return result;
                }
            }
        } else if (query.charAt(0) == sequence.charAt(idx)) {
            if (query.length() == 1) //G
            {
                return String.valueOf(sequence.charAt(idx));
            } else {
                if (query.contains(",")) //G(2,4)
                {
                    int counter = 0;
                    int minNumber = Integer.parseInt(String.valueOf(query.charAt(2)));
                    int maxNumber = Integer.parseInt(String.valueOf(query.charAt(4)));
                    String result = "";
                    for (int i = 0; i < maxNumber; i++) {
                        if (sequence.charAt(idx + i) == query.charAt(0)) counter++;
                        result += query.charAt(0);
                    }
                    if (counter >= minNumber && counter <= maxNumber) return result;
                } else //G(4)
                {
                    int counter = 0;
                    int number = Integer.parseInt(String.valueOf(query.charAt(2)));
                    String result = "";
                    for (int i = 0; i < number; i++) {
                        if (sequence.charAt(idx + i) == query.charAt(0)) counter++;
                        result += query.charAt(0);
                    }
                    if (counter == number) return result;
                }
            }
        }
        return "0";
    }
}
