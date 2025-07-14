package DataStructurewithabstractclasses;

public abstract class Listitem {
    protected Listitem rightLink = null;
    protected Listitem leftLink = null;
    protected Object value;

    public Listitem(Object value) {
        this.value = value;
    }

    abstract Listitem next();
    abstract void setNext(Listitem rightLink);
    abstract Listitem previous();
    abstract void setPrevious(Listitem leftLink);
    abstract int compareTo(Listitem item);

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
