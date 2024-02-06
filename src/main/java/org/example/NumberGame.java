package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberGame extends AbstractGame{

    @Override
    List<String> generateCharList() {
        return new ArrayList<>(Arrays.asList("1","2", "3", "4", "5", "6", "7", "8", "9","0"));
    }
}
