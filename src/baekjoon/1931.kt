package baekjoon

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var answer = 0

    val n = br.readLine().toInt()
    val schedules = PriorityQueue(compareBy<Schedule>({ it.end }, { it.start }))

    for (i in 0 until n) {
        val schedule = br.readLine().split(' ')
        schedules.add(Schedule(schedule[0].toInt(), schedule[1].toInt()))
    }

    var now = 0
    while (schedules.isNotEmpty()) {
        val schedule = schedules.poll()
        if (now <= schedule.start) {
            answer++
            now = schedule.end
        }
    }

    bw.write(answer.toString())

    br.close()
    bw.flush()
    bw.close()
}

data class Schedule(var start: Int, var end: Int)