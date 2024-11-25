package lr4.menu;

import java.util.*;
import java.util.Map.Entry;

import lr4.music.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Menu {
    // Поле для збереження об'єкта MusicService, який буде обробляти операції з музичними альбомами
    private MusicService musicService;
    // Мапа для збереження команд, де ключ - це номер команди, значення - це команда
    private Map<Integer, Command> commandMap;
    // Поле для збереження об'єкта InputHandler для отримання вводу від користувача
    private InputHandler inputHandler;
    // Логгер для запису повідомлень у журнал
    private static final Logger logger = LoggerFactory.getLogger(Menu.class);

    // Конструктор класу, який ініціалізує музичний сервіс, ввід і мапу команд
    public Menu(MusicService musicService, InputHandler inputHandler) {
        this.musicService = musicService; // ініціалізуємо musicService
        this.inputHandler = inputHandler; // ініціалізуємо inputHandler
        this.commandMap = new HashMap<>(); // Ініціалізуємо мапу для команд
        initializeCommands(); // Викликаємо метод для ініціалізації команд
    }

    // Ініціалізація мапи команд (прив'язуємо номер до конкретної команди)
    public void initializeCommands() {
        commandMap.put(1, new CreateAlbumCommand(musicService, inputHandler));
        commandMap.put(2, new AddCompositionCommand(musicService, inputHandler));
        commandMap.put(3, new SortByStyleCommand(musicService));
        commandMap.put(4, new GetCompsWithinBoundsCommand(musicService, inputHandler));
        commandMap.put(5, new SaveToFileCommand(musicService, inputHandler));
        commandMap.put(6, new LoadFromFileCommand(musicService, inputHandler));
        commandMap.put(7, new PrintAlbumsCommand(musicService));
        commandMap.put(8, new PrintAlbumsCompsCommand(musicService));
        commandMap.put(9, new ExitCommand());
    }

    // Метод для виведення всіх доступних команд
    public void printCommands() {
        // Отримуємо набір всіх записів з мапи команд
        Set<Entry<Integer, Command>> entrySet = commandMap.entrySet();
        for (Entry<Integer, Command> entry : entrySet) {
            // Виводимо номер команди та її опис
            System.out.println(entry.getKey() + ". " + entry.getValue().description());
        }
    }

    // Метод для запуску меню, обробки вводу користувача та виконання команд
    public void run() {
        try {
            int choice;
            while (true) {
                // Виводимо список доступних команд
                printCommands();
                // Отримуємо ввід користувача
                choice = inputHandler.getInt("\n" + "Виберіть варіант: ");
                System.out.println();
                // Отримуємо команду за вибраним номером
                Command command = commandMap.get(choice);
                if (command != null) {
                    // Якщо команда знайдена, виконуємо її
                    command.execute();
                } else {
                    // Якщо команда не знайдена, виводимо попередження
                    logger.warn("\n" + "Невірний вибір");
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            // Якщо виникає помилка під час роботи меню, логуємо її
            logger.error("\n" + "Не вдалося запустити меню", e);
        }
    }

    // Гетер для мапи команд (може бути використано для тестування)
    public Map<Integer, Command> getCommandMap() {
        return commandMap;
    }
}
