package integrativeproject.app;

public enum TaskPriority { LOW(0), MEDIUM(1), HIGH(2), URGENT(3); public final int weight; TaskPriority(int w){this.weight=w;} }
