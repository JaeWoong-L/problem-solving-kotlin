import kotlin.math.pow

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()
lateinit var inOrder: IntArray
lateinit var levelOrder: Array<MutableList<Int>>

fun main() {
    val k = br.readLine().toInt()
    inOrder = br.readLine().split(' ').map { it.toInt() }.toIntArray()
    levelOrder = Array(k) { mutableListOf() }

    solve(0, 2.0.pow(k).toInt() - 2, 0)
    for(line in levelOrder) {
        for(node in line) {
            bw.write("$node ")
        }
        bw.newLine()
    }

    bw.flush()
    bw.close()
}

fun solve(in_start: Int, in_end: Int, depth: Int) {
    if (in_start > in_end) return

    val rootIndex = (in_start + in_end) / 2
    val root = inOrder[rootIndex]
    levelOrder[depth].add(root)

    solve(in_start, rootIndex - 1, depth + 1)
    solve(rootIndex + 1, in_end, depth + 1)
}