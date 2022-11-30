// 전화번호 목록
val br = System.`in`.bufferedReader()
val bw = System.out.bufferedWriter()

fun main() {
    val t = br.readLine().toInt()
    repeat(t) {
        val n = br.readLine().toInt()
        val phoneNumbers = mutableListOf<String>()
        for(i in 0 until n) {
            phoneNumbers.add(br.readLine())
        }
        phoneNumbers.sort()
        var answer = "YES"
        for(i in 0 until n - 1) {
            if(phoneNumbers[i + 1].startsWith(phoneNumbers[i])) {
                answer = "NO"
                break;
            }
        }
        bw.write(answer)
        bw.newLine()
    }

    bw.flush()
    bw.close()
}