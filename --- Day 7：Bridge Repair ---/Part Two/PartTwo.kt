fun main(){
    val n=850
    var tot=0.toLong()
    for (i in 1..n){
        val puzzle=readln().split(": ")
        val target=puzzle[0].toLong()
        val numsStr=puzzle[1].split(' ')
        val l=numsStr.size
        val numsLong=ArrayList<Long>()
        for (j in 0..l-1){
            numsLong.add(numsStr[j].toLong())
        }
        var can=false
        var fin=1
        for (j in 0..l-1){
            fin*=3
        }
        fin-=1
        for (j in 0..fin){
            var cur=numsLong[0]
            var bits=j
            for (k in 1..l-1){
                if (bits%3==1){
                    cur*=numsLong[k]
                } else if (bits%3==0){
                    cur+=numsLong[k]
                } else{
                    cur=(cur.toString()+numsLong[k].toString()).toLong()
                }
                bits/=3
            }
            if (cur==target){
                can=true
                break
            }
        }
        if (can){
            tot+=target
        }
    }
    println(tot)
}
