val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()
lateinit var children: Array<MutableList<Int>>
var root = 0
var targetNode = 0
var leafCount = 0

fun main() {
    val n = br.readLine().toInt() // 1 <= n <= 50 자연수
    children = Array(n) { mutableListOf() }
    val nodes = br.readLine().split(' ').map {it.toInt()}
    for(i in 0 until n) {
        val node = nodes[i]
        if(node == -1) {
            root = i
        }
        else {
            children[node].add(i)
        }
    }
    targetNode = br.readLine().toInt()

    solve(root)
    bw.write(leafCount.toString())

    bw.flush()
    bw.close()
}

fun solve(node: Int) {
    if(node == targetNode) return
    else if(children[node].isEmpty()) {
        leafCount++
        return
    }
    else {
        var isLeaf = true
        for(i in children[node]) {
            if(i != targetNode) {
                solve(i)
                isLeaf = false
            }
        }
        if(isLeaf) {
            leafCount++
            return
        }
    }
}