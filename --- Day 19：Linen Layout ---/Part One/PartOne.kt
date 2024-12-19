// 2024/12/19
import kotlin.collections.ArrayDeque
fun main(){
    val designs=400
    val exists=mutableMapOf<String,Boolean>()
    val towels=readln().split(", ")
    for(i in towels){
        exists[i]=true
    }
    readln()
    var tot=0
    for(i in 1..designs){
        val design=readln()
        val len=design.length
        val has=Array<Boolean>(len+1){false}
        val curs=ArrayDeque<Int>()
        has[0]=true
        curs.add(0)
        while(!curs.isEmpty()){
            val cur=curs.removeFirst()
            if(cur==len){
                break
            }
            var curStr=design[cur].toString()
            for(i in cur+1..len){
                if(exists[curStr]==true&&!has[i]){
                    curs.add(i)
                    has[i]=true
                }
                if(i!=len){
                    curStr+=design[i]
                }
            }
        }
        if(has[len]){
            tot++
        }
    }
    println(tot)
}
