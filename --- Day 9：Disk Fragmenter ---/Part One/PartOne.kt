fun main(){
    val drive=readln()
    val numbers=drive.map{
        it.toString().toInt()
    }
    val len=drive.length
    val driveList=ArrayList<Int>()
    var curID=0
    var tot=0
    for (i in 0..len-1){
        if(i%2==1) {
            for (j in 1..numbers[i]){
                driveList.add(-1)
            }
            tot+=numbers[i]
        }
        else{
            for (j in 1..numbers[i]){
                driveList.add(curID)
            }
            curID++
            tot+=numbers[i]
        }
    }
    var lastEmpty=-1
    for (i in 0..tot-1){
        if(driveList[i]==-1){
            lastEmpty=i
            break
        }
    }
    for (i in tot-1 downTo 0){
        if(lastEmpty==-1||i<=lastEmpty){
            break
        }
        if(driveList[i]!=-1){
            driveList[lastEmpty]=driveList[i]
            driveList[i]=-1
            while(driveList[lastEmpty]!=-1){
                lastEmpty++
            }
        }
    }
    var ans=0.toLong()
    for (i in 0.toLong()..tot-1){
        if(driveList[i.toInt()]==-1){
            break
        }
        ans+=driveList[i.toInt()].toLong()*i
    }
    println(ans)
}
