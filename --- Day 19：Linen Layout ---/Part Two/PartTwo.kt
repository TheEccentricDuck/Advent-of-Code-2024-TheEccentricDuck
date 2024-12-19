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
    var tot=0L
    for(i in 1..designs){
        val design=readln()
        val len=design.length
        val dp=Array<Long>(len+1){0}
        dp[0]=1
        for(i in 0..len-1){
            if(dp[i]>0){
                var curStr=design[i].toString()
                for(j in i+1..len){
                    if(exists[curStr]==true){
                        dp[j]+=dp[i]
                    }
                    if(j!=len){
                        curStr+=design[j]
                    }
                }
            }
        }
        tot+=dp[len]
    }
    println(tot)
}
