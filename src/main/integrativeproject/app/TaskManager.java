package integrativeproject.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class TaskManager {
    private final SinglyLinkedList<Task> tasks = new SinglyLinkedList<>();
    private final AvlTree<Integer> idIndex = new AvlTree<>();
    private final BinaryHeap<Task> pq = new BinaryHeap<>(Comparator
            .comparingInt((Task t) -> -t.getPriority().weight)
            .thenComparing(t -> t.getDueDate(), java.util.Comparator.nullsLast(java.util.Comparator.naturalOrder()))
            .thenComparingInt(Task::getId));
    private final ArrayStack<Command> undo = new ArrayStack<>(256);
    private final ArrayStack<Command> redo = new ArrayStack<>(256);
    private int seq = 1;

    public Task create(String desc, LocalDate due, TaskPriority prio){
        Task t = new Task(nextId(), desc, due, prio);
        exec(new AddTask(t));
        return t;
    }
    public boolean remove(int id){
        Task t = findById(id);
        if (t == null) return false;
        exec(new RemoveTask(copyOf(t)));
        return true;
    }
    public boolean complete(int id){ exec(new CompleteTask(id)); return true; }
    public boolean reschedule(int id, LocalDate newDate){ exec(new RescheduleTask(id,newDate)); return true; }
    public String undo(){ if(undo.isEmpty()) return "nothing to undo"; Command c=undo.pop(); c.undo(this); redo.push(c); return "undo: "+c.label(); }
    public String redo(){ if(redo.isEmpty()) return "nothing to redo"; Command c=redo.pop(); c.apply(this); undo.push(c); return "redo: "+c.label(); }

    public Task findById(int id){
        if(!idIndex.contains(id)) return null;
        for (Object o : tasks.toArray()) { Task t=(Task)o; if(t.getId()==id) return t; }
        return null;
    }
    public List<Task> listAll(){
        ArrayList<Task> out=new ArrayList<>(); for(Object o:tasks.toArray()) out.add((Task)o); return out;
    }
    public List<Task> listByPriority(){
        BinaryHeap<Task> tmp = new BinaryHeap<>(Comparator
            .comparingInt((Task t) -> -t.getPriority().weight)
            .thenComparing(t -> t.getDueDate(), java.util.Comparator.nullsLast(java.util.Comparator.naturalOrder()))
            .thenComparingInt(Task::getId));
        for(Task t: listAll()) if (t.getState()==TaskState.PENDING) tmp.insert(t);
        ArrayList<Task> out=new ArrayList<>(); Task x; while((x=tmp.poll())!=null) out.add(x); return out;
    }

    void _addDirect(Task t){ if(idIndex.contains(t.getId())) throw new IllegalStateException("id exists "+t.getId()); tasks.addLast(t); idIndex.insert(t.getId()); pq.insert(t); }
    void _removeDirect(int id){
        SinglyLinkedList<Task> tmp=new SinglyLinkedList<>();
        for(Object o:tasks.toArray()){ Task t=(Task)o; if(t.getId()!=id) tmp.addLast(t); }
        while(!tasks.isEmpty()) tasks.removeFirst(); for(Object o:tmp.toArray()) tasks.addLast((Task)o);
        idIndex.remove(id);
    }

    private void exec(Command c){ c.apply(this); undo.push(c); while(!redo.isEmpty()) redo.pop(); }
    private int nextId(){ return seq++; }
    private static Task copyOf(Task t){ Task c=new Task(t.getId(), t.getDescription(), t.getDueDate(), t.getPriority()); c.setState(t.getState()); return c; }
}
