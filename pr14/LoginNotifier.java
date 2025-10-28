package pr14;

// Второй слушатель - для вывода сообщения пользователю
public class LoginNotifier implements UserLoginListener {
    @Override
    public void userLoggedIn(UserLoginEvent event) {
        System.out.println("Пользователь вошёл в систему");
        System.out.println("Добро пожаловать, " + event.getUsername() + "!");
    }
}
