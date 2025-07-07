public enum Suit {
	CLUB, DIAMOND, HEART, SPADE;

	public char getImage() {
		switch (this) {
		case CLUB:
			return (char) 9827;
		case DIAMOND:
			return (char) 9830;
		case HEART:
			return (char) 9829;
		case SPADE:
			return (char) 9824;
		default:
			return 'X';
		}
	}
}