package pr14;

// Первый слушатель - для логирования
public class LoginLogger implements UserLoginListener {
    @Override
    public void userLoggedIn(UserLoginEvent event) {
        System.out.println("[LOG] Пользователь '" + event.getUsername() + "' успешно авторизовался в системе");
    }
}
