package integrator.app;

public interface Command { void apply(TaskManager m); void undo(TaskManager m); String label(); }
