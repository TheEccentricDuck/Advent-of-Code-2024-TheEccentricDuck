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
                var sides=0
                stack.add(Pair(i,j))
                while(!stack.isEmpty()){
                    val pair=stack.removeFirst()
                    val isDirecs=arrayListOf<Int>()
                    var adjCount=0
                    val adj=arrayListOf<Int>()
                    for(k in 0..3){
                        val newX=pair.first+direc[k].first
                        val newY=pair.second+direc[k].second
                        if(newX<0||newX>=side||newY<0||newY>=side){
                            isDirecs.add(k)
                        }else{
                            if(plot[newX][newY]!=curChar){
                                isDirecs.add(k)
                            }else{
                                if(!has[newX][newY]){
                                    area++
                                    has[newX][newY]=true
                                    stack.add(Pair(newX,newY))
                                }
                                adjCount++
                                adj.add(k)
                            }
                        }
                    }
                    if(adjCount>=2){
                        if(0 in adj&&1 in adj){
                            val newX=pair.first+direc[0].first+direc[1].first
                            val newY=pair.second+direc[0].second+direc[1].second
                            if(plot[newX][newY]!=curChar){
                                sides++
                            }
                        }
                        if(2 in adj&&1 in adj){
                            val newX=pair.first+direc[2].first+direc[1].first
                            val newY=pair.second+direc[2].second+direc[1].second
                            if(plot[newX][newY]!=curChar){
                                sides++
                            }
                        }
                        if(2 in adj&&3 in adj){
                            val newX=pair.first+direc[2].first+direc[3].first
                            val newY=pair.second+direc[2].second+direc[3].second
                            if(plot[newX][newY]!=curChar){
                                sides++
                            }
                        }
                        if(0 in adj&&3 in adj){
                            val newX=pair.first+direc[0].first+direc[3].first
                            val newY=pair.second+direc[0].second+direc[3].second
                            if(plot[newX][newY]!=curChar){
                                sides++
                            }
                        }
                    }
                    if(isDirecs.size==4){
                        sides+=4
                    }else if(isDirecs.size==3){
                        sides+=2
                    }else if((0 in isDirecs&&3 in isDirecs)||(1 in isDirecs&&2 in isDirecs)||(2 in isDirecs&&3 in isDirecs)||(0 in isDirecs&&1 in isDirecs)){
                        sides++
                    }
                }
                tot+=sides*area
            }
        }
    }
    println(tot)
}
