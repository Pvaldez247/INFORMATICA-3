package integrativeproject.app;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class BinaryHeap<T> {
    private final ArrayList<T> a=new ArrayList<>();
    private final Comparator<? super T> c;
    public BinaryHeap(Comparator<? super T> c){ if(c==null) throw new IllegalArgumentException("cmp"); this.c=c; }
    public void insert(T v){ a.add(v); up(a.size()-1); }
    public T peek(){ return a.isEmpty()?null:a.get(0); }
    public T poll(){ if(a.isEmpty()) return null; T top=a.get(0); T last=a.remove(a.size()-1); if(!a.isEmpty()){ a.set(0,last); down(0);} return top; }
    public int size(){ return a.size(); }
    public List<T> snapshot(){ return new ArrayList<>(a); }
    private void up(int i){ while(i>0){ int p=(i-1)/2; if(c.compare(a.get(i),a.get(p))<0){ swap(i,p); i=p;} else break; } }
    private void down(int i){ int n=a.size(); while(true){ int l=2*i+1,r=l+1,b=i; if(l<n&&c.compare(a.get(l),a.get(b))<0)b=l; if(r<n&&c.compare(a.get(r),a.get(b))<0)b=r; if(b==i)break; swap(i,b); i=b; } }
    private void swap(int i,int j){ T t=a.get(i); a.set(i,a.get(j)); a.set(j,t); }
}
