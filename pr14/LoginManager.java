package pr14;

import java.util.ArrayList;
import java.util.List;

// источник событий
public class LoginManager {
    private List<UserLoginListener> listeners = new ArrayList<>();

    // Метод для регистрации слушателей
    public void addUserLoginListener(UserLoginListener listener) {
        listeners.add(listener);
    }

    // Метод для удаления слушателей
    public void removeUserLoginListener(UserLoginListener listener) {
        listeners.remove(listener);
    }

    // Метод, который имитирует процесс авторизации и генерирует событие
    public void loginUser(String username, String password) {
        // Здесь могла бы быть реальная логика проверки пароля
        System.out.println("Проверка авторизации для пользователя: " + username);

        // Имитируем успешную авторизацию
        boolean loginSuccessful = true; // В реальном приложении здесь была бы проверка

        if (loginSuccessful) {
            // Создаем событие
            UserLoginEvent event = new UserLoginEvent(this, username);
            // Уведомляем всех слушателей
            notifyListeners(event);
        }
    }

    // Приватный метод для уведомления всех зарегистрированных слушателей
    private void notifyListeners(UserLoginEvent event) {
        for (UserLoginListener listener : listeners) {
            listener.userLoggedIn(event);
        }
    }
}
