import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MoodColorApp3 {
    private JFrame frame;
    private JLabel moodLabel;
    private JPanel mainPanel;
    private JPanel buttonPanel;

    public MoodColorApp3() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Создаем главное окно
        frame = new JFrame("Цвет настроения - разные события");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Главная панель
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Панель для кнопок
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Создаем кнопки
        JButton redButton = new JButton("Красный");
        JButton blueButton = new JButton("Синий");
        JButton greenButton = new JButton("Зелёный");

        // Настраиваем кнопки с ActionListener и MouseListener
        setupButtonWithMultipleListeners(redButton, Color.RED, "Красный");
        setupButtonWithMultipleListeners(blueButton, Color.BLUE, "Синий");
        setupButtonWithMultipleListeners(greenButton, Color.GREEN, "Зелёный");

        // Добавляем кнопки
        buttonPanel.add(redButton);
        buttonPanel.add(blueButton);
        buttonPanel.add(greenButton);

        // Создаем метку
        moodLabel = new JLabel("Выберите цвет настроения", SwingConstants.CENTER);
        moodLabel.setFont(new Font("Arial", Font.BOLD, 16));
        moodLabel.setOpaque(true);
        moodLabel.setBackground(Color.WHITE);

        // Добавляем компоненты
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(moodLabel, BorderLayout.SOUTH);
        frame.add(mainPanel);

        frame.setVisible(true);
    }

    private void setupButtonWithMultipleListeners(JButton button, Color color, String colorName) {
        // ActionListener - реагирует на нажатие кнопки
        ActionListener actionListener = e -> {
            changeColor(color, colorName);
            System.out.println("Кнопка \"" + colorName + "\" была нажата");
        };

        // MouseListener - реагирует на наведение мыши
        MouseAdapter mouseListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Показываем всплывающее сообщение при наведении
                JOptionPane.showMessageDialog(
                        frame,
                        "Ты навёл курсор на кнопку \"" + colorName + "\"!",
                        "Внимание!",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        };

        // Добавляем оба слушателя
        button.addActionListener(actionListener);
        button.addMouseListener(mouseListener);
    }

    private void changeColor(Color color, String colorName) {
        mainPanel.setBackground(color);
        buttonPanel.setBackground(color);
        moodLabel.setBackground(color);
        moodLabel.setText("Ваш цвет настроения: " + colorName);
        updateButtonColors(color);
    }

    private void updateButtonColors(Color backgroundColor) {
        Component[] components = buttonPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (backgroundColor == Color.RED || backgroundColor == Color.BLUE) {
                    button.setForeground(Color.WHITE);
                } else {
                    button.setForeground(Color.BLACK);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MoodColorApp3());
    }
}