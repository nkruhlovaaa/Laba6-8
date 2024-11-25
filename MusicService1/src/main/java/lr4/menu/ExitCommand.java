package lr4.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExitCommand implements Command {

    // Статичний логер для запису повідомлень про виконання команди
    private static final Logger logger = LoggerFactory.getLogger(ExitCommand.class);

    // Реалізація методу execute для виконання команди "Вихід"
    @Override
    public void execute() {
        try {
            // Записуємо інформаційне повідомлення про вихід із програми
            logger.info("\n" + "Вихід із програми");

            // Завершуємо роботу програми з кодом 0 (успішне завершення)
            System.exit(0);
        } catch (Exception e) {
            // Якщо виникає помилка під час виходу, записуємо помилку в лог
            logger.error("\n" + "Не вдалося вийти з програми", e);
        }
    }

    // Опис команди, який повертається для відображення в меню
    @Override
    public String description() {
        return "Вихід"; // Повертає опис команди "Вихід"
    }
}
