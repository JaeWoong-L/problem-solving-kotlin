val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

lateinit var graph: Array<ArrayList<Int>>
lateinit var size: IntArray
lateinit var visited: BooleanArray

fun main() {
    val (n, r, q) = br.readLine().split(' ').map { it.toInt() }
    graph = Array(n + 1) { ArrayList() }
    size = IntArray(n + 1) { 1 }
    visited = BooleanArray(n + 1)
    repeat(n - 1) {
        val (u, v) = br.readLine().split(' ').map { it.toInt() }
        graph[u].add(v)
        graph[v].add(u)
    }

    countSubtreeNodes(r)

    repeat(q) {
        val node = br.readLine().toInt()
        bw.write("${size[node]}\n")
    }

    bw.flush()
    bw.close()
}

fun countSubtreeNodes(curr: Int) {
    visited[curr] = true
    for(child in graph[curr]) {
        if(!visited[child]) {
            countSubtreeNodes(child)
            size[curr] += size[child]
        }
    }
}