package example;

public class Holman {
    String name;
    int howLong;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getHowLong() {
        return howLong;
    }

    public void setHowLong(final int howLong) {
        this.howLong = howLong;
    }

    @Override
    public String toString() {
        return "Holman{" +
                "name='" + name + '\'' +
                ", howLong=" + howLong +
                '}';
    }
}
