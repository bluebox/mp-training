package DataStructurewithabstractclasses;
public class Node extends Listitem {
    public Node(Object value) {
        super(value);
    }
    @Override
    Listitem next() {
        return this.rightLink;
    }
    @Override
    void setNext(Listitem rightLink) {
        this.rightLink = rightLink;
    }
    @Override
    Listitem previous() {
        return this.leftLink;
    }
    @Override
    void setPrevious(Listitem leftLink) {
        this.leftLink = leftLink;
    }
    @Override
    int compareTo(Listitem item) {
        if (this.value instanceof Comparable && item.getValue() instanceof Comparable) {
            Comparable thisVal = (Comparable) this.value;
            Comparable otherVal = (Comparable) item.getValue();
            return thisVal.compareTo(otherVal);
        }
        throw new IllegalArgumentException("Values are not comparable");
    }
}