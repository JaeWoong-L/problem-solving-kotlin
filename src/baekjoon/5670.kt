val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() {
    while(true) {
        val str = br.readLine()
        if(str.isNullOrBlank()) break

        val n = str.toInt()
        val words = ArrayList<String>()
        val trie = Trie()
        repeat(n) {
            val word = br.readLine()
            words.add(word)
            trie.insert(word)
        }

        var sum = 0
        for(word in words) {
            sum += trie.getCount(word)
        }

        val avg: Double = sum / n.toDouble()
        bw.write(String.format("%.2f", avg))
        bw.newLine()
    }

    br.close()
    bw.flush()
    bw.close()
}

class TrieNode(var endOfWord: Boolean = false, var children: MutableMap<Char, TrieNode> = HashMap()) {}

class Trie(val root: TrieNode = TrieNode()) {

    fun insert(word: String) {
        var currNode = root
        for(ch in word) {
            currNode = currNode.children.computeIfAbsent(ch) { TrieNode() }
        }
        currNode.endOfWord = true
    }

    fun getCount(word: String): Int {
        var currNode = root.children[word[0]]
        var count = 1
        for(i in 1 until word.length) {
            if(currNode!!.children.size >= 2) {
                count++
            }
            else if(currNode.children.size == 1 && currNode.endOfWord) {
                count++
            }
            currNode = currNode.children[word[i]]
        }

        return count
    }
}