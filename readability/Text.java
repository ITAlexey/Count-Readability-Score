package readability;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

interface CountSomething {
	int 	countCharactersInText(List<String> sentences);
	void 	countAnySyllablesInText(List<String[]> wordsInSentence);
}

public class Text implements CountSomething {
	private final int			characterInText;
	private final int			sentencesInText;
	private final int			wordsInText;
	private int					syllables;
	private int					polysyllables;
	public final String  		END_OF_SENTENCE;
	public final String 		VISIBLE_SYMBOLS;
	public static String  		FILE_NAME;
	protected List<String>		sentences;
	protected List<String[]>	wordsInSentence;

	public Text(FileReader reader) throws IOException {
		syllables = 0;
		polysyllables = 0;
		END_OF_SENTENCE = "[^!.?]+[.?!]";
		VISIBLE_SYMBOLS = "[^\\s\\u00a0]";
		sentences = new ArrayList<>();
		createListOfSentences(sentences, reader);
		createListOfWordsInSentence(sentences);
		characterInText = countCharactersInText(sentences);
		wordsInText = countWordsInText(wordsInSentence);
		sentencesInText = sentences.size();
		countAnySyllablesInText(wordsInSentence);
	}

	private void createListOfWordsInSentence(List<String> sentences) {
		wordsInSentence = new ArrayList<>();
		sentences.forEach(sentence -> wordsInSentence.add(splitSentenceInWords(sentence)));
	}

	private void createListOfSentences(List<String> sentences, FileReader reader) throws IOException {
		int sign;
		StringBuilder sentence = new StringBuilder();
		while ((sign = reader.read()) != -1) {
			sentence.append((char) sign);
			if (sentence.toString().matches(END_OF_SENTENCE)) {
				sentences.add(sentence.toString());
				clear(sentence);
			}
		}
		if (!sentence.toString().isEmpty() && !sentence.toString().equals("\n")) {
			sentences.add(sentence.toString());
		}
	}

	private void clear(StringBuilder sentence) {
		sentence.delete(0, sentence.length());
	}

	public Integer countWordsInText(List<String[]> wordsInSentence) {
		return wordsInSentence.stream()
				.mapToInt(s -> s.length)
				.sum();
	}

	public String[] splitSentenceInWords(String sentence) {
		return sentence
				.replaceAll("\\u00a0", " ")
				.trim()
				.split("\\s+");
	}

	@Override
	public int countCharactersInText(List<String> sentences) {
		return sentences.stream()
				.map(s -> s.toCharArray())
				.mapToInt(s -> countCharsInSentence(s))
				.sum();
	}

	@Override
	public void countAnySyllablesInText(List<String[]> sentences) {
		sentences.forEach(s -> {
			int counter;
			for (String word : s) {
				counter = countAnySyllable(word);
				if (counter >= 3) {
					polysyllables++;
				}
				syllables += counter;
			}
		});
	}

	private int countAnySyllable(String word) {
		final String VOWELS = "[aeiouy]";
		final char[] wordInArr = word.toCharArray();
		int counter = word.toLowerCase().charAt(word.length() - 1) == 'e' ? -1 : 0;
		boolean isVowel = false;

		for (int i = 0; i < wordInArr.length; i++) {
			String s = Character.toString(wordInArr[i]);
			if (s.toLowerCase().matches(VOWELS) && !isVowel) {
				counter++;
				isVowel = true;
			} else {
				isVowel = false;
			}
		}
		return counter <= 0 ? 1 : counter;
	}

	private int countCharsInSentence(char[] sentence) {
		int counter = 0;
		for (char ch : sentence) {
			counter = String.valueOf(ch).matches(VISIBLE_SYMBOLS) ? counter + 1 : counter;
		}
		return counter;
	}

	@Override
	public String toString() {
		return String.format("Words: %d\nSentences: %d\nCharacters: " +
				"%d\nSyllables: %d\nPolysyllables: %d\n"
				, wordsInText, sentencesInText, characterInText, syllables, polysyllables);
	}

	public int getWordsInText() {
		return wordsInText;
	}

	public int getSentencesInText() {
		return sentencesInText;
	}

	public int getCharacterInText() {
		return characterInText;
	}

	public int getPolysyllables() {
		return polysyllables;
	}

	public int getSyllables() {
		return syllables;
	}
}