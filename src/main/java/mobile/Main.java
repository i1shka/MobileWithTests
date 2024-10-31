package mobile;

import mobile.Operator;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        String file = "input.txt";
//        читаем данные из файла
        List<String> inptData = readFile(file);

//        создаем переменные
        int x = Integer.parseInt(inptData.get(inptData.size() - 2));       //координата X абонента
        int y = Integer.parseInt(inptData.get(inptData.size() - 1));       //координата Y абонента
        inptData = inptData.subList(0, inptData.size() - 2);
        Operator op = getOperator(inptData);                            //координаты базовок

//      рассчитываем доступность каждой базовки
        Map<String, Integer> res = op.checkCoverage(x, y);

//        Записываем результат в файл
        writing(res);
    }

    static List<String> readFile(String file) {
        List<String> inptData = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(file));
            scan.nextLine();
            while (scan.hasNext()) {
                inptData.add(scan.next());
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return inptData;
    }

    public static Operator getOperator(List<String> inptData) {
        Operator op = new Operator();
        for (int i = 0; i < inptData.size(); i += 4) {
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(Integer.parseInt(inptData.get(i + 1)));
            arr.add(Integer.parseInt(inptData.get(i + 2)));
            arr.add(Integer.parseInt(inptData.get(i + 3)));
            new Operator(inptData.get(i), arr);
        }
        return op;
    }

    private static void writing(Map<String, Integer> res) {
        try {
            FileWriter writer = new FileWriter("output.txt", false);
            writer.write(Integer.toString(res.size()));
            writer.write("\n");
            for (Map.Entry<String, Integer> e : res.entrySet()) {
                writer.write(e.getKey() + " " + e.getValue() + "\n");
            }
            writer.close();
            System.out.println("output.txt created");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
