package integrator.app;

import java.time.LocalDate;

public final class Task {
    private final int id;
    private String description;
    private LocalDate dueDate;
    private TaskPriority priority;
    private TaskState state;

    public Task(int id, String description, LocalDate dueDate, TaskPriority priority) {
        if (description == null || description.isBlank()) throw new IllegalArgumentException("description is empty");
        this.id = id; this.description = description.trim(); this.dueDate = dueDate;
        this.priority = priority == null ? TaskPriority.MEDIUM : priority; this.state = TaskState.PENDING;
    }
    public int getId(){return id;} public String getDescription(){return description;} public void setDescription(String d){ if(d==null||d.isBlank()) throw new IllegalArgumentException("empty"); this.description=d.trim(); }
    public LocalDate getDueDate(){return dueDate;} public void setDueDate(LocalDate d){ this.dueDate=d; }
    public TaskPriority getPriority(){return priority;} public void setPriority(TaskPriority p){ if(p==null) throw new IllegalArgumentException("null"); this.priority=p; }
    public TaskState getState(){return state;} public void setState(TaskState s){ if(s==null) throw new IllegalArgumentException("null"); this.state=s; }
    @Override public String toString(){ return "#" + id + " [" + state + "] " + (dueDate==null? "" : (dueDate + " ")) + "(" + priority + ") " + description; }
}
