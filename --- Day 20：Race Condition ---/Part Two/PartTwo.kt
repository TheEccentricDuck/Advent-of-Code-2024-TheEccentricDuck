// 2024/12/20
import java.util.PriorityQueue
import java.util.Stack
import kotlin.math.*

val comparator = compareBy<Pair<Int, Pair<Int,Int>>> { it.first }
fun main(){
    val side=141
    val maze=ArrayList<String>()
    var startX=0
    var startY=0
    var endX=0
    var endY=0
    for(i in 0..side-1){
        maze.add(readln())
        for(j in 0..side-1){
            if(maze[i][j]=='S'){
                startX=i
                startY=j
            }else if(maze[i][j]=='E'){
                endX=i
                endY=j
            }
        }
    }
    var has=Array<Array<Boolean>>(side){Array<Boolean>(side){false} }
    var fromDist=Array<Array<Int>>(side){Array<Int>(side){Int.MAX_VALUE} }
    var pq= PriorityQueue<Pair<Int,Pair<Int,Int>>>(comparator)
    val direcs=arrayOf(1 to 0,-1 to 0,0 to 1,0 to -1)
    pq.add(Pair(0,Pair(startX,startY)))
    fromDist[startX][startY]=0
    while(!pq.isEmpty()){
        val p=pq.remove().second
        if(has[p.first][p.second]){
            continue
        }
        has[p.first][p.second]=true
        for(i in 0..3){
            val newX=p.first+direcs[i].first
            val newY=p.second+direcs[i].second
            if(maze[newX][newY]!='#'&&fromDist[p.first][p.second]+1<fromDist[newX][newY]){
                pq.add(Pair(fromDist[p.first][p.second]+1,Pair(newX,newY)))
                fromDist[newX][newY]=fromDist[p.first][p.second]+1
            }
        }
    }
    has=Array<Array<Boolean>>(side){Array<Boolean>(side){false} }
    var toDist=Array<Array<Int>>(side){Array<Int>(side){Int.MAX_VALUE} }
    pq.add(Pair(0,Pair(endX,endY)))
    toDist[endX][endY]=0
    while(!pq.isEmpty()){
        val p=pq.remove().second
        if(has[p.first][p.second]){
            continue
        }
        has[p.first][p.second]=true
        for(i in 0..3){
            val newX=p.first+direcs[i].first
            val newY=p.second+direcs[i].second
            if(maze[newX][newY]!='#'&&toDist[p.first][p.second]+1<toDist[newX][newY]){
                pq.add(Pair(toDist[p.first][p.second]+1,Pair(newX,newY)))
                toDist[newX][newY]=toDist[p.first][p.second]+1
            }
        }
    }
    var tot=0
    for(i in 1..side-2){
        for(j in 1..side-2){
            if(maze[i][j]!='#'){
                for(k in i-20..i+20){
                    for(l in j-20..j+20){
                        if(k>0&&k<side-1&&l>0&&l<side-1&&maze[k][l]!='#'&&abs(k-i)+abs(l-j)<=20){
                            val save=fromDist[i][j]+abs(k-i)+abs(l-j)+toDist[k][l]
                            if(fromDist[endX][endY]-save>=100){
                                tot++
                            }
                        }
                    }
                }
            }
        }
    }
    println(tot)
}
