package fg331;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Main extends JFrame {

    private JLabel label = new JLabel("Изберете число от 1 до 4 за сортиране:");
    private JLabel label1 = new JLabel("1: сортиране низходящо по стаж и низходящо по възраст");
    private JLabel label2 = new JLabel("2: сортиране възходящо по стаж и възходящо по възраст");
    private JLabel label3 = new JLabel("3: сортиране низходящо по стаж и по пол Жена");
    private JLabel label4 = new JLabel("4: сортиране низходящо по стаж и по пол Мъж");
    private JLabel labelInc = new JLabel("Грешно въведено число");
    private JTextField textField = new JTextField();
    private JButton sort = new JButton("Sort");
    private BufferedImage HR;
    private JLabel HRLabel;

    private String whole = "";
    private String colleague = "";
    private ArrayList<String> arrayList = new ArrayList<>();

    // страничен код изпозлван за създаване на входната информация от файла
    // Колежката в тази програма е кандидат 12 Галя Игнатова

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        try {
            HR = ImageIO.read(new File("res/images/HR.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        HRLabel = new JLabel(new ImageIcon(HR));
        fileReader();
        screenSetter();

        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (textField.getText()) {
                    case "1":
                        labelInc.setVisible(false);
                        sortByOne();
                        break;
                    case "2":
                        labelInc.setVisible(false);
                        sortByTwo();
                        break;
                    case "3":
                        labelInc.setVisible(false);
                        sortByThree();
                        break;
                    case "4":
                        labelInc.setVisible(false);
                        sortByFour();
                        break;
                    default:
                        labelInc.setVisible(true);
                }
            }
        });
    }

    private void fileReader() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String string;
            BufferedReader reader = new BufferedReader(new FileReader("res/txt/names.txt"));
            while ((string = reader.readLine()) != null) {
                String[] bits = string.split("\\s+");

                if (bits[0].equals("12")) {
                    colleague = string;
                } else {
                    stringBuilder.append(string).append("\n");
                }

//            String gender = "";
//                int rand = (int) (Math.random() * (27 - 18) + 18);
//                int rand1 = (int) (Math.random() * (27 - 18) + 18);
//                char[] chars = bits[2].toCharArray();
//                int work = rand - rand1;
//                if (work < 0)
//                    work = 0;
//                if (chars[chars.length - 1] == 'а')
//                    gender = "Жена";
//                else
//                    gender = "Мъж";
//                if (bits[0].equals("100"))
//                    stringBuilder.append(string).append(" ").append(rand).append(" ").append(gender).append(" ").append(work);
//                else {
//                    stringBuilder.append(string).append(" ").append(rand).append(" ").append(gender).append(" ").append(work).append("\n");
//                }
            }
            whole = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void screenSetter() {

        //настройки на JFrame
        setTitle("Candidates");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setMinimumSize(new Dimension(500, 500));
        setVisible(true);

        //създаване на JPanel и настройки
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // добавяне на компоненти към JPanel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        panel.add(HRLabel);

        constraints.gridheight = 1;
        constraints.gridy = 4;
        panel.add(label, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(label1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(label2, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(label3, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        panel.add(label4, constraints);

        constraints.gridx = 0;
        constraints.gridy = 9;
        panel.add(textField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 10;
        panel.add(sort, constraints);

        constraints.gridx = 0;
        constraints.gridy = 11;
        labelInc.setVisible(false);
        panel.add(labelInc, constraints);

        // добавяне на JPanel към JFrame
        add(panel);

        pack();
        setLocationRelativeTo(null);
    }

    private void sortByOne() {
        ArrayListSetter();

        arrayList.sort(new Comparator1());
        fileWriter(arrayList);
        ArrayListReset();
    }

    private void sortByTwo() {
        ArrayListSetter();

        arrayList.sort(new Comparator2());
        fileWriter(arrayList);
        ArrayListReset();
    }

    private void sortByThree() {
        ArrayListSetter();

        arrayList.sort(new Comparator3());
        fileWriter(arrayList);
    }

    private void sortByFour() {
        ArrayListSetter();

        arrayList.sort(new Comparator4());
        fileWriter(arrayList);
        ArrayListReset();
    }

    private void ArrayListSetter() {
        String[] lines = whole.split("\\s");

        for (int i = 0; i < lines.length - 1; i = i + 7) {
            arrayList.add(lines[i] + " " + lines[i + 1] + " " + lines[i + 2] + " " + lines[i + 3] + " " + lines[i + 4] + " " + lines[i + 5] + " " + lines[i + 6]);
        }
    }

    private void ArrayListReset() {
        arrayList = new ArrayList<String>();
    }

    private void fileWriter(ArrayList<String> arrayList) {
        PrintStream printStream = null;
        try {
            printStream = new PrintStream(new FileOutputStream("res/txt/output.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int insertion = 4;
        for (int i = 0; i < arrayList.size(); i++) {
            String[] bits = arrayList.get(i).split("\\s");
            if (i == insertion) {
                String[] bitsInsertion = colleague.split("\\s");
                for (int j = 0; j < 5; j++) {
                    if (j == 3) continue;
                    printStream.print(bitsInsertion[j] + " ");
                }
                printStream.print("\n");
            }
            for (int j = 0; j < 5; j++) {
                if (j == 3) continue;
                printStream.print(bits[j] + " ");
            }
            if (i + 1 < arrayList.size())
                printStream.print("\n");
        }
    }
}