import java.util.*

fun main() {
    var answer = 0

    val sc = Scanner(System.`in`)

    val n = sc.nextInt()
    var k = sc.nextInt()

    val coins = IntArray(n)

    for(i in n-1 downTo 0) {
        coins[i] = sc.nextInt()
    }

    for(coin in coins) {
        if(k >= coin) {
            answer += k / coin
            k %= coin
        }
    }

    print(answer)
}