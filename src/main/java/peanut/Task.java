package peanut;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;

    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void mark() {this.isDone = true; }

    public void unmark() {this.isDone = false ; }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }



    public void setDone(boolean done) {
        this.isDone = done;
    }


}

