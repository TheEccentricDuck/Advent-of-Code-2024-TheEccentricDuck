fun main(){
    var nums=readln().split(" ").map{ it.toLong() }
    for(i in 1..25){
        val newNums=arrayListOf<Long>()
        val length=nums.size
        for(j in 0..length-1){
            val numStr=nums[j].toString()
            if(nums[j]==0.toLong()){
                newNums.add(1)
            }else if(numStr.length%2==0){
                newNums.add(numStr.slice(0..numStr.length/2-1).toLong())
                newNums.add(numStr.slice(numStr.length/2..numStr.length-1).toLong())
            }else{
                newNums.add(nums[j]*2024)
            }
        }
        nums=newNums
    }
    println(nums.size)
}
