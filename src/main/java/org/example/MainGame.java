package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> gameHistory = new ArrayList<>();

        while (true) {
            AbstractGame ag = startChoice(scanner);
            printTryCount(scanner, ag);

            while (true) {
                if (ag.getGameStatus().equals(GameStatus.WIN)) {
                    System.out.println("Вы победили!");
                    break;
                } else if (ag.getGameStatus().equals(GameStatus.LOOSE)) {
                    System.out.println("Вы проиграли!");
                    break;
                }

                System.out.println("1 - Ввести слово для угадывания");
                System.out.println("2 - Поставить игру на паузу");
                System.out.println("3 - Начать игру заново");
                System.out.println("4 - Посмотреть историю игры");
                System.out.println("5 - Продолжить игру");
                System.out.println("0 - Выйти из игры");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        playGame(ag, scanner, gameHistory);
                        break;
                    case 2:
                        if (ag.getGameStatus() == GameStatus.START) {
                            ag.pause();
                            System.out.println("Игра поставлена на паузу.");
                        } else {
                            System.out.println("Нельзя поставить игру на паузу в текущем состоянии.");
                        }
                        break;
                    case 3:
                        ag.restart();
                        if (ag.getGameStatus()==GameStatus.INIT){
                        gameHistory.clear();
                        startChoice(scanner);
                        System.out.println("Игра начата заново");
                        printTryCount(scanner, ag);
                        }
                        break;
                    case 4:
                        historyGame(gameHistory);
                        break;
                    case 5:
                        ag.backToGame();
                        System.out.println("Продолжайте игру");
                        break;
                    case 0:
                        System.out.println("Завершение игры.");
                        System.exit(0);
                    default:
                        System.out.println("Неверный выбор.");
                }
            }
            System.out.println("Хотите посмотреть историю игры? 1- Да, 0 - Нет");
            int input = scanner.nextInt();
            if (input == 1) {
                historyGame(gameHistory);
            }
            System.out.println("Хотите выйти из игры? 1 - Да, 0 - Нет");
            int input2 = scanner.nextInt();
            if (input2 == 1) {break;}
        }
    }

    /**
     * Печатает историю игры
     * @param gameHistory аргумент список с историей
     */
    private static void historyGame(List<String> gameHistory) {
            System.out.println("История игры:");
            for (String entry : gameHistory) {
                System.out.println(entry);
            }
    }
    /**
     * Основной метод для игры, ввод слова и запись в историю
     * @param scanner аргумент командной строки и AbstractGame и список с историей
     */
    private static void playGame(AbstractGame ag, Scanner scanner, List<String> gameHistory) {
        if (ag.getGameStatus() != GameStatus.START) {
            System.out.println("Игра не начата или уже завершена.");
            return;
        }
        System.out.println("Введите слово: ");
        String userInput = scanner.nextLine();
        try {
        Answer answer = ag.inputValue(userInput);
        System.out.println(answer.toString());
        String historyEntry = userInput + " -> " + answer.toString();
        gameHistory.add(historyEntry);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Спрашивает желаемое количество попыток и длину слова
     * @param scanner аргумент командной строки и AbstractGame
     */
    private static void printTryCount(Scanner scanner, AbstractGame ag) {
        System.out.println("Введите количество попыток, за которые хотите угадать слово: ");
        int tryCount = scanner.nextInt();
        System.out.println("Введите длину слова для угадывания: ");
        int sizeWord = scanner.nextInt();
        ag.start(sizeWord, tryCount);
        System.out.println("Компьютер загадал слово, попытайтесь его угадать, у вас " + tryCount + " попыток");
    }


    /**
     * Начальный метод, который спрашивает какой вид игры, вы хотите сыграть, возвращает значение AbstractGame
     * @param scanner аргумент командной строки
     */
    public static AbstractGame startChoice(Scanner scanner) {
        System.out.println("Какой тип символов вы хотите угадывать: 1 - русский, 2- английский, 3 - цифры");

        int input = scanner.nextInt();

        AbstractGame ag = null;

        switch (input) {
            case 1:
                ag = new RuAlphabetGame();
                break;
            case 2:
                ag = new EngAlphabetGame();
                break;
            case 3:
                ag = new NumberGame();
                break;
            default:
                System.out.println("Неверный ввод. Завершение программы.");
                System.exit(0);
        }
        return ag;
    }
}


