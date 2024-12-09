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
    var itInd=tot-1
    for (i in curID-1 downTo 0){
        while(driveList[itInd]!=i){
            itInd--
        }
        if(lastEmpty>itInd){
            break
        }
        var available=0
        var availableInd=-1
        for (j in lastEmpty..tot-1){
            if(j>=itInd){
                break
            }
            if(driveList[j]==-1){
                available++
            }
            else{
                if(available>=numbers[2*i]){
                    availableInd=j-available
                    break
                }
                available=0
            }
        }
        if(availableInd!=-1){
            while(driveList[itInd]==i){
                driveList[itInd]=-1
                itInd--
            }
            for (j in availableInd..availableInd+numbers[2*i]-1){
                driveList[j]=i
            }
            while(driveList[lastEmpty]!=-1){
                lastEmpty++
            }
        }
    }
    var ans=0.toLong()
    for (i in 0.toLong()..tot-1){
        if(driveList[i.toInt()]!=-1){
            ans+=driveList[i.toInt()].toLong()*i
        }
    }
    println(ans)
}
