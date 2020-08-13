import java.util.Scanner;

/**
 * A program that counts words in a document, printing the most frequent.
 */
public class WordCount {
    public static void main(String[] args) {
        Map<String, Integer> freq = new ChainHashMap<>();  // or any concrete map
        // scan input for words, using all nonletters as delimiters
        Scanner doc = new Scanner(System.in).useDelimiter("[^a-zA-Z]+");
        while (doc.hasNext()) {
            String word = doc.next().toLowerCase();  // convert next word to lowercase
            Integer count = freq.get(word);  // get the previous count for this word
            if (count == null)
                count = 0;  // if not in map, previous count is zero
            freq.put(word, 1 + count);  // (re)assign new count for this word
        }
        int maxCount = 0;
        String maxWord = "no word";
        for (Entry<String, Integer> ent : freq.entrySet())  // find max-count word
            if (ent.getValue() > maxCount) {
                maxWord = ent.getKey();
                maxCount = ent.getValue();
            }
        System.out.println("The most frequent word is '" + maxWord);
        System.out.println("' with " + maxCount + " occurrences.");
    }
}
