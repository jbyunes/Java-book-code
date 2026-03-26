package chapter.streams.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public record Person(String name, String department, List<Integer> salaries) {
    public int salary() { return salaries.getLast(); }   
    public final static String[] names = {"Joe", "Jack", "William", "Averell",
            "Amadou", "Boubacar", "Cyril", "Djibril", "Eddy", "Fabien",
            "Gaston", "Hector", "Ibrahim", "Jules", "Kader", "Lamine",
            "Mamadou", "Nabil", "Olivier", "Pascal", "Quentin", "Rachid",
            "Sylvain", "Thierry", "Ulysse", "Vincent", "Willy", "Xavier",
            "Yannick", "Zinedine",
            "Sophie", "Alice", "Julie", "Marie", "Aline", "Catherine",
            "Sylvie", "Nathalie", "Isabelle", "Caroline", "Emilie",
            "Samira", "Fatoumata", "Aminata", "Kadiatou", "Mariam",
            "Aicha", "Djenaba", "Kadi", "Nene", "Oumou", "Ramatoulaye"};
    public final static List<String> departments = List.of("mail", "financial", "canteen",
            "cleaning", "security", "IT", "management", "HR", "legal",
            "marketing", "sales", "production", "logistics", "maintenance",
            "quality", "R&D", "purchasing", "accounting", "communication");

    public static Person randomEmployee(Random random) {
        int n = random.nextInt(1, 10);
        var salaries = new ArrayList<Integer>();
        for (int i=0; i<n; i++) {
            salaries.add(random.nextInt(10,50)*100);
        }
        salaries.sort(Integer::compare);
        String name = names[random.nextInt(names.length)];
        String department = departments.get(random.nextInt(departments.size()));
        return new Person(name, department, salaries);
    }

    // deterministic list of employees
    public static List<Person> randomEmployees(int n) {
        Random random = new Random(1);
        var persons = new LinkedList<Person>();
        for (int i=0; i<n; i++) {
            persons.add(randomEmployee(random));
        }
        return persons;
    }
    // deterministic iterator of employees
    public static Iterator<Person> iteratorRandomEmployees() {
        return new Iterator<>() {
            private Supplier<Person> supplier = supplierRandomEmployees();
            @Override
            public boolean hasNext() { return true; }
            @Override
            public Person next() { return supplier.get(); }
        };
    }
    // deterministic supplier of employees
    public static Supplier<Person> supplierRandomEmployees() {
        var r = new Random(1);
        return () -> randomEmployee(r);
    }

    public static List<Person> employees = List
        .of(new Person("Joe", "financial", List.of(1000, 3500, 2000)),
            new Person("Jack", "mail", List.of(1000, 1000, 1200)),
            new Person("William", "mail", List.of(1200, 1350, 1500)),
            new Person("Averell", "canteen", List.of(1200, 1200, 1200)));
}