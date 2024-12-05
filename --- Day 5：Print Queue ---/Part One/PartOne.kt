fun main(){
    var rules=1176
    var updates=199
    var rulesArr = Array<ArrayList<Int>>(100){arrayListOf<Int>()}
    for (i in 1..rules){
        var curs=readln().split('|')
        rulesArr[curs[1].toInt()].add(curs[0].toInt())
    }
    readln()
    var tot=0
    for (i in 1..updates){
        var curs=readln().split(',')
        var cursInt=ArrayList<Int>()
        var has=BooleanArray(100){false}
        var pre=BooleanArray(100){false}
        var can=true
        var addN=0
        for (j in 0..curs.size-1){
            cursInt.add(curs[j].toInt())
            pre[cursInt[j]]=true
        }
        for (j in 0..curs.size-1){
            var cur=cursInt[j]
            for (k in rulesArr[cur]){
                if (pre[k]&&!has[k]){
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
        tot+=addN
    }
    println(tot)
}
