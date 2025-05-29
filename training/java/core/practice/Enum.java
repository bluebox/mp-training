public enum Enum {
    MON, TUE, WED, THUR, FRI, SAT, SUN;
}

enum Example1{
	MAXVALUE(300),MINVALUE(-300);
	
	private int val;
	private Example1(int val){
		this.val=val;
	}
	public int getValue() {
		return this.val;
	}
}

enum Example2{
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