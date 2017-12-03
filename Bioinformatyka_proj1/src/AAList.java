class AAList {
    protected String list;

    public AAList(String s) {
        list = s;
    }

    public int count() {
        return list.length();
    }

    public String Find(int l, String str) {
        if (str.startsWith("[")) {
            for (int s = 1; s < str.length() - 1; s++) {
                if (list.charAt(l) == str.charAt(s)) return String.valueOf(list.charAt(l));
            }
        }
        if (str.startsWith("{")) {
            int check = 0;
            for (int s = 1; s < str.length() - 1; s++) {
                if (list.charAt(l) == str.charAt(s)) check = 1;
            }
            if (check == 0) return String.valueOf(list.charAt(l));
        }
        if (str.startsWith("x")) {
            if (str.length() == 1) {
                return String.valueOf(list.charAt(l));
            } else //x(3)
            {
                String result = "";
                int number = Integer.parseInt(String.valueOf(str.charAt(2)));
                for (int i = 0; i < number; i++)
                    result += String.valueOf(list.charAt(l + i));
                return result;
            }
        } else if (str.charAt(0) == list.charAt(l)) {
            if (str.length() == 1) //G
            {
                return String.valueOf(list.charAt(l));
            } else {
                if (str.contains(",")) //G(2,4)
                {
                    int counter = 0;
                    int minNumber = Integer.parseInt(String.valueOf(str.charAt(2)));
                    int maxNumber = Integer.parseInt(String.valueOf(str.charAt(4)));
                    String result = "";
                    for (int i = 0; i < maxNumber; i++) {
                        if (list.charAt(l + i) == str.charAt(0)) counter++;
                        result += str.charAt(0);
                    }
                    if (counter >= minNumber && counter <= maxNumber) return result;
                } else //G(4)
                {
                    int counter = 0;
                    int number = Integer.parseInt(String.valueOf(str.charAt(2)));
                    String result = "";
                    for (int i = 0; i < number; i++) {
                        if (list.charAt(l + i) == str.charAt(0)) counter++;
                        result += str.charAt(0);
                    }
                    if (counter == number) return result;
                }
            }
        }
        return "0";
    }
}

