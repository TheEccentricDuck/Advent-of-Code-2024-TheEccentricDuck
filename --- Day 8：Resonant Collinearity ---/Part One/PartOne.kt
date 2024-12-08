fun main(){
    val side=50
    val antiNodes=mutableMapOf<Pair<Int,Int>,Int>()
    val locs=mutableMapOf<Char,ArrayList<Pair<Int,Int>>>()
    val maze=Array<String>(side){""}
    for (i in 0..side-1) {
        maze[i]=readln()
        for (j in 0..side-1){
            if(maze[i][j]!='.'){
                try {
                    locs[maze[i][j]]!!.add(Pair(i,j))
                } catch(e:NullPointerException){
                    locs[maze[i][j]]=arrayListOf<Pair<Int,Int>>()
                    locs[maze[i][j]]!!.add(Pair(i,j))
                }
            }
        }
    }
    locs.forEach{(k,v)->
        val len=v.size
        for (i in 0..len-2){
            for (j in i+1..len-1){
                val difx=v[j].first-v[i].first
                val dify=v[j].second-v[i].second
                var newx=v[j].first+difx
                var newy=v[j].second+dify
                if (newx>=0&&newx<side&&newy>=0&&newy<side){
                    antiNodes[Pair(newx,newy)]=0
                }
                newx=v[i].first-difx
                newy=v[i].second-dify
                if (newx>=0&&newx<side&&newy>=0&&newy<side){
                    antiNodes[Pair(newx,newy)]=0
                }
            }
        }
    }
    println(antiNodes.size)
}
