import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MoodColorApp {
    private JFrame frame;
    private JLabel moodLabel;
    private JPanel buttonPanel;

    public MoodColorApp() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Создаем главное окно
        frame = new JFrame("Цвет настроения - Задание 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Создаем панель для кнопок
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE); // начальный цвет

        // Создаем кнопки
        JButton redButton = new JButton("Красный");
        JButton blueButton = new JButton("Синий");
        JButton greenButton = new JButton("Зелёный");

        // Добавляем слушатели для кнопок
        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColor(Color.RED, "Красный");
            }
        });

        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColor(Color.BLUE, "Синий");
            }
        });

        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColor(Color.GREEN, "Зелёный");
            }
        });

        // Добавляем кнопки на панель
        buttonPanel.add(redButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(greenButton);

        // Создаем метку для отображения цвета настроения
        moodLabel = new JLabel("Выберите цвет настроения", SwingConstants.CENTER);
        moodLabel.setFont(new Font("Arial", Font.BOLD, 16));
        moodLabel.setOpaque(true);
        moodLabel.setBackground(Color.WHITE);

        // Добавляем компоненты в окно
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(moodLabel, BorderLayout.SOUTH);

        // Отображаем окно
        frame.setVisible(true);
    }

    private void changeColor(Color color, String colorName) {
        // Меняем цвет ВСЕГО окна
        frame.getContentPane().setBackground(color);
        buttonPanel.setBackground(color);
        moodLabel.setBackground(color);
        moodLabel.setText("Ваш цвет настроения: " + colorName);

        // Также меняем цвет кнопок для лучшей видимости
        Component[] components = buttonPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;

                // Меняем цвет текста кнопок для контраста с фоном
                if (color == Color.RED || color == Color.BLUE) {
                    button.setForeground(Color.WHITE);
                } else {
                    button.setForeground(Color.BLACK);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MoodColorApp();
            }
        });
    }
}