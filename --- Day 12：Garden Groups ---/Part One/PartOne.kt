// 2024/12/12
fun main(){
    val side=140
    val plot=arrayListOf<String>()
    for(i in 1..side){
        plot.add(readln())
    }
    var has=Array<Array<Boolean>>(side){Array<Boolean>(side){false} }
    val direc=arrayOf(-1 to 0,0 to 1,1 to 0,0 to -1)
    var tot=0
    for(i in 0..side-1){
        for(j in 0..side-1){
            if(!has[i][j]){
                has[i][j]=true
                val curChar=plot[i][j]
                val stack=ArrayDeque<Pair<Int,Int>>()
                var area=1
                var perimeter=0
                stack.add(Pair(i,j))
                while(!stack.isEmpty()){
                    val pair=stack.removeFirst()
                    for(k in 0..3){
                        val newX=pair.first+direc[k].first
                        val newY=pair.second+direc[k].second
                        if(newX<0||newX>=side||newY<0||newY>=side){
                            perimeter++
                        }else{
                            if(plot[newX][newY]!=curChar){
                                perimeter++
                            }else if(!has[newX][newY]){
                                area++
                                has[newX][newY]=true
                                stack.add(Pair(newX,newY))
                            }
                        }
                    }
                }
                tot+=area*perimeter
            }
        }
    }
    println(tot)
}
