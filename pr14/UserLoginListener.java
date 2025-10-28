package pr14;

import java.util.EventListener;

// Интерфейс слушателя для событий авторизации
public interface UserLoginListener extends EventListener {
    void userLoggedIn(UserLoginEvent event); // реакция слушателя на событие
}
