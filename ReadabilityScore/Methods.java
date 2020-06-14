package readability;

public enum Methods {
	ARI {
		@Override
		ReadabilityIndex InitializeMethod(Text text) {
			return new ARI(text);
		}
	},
	FK {
		@Override
		ReadabilityIndex InitializeMethod(Text text) {
			return new FK(text);
		}
	},
	SMOG {
		@Override
		ReadabilityIndex InitializeMethod(Text text) {
			return new SMOG(text);
		}
	},
	CL {
		@Override
		ReadabilityIndex InitializeMethod(Text text) {
			return new CL(text);
		}
	};

	abstract ReadabilityIndex InitializeMethod(Text text);

	public static void displayResultOfMethod(ReadabilityIndex method) {
		method.setScore(method.countReadabilityIndex());
		GradeLevel level = GradeLevel.chooseLevel((int) Math.round(method.getScore()));
		method.setYears(level.getGradeLevel());
		System.out.println(method.toString());
	}
}
