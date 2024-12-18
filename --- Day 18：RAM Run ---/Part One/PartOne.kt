// 2024/12/18
import java.util.PriorityQueue

val comparator = compareBy<Pair<Int,Pair<Int,Int>>> { it.first }
fun main(){
    val side=70
    val sim=1024
    val blocked=Array<Array<Boolean>>(side+1){Array<Boolean>(side+1){false} }
    for(i in 1..sim){
        val coord=(readln().split(",")).map{it.toInt()}
        blocked[coord[1]][coord[0]]=true
    }
    val has=Array<Array<Boolean>>(side+1){Array<Boolean>(side+1){false} }
    val dist=Array<Array<Int>>(side+1){Array<Int>(side+1){Int.MAX_VALUE} }
    val pq=PriorityQueue<Pair<Int,Pair<Int,Int>>>(comparator)
    val direcs=arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
    dist[0][0]=0
    pq.add(Pair(0,Pair(0,0)))
    while(!pq.isEmpty()){
        val p=pq.remove().second
        if(has[p.first][p.second]){
            continue
        }
        has[p.first][p.second]=true
        for(i in 0..3){
            val newX=p.first+direcs[i].first
            val newY=p.second+direcs[i].second
            if(newX>=0&&newX<=side&&newY>=0&&newY<=side&&!blocked[newX][newY]&&dist[p.first][p.second]+1<dist[newX][newY]){
                dist[newX][newY]=dist[p.first][p.second]+1
                pq.add(Pair(dist[p.first][p.second],Pair(newX,newY)))
            }
        }
    }
    println(dist[side][side])
}
