package pr10;

// Интерфейс для воина
interface Warrior {
    void attack();
    void displayStats();
    String getRace();
}

// Интерфейс для мага
interface Mage {
    void castSpell();
    void displayStats();
    String getRace();
}

// Абстрактная фабрика персонажей
interface CharacterFactory {
    Warrior createWarrior();
    Mage createMage();
}

// Базовый класс для персонажей
abstract class Character {
    protected String race;
    protected int health;
    protected int damage;
    protected String specialAbility;

    public void displayStats() {
        System.out.println("Раса: " + race);
        System.out.println("Здоровье: " + health);
        System.out.println("Урон: " + damage);
        System.out.println("Особое умение: " + specialAbility);
    }

    public String getRace() {
        return race;
    }
}