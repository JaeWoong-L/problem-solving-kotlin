class Solution {
    lateinit var ryanInfo: IntArray
    lateinit var apeachInfo: IntArray
    var answer = IntArray(11)
    var maxDiff = 0

    fun solution(n: Int, info: IntArray): IntArray {
        ryanInfo = IntArray(11)
        apeachInfo = info
        combinationWithReplacement(n)
        if(maxDiff == 0)
            answer = intArrayOf(-1)

        return answer
    }

    fun combinationWithReplacement(count: Int, index: Int = 0) {
        if (count == 0) {
            val diff = getDiff(ryanInfo, apeachInfo)
            if(diff > maxDiff) {
                maxDiff = diff
                answer = ryanInfo.copyOf()
            }
            else if(diff == maxDiff) {
                answer = getAnswer(ryanInfo, answer).copyOf()
            }
            return
        }

        for (i in index until 11) {
            ryanInfo[i]++
            combinationWithReplacement(count - 1, i)
            ryanInfo[i]--
        }
    }

    fun getDiff(arr1: IntArray, arr2: IntArray): Int {
        var diff = 0
        for(i in arr1.indices) {
            if(arr1[i] == 0 && arr2[i] == 0) continue
            if(arr1[i] > arr2[i]) diff += 10 - i
            else diff -= 10 - i
        }

        return diff
    }

    fun getAnswer(arr1: IntArray, arr2: IntArray): IntArray {
        for(i in arr1.indices.reversed()) {
            if(arr1[i] > arr2[i]) return arr1
            else if(arr1[i] < arr2[i]) return arr2
        }
        return arr1
    }
}

fun main() {
    val n = 9
    val info = intArrayOf(0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1)
    println(Solution().solution(n, info).contentToString())
}