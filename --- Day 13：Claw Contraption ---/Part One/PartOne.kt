// 2024/12/13
import kotlin.Byte.Companion.MAX_VALUE
import kotlin.math.min

fun main(){
    val machines=320
    var totPrice=0
    for(i in 1..machines){
        val buttonA=readln().split("+",", ")
        val buttonB=readln().split("+",", ")
        val prize=readln().split("=",", ")
        readln()
        val aX=buttonA[1].toInt()
        val aY=buttonA[3].toInt()
        val bX=buttonB[1].toInt()
        val bY=buttonB[3].toInt()
        val prizeX=prize[1].toInt()
        val prizeY=prize[3].toInt()
        var maxA=min(prizeX/aX,prizeY/aY)
        maxA=min(100,maxA)
        var minPrice=Int.MAX_VALUE
        for(j in 0..maxA){
            var has=false
            var cur=3*j
            val needX=prizeX-(aX*j)
            val needY=prizeY-(aY*j)
            if(needX>=0&&needY>=0){
                if(needX%bX==0&&needY%bY==0&&needX/bX==needY/bY&&needX/bX<=100){
                    cur+=needX/bX
                    has=true
                }
            }
            if(has){
                minPrice=min(minPrice,cur)
            }
        }
        if(minPrice!=Int.MAX_VALUE){
            totPrice+=minPrice
        }
    }
    println(totPrice)
}
