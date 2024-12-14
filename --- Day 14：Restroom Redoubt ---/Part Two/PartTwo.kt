// 2024/12/14
import kotlin.math.abs

data class Robot(var pX:Int,var pY:Int,var vX:Int,var vY:Int)

fun main(){
    val robotsN=500
    val width=101
    val height=103
    var robots=arrayListOf<Robot>()
    var start=arrayListOf<Robot>()
    for(i in 1..robotsN){
        val line=readln().split(","," ","=")
        robots.add(Robot(line[1].toInt(),line[2].toInt(),line[4].toInt(),line[5].toInt()))
        start.add(Robot(line[1].toInt(),line[2].toInt(),line[4].toInt(),line[5].toInt()))
    }
    var minDist=Int.MAX_VALUE
    var minDistTime=0
    var i=1
    while(true){
        for(j in 0..robotsN-1){
            robots[j].pX+=robots[j].vX
            robots[j].pY+=robots[j].vY
            while(robots[j].pX<0){
                robots[j].pX+=width
            }
            while(robots[j].pX>=width){
                robots[j].pX-=width
            }
            while(robots[j].pY<0){
                robots[j].pY+=height
            }
            while(robots[j].pY>=height){
                robots[j].pY-=height
            }
        }
        var curDist=0
        for(j in 0..robotsN-1){
            for(k in j+1..robotsN-1){
                curDist+=abs(robots[k].pX-robots[j].pX)+abs(robots[k].pY-robots[j].pY)
            }
        }
        if(curDist<minDist){
            minDist=curDist
            minDistTime=i
        }
        i++
        var out=false
        for(j in 0..robotsN-1){
            if(robots[j]==start[j]){
                out=true
                break
            }
        }
        println("Finished: $i")
        if(out){
            break
        }
    }
    println(minDistTime)
}
