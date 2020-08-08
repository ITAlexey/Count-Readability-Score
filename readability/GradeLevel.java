package readability;

public enum GradeLevel {
	KINDERGARTEN(6),
	FIRST_SECOND(7),
	THIRD(9),
	FOURTH(10),
	FIFTH(11),
	SIXTH(12),
	SEVENTH(13),
	EIGHTH(14),
	NINTH(15),
	TENTH(16),
	ELEVENTH(17),
	TWELFTH(18),
	COLLEGE(24);

	private final int	gradeLevel;


	GradeLevel(final int gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public int getGradeLevel() {
		return gradeLevel;
	}

	public static GradeLevel chooseLevel(int score) {
		switch (score) {
			case 1:
				return valueOf("KINDERGARTEN");
			case 2:
				return valueOf("FIRST_SECOND");
			case 3:
				return valueOf("THIRD");
			case 4:
				return valueOf("FOURTH");
			case 5:
				return valueOf("FIFTH");
			case 6:
				return valueOf("SIXTH");
			case 7:
				return valueOf("SEVENTH");
			case 8:
				return valueOf("EIGHTH");
			case 9:
				return valueOf("NINTH");
			case 10:
				return valueOf("TENTH");
			case 11:
				return valueOf("ELEVENTH");
			case 12:
				return valueOf("TWELFTH");
			default:
				return valueOf("COLLEGE");
		}
	}

}
