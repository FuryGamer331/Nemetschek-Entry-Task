package fg331;

public class Comparator2 implements java.util.Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] lines1 = o1.split("\\s");
        String[] lines2 = o2.split("\\s");

        if (Integer.parseInt(lines2[5]) > Integer.parseInt(lines1[5]))
            return -1;
        else if (Integer.parseInt(lines2[5]) < Integer.parseInt(lines1[5]))
            return 1;

        if (Integer.parseInt(lines2[3]) > Integer.parseInt(lines1[3]))
            return -1;
        else if (Integer.parseInt(lines2[3]) < Integer.parseInt(lines1[3]))
            return 1;

        return 0;
    }
}