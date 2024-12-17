// 2024/12/17
fun combo(cur:Int,a:Int,b:Int,c:Int):Int{
    if(cur<4){
        return cur
    }
    if(cur==4){
        return a
    }
    if(cur==5){
        return b
    }
    return c
}

fun main(){
    var a=readln().split(": ")[1].toInt()
    var b=readln().split(": ")[1].toInt()
    var c=readln().split(": ")[1].toInt()
    readln()
    val program=readln().split(": ")[1]
    var instructionsStr=program.split(",")
    var instructions=instructionsStr.map{it.toInt()}
    var curPointer=0
    var ans=""
    while(curPointer<instructions.size){
        if(instructions[curPointer]==0){
            a/=Math.pow(2.0,combo(instructions[curPointer+1],a,b,c).toDouble()).toInt()
            curPointer+=2
        }else if(instructions[curPointer]==1){
            b=b xor instructions[curPointer+1]
            curPointer+=2
        }else if(instructions[curPointer]==2){
            b=combo(instructions[curPointer+1],a,b,c)%8
            curPointer+=2
        }else if(instructions[curPointer]==3){
            if(a==0){
                curPointer+=2
            }else{
                curPointer=instructions[curPointer+1]
            }
        }else if(instructions[curPointer]==4){
            b=b xor c
            curPointer+=2
        }else if(instructions[curPointer]==5){
            if(ans.isEmpty()){
                ans=(combo(instructions[curPointer+1],a,b,c)%8).toString()
            }else{
                ans+=","+(combo(instructions[curPointer+1],a,b,c)%8).toString()
            }
            curPointer+=2
        }else if(instructions[curPointer]==6){
            b=a/Math.pow(2.0,combo(instructions[curPointer+1],a,b,c).toDouble()).toInt()
            curPointer+=2
        }else{
            c=a/Math.pow(2.0,combo(instructions[curPointer+1],a,b,c).toDouble()).toInt()
            curPointer+=2
        }
    }
    println(ans)
}
