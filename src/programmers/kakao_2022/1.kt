//class Solution {
//    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray =
//    report.map { it.split(" ") }
//        .groupBy { it[1] }
//        .map { it.value.distinct() }
//        .filter { it.size >= k }
//        .flatten()
//        .map { it[0] }
//        .groupingBy { it }
//        .eachCount()
//        .run { id_list.map { getOrDefault(it, 0) }.toIntArray() }
//}

// Lv 1. 신고 결과 받기
class Solution {
    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        val n = id_list.size
        var answer = IntArray(n) { 0 }
        val reported = Array(n) { BooleanArray(n) {false} }
        val report_count = IntArray(n) { 0 }
        for(s in report) {
            val (r, c) = s.split(' ')
            val i = id_list.indexOf(r)
            val j = id_list.indexOf(c)

            if(!reported[i][j]) {
                report_count[j]++
                reported[i][j] = true
            }
        }

        for(j in 0 until n) {
            if(report_count[j] >= k) {
                for(i in 0 until n) {
                    if(reported[i][j]) answer[i]++
                }
            }
        }

        return answer
    }
}

fun main() {
    val id_list = arrayOf("muzi", "frodo", "apeach", "neo")
    val report = arrayOf("muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi")
    val k = 2
    val answer = Solution().solution(id_list, report, k)
    answer.forEach { print("$it ") }
}