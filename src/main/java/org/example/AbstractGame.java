package org.example;

import java.util.List;
import java.util.Random;

//b)	Абстрактный класс AbstractGame (который предзаполняет слово
// компьютера - generateWord(),
// на основе generateCharList() - который является абстрактным методом)
public abstract class AbstractGame implements Game{
    abstract List<String> generateCharList();
    private String word;
    private Integer tryCount;
    private GameStatus status = GameStatus.INIT;
    private String generateWord(Integer sizeWord){
        List<String> alphabet = generateCharList();
        String res = "";
        Random rnd = new Random();
        for (int i=0; i<sizeWord;i++){
            int randomIndex = rnd.nextInt(alphabet.size());
            res+=alphabet.get(randomIndex);
            alphabet.remove(randomIndex);
        }
        return res;
    }

    @Override
    public void start(Integer sizeWord, Integer tryCount) {
         word = generateWord(sizeWord);
         this.tryCount = tryCount;
         status = GameStatus.START;
    }

    @Override
    public Answer inputValue(String value) {
        if (!getGameStatus().equals(GameStatus.START)){
            throw new RuntimeException("Игра не запущена");
        }
        int countCow = 0;
        int countBull = 0;

        for (int i = 0; i < word.length(); i++) {
            if (value.charAt(i) == word.charAt(i)) {
                countBull++;
                countCow++;
            } else if (word.contains(String.valueOf(value.charAt(i)))){
                countCow++;
            }
        }
        tryCount--;
        if (tryCount ==0) {
            status = GameStatus.LOOSE;
        }
        if (countBull == word.length()){
            status = GameStatus.WIN;
        }
        return new Answer(countCow, countBull, tryCount);
    }

    @Override
    public GameStatus getGameStatus() {
        return status;
    }

    @Override
    public void restart() {
        if (!getGameStatus().equals(GameStatus.PAUSE)){
            System.out.println("Поставьте игру на паузу!");
        } else {
            status = GameStatus.INIT;
        }
    }

    @Override
    public void pause() {
        status = GameStatus.PAUSE;
    }

    @Override
    public void backToGame() {
        status = GameStatus.START;
    }
}
