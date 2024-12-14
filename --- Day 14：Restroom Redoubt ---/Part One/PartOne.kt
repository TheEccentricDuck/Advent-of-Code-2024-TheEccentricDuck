// 2024/12/14
data class Robot(var pX:Int,var pY:Int,var vX:Int,var vY:Int)

fun main(){
    val robotsN=500
    val width=101
    val height=103
    var robots=arrayListOf<Robot>()
    for(i in 1..robotsN){
        val line=readln().split(","," ","=")
        robots.add(Robot(line[1].toInt(),line[2].toInt(),line[4].toInt(),line[5].toInt()))
    }
    for(i in 1..100){
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
    }
    var quadOne=0
    var quadTwo=0
    var quadThree=0
    var quadFour=0
    for(i in 0..robotsN-1){
        if(robots[i].pY>height/2){
            if(robots[i].pX>width/2){
                quadFour++
            }else if(robots[i].pX<width/2){
                quadThree++
            }
        }else if(robots[i].pY<height/2){
            if(robots[i].pX>width/2){
                quadOne++
            }else if(robots[i].pX<width/2){
                quadTwo++
            }
        }
    }
    println(quadOne*quadTwo*quadThree*quadFour)
}
