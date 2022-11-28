val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() {
    val binaryTree = BinaryTree()

    val n = br.readLine().toInt()

    repeat (n) {
        val (root, left, right) = br.readLine().split(' ').map {it.single()}
        binaryTree.add(root, left, right)
    }
    br.close()

    binaryTree.preorder(binaryTree.root!!)
    bw.newLine()
    binaryTree.inorder(binaryTree.root!!)
    bw.newLine()
    binaryTree.postorder(binaryTree.root!!)

    bw.flush()
    bw.close()
}

data class Node(
    var data: Char,
    var left: Node? = null,
    var right: Node? = null,
)

class BinaryTree {
    var root: Node? = null;
    fun add(data: Char, left: Char, right: Char) {
        if(root == null) {
            root = Node(data)
            if(left != '.') root!!.left = Node(left)
            if(right != '.') root!!.right = Node(right)
        }
        else search(root!!, data, left, right)
    }

    fun search(parent: Node, data: Char, left: Char, right: Char) {
        if(parent.data == data) {
            if(left != '.') parent.left = Node(left)
            if(right != '.') parent.right = Node(right)
        }
        else {
            parent.left?.let { search(it, data, left, right) }
            parent.right?.let { search(it, data, left, right) }
        }
    }

    fun preorder(root: Node) {
        bw.write(root.data.toString())
        root.left?.let { preorder(root.left!!) }
        root.right?.let { preorder(root.right!!) }
    }

    fun inorder(root: Node) {
        root.left?.let { inorder(root.left!!) }
        bw.write(root.data.toString())
        root.right?.let { inorder(root.right!!) }
    }

    fun postorder(root: Node) {
        root.left?.let { postorder(root.left!!) }
        root.right?.let { postorder(root.right!!) }
        bw.write(root.data.toString())
    }
}