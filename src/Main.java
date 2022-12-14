import javax.sound.midi.Soundbank;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        //Загрузка списка сотрудников из файла
        ArrayList<Employee> staff = loadStaffFromFile();

        //Сортировка по наименованию
//        Collections.sort(staff, new Comparator<Employee>() {
//            @Override
//            public int compare(Employee o1, Employee o2) {
//                return o1.getName().compareTo(o2.getName());
//            }
//        });
        //Сортировка по наименованию (Лямбда выражения)
//        Collections.sort(staff, (o1, o2) -> o1.getName().compareTo(o2.getName()));

        //Сортировка по зарплате
//        Collections.sort(staff, new Comparator<Employee>() {
//            @Override
//            public int compare(Employee o1, Employee o2) {
//                return o1.getName().compareTo(o2.getName());
//            }
//        });
//Сортировка по зарплате (Лямбда выражения)
//        Collections.sort(staff, (o1, o2) -> o1.getSalary().compareTo(o2.getSalary()));

//        Сортировка по наименованию и зарплате
//        Collections.sort(staff, new Comparator<Employee>() {
//            @Override
//            public int compare(Employee o1, Employee o2) {
//                int name = o1.getName().compareTo(o2.getName());
//                if (name != 0) {
//                    return name;
//                }
//                return o1.getSalary().compareTo(o2.getSalary());
//            }
//        });

//        Сортировка по наименованию и зарплате (Лямбда выражения)
        Collections.sort(staff, ((o1, o2) -> {
            int name = o1.getName().compareTo(o2.getName());
            if (name != 0) {
                return name;
            }
            return o1.getSalary().compareTo(o2.getSalary());
        }));

        for (Employee employee : staff) {
            System.out.println(employee);
        }

    }

    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (String line : lines) {
                String[] fragments = line.split("\t");
                if (fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(fragments[0], Integer.parseInt(fragments[1]), (new SimpleDateFormat(dateFormat)).parse(fragments[2])));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}