fun gcd(a: Int, b: Int): Int {
    var x=a
    var y=b
    while (y!=0) {
        val orig=y
        y=x%y
        x=orig
    }
    return x
}
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
                var difx=v[j].first-v[i].first
                var dify=v[j].second-v[i].second
                val div=gcd(difx,dify)
                difx/=div
                dify/=div
                var newx=v[j].first+difx
                var newy=v[j].second+dify
                while (newx>=0&&newx<side&&newy>=0&&newy<side){
                    antiNodes[Pair(newx,newy)]=0
                    newx+=difx
                    newy+=dify
                }
                newx=v[i].first-difx
                newy=v[i].second-dify
                while (newx>=0&&newx<side&&newy>=0&&newy<side){
                    antiNodes[Pair(newx,newy)]=0
                    newx-=difx
                    newy-=dify
                }
            }
        }
        if(len>=2){
            for(i in 0..len-1){
                antiNodes[Pair(v[i].first,v[i].second)]=0
            }
        }
    }
    println(antiNodes.size)
}
