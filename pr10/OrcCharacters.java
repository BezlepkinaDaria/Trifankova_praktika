package pr10;

// Орочий воин
class OrcWarrior extends Character implements Warrior {
    public OrcWarrior() {
        this.race = "Орк";
        this.health = 120;
        this.damage = 35;
        this.specialAbility = "Берсерк";
    }

    @Override
    public void attack() {
        System.out.println("Орочий воин яростно атакует топором!");
    }

    @Override
    public void displayStats() {
        System.out.println("ОРОЧИЙ ВОИН");
        super.displayStats();
    }
}

// Орочий маг
class OrcMage extends Character implements Mage {
    public OrcMage() {
        this.race = "Орк";
        this.health = 80;
        this.damage = 45;
        this.specialAbility = "Шаманская магия";
    }

    @Override
    public void castSpell() {
        System.out.println("Орочий маг призывает духов предков!");
    }

    @Override
    public void displayStats() {
        System.out.println("ОРОЧИЙ МАГ");
        super.displayStats();
    }
}

// Фабрика орков
class OrcFactory implements CharacterFactory {
    @Override
    public Warrior createWarrior() {
        return new OrcWarrior();
    }

    @Override
    public Mage createMage() {
        return new OrcMage();
    }
}