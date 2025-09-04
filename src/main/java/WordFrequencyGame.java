import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String ANY_SPACE = "\\s+"; // \s means space,

    public String getResult(String inputStr) {
        String[] words = inputStr.split(ANY_SPACE);
        if (words.length == 1) {
            return inputStr + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                List<Input> wordFrequencies = countWordFrequency(words);
                wordFrequencies.sort((w1, w2) -> w2.count() - w1.count());
                return composeOutPut(wordFrequencies);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private static String composeOutPut(List<Input> wordFrequencies) {
        return wordFrequencies.stream()
                .map(w -> w.value() + " " + w.count())
                .collect(Collectors.joining("\n"));
    }

    private List<Input> countWordFrequency(String[] words) {
        List<Input> wordFrequencies = new ArrayList<>();
        Map<String, Integer> wordsCount = groupSameWords(words);
        for (Map.Entry<String, Integer> entry : wordsCount.entrySet()) {
            wordFrequencies.add(new Input(entry.getKey(), entry.getValue()));
        }
        return wordFrequencies;
    }

    private static Map<String, Integer> groupSameWords(String[] words) {
        Map<String, Integer> wordsCount = new HashMap<>();
        for (String word : words) {
            if (wordsCount.containsKey(word)) {
                wordsCount.put(word, wordsCount.get(word) + 1);
            } else {
                wordsCount.put(word, 1);
            }
        }
        return wordsCount;
    }

}
