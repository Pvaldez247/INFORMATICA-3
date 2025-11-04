package integrativeproject.app;

public final class AvlTree<T extends Comparable<? super T>> {
    private static final class N<T>{ T k; N<T> l,r; int h=1; N(T k){this.k=k;} }
    private N<T> root; private int sz;
    public int size(){return sz;} public boolean isEmpty(){return sz==0;}
    public boolean contains(T k){ N<T> n=root; while(n!=null){ int c=k.compareTo(n.k); if(c==0) return true; n=(c<0)?n.l:n.r; } return false; }
    public void insert(T k){ root=ins(root,k); }
    public boolean remove(T k){ int b=sz; root=rem(root,k); return sz<b; }
    private N<T> ins(N<T> n,T k){ if(n==null){sz++; return new N<>(k);} int c=k.compareTo(n.k); if(c<0)n.l=ins(n.l,k); else if(c>0)n.r=ins(n.r,k); return reb(up(n)); }
    private N<T> rem(N<T> n,T k){ if(n==null) return null; int c=k.compareTo(n.k); if(c<0)n.l=rem(n.l,k); else if(c>0)n.r=rem(n.r,k); else{ if(n.l==null||n.r==null){ N<T> ch=(n.l!=null)?n.l:n.r; sz--; return ch; } else { N<T> s=n.r; while(s.l!=null) s=s.l; n.k=s.k; n.r=rem(n.r,s.k);} } return reb(up(n)); }
    private static <T> int h(N<T> n){ return n==null?0:n.h; }
    private static <T> N<T> up(N<T> n){ n.h=1+Math.max(h(n.l),h(n.r)); return n; }
    private static <T> int bal(N<T> n){ return h(n.l)-h(n.r); }
    private N<T> reb(N<T> n){ int b=bal(n); if(b>1){ if(bal(n.l)<0) n.l=rotL(n.l); return rotR(n);} if(b<-1){ if(bal(n.r)>0) n.r=rotR(n.r); return rotL(n);} return n; }
    private N<T> rotR(N<T> y){ N<T> x=y.l; N<T> t=x.r; x.r=y; y.l=t; up(y); up(x); return x; }
    private N<T> rotL(N<T> x){ N<T> y=x.r; N<T> t=y.l; y.l=x; x.r=t; up(x); up(y); return y; }
}
