// 2024/12/16
import java.util.*
import kotlin.collections.ArrayList

data class Pos(val x:Int,val y:Int,val direc:Int)
val side=141
val direcs=arrayOf(Pair(0,-1),Pair(-1,0),Pair(0,1),Pair(1,0))
val maze=Array<ArrayList<Char>>(side){arrayListOf()}

fun main(){
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
    val comparator=compareBy<Pair<Long,Pos>>{it.first}
    val pq=PriorityQueue<Pair<Long,Pos>>(comparator)
    val dist=Array<Array<Array<Long>>>(side){Array<Array<Long>>(side){Array<Long>(4){1000000000000000000} } }
    val has=Array<Array<Array<Boolean>>>(side){Array<Array<Boolean>>(side){Array<Boolean>(4){false} } }
    val adjacency=Array<Array<ArrayList<Pair<Int,Int>>>>(side){Array<ArrayList<Pair<Int,Int>>>(side){arrayListOf<Pair<Int,Int>>()} }
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
        if(maze[newX][newY]!='#'&&dist[p.x][p.y][p.direc]+1<=dist[newX][newY][p.direc]){
            if(dist[p.x][p.y][p.direc]+1<dist[newX][newY][p.direc]){
                dist[newX][newY][p.direc]=dist[p.x][p.y][p.direc]+1
                pq.add(Pair(dist[newX][newY][p.direc],Pos(newX,newY,p.direc)))
            }
            adjacency[newX][newY].add(Pair(p.x,p.y))
        }
    }
    var minNum=dist[endX][endY][0]
    val minInds=arrayListOf<Int>()
    for(i in 1..3){
        if(dist[endX][endY][i]<minNum){
            minInds.clear()
            minInds.add(i)
            minNum=dist[endX][endY][i]
        }else if(dist[endX][endY][i]==minNum){
            minInds.add(i)
        }
    }
    val bestSeats=Array<Array<Boolean>>(side){Array<Boolean>(side){false} }
    bestSeats[startX][startY]=true
    bestSeats[endX][endY]=true
    val dq=ArrayDeque<Pos>()
    for(i in minInds){
        val newX=endX-direcs[i].first
        val newY=endY-direcs[i].second
        dq.add(Pos(newX,newY,(i+2)%4))
    }
    while(!dq.isEmpty()){
        val p=dq.remove()
        bestSeats[p.x][p.y]=true
        for(i in 0..3){
            val newX=p.x+direcs[i].first
            val newY=p.y+direcs[i].second
            var dif=i-p.direc
            if(dif==2||dif==-2){
                continue
            }
            if(dif!=0){
                dif=1
            }
            dif*=1000
            dif+=1
            if(maze[newX][newY]!='#'&&dist[newX][newY][(i+2)%4]==dist[p.x][p.y][(p.direc+2)%4]-dif){
                dq.add(Pos(newX,newY,i))
            }
        }
    }
    var tot=0
    for(i in 0..side-1){
        for(j in 0..side-1){
            if(bestSeats[i][j]){
                tot++
            }
        }
    }
    println(tot)
}
