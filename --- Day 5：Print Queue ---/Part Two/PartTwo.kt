var has=BooleanArray(100){false}
fun main(){
    val rules=1176
    val updates=199
    val rulesArr = Array<ArrayList<Int>>(100){arrayListOf<Int>()}
    val allow=Array<ArrayList<Int>>(100){arrayListOf<Int>()}
    for (i in 1..rules){
        val curs=readln().split('|')
        rulesArr[curs[1].toInt()].add(curs[0].toInt())
        allow[curs[0].toInt()].add(curs[1].toInt())
    }
    readln()
    var tot=0
    for (i in 1..updates){
        val curs=readln().split(',')
        var cursInt=ArrayList<Int>()
        val pre=BooleanArray(100){false}
        var wasFalse=false
        var addN=0
        for (j in 0..curs.size-1){
            cursInt.add(curs[j].toInt())
            pre[cursInt[j]]=true
        }
        while (true){
            var can=true
            for (j in 0..99){
                has[j]=false
            }
            for (j in 0..curs.size-1){
                val cur=cursInt[j]
                for (k in rulesArr[cur]){
                    if (pre[k]&&!has[k]){
                        for (l in j+1..curs.size-1){
                            if (cursInt[l]==k){
                                (cursInt[j] to cursInt[l]).apply{
                                    cursInt[j]=second
                                    cursInt[l]=first
                                }
                                break
                            }
                        }
                        wasFalse=true
                        can=false
                        break
                    }
                }
                if (!can){
                    break
                }
                has[cur]=true
                if (j==curs.size/2){
                    addN=cur
                }
            }
            if (can){
                break
            }
        }
        if (wasFalse){
            tot+=addN
        }
        println("Finished: $i")
    }
    println(tot)
}
