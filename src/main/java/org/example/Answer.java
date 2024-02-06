package org.example;

public class Answer {
    private int countCow;
    private int countBull;
    private int tryCount;

    public Answer(int countCow, int countBull, int tryCount) {
        this.countCow = countCow;
        this.countBull = countBull;
        this.tryCount = tryCount;
    }

    @Override
    public String toString() {
        return countCow + " коровы " + countBull+ " быка, осталось попыток: "+ tryCount;
    }
}
