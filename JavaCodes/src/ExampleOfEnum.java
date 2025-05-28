public enum ExampleOfEnum {
    MON, TUE, WED, THUR, FRI, SAT, SUN;
}

enum ExampleWithValue{
	MAXVALUE(1000),MINVALUE(-1000);
	
	private int val;
	private ExampleWithValue(int val){
		this.val=val;
	}
	public int getValue() {
		return this.val;
	}
}

enum ExampleWithMethod{
	METHOD{
		public int add(int x,int y) {
			return x+y;
		}
	},
	VALUE{
		public int add(int x,int y) {
			return x+y;
		}
	};
	public abstract int add(int x,int y);
}