package genericClassChallenge;

import java.util.*;

public class Layer<T extends Mappable> {
	private List<T> elements;
	public Layer()
	{
		this.elements=new ArrayList<T>();
	}
	public void add(T element) {
        elements.add(element);
    }

    public void renderLayer() {
        for (T element : elements) {
            element.render();
        }
    }
}
