public class Task {
    private String name;
    private boolean isDone;
    
    //Constructors
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    //Getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isDone() {
        return isDone;
    }
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    
}
