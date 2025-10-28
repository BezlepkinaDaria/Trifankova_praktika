// Создать систему управления светом «Умный дом», где каждая кнопка на пульте выполняет определённую команду.
//Использовать шаблон Command, добавить возможность отмены последнего действия.

package pr12;

// Демонстрационный класс
public class SmartHomeDemo {
    public static void main(String[] args) {
        // Создаем устройства
        Light livingRoomLight = new Light("Гостиная");
        Light kitchenLight = new Light("Кухня");

        // Создаем команды
        Command livingRoomOn = new LightOnCommand(livingRoomLight);
        Command livingRoomOff = new LightOffCommand(livingRoomLight);
        Command kitchenOn = new LightOnCommand(kitchenLight);
        Command kitchenOff = new LightOffCommand(kitchenLight);
        Command brightness50 = new BrightnessCommand(livingRoomLight, 50);
        Command brightness100 = new BrightnessCommand(livingRoomLight, 100);

        // Настраиваем пульт
        RemoteControl remote = new RemoteControl();
        remote.setCommand(0, livingRoomOn);
        remote.setCommand(1, livingRoomOff);
        remote.setCommand(2, kitchenOn);
        remote.setCommand(3, brightness50);
        remote.setCommand(4, brightness100);

        System.out.println("=== Управление умным домом ===");

        // Демонстрация работы
        remote.pressButton(0); // Включить гостиную
        remote.pressButton(3); // Яркость 50%
        remote.pressButton(2); // Включить кухню

        System.out.println("\n--- Статус после команд ---");
        livingRoomLight.printStatus();
        kitchenLight.printStatus();

        System.out.println("\n--- Отмена 2 действий ---");
        remote.pressUndo(); // Отмена включения кухни
        remote.pressUndo(); // Отмена яркости 50%

        System.out.println("\n--- Статус после отмены ---");
        livingRoomLight.printStatus();
        kitchenLight.printStatus();

        remote.showStatus();

        // Дополнительная демонстрация
        System.out.println("\n--- Дополнительные команды ---");
        remote.pressButton(4); // Яркость 100%
        remote.pressButton(1); // Выключить гостиную
        remote.pressUndo();    // Отмена выключения

        System.out.println("\n--- Финальный статус ---");
        livingRoomLight.printStatus();
    }
}