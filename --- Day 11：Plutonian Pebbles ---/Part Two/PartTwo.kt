fun main(){
    var nums=readln().split(" ").map{ Pair(it.toLong(),1.toLong()) }.toMutableList()
    for(i in 1..75){
        val newNums=mutableMapOf<Long,Long>()
        for(j in nums){
            val numStr=j.first.toString()
            if(j.first==0.toLong()){
                if(newNums.containsKey(1)){
                    newNums[1]=newNums[1]!!+j.second
                }else{
                    newNums[1]=j.second
                }
            }else if(numStr.length%2==0){
                val numOne=numStr.slice(0..numStr.length/2-1).toLong();
                val numTwo=numStr.slice(numStr.length/2..numStr.length-1).toLong()
                if(newNums.containsKey(numOne)){
                    newNums[numOne]=newNums[numOne]!!+j.second
                }else{
                    newNums[numOne]=j.second
                }
                if(newNums.containsKey(numTwo)){
                    newNums[numTwo]=newNums[numTwo]!!+j.second
                }else{
                    newNums[numTwo]=j.second
                }
            }else{
                val num=2024*j.first
                if(newNums.containsKey(num)){
                    newNums[num]=newNums[num]!!+j.second
                }else{
                    newNums[num]=j.second
                }
            }
        }
        nums.clear()
        for(j in newNums){
            nums.add(j.toPair())
        }
    }
    var tot=0.toLong()
    for(i in nums){
        tot+=i.second
    }
    println(tot)
}
