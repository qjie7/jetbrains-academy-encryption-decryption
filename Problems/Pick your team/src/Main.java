import java.util.Scanner;

class SelectionContext {

    private PersonSelectionAlgorithm algorithm;

    public void setAlgorithm(PersonSelectionAlgorithm algorithm) {
        // write your code here
        this.algorithm = algorithm;
    }

    public Person[] selectPersons(Person[] persons) {
        // write your code here
        return this.algorithm.select(persons);
    }
}

interface PersonSelectionAlgorithm {

    Person[] select(Person[] persons);
}

class TakePersonsWithStepAlgorithm implements PersonSelectionAlgorithm {

    private int step;

    public TakePersonsWithStepAlgorithm(int step) {
        // write your code here
        this.step = step;

    }

    @Override
    public Person[] select(Person[] persons) {
        // write your code here
        Person[] p = new Person[(int)Math.ceil( (persons.length - 1) / step + 1)];
        int count = 0 ;
        for (int i = 0; i < persons.length; i += step) {
            p[count++] = persons[i];
        }
        return p;
    }
}


class TakeLastPersonsAlgorithm implements PersonSelectionAlgorithm {

    private int count;


    public TakeLastPersonsAlgorithm(int count) {
        // write your code here
        this.count = count;

    }

    @Override
    public Person[] select(Person[] persons) {
        // write your code here
        Person[] p = new Person[count];
        int index = 0;
        for (int i = persons.length - count; i < persons.length; i++) {
            p[index] = persons[i];
            index++;
        }
        return p;
    }
}

class Person {

    String name;

    public Person(String name) {
        this.name = name;
    }
}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int count = Integer.parseInt(scanner.nextLine());
        final Person[] persons = new Person[count];

        for (int i = 0; i < count; i++) {
            persons[i] = new Person(scanner.nextLine());
        }

        final String[] configs = scanner.nextLine().split("\\s+");

        final PersonSelectionAlgorithm alg = create(configs[0], Integer.parseInt(configs[1]));
        SelectionContext ctx = new SelectionContext();
        ctx.setAlgorithm(alg);

        final Person[] selected = ctx.selectPersons(persons);
        for (Person p : selected) {
            System.out.println(p.name);
        }
    }

    public static PersonSelectionAlgorithm create(String algType, int param) {
        switch (algType) {
            case "STEP": {
                return new TakePersonsWithStepAlgorithm(param);
            }
            case "LAST": {
                return new TakeLastPersonsAlgorithm(param);
            }
            default: {
                throw new IllegalArgumentException("Unknown algorithm type " + algType);
            }
        }
    }
}