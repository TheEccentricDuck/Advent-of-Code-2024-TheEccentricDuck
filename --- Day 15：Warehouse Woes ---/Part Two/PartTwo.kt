// 2024/12/15
import java.util.*
import kotlin.collections.ArrayList
fun main(){
    val side=50
    val insLines=20
    var maze:ArrayList<ArrayList<Char>> =arrayListOf()
    var robotX=0
    var robotY=0
    for(i in 0..side-1){
        val temp=readln()
        val ins=arrayListOf<Char>()
        for(j in 0..side-1){
            ins.add(temp[j])
        }
        maze.add(ins)
    }
    var wideMaze:ArrayList<ArrayList<Char>> =arrayListOf()
    for(i in 0..side-1){
        val wide=arrayListOf<Char>()
        for(j in 0..side-1){
            if(maze[i][j]=='@'){
                wide.add('@');
                wide.add('.');
                robotX=i
                robotY=2*j
            }else if (maze[i][j]=='O'){
                wide.add('[')
                wide.add(']')
            }else{
                wide.add(maze[i][j])
                wide.add(maze[i][j])
            }
        }
        wideMaze.add(wide)
    }
    readln();
    var instructions=readln();
    for(i in 1..insLines-1){
        instructions+=readln()
    }
    val direc=mapOf('<' to Pair(0,-1),'v' to Pair(1,0),'^' to Pair(-1,0),'>' to Pair(0,1))
    for(i in 0..instructions.length-1){
        var wall=false
        val curDirecX=direc[instructions[i]]!!.first
        val curDirecY=direc[instructions[i]]!!.second
        val q=ArrayDeque<Pair<Int,Pair<Int,Int>>>()
        val all=arrayListOf<Pair<Int,Pair<Int,Int>>>()
        q.addLast(Pair(0,Pair(robotX,robotY)))
        all.add(Pair(0,Pair(robotX,robotY)))
        var op=0
        while(!q.isEmpty()){
            val p=q.removeFirst()
            val newX=p.second.first+curDirecX
            val newY=p.second.second+curDirecY
            if(wideMaze[newX][newY]=='#'){
                wall=true
                break
            }
            if(wideMaze[newX][newY]=='['){
                if(instructions[i]=='v'||instructions[i]=='^'){
                    q.addLast(Pair(p.first,Pair(newX,newY)))
                    q.addLast(Pair(p.first,Pair(newX,newY+1)))
                } else{
                    q.addLast(Pair(p.first,Pair(newX,newY+1)))
                }
                all.add(Pair(op,Pair(newX,newY)))
                op++
                all.add(Pair(op,Pair(newX,newY+1)))
                op++
            } else if(wideMaze[newX][newY]==']'){
                if(instructions[i]=='v'||instructions[i]=='^'){
                    q.addLast(Pair(p.first,Pair(newX,newY)))
                    q.addLast(Pair(p.first,Pair(newX,newY-1)))
                } else{
                    q.addLast(Pair(p.first,Pair(newX,newY-1)))
                }
                all.add(Pair(op,Pair(newX,newY)))
                op++
                all.add(Pair(op,Pair(newX,newY-1)))
                op++
            }
        }
        if(!wall){
            val has=Array<Array<Boolean>>(side){Array<Boolean>(2*side){false} }
            for(pos in all.reversed()){
                if(!has[pos.second.first][pos.second.second]){
                    wideMaze[pos.second.first+curDirecX][pos.second.second+curDirecY]=wideMaze[pos.second.first][pos.second.second]
                    wideMaze[pos.second.first][pos.second.second]='.'
                    has[pos.second.first][pos.second.second]=true
                }
            }
            robotX=robotX+curDirecX
            robotY=robotY+curDirecY
        }
    }
    var gps=0
    for(i in 0..side-1){
        for(j in 0..2*side-1){
            if(wideMaze[i][j]=='['){
                gps+=100*i+j
            }
        }
    }
    println(gps)
}
