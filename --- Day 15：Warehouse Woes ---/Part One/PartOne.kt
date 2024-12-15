// 2024/12/15
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
        for(j in 0..side-1){
            if(maze[i][j]=='@'){
                robotX=i
                robotY=j
            }
        }
    }
    readln();
    var instructions=readln();
    for(i in 1..insLines-1){
        instructions+=readln()
    }
    val direc=mapOf('<' to Pair(0,-1),'v' to Pair(1,0),'^' to Pair(-1,0),'>' to Pair(0,1))
    for(i in 0..instructions.length-1){
        var boxes=0
        var wall=false
        var endX=robotX+direc[instructions[i]]!!.first
        var endY=robotY+direc[instructions[i]]!!.second
        while(maze[endX][endY]!='.'){
            if(maze[endX][endY]=='O'){
                boxes++
            }else if(maze[endX][endY]=='#'){
                wall=true
                break
            }
            endX+=direc[instructions[i]]!!.first
            endY+=direc[instructions[i]]!!.second
        }
        if(!wall){
            endX=robotX+direc[instructions[i]]!!.first
            endY=robotY+direc[instructions[i]]!!.second
            maze[robotX][robotY]='.'
            maze[endX][endY]='@'
            robotX=endX
            robotY=endY
            for(j in 1..boxes){
                endX+=direc[instructions[i]]!!.first
                endY+=direc[instructions[i]]!!.second
                maze[endX][endY]='O'
            }
        }
    }
    var gps=0
    for(i in 0..side-1){
        for(j in 0..side-1){
            if(maze[i][j]=='O'){
                gps+=100*i+j
            }
        }
    }
    println(gps)
}
