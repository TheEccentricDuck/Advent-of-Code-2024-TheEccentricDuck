fun main(){
    val length=130
    val maze=ArrayList<String>()
    var guardx=0
    var guardy=0
    val direc=Array<Pair<Int,Int>>(4){Pair(-1,0)}
    direc[1]=Pair(0,1)
    direc[2]=Pair(1,0)
    direc[3]=Pair(0,-1)
    var found=false
    for (i in 1..length){
        maze.add(readln())
        if(!found){
            for (j in 0..length-1){
                if (maze[i-1][j]=='^'){
                    guardx=i-1
                    guardy=j
                    found=true
                    break
                }
            }
        }
    }
    var curDirec=0
    val curPos=ArrayDeque<Pair<Int,Int>>()
    val has=Array<Array<Boolean>>(length+1){Array<Boolean>(length+1){false} }
    curPos.add(Pair(guardx,guardy))
    while (!curPos.isEmpty()){
        val cur=curPos.removeFirst()
        val newX=cur.first+direc[curDirec].first
        val newY=cur.second+direc[curDirec].second
        has[cur.first][cur.second]=true
        if (newX<0||newX>=length||newY<0||newY>=length){
            break
        }
        if(maze[newX][newY]=='#'){
            curDirec++
            curDirec%=4
            curPos.add(cur)
        }
        else{
            curPos.add(Pair(newX,newY))
        }
    }
    var positions=0
    for(i in 0..length-1){
        for(j in 0..length-1){
            if(has[i][j]){
                positions++
            }
        }
    }
    println(positions)
}
