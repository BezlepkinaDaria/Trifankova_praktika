package pr12;

// Интерфейс команды
public interface Command {
    void execute();
    void undo();
}

// Класс света
class Light {
    private String location;
    private boolean isOn;
    private int brightness;

    public Light(String location) {
        this.location = location;
        this.isOn = false;
        this.brightness = 100;
    }

    public void turnOn() {
        this.isOn = true;
        System.out.println(location + " свет включен");
    }

    public void turnOff() {
        this.isOn = false;
        System.out.println(location + " свет выключен");
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
        System.out.println(location + " яркость установлена на " + brightness + "%");
    }

    public boolean isOn() {
        return isOn;
    }

    public int getBrightness() {
        return brightness;
    }

    public void printStatus() {
        System.out.println(location + " свет: " + (isOn ? "ВКЛ" : "ВЫКЛ") +
                ", яркость: " + brightness + "%");
    }
}

// Команда включения света
class LightOnCommand implements Command {                                     // команда
    private Light light;
    private int previousBrightness;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        previousBrightness = light.getBrightness();
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
        light.setBrightness(previousBrightness);
    }
}

// Команда выключения света
class LightOffCommand implements Command {
    private Light light;
    private boolean wasOn;
    private int previousBrightness;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        wasOn = light.isOn();
        previousBrightness = light.getBrightness();
        light.turnOff();
    }

    @Override
    public void undo() {
        if (wasOn) {
            light.turnOn();
            light.setBrightness(previousBrightness);
        }
    }
}

// Команда изменения яркости
class BrightnessCommand implements Command {
    private Light light;
    private int newBrightness;
    private int previousBrightness;

    public BrightnessCommand(Light light, int brightness) {
        this.light = light;
        this.newBrightness = brightness;
    }

    @Override
    public void execute() {
        previousBrightness = light.getBrightness();
        light.setBrightness(newBrightness);
    }

    @Override
    public void undo() {
        light.setBrightness(previousBrightness);
    }
}
