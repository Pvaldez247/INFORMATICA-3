package integrator.app;

import java.time.LocalDate;

final class AddTask implements Command {
    private final Task task; AddTask(Task t){ this.task=t; }
    @Override public void apply(TaskManager m){ m._addDirect(task); }
    @Override public void undo(TaskManager m){ m._removeDirect(task.getId()); }
    @Override public String label(){ return "AddTask #"+task.getId(); }
}

final class RemoveTask implements Command {
    private final Task snapshot; RemoveTask(Task t){ this.snapshot=t; }
    @Override public void apply(TaskManager m){ m._removeDirect(snapshot.getId()); }
    @Override public void undo(TaskManager m){ m._addDirect(snapshot); }
    @Override public String label(){ return "RemoveTask #"+snapshot.getId(); }
}

final class CompleteTask implements Command {
    private final int id; private TaskState prev;
    CompleteTask(int id){ this.id=id; }
    @Override public void apply(TaskManager m){ Task t=m.findById(id); if(t==null) throw new IllegalArgumentException("no task id "+id); prev=t.getState(); t.setState(TaskState.DONE); }
    @Override public void undo(TaskManager m){ Task t=m.findById(id); if(t!=null) t.setState(prev); }
    @Override public String label(){ return "CompleteTask #"+id; }
}

final class RescheduleTask implements Command {
    private final int id; private final LocalDate newDate; private LocalDate oldDate;
    RescheduleTask(int id, LocalDate newDate){ this.id=id; this.newDate=newDate; }
    @Override public void apply(TaskManager m){ Task t=m.findById(id); if(t==null) throw new IllegalArgumentException("no task id "+id); oldDate=t.getDueDate(); t.setDueDate(newDate); }
    @Override public void undo(TaskManager m){ Task t=m.findById(id); if(t!=null) t.setDueDate(oldDate); }
    @Override public String label(){ return "RescheduleTask #"+id; }
}
