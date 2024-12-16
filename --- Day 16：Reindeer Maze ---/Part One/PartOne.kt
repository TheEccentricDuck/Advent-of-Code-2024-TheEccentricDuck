// 2024/12/16
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

data class Pos(val x:Int,val y:Int,val direc:Int)
fun main(){
    val side=141
    val maze=Array<ArrayList<Char>>(side){arrayListOf()}
    var startX=0
    var startY=0
    var endX=0
    var endY=0
    for(i in 0..side-1){
        val cur=readln()
        val plus= arrayListOf<Char>()
        var ind=0
        for(j in cur){
            plus.add(j)
            if(j=='S'){
                startX=i
                startY=ind
            }else if(j=='E'){
                endX=i
                endY=ind
            }
            ind++
        }
        maze[i]=plus
    }
    val direcs=arrayOf(Pair(0,-1),Pair(-1,0),Pair(0,1),Pair(1,0))
    val comparator=compareBy<Pair<Long,Pos>>{it.first}
    val pq=PriorityQueue<Pair<Long,Pos>>(comparator)
    val dist=Array<Array<Array<Long>>>(side){Array<Array<Long>>(side){Array<Long>(4){1000000000000000000} } }
    val has=Array<Array<Array<Boolean>>>(side){Array<Array<Boolean>>(side){Array<Boolean>(4){false} } }
    dist[startX][startY][0]=0
    val start=Pos(startX,startY,0)
    pq.add(Pair(0L,start))
    while(!pq.isEmpty()){
        val p=pq.remove().second
        if(has[p.x][p.y][p.direc]){
            continue
        }
        has[p.x][p.y][p.direc] = true
        var newDirec=p.direc-1
        if(newDirec<0){
            newDirec+=4
        }
        if(dist[p.x][p.y][p.direc]+1000<dist[p.x][p.y][newDirec]){
            dist[p.x][p.y][newDirec]=dist[p.x][p.y][p.direc]+1000
            pq.add(Pair(dist[p.x][p.y][newDirec],Pos(p.x,p.y,newDirec)))
        }
        newDirec=p.direc+1
        if(newDirec>3){
            newDirec-=4
        }
        if(dist[p.x][p.y][p.direc]+1000<dist[p.x][p.y][newDirec]){
            dist[p.x][p.y][newDirec]=dist[p.x][p.y][p.direc]+1000
            pq.add(Pair(dist[p.x][p.y][newDirec],Pos(p.x,p.y,newDirec)))
        }
        val newX=p.x+direcs[p.direc].first
        val newY=p.y+direcs[p.direc].second
        if(maze[newX][newY]!='#'&&dist[p.x][p.y][p.direc]+1<dist[newX][newY][p.direc]){
            dist[newX][newY][p.direc]=dist[p.x][p.y][p.direc]+1
            pq.add(Pair(dist[newX][newY][p.direc],Pos(newX,newY,p.direc)))
        }
    }
    var minNum=dist[endX][endY][0]
    for(i in 1..3){
        minNum=min(minNum,dist[endX][endY][i])
    }
    println(minNum)
}
