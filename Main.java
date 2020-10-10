import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        System.out.println("Кол-во людей младше 18 лет " + getUnderage(persons));
        System.out.println("Кол-во призывников " + getConscript(persons));
        System.out.println("Кол-во трудоспособных людей " + getWorkablePeople(persons));


    }

    //кол-во людей не достигших 18 лет
    public static long getUnderage(Collection<Person> persons) {
        return persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
    }

    //призывники
    public static List<String> getConscript(Collection<Person> persons) {
        return persons.stream()
                .filter(x -> x.getAge() > 17)
                .filter(x -> x.getAge() < 28)
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
    }

    //потенциально работоспособные люди
    public static List<String> getWorkablePeople(Collection<Person> persons) {
        return persons.stream()
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> x.getAge() > 17)
                .filter(x -> (x.getAge() < 61 && x.getSex().equals(Sex.WOMEN)) || (x.getAge() < 65 && x.getSex().equals(Sex.MAN)))
                .map(x -> x.getFamily())
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }
}
