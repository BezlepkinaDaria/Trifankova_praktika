package pr14;

import java.util.EventObject;

// Класс пользовательского события для авторизации
public class UserLoginEvent extends EventObject {
    private String username;
// создает новое событие
    public UserLoginEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}