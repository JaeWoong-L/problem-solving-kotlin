import kotlin.math.sqrt

/*
class Solution {
    fun solution(n: Int, k: Int): Int {
        return n.toString(k)
            .split('0')
            .count { it != "1" && it != "" && isPrimeNumber(it) }
    }

    private fun isPrimeNumber(n: String): Boolean {
        var num = n.toLong()
        if (num < 2) return false

        for (i in 2..sqrt(num.toDouble()).toInt()) {
            if (num % i == 0L) {
                return false
            }
        }
        return true
    }
}
*/

class Solution {
    fun solution(n: Int, k: Int): Int {
        return n.toString(k)
            .split('0')
            .count { it != "1" && it != "" && isPrimeNumber(it) }
    }

    private fun isPrimeNumber(n: String): Boolean {
        var num = n.toLong()
        if (num < 2) return false

        for (i in 2..sqrt(num.toDouble()).toInt()) {
            if (num % i == 0L) {
                return false
            }
        }
        return true
    }
}

fun main() {
    val n = 1100110001
    val k = 10
    val answer = Solution().solution(n, k)
    print(answer)
}


