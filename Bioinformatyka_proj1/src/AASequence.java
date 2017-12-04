class AASequence {
    protected String sequence;

    public AASequence(String s) {
        sequence = s;
    }

    public int count() {
        return sequence.length();
    }

    public String Find(int l, String str) {
        if (str.startsWith("[")) {
            for (int s = 1; s < str.length() - 1; s++) {
                if (sequence.charAt(l) == str.charAt(s)) return String.valueOf(sequence.charAt(l));
            }
        }
        if (str.startsWith("{")) {
            int check = 0;
            for (int s = 1; s < str.length() - 1; s++) {
                if (sequence.charAt(l) == str.charAt(s)) check = 1;
            }
            if (check == 0) return String.valueOf(sequence.charAt(l));
        }
        if (str.startsWith("x")) {
            if (str.length() == 1) { //x
                return String.valueOf(sequence.charAt(l));
            } else {
                if (str.contains(",")) //x(2,4)
                {
                    int counter = 0;
                    int minNumber = Integer.parseInt(String.valueOf(str.charAt(2)));
                    int maxNumber = Integer.parseInt(String.valueOf(str.charAt(4)));
                    String result = "";
                    for (int i = 0; i < maxNumber; i++) {
                        result += String.valueOf(sequence.charAt(l + i));
                        counter++;
                    }
                    if (counter >= minNumber && counter <= maxNumber) return result;
                } else //x(3)
                {
                    String result = "";
                    int number = Integer.parseInt(String.valueOf(str.charAt(2)));
                    for (int i = 0; i < number; i++)
                        result += String.valueOf(sequence.charAt(l + i));
                    return result;
                }
            }
        } else if (str.charAt(0) == sequence.charAt(l)) {
            if (str.length() == 1) //G
            {
                return String.valueOf(sequence.charAt(l));
            } else {
                if (str.contains(",")) //G(2,4)
                {
                    int counter = 0;
                    int minNumber = Integer.parseInt(String.valueOf(str.charAt(2)));
                    int maxNumber = Integer.parseInt(String.valueOf(str.charAt(4)));
                    String result = "";
                    for (int i = 0; i < maxNumber; i++) {
                        if (sequence.charAt(l + i) == str.charAt(0)) counter++;
                        result += str.charAt(0);
                    }
                    if (counter >= minNumber && counter <= maxNumber) return result;
                } else //G(4)
                {
                    int counter = 0;
                    int number = Integer.parseInt(String.valueOf(str.charAt(2)));
                    String result = "";
                    for (int i = 0; i < number; i++) {
                        if (sequence.charAt(l + i) == str.charAt(0)) counter++;
                        result += str.charAt(0);
                    }
                    if (counter == number) return result;
                }
            }
        }
        return "0";
    }
}
