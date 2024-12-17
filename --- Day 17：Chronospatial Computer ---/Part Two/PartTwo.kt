// 2024/12/17
import kotlin.math.min
fun combo(cur:Long,a:Long,b:Long,c:Long):Long{
    if(cur<4){
        return cur
    }
    if(cur==4L){
        return a
    }
    if(cur==5L){
        return b
    }
    return c
}

fun dfs(curBit:Long,cur:Long,instructions:List<Long>,aI:Long,bI:Long,cI:Long):Long{
    if(curBit==-1L){
        return cur
    }
    var a=aI
    var b=bI
    var c=cI
    var curs=cur
    val addN=Math.pow(8.toDouble(),curBit.toDouble()).toLong()
    var nextN=arrayListOf<Long>()
    for(i in 1..8){
        a=curs
        b=bI
        c=cI
        var curPointer=0
        val ans=arrayListOf<Long>()
        while(curPointer<instructions.size){
            if(instructions[curPointer]==0L){
                a/=Math.pow(2.0,combo(instructions[curPointer+1],a,b,c).toDouble()).toLong()
                curPointer+=2
            }else if(instructions[curPointer]==1L){
                b=b xor instructions[curPointer+1]
                curPointer+=2
            }else if(instructions[curPointer]==2L){
                b=combo(instructions[curPointer+1],a,b,c)%8
                curPointer+=2
            }else if(instructions[curPointer]==3L){
                if(a==0L){
                    curPointer+=2
                }else{
                    curPointer=instructions[curPointer+1].toInt()
                }
            }else if(instructions[curPointer]==4L){
                b=b xor c
                curPointer+=2
            }else if(instructions[curPointer]==5L){
                ans.add(combo(instructions[curPointer+1],a,b,c)%8)
                curPointer+=2
            }else if(instructions[curPointer]==6L){
                b=a/Math.pow(2.0,combo(instructions[curPointer+1],a,b,c).toDouble()).toInt()
                curPointer+=2
            }else{
                c=a/Math.pow(2.0,combo(instructions[curPointer+1],a,b,c).toDouble()).toInt()
                curPointer+=2
            }
        }
        if(ans.size==instructions.size&&ans[curBit.toInt()]==instructions[curBit.toInt()]){
            nextN.add(curs)
        }
        curs+=addN
    }
    if(nextN.isEmpty()){
        return Long.MAX_VALUE
    }
    var minN=Long.MAX_VALUE
    for(i in nextN){
        minN=min(dfs(curBit-1,i,instructions,aI,bI,cI),minN)
    }
    return minN
}

fun main(){
    var a=readln().split(": ")[1].toLong()
    var b=readln().split(": ")[1].toLong()
    var c=readln().split(": ")[1].toLong()
    readln()
    val program=readln().split(": ")[1]
    var instructionsStr=program.split(",")
    var instructions=instructionsStr.map{it.toLong()}
    println(dfs((instructions.size-1).toLong(),0L,instructions,a,b,c))
}
