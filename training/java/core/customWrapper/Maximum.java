package customWrapper;

public class Maximum {
	int max=0;
	int size=0;
	public void insert(int x) {
		this.size++;
		if(this.max>=x) {
			return ;
		}
		this.max=x;
	}
	public int size1() {
		return this.size;
	}
	public int maxi() {
		return this.max;
	}
}
