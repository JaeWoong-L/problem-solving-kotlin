import kotlin.math.min

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

var n = 0
lateinit var graph: Array<ArrayList<Int>>
lateinit var dp: Array<IntArray>
lateinit var visited: BooleanArray

fun main() {
    n = br.readLine().toInt()
    graph = Array(n + 1) { ArrayList() }
    dp = Array(n + 1) { IntArray(2) }
    visited = BooleanArray(n + 1)
    repeat(n - 1) {
        val (u, v) = br.readLine().split(' ').map { it.toInt() }
        graph[u].add(v)
        graph[v].add(u)
    }
    br.close()

    dfs(1)
    bw.write(min(dp[1][0], dp[1][1]).toString())

    bw.flush()
    bw.close()
}

fun dfs(curr: Int) {
    visited[curr] = true
    dp[curr][0] = 0
    dp[curr][1] = 1
    for(next in graph[curr]) {
        if(!visited[next]) {
            dfs(next)
            dp[curr][0] += dp[next][1]
            dp[curr][1] += min(dp[next][0], dp[next][1])
        }
    }
}