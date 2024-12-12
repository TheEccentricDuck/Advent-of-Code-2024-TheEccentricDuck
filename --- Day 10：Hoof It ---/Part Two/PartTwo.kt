val side=45
var heads=0
val maze=ArrayList<String>()
var direc=Array<Pair<Int,Int>>(4){Pair(0,0)}
fun dfs(x:Int,y:Int){
    val cur=maze[x][y].toString().toInt()
    if(cur==9){
        heads++
        return
    }
    for(i in 0..3){
        val newx=x+direc[i].first
        val newy=y+direc[i].second
        if(newx>=0&&newx<side&&newy>=0&&newy<side){
            val next=maze[newx][newy].toString().toInt()
            if(next==cur+1){
                dfs(newx,newy)
            }
        }
    }
}
fun main(){
    direc[0]=Pair(-1,0)
    direc[1]=Pair(0,1)
    direc[2]=Pair(1,0)
    direc[3]=Pair(0,-1)
    for(i in 1..side){
        maze.add(readln())
    }
    for(i in 0..side-1){
        for(j in 0..side-1){
            val cur=maze[i][j].toString().toInt()
            if(cur==0){
                dfs(i,j)
            }
        }
    }
    println(heads)
}
