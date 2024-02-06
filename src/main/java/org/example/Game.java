package org.example;


//a)	Интерфейс взаимодействий Game (должны быть описаны сигнатуры методов
// start, inputValue,getGameStatus)a)
// Интерфейс взаимодействий Game
// (должны быть описаны сигнатуры методов start, inputValue,getGameStatus)
public interface Game {
    void start(Integer sizeWord, Integer tryCount);
    Answer inputValue(String value);
    GameStatus getGameStatus();

    void restart();

    void pause();

    void backToGame();
}
