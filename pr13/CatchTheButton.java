import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CatchTheButton extends JFrame {
    private JButton catchButton;
    private Timer moveTimer;
    private boolean isMoving = true;

    public CatchTheButton() {
        // 1. Окно (JFrame)
        setTitle("Поймай кнопку");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        // 2. Интерактивный компонент - кнопка
        catchButton = new JButton("Поймай меня!");
        catchButton.setBounds(150, 150, 120, 30);
        add(catchButton);

        // Таймер для задержки перед перемещением (дает время на клик)
        moveTimer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isMoving) {
                    moveButtonToRandomPosition();
                }
            }
        });

        // 3. Обработчик MouseListener для наведения курсора
        catchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isMoving) {
                    // Запускаем таймер с задержкой для перемещения
                    moveTimer.setRepeats(false); // Однократное срабатывание
                    moveTimer.start();
                }
            }
        });

        // 3. Обработчик ActionListener для нажатия на кнопку
        catchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Останавливаем перемещение
                isMoving = false;
                moveTimer.stop();

                // 4. Изменение текста - вывод сообщения (точно по заданию)
                JOptionPane.showMessageDialog(CatchTheButton.this,
                        "Ты поймал кнопку!");

                // Меняем внешний вид пойманной кнопки
                catchButton.setText("Поймана!");
                catchButton.setBackground(Color.GREEN);
                catchButton.setEnabled(false);
            }
        });

        setVisible(true);
    }

    private void moveButtonToRandomPosition() {
        int windowWidth = getContentPane().getWidth();
        int windowHeight = getContentPane().getHeight();
        int buttonWidth = catchButton.getWidth();
        int buttonHeight = catchButton.getHeight();

        // Вычисляем доступные координаты для перемещения
        int maxX = windowWidth - buttonWidth - 10;
        int maxY = windowHeight - buttonHeight - 10;

        if (maxX > 0 && maxY > 0) {
            // Генерируем случайные координаты
            int randomX = (int)(Math.random() * maxX) + 5;
            int randomY = (int)(Math.random() * maxY) + 5;

            // Перемещаем кнопку
            catchButton.setLocation(randomX, randomY);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CatchTheButton();
            }
        });
    }
}