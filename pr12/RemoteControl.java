package pr12;

import java.util.Stack;

// Пульт управления
public class RemoteControl {
    private Stack<Command> commandHistory;
    private Command[] buttons;

    public RemoteControl() {
        commandHistory = new Stack<>();
        buttons = new Command[5]; // 5 кнопок на пульте
    }

    public void setCommand(int slot, Command command) {
        if (slot >= 0 && slot < buttons.length) {
            buttons[slot] = command;
        }
    }

    public void pressButton(int slot) {
        if (slot >= 0 && slot < buttons.length && buttons[slot] != null) {
            buttons[slot].execute();
            commandHistory.push(buttons[slot]);
        } else {
            System.out.println("На этой кнопке нет команды!");
        }
    }

    public void pressUndo() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
        } else {
            System.out.println("Нет действий для отмены!");
        }
    }

    public void showStatus() {
        System.out.println("История команд (" + commandHistory.size() + " действий)");
    }
}
