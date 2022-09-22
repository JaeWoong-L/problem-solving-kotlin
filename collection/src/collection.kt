fun main() {
    val n = 11
    val r = 5
//    Selection(n).permutation(r)
//    println()
//    Selection(n).permutationWithReplacement(r)
//    println()
//    Selection(n).combination(r)
//    println()
//    Selection(n).combinationWithReplacement(r)
}

/**
 * {1, 2, ... , n} 배열에서 r개를 뽑는 함수.
 *
 * 순열, 중복순열, 조합, 중복조합.
 *
 * 출처: https://hanyeop.tistory.com/362
 */
class Selection(private val n: Int) {
    private val arr = IntArray(n) { it + 1 }

    fun permutation(count: Int, list: ArrayList<Int> = ArrayList<Int>()) {
        if(count == 0) {
            list.forEach { print("$it ") }
            println()
            return
        }

        for(i in 0 until n) {
            if(!list.contains(arr[i])) {
                list.add(arr[i])
                permutation(count - 1, list)
                list.removeLast()
            }
        }
    }

    fun permutationWithReplacement(count: Int, list: ArrayList<Int> = ArrayList<Int>()) {
        if(count == 0) {
            list.forEach { print("$it ") }
            println()
            return
        }

        for(i in 0 until n) {
            list.add(arr[i])
            permutationWithReplacement(count - 1, list)
            list.removeLast()
        }
    }

    fun combination(count: Int, index: Int = 0, list: ArrayList<Int> = ArrayList<Int>()) {
        if(count == 0) {
            list.forEach { print("$it ") }
            println()
            return
        }

        for(i in index until n) {
            list.add(arr[i])
            combination(count - 1, i + 1, list)
            list.removeLast()
        }
    }

    fun combinationWithReplacement(count: Int, index: Int = 0, list: ArrayList<Int> = ArrayList<Int>()) {
        if(count == 0) {
            list.forEach { print("$it ") }
            println()
            return
        }

        for(i in index until n) {
            list.add(arr[i])
            combinationWithReplacement(count - 1, i, list)
            list.removeLast()
        }
    }
}