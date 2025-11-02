package sorting.app;

public final class SortStats {
    public long comparisons;
    public long moves;
    private long startNs;
    private long durationNs;

    public void start() { startNs = System.nanoTime(); }
    public void stop() { durationNs = System.nanoTime() - startNs; }
    public long millis() { return durationNs / 1_000_000; }

    @Override public String toString() {
        return "time=" + millis() + "ms, comparisons=" + comparisons + ", moves=" + moves;
    }
}
