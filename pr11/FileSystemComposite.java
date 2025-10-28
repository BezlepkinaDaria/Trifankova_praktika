// Реализовать систему управления каталогом файлов, где папки и файлы образуют древовидную структуру.
// Использовать шаблон Composite, позволяющий одинаково работать с файлами и папками.

package pr11;

import java.util.ArrayList;
import java.util.List;

// Базовый компонент файловой системы
interface FileSystemComponent {
    String getName();
    void display(String indent);
    long getSize();
}

// Листовой элемент - файл
class File implements FileSystemComponent {
    private final String name;
    private final long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "📄 " + name + " (" + size + " bytes)");
    }

    @Override
    public long getSize() {
        return size;
    }
}

// Композитный элемент - папка
class Folder implements FileSystemComponent {
    private final String name;
    private final List<FileSystemComponent> children;

    public Folder(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "📁 " + name + " (" + getSize() + " bytes)");
        for (FileSystemComponent component : children) {
            component.display(indent + "  ");
        }
    }

    @Override
    public long getSize() {
        long totalSize = 0;
        for (FileSystemComponent component : children) {
            totalSize += component.getSize();
        }
        return totalSize;
    }

    // Методы для управления детьми
    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public void remove(FileSystemComponent component) {
        children.remove(component);
    }

    public List<FileSystemComponent> getChildren() {
        return children;
    }
}

// Демонстрация работы паттерна Composite                                                  // !!!
class FileSystemDemo {
    public static void main(String[] args) {
        // Создаем файлы
        FileSystemComponent file1 = new File("document.txt", 1500);
        FileSystemComponent file2 = new File("image.jpg", 2500);
        FileSystemComponent file3 = new File("program.java", 3000);
        FileSystemComponent file4 = new File("readme.md", 500);

        // Создаем папки
        Folder documents = new Folder("Documents");
        Folder pictures = new Folder("Pictures");
        Folder projects = new Folder("Projects");
        Folder root = new Folder("Root");

        // Добавляем файлы в папки
        documents.add(file1);
        documents.add(file4);

        pictures.add(file2);

        projects.add(file3);

        // Создаем вложенную структуру
        root.add(documents);
        root.add(pictures);
        root.add(projects);

        // Добавляем файл напрямую в корень
        FileSystemComponent file5 = new File("config.ini", 200);
        root.add(file5);

        // Создаем подпапку внутри Projects
        Folder src = new Folder("src");
        FileSystemComponent file6 = new File("Main.java", 1200);
        src.add(file6);
        projects.add(src);

        // Демонстрируем работу с древовидной структурой
        System.out.println("=== Структура файловой системы ===");
        root.display("");

        System.out.println("\n=== Информация о размерах ===");
        System.out.println("Общий размер корневой папки: " + root.getSize() + " bytes");
        System.out.println("Размер папки Documents: " + documents.getSize() + " bytes");
        System.out.println("Размер папки Projects: " + projects.getSize() + " bytes");
        System.out.println("Размер файла document.txt: " + file1.getSize() + " bytes");

        // Демонстрация единообразной работы с компонентами
        System.out.println("\n=== Обработка всех компонентов ===");
        processComponent(root, "");
    }

    // Метод, демонстрирующий единообразную работу с любым компонентом
    public static void processComponent(FileSystemComponent component, String prefix) {                   // !!!
        System.out.println(prefix + "Обрабатываем: " + component.getName());
        System.out.println(prefix + "Размер: " + component.getSize() + " bytes");

        // Если это папка, можно обработать её детей
        if (component instanceof Folder) {
            Folder folder = (Folder) component;
            for (FileSystemComponent child : folder.getChildren()) {
                processComponent(child, prefix + "  ");
            }
        }
    }
}