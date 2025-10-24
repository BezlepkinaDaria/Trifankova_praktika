// создает бъекты классов Warrior и Mage с уникальными характеристиками для каждой расы.
package pr10;

public class Main1 {
    public static void main(String[] args) {
        System.out.println("=== ФАБРИКА ИГРОВЫХ ПЕРСОНАЖЕЙ ===");

        // Создаем фабрики для разных рас
        CharacterFactory elfFactory = new ElfFactory();
        CharacterFactory orcFactory = new OrcFactory();

        System.out.println("=== СОЗДАНИЕ ЭЛЬФИЙСКИХ ПЕРСОНАЖЕЙ ===");

        // Создаем эльфийских персонажей
        Warrior elfWarrior = elfFactory.createWarrior();
        Mage elfMage = elfFactory.createMage();

        System.out.println("--- Эльфийский воин ---");
        elfWarrior.displayStats();
        elfWarrior.attack();

        System.out.println("--- Эльфийский маг ---");
        elfMage.displayStats();
        elfMage.castSpell();

        System.out.println("=== СОЗДАНИЕ ОРОЧЬИХ ПЕРСОНАЖЕЙ ===");

        // Создаем орочьих персонажей
        Warrior orcWarrior = orcFactory.createWarrior();
        Mage orcMage = orcFactory.createMage();

        System.out.println("--- Орочий воин ---");
        orcWarrior.displayStats();
        orcWarrior.attack();

        System.out.println("--- Орочий маг ---");
        orcMage.displayStats();
        orcMage.castSpell();

        // Демонстрация работы с разными фабриками
        System.out.println("=== СРАВНЕНИЕ РАС ===");

        System.out.println("Сравнение воинов:");
        compareWarriors(elfWarrior, orcWarrior);

        System.out.println("Сравнение магов:");
        compareMages(elfMage, orcMage);

        // Демонстрация создания армии
        System.out.println("=== СОЗДАНИЕ АРМИЙ ===");

        System.out.println("Эльфийская армия:");
        createArmy(elfFactory, 2, 1);

        System.out.println("Орочья армия:");
        createArmy(orcFactory, 1, 2);
    }

    // Метод для сравнения воинов
    public static void compareWarriors(Warrior w1, Warrior w2) {
        System.out.println(w1.getRace() + " воин vs " + w2.getRace() + " воин");
        w1.displayStats();
        System.out.println("---");
        w2.displayStats();
    }

    // Метод для сравнения магов
    public static void compareMages(Mage m1, Mage m2) {
        System.out.println(m1.getRace() + " маг vs " + m2.getRace() + " маг");
        m1.displayStats();
        System.out.println("---");
        m2.displayStats();
    }

    // Метод для создания армии
    public static void createArmy(CharacterFactory factory, int warriorsCount, int magesCount) {
        System.out.println("Создание армии:");

        for (int i = 1; i <= warriorsCount; i++) {
            Warrior warrior = factory.createWarrior();
            System.out.println("Воин " + i + ":");
            warrior.displayStats();
            warrior.attack();
        }

        for (int i = 1; i <= magesCount; i++) {
            Mage mage = factory.createMage();
            System.out.println("Маг " + i + ":");
            mage.displayStats();
            mage.castSpell();
        }
    }
}