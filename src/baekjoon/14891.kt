import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.pow

const val WHEEL_NUM = 4
const val SAW_NUM = 8
const val RIGHT = 2
const val LEFT = 6

var wheels = MutableList<MutableList<Boolean>>(WHEEL_NUM) { MutableList<Boolean>(SAW_NUM) { false } } // N극: false, S극: true
val queries = Array<Query>(100) { Query(0, 0) }
var n = 0

var wheelState = Array<Int>(WHEEL_NUM) { 0 } // 0: 회전 x, 1: CW, -1: CCW

fun input() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    for (i in 0 until WHEEL_NUM) {
        val line = br.readLine()
        for (j in line.indices) {
            if (line[j] == '1') {
                wheels[i][j] = true
            }
        }
    }

    n = br.readLine().toInt()
    for (i in 0 until n) {
        val query = br.readLine().split(' ')
        queries[i] = Query(query[0].toInt() - 1, query[1].toInt())
    }

    br.close()
}

fun output(answer: String) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    print(answer)

    bw.flush()
    bw.close()
}

fun setState(num: Int, dir: Int) {
    if(wheelState[num] != 0) return

    wheelState[num] = dir
    if(num - 1 >= 0 && wheels[num - 1][RIGHT] != wheels[num][LEFT]) {
        setState(num - 1, dir * -1)
    }
    if(num + 1 < WHEEL_NUM && wheels[num][RIGHT] != wheels[num + 1][LEFT]) {
        setState(num + 1, dir * -1)
    }
}

fun rotate(num: Int, dir: Int) {
    if (dir == 1) { // CW
        val lastVal = wheels[num].last()
        wheels[num].removeLast()
        wheels[num].add(0, lastVal)
    } else if (dir == -1) { // CCW
        val firstVal = wheels[num].first()
        wheels[num].removeFirst()
        wheels[num].add(firstVal)
    }
}

fun calculateScore(): String {
    var score = 0
    for (i in 0 until 4) {
//        println("wheels[$i].first() == ${wheels[i].first()}")
        if (wheels[i].first()) { // 12시가 S극(true)이면 덧셈
            score += 2.0.pow(i.toDouble()).toInt()
        }
    }

    return score.toString()
}

fun solve(): String {
    for(i in 0 until n) {
        for(j in wheelState.indices) {
            wheelState[j] = 0
        }
        setState(queries[i].num, queries[i].dir)
        for (j in 0 until WHEEL_NUM) {
            rotate(j, wheelState[j])
        }
    }

    return calculateScore()
}

fun main() {

    input()
    val answer = solve()
    output(answer)

}

data class Query(var num: Int, var dir: Int)