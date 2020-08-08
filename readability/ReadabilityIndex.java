package readability;

import java.util.Arrays;

public abstract class ReadabilityIndex {
	protected double		score;
	protected int			years;
	protected int			words;
	protected int			characters;
	protected int			sentences;
	protected int			syllables;
	protected int			polysyllables;
	protected double		A;
	protected double		B;
	protected double		C;

	ReadabilityIndex(Text text) {
		characters = text.getCharacterInText();
		words = text.getWordsInText();
		sentences = text.getSentencesInText();
		syllables = text.getSyllables();
		polysyllables = text.getPolysyllables();
		A = 0;
		B = 0;
		C = 0;
		score = 0;
	}

	abstract double countReadabilityIndex();

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}
}

class CL extends ReadabilityIndex {
	private final double	L;
	private final double	S;
	private final int		HUNDRED;

	CL(Text text) {
		super(text);
		A = 0.0588;
		B = 0.296;
		C = 15.8;
		HUNDRED = 100;
		L = characters / words * HUNDRED;
		S = sentences / words * HUNDRED;
	}

	@Override
	double countReadabilityIndex() {
		return A * L - B * S - C;
	}

	@Override
	public String toString() {
		return "Coleman–Liau index: " + score + " (about " + years + " year olds).";
	}
}

class ARI extends ReadabilityIndex {

	ARI(Text text) {
		super(text);
		A = 4.71;
		B = 0.5;
		C = 21.43;
	}

	@Override
	double countReadabilityIndex() {
		return A * characters / words + B * words / sentences - C;
	}

	@Override
	public String toString() {
		return "Automated Readability Index: " + score + " (about " + years + " year olds).";
	}
}

class FK extends ReadabilityIndex {

	FK(Text text) {
		super(text);
		A = 0.39;
		B = 11.8;
		C = 15.59;
	}

	@Override
	double countReadabilityIndex() {
		return A * words / sentences + B * syllables / words - C;
	}

	@Override
	public String toString() {
		return "Flesch–Kincaid readability tests: " + score + " (about " + years + " year olds).";
	}
}

class SMOG extends ReadabilityIndex {

	SMOG(Text text) {
		super(text);
		A = 1.043;
		B = 30;
		C = 3.1291;
	}

	@Override
	double countReadabilityIndex() {
		return A * Math.sqrt(polysyllables * B / sentences) + C;
	}

	@Override
	public String toString() {
		return "Simple Measure of Gobbledygook: " + score + " (about " + years + " year olds).";
	}
}
