package meetingrooms;

public class MeetingRoom {

    private long id;

    private String name;

    private int width;

    private int length;

    public MeetingRoom(long id, String name, int width, int length) {
        this(name, width, length);
        this.id = id;
    }

    public MeetingRoom(String name, int width, int length) {
        this.name = name;
        this.width = width;
        this.length = length;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getArea() {
        return width * length;
    }

    public void printAll() {
        System.out.println(String.format("%s : szélesség: %dm, hosszúság: %dm, terület: %dm2", this.getName(), this.getWidth(), this.getLength(), this.getArea()));
    }

    public void printDimensions() {
        System.out.println(String.format("szélesség: %dm, hosszúság: %dm, terület: %dm2", this.getWidth(), this.getLength(), this.getArea()));
    }
}
