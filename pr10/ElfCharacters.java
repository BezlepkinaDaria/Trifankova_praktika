package pr10;

// Эльфийский воин
class ElfWarrior extends Character implements Warrior {
    public ElfWarrior() {
        this.race = "Эльф";
        this.health = 90;
        this.damage = 25;
        this.specialAbility = "Стрельба из лука";
    }

    @Override
    public void attack() {
        System.out.println("Эльфийский воин стреляет из лука!");
    }

    @Override
    public void displayStats() {
        System.out.println("ЭЛЬФИЙСКИЙ ВОИН");
        super.displayStats();
    }
}

// Эльфийский маг
class ElfMage extends Character implements Mage {
    public ElfMage() {
        this.race = "Эльф";
        this.health = 70;
        this.damage = 40;
        this.specialAbility = "Магия природы";
    }

    @Override
    public void castSpell() {
        System.out.println("Эльфийский маг использует магию природы!");
    }

    @Override
    public void displayStats() {
        System.out.println("ЭЛЬФИЙСКИЙ МАГ");
        super.displayStats();
    }
}

// Фабрика эльфов
class ElfFactory implements CharacterFactory {
    @Override
    public Warrior createWarrior() {
        return new ElfWarrior();
    }

    @Override
    public Mage createMage() {
        return new ElfMage();
    }
}