import java.util.*

val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

lateinit var tree: Array<ArrayList<Relation>>
lateinit var visited: BooleanArray
var answer = 0
var answerNode = 0
const val ROOT = 1

fun main() {
    val n = br.readLine().toInt()
    tree = Array(n + 1) { ArrayList() }
    visited = BooleanArray(n + 1)
    repeat(n - 1) {
        val st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val w = st.nextToken().toInt()
        tree[a].add(Relation(b, w))
        tree[b].add(Relation(a, w))
    }
    br.close()

    getMaxDistanceFrom(ROOT, 0)
    visited = BooleanArray(n + 1)
    getMaxDistanceFrom(answerNode, 0)

    bw.write(answer.toString())

    bw.flush()
    bw.close()
}

fun getMaxDistanceFrom(curr: Int, dist: Int) {
    if (visited[curr]) return

    visited[curr] = true
    if (answer < dist) {
        answer = dist
        answerNode = curr
    }
    for (node in tree[curr]) {
        getMaxDistanceFrom(node.index, dist + node.weight)
    }
}

class Relation(val index: Int, val weight: Int)