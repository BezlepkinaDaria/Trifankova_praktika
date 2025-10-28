package pr14;

public class Main {
    public static void main(String[] args) {
        // Создаем источник событий
        LoginManager loginManager = new LoginManager();

        // Создаем слушателей
        LoginLogger logger = new LoginLogger();
        LoginNotifier notifier = new LoginNotifier();

        // Регистрируем слушателей у источника событий
        loginManager.addUserLoginListener(logger);
        loginManager.addUserLoginListener(notifier);

        System.out.println("=== Демонстрация системы авторизации ===\n");

        // Имитируем процесс авторизации
        loginManager.loginUser("ivan_petrov", "password123");

        System.out.println("\n--- Тест с другим пользователем ---");

        // Еще один тест
        loginManager.loginUser("anna_sidorova", "qwerty");

        System.out.println("\n--- Тест с удалением слушателя ---");

        // Удаляем один из слушателей и тестируем снова
        loginManager.removeUserLoginListener(notifier);
        loginManager.loginUser("test_user", "testpass");
    }
}