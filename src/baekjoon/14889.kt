import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs
import kotlin.math.min

var n = 0
val s = ArrayList<List<Int>>()
var answer = Int.MAX_VALUE
var visited = Array<Boolean>(20) { false }

fun pickMembers(start: Int, depth: Int) {
    if(depth == n/2) {
        var sum1 = 0
        var sum2 = 0
        for(i in 0 until n) {
            if(visited[i]) {
                for(j in 0 until n) {
                    if(i != j && visited[j]) {
                        sum1 += s[i][j]
                    }
                }
            }
            else {
                for(j in 0 until n) {
                    if(i != j && !visited[j]) {
                        sum2 += s[i][j]
                    }
                }
            }
        }
        answer = min(answer, abs(sum1 - sum2))

        return
    }

    for(i in start until n) {
        visited[i] = true
        pickMembers(i + 1, depth + 1)
        visited[i] = false
    }
}

fun main() {
    var br = BufferedReader(InputStreamReader(System.`in`))
    var bw = BufferedWriter(OutputStreamWriter(System.out))

    n = br.readLine().toInt()
    for (i in 0 until n) {
        val line = br.readLine().split(' ').map { it.toInt() }
        s.add(line)
    }

    pickMembers(0, 0)

    bw.write(answer.toString())

    br.close()
    bw.flush()
    bw.close()
}