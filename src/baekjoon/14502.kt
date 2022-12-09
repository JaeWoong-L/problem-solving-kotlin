import java.util.*
import kotlin.collections.ArrayList

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

var n = 0
var m = 0
lateinit var board: Array<IntArray>
lateinit var virusBoard: Array<IntArray>
lateinit var virusIndices : ArrayList<Point>
val dx = intArrayOf(1, -1, 0, 0)
val dy = intArrayOf(0, 0, 1, -1)
var answer = 0

fun main() {
    val st = StringTokenizer(br.readLine())
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()
    board = Array(n) { IntArray(m) }
    virusBoard = Array(n) { IntArray(m) }
    virusIndices = ArrayList()
    for (i in 0 until n) {
        board[i] = br.readLine().split(' ').map { it.toInt() }.toIntArray()
        for (j in 0 until m) {
            if (board[i][j] == 2) {
                virusIndices.add(Point(i, j))
            }
        }
    }
    br.close()

    solve(3, 0)

    bw.write(answer.toString())
    bw.flush()
    bw.close()
}

fun solve(remains: Int, start: Int) {
    if (remains == 0) {
        for (i in 0 until n) {
            for (j in 0 until m) {
                virusBoard[i][j] = board[i][j]
            }
        }
        for(virus in virusIndices) {
            dfs(virus.y, virus.x)
        }
        updateAnswer()
        return
    }

    for(i in start until n * m) {
        val y = i / m
        val x = i % m

        if(board[y][x] == 0) {
            board[y][x] = 1
            solve(remains - 1, i + 1)
            board[y][x] = 0
        }
    }
}

fun dfs(y: Int, x: Int) {
    for (i in 0 until 4) {
        val ny = y + dy[i]
        val nx = x + dx[i]

        if (ny in 0 until n && nx in 0 until m) {
            if (virusBoard[ny][nx] == 0) {
                virusBoard[ny][nx] = 2
                dfs(ny, nx)
            }
        }
    }
}

fun updateAnswer() {
    var count = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (virusBoard[i][j] == 0) {
                count++
            }
        }
    }
    if (count > answer) {
        answer = count
    }
}

data class Point(val y: Int, val x: Int)