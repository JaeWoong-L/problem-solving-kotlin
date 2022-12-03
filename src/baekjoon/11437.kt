import kotlin.math.ceil
import kotlin.math.log

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()
var n = 0
var k = 0
lateinit var tree: Array<MutableList<Int>>
lateinit var parent: Array<IntArray>
lateinit var depths: IntArray
lateinit var visited: BooleanArray

fun main() {
    n = br.readLine().toInt() // 노드 개수
    k = ceil(log(n.toDouble(), 2.0)).toInt() // 최대 depth
    tree = Array(n + 1) { mutableListOf() } // 두 노드 사이의 관계 그래프
    parent = Array(n + 1) { IntArray(k + 1) } // sparse table
    depths = IntArray(n + 1)
    visited = BooleanArray(n + 1) { false }
    for(i in 0 until n - 1) {
        val (a, b) = br.readLine().split(' ').map { it.toInt() }
        tree[a].add(b)
        tree[b].add(a)
    }
    dfs(1, 1, 0)

    for(j in 1 ..k) {
        for(i in 1..n) {
            parent[i][j] = parent[parent[i][j - 1]][j - 1]
        }
    }

    val m = br.readLine().toInt()
    repeat(m) {
        val (a, b) = br.readLine().split(' ').map { it.toInt() }
        bw.write("${lca(a, b)}\n")
    }

    bw.flush()
    bw.close()
}

fun dfs(curr: Int, prev: Int, depth: Int) {
    visited[curr] = true
    depths[curr] = depth
    parent[curr][0] = prev
    for(i in tree[curr].indices) {
        if(!visited[tree[curr][i]]) {
            dfs(tree[curr][i], curr, depth + 1)
        }
    }
}

fun lca(a: Int, b: Int): Int {
    var nodeA = a
    var nodeB = b

    // nodeB의 depth가 항상 더 깊도록
    if(depths[nodeA] > depths[nodeB]) {
        val tmp = nodeA
        nodeA = nodeB
        nodeB = tmp
    }

    // 둘의 depth를 동일하게 맞춰줌
    for(i in k downTo 0) {
        val diff = depths[nodeB] - depths[nodeA]
        if(diff >= (1 shl i)) nodeB = parent[nodeB][i]
    }

    // 둘이 부모, 자식 관계였다면 바로 return
    if(nodeA == nodeB) return nodeA

    // 공통 부모가 나올 때까지 올라가며 탐색
    for(i in k downTo 0) {
        if(parent[nodeA][i] != parent[nodeB][i]) {
            nodeA = parent[nodeA][i]
            nodeB = parent[nodeB][i]
        }
    }

    return parent[nodeA][0]
}