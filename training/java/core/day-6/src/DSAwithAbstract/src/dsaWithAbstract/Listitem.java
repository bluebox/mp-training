package dsaWithAbstract;

abstract class Listitem {
    protected Object rightLink;
    protected Object leftLink;
    protected Object value;

    public Listitem(Object value) {
        this.value = value;
    }

    abstract Listitem next();
    abstract void setNext(Listitem item);
    abstract Listitem previous();
    abstract void setPrevious(Listitem item);
    abstract int compareTo(Listitem item);

    Object getValue() {
        return this.value;
    }

    void setValue(Object value) {
        this.value = value;
    }
}





