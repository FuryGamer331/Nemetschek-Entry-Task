package fg331;

public class Comparator4 implements java.util.Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] lines1 = o1.split("\\s");
        String[] lines2 = o2.split("\\s");

        if (Integer.parseInt(lines1[5]) > Integer.parseInt(lines2[5]))
            return -1;
        else if (Integer.parseInt(lines1[5]) < Integer.parseInt(lines2[5]))
            return 1;


        if (lines1[4].equals("Мъж") && lines2[4].equals("Жена"))
            return -1;
        else if (lines1[4].equals("Жена") && lines2[4].equals("Мъж"))
            return 1;

        return 0;
    }
}