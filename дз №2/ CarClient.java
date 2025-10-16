// Интерфейсы для семейства продуктов
interface Sedan {
    void drive();
}

interface SUV {
    void drive();
}

// Конкретные продукты для эконом-класса
class EconomySedan implements Sedan {
    @Override
    public void drive() {
        System.out.println("Driving economy sedan");
    }
}

class EconomySUV implements SUV {
    @Override
    public void drive() {
        System.out.println("Driving economy SUV");
    }
}

// Конкретные продукты для люкс-класса
class LuxurySedan implements Sedan {
    @Override
    public void drive() {
        System.out.println("Driving luxury sedan");
    }
}

class LuxurySUV implements SUV {
    @Override
    public void drive() {
        System.out.println("Driving luxury SUV");
    }
}

// Абстрактная фабрика
interface CarFactory {
    Sedan createSedan();
    SUV createSUV();
}

// Конкретные фабрики
class EconomyCarFactory implements CarFactory {
    @Override
    public Sedan createSedan() {
        return new EconomySedan();
    }

    @Override
    public SUV createSUV() {
        return new EconomySUV();
    }
}

class LuxuryCarFactory implements CarFactory {
    @Override
    public Sedan createSedan() {
        return new LuxurySedan();
    }

    @Override
    public SUV createSUV() {
        return new LuxurySUV();
    }
}

// Клиентский код
class CarClient {
    private Sedan sedan;
    private SUV suv;

    public CarClient(CarFactory factory) {
        sedan = factory.createSedan();
        suv = factory.createSUV();
    }

    public void driveAllCars() {
        sedan.drive();
        suv.drive();
    }

    public static void main(String[] args) {
        // Создаем эконом-автомобили
        CarFactory economyFactory = new EconomyCarFactory();
        CarClient economyClient = new CarClient(economyFactory);
        System.out.println("Economy cars:");
        economyClient.driveAllCars();

        // Создаем люкс-автомобили
        CarFactory luxuryFactory = new LuxuryCarFactory();
        CarClient luxuryClient = new CarClient(luxuryFactory);
        System.out.println("\nLuxury cars:");
        luxuryClient.driveAllCars();
    }
}
