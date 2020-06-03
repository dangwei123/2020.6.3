数轴世界建立之初，还没有任何的生机。直到有一天，在数轴的整点上，慢慢诞生了一个一个新兴的文明。如果两个文明相邻，也就是在他们之间
不存在其他的整点，他们就会慢慢受到彼此的影响，逐渐融为一个整体。而当所有的文明大一统，全部融为一个整体的时候，这个数轴世界才会重
归和平。为了能够让自己的文明发展壮大，牛牛决定根据每个文明诞生的年代表，计算出什么时候才是和平的黄金时代。

简明题意：
我们定义一个整数可重集合是好的，当且仅当对于集合中任意两个元素 a, b (a \leq ba≤b) ，所有满足 a\leq c\leq ba≤c≤b 的元素 c 
都在集合中出现过。现在，给你一个数组 mSet，你需要做的是，对于这个数组的每一个前缀，判断这个前缀是不是一个好的集合。所以，
你将计算出的是一个数组，为布尔类型。
import java.util.*;


public class Solution {
    /**
     * 检查数组的每个前缀是不是一个好的集合
     * @param mSet int整型一维数组 
     * @return bool布尔型一维数组
     */
    public boolean[] continuousSet (int[] mSet) {
        // write code here
        boolean[] res=new boolean[mSet.length];
        if(mSet.length==0) return res;
        Set<Integer> set=new HashSet<>();
        int min=mSet[0];
        int max=mSet[0];
        for(int i=0;i<mSet.length;i++){
            max=Math.max(max,mSet[i]);
            min=Math.min(min,mSet[i]);
            
            if(set.add(mSet[i])){
                res[i]=(max-min+1==set.size());
            }else{
                res[i]=res[i-1];
            }
        }
        return res;
    }
}

有n个房间，房间之间有通道相连，一共有n-1个通道，每两个房间之间都可以通过通道互相到达。

牛牛一开始在1号房间。牛妹在某个另外的房间。牛牛想去寻找牛妹，但是他没有地图，所以只能在房间之间乱走。
牛牛每走过一条通道需要花费1金币，每条通道最多只能走两次。
牛牛有一个乱走规则：如果当前牛牛有 没走过的通道 可以走，牛牛就不会去走 走过一次的通道
这个规则可以保证只要钱足够就一定可以找到牛妹。

有m个查询，每次询问如果牛妹在x_ix 
i
​	
 号房间，请问牛牛身上至少要带多少金币才可以保证任何情况下都可以找到牛妹。
 import java.util.*;


public class Solution {
    /**
     * 寻找牛妹
     * @param n int整型 
     * @param m int整型 
     * @param u int整型一维数组 
     * @param v int整型一维数组 
     * @param x int整型一维数组 
     * @return int整型一维数组
     */
    private ArrayList<Integer>[] arr;
    private int[] distinct;
    private int[] children;
    public int[] solve (int n, int m, int[] u, int[] v, int[] x) {
        arr=new ArrayList[n+1];
        distinct=new int[n+1];
        children=new int[n+1];
        for(int i=1;i<=n;i++){
            arr[i]=new ArrayList<>();
        }
        for(int i=0;i<u.length;i++){
            arr[u[i]].add(v[i]);
            arr[v[i]].add(u[i]);
        }
        
        dfs(1,-1,0);
        int totale=2*u.length;
        
        int[] res=new int[m];
        for(int i=0;i<m;i++){
            res[i]=totale-2*children[x[i]]-distinct[x[i]];
        }
        return res;
    }
    
    private int dfs(int cur,int pre,int count){
        distinct[cur]=count;
        int cnt=0;
        for(int i:arr[cur]){
            if(i==pre) continue;
            cnt+=dfs(i,cur,count+1);
        }
        children[cur]=cnt;
        return cnt+1;
    }
}
