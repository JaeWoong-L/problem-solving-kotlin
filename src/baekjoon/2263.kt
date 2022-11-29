// 트리의 순회
val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()
lateinit var inOrder: List<Int>
lateinit var postOrder: List<Int>
val inOrderIdx = IntArray(100001)

fun main() {
    val n = br.readLine().toInt()

    inOrder = br.readLine().split(' ').map { it.toInt() }
    postOrder = br.readLine().split(' ').map { it.toInt() }
    br.close()

    for(i in inOrder.indices) {
        inOrderIdx[inOrder[i]] = i
    }

    solve(0, n-1, 0, n-1)

    bw.flush()
    bw.close()
}

fun solve(inStart: Int, inEnd: Int, postStart: Int, postEnd: Int) {
    if(inStart > inEnd || postStart > postEnd)
        return

    val root = postOrder[postEnd]
    bw.write("$root ")

    val rootIdx = inOrderIdx[root] // inOrder의 root 위치
    val left = rootIdx - inStart // 왼쪽 subtree의 노드 개수

    solve(inStart, rootIdx - 1, postStart, postStart + left - 1)
    solve(rootIdx + 1, inEnd, postStart + left, postEnd - 1)
}