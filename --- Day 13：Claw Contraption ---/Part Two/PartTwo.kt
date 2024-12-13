// 2024/12/13
import kotlin.Byte.Companion.MAX_VALUE
import kotlin.math.min

fun main(){
    val machines=320
    var totPrice=0.toLong()
    for(i in 1..machines){
        val buttonA=readln().split("+",", ")
        val buttonB=readln().split("+",", ")
        val prize=readln().split("=",", ")
        readln()
        var aX=buttonA[1].toLong()
        var aY=buttonA[3].toLong()
        var bX=buttonB[1].toLong()
        var bY=buttonB[3].toLong()
        var prizeX=prize[1].toLong()+10000000000000
        var prizeY=prize[3].toLong()+10000000000000
        val adbc=aX*bY-(bX*aY)
        if(adbc!=0.toLong()){
            val d=bY
            val nB=-bX
            val nC=-aY
            val a=aX
            var top=d*prizeX+nB*prizeY
            var bot=nC*prizeX+a*prizeY
            if(top%adbc==0.toLong()&&bot%adbc==0.toLong()){
                top/=adbc
                bot/=adbc
                if(top>=0&&bot>=0){
                    totPrice+=3*top+bot
                }
            }
        }
    }
    println(totPrice)
}
