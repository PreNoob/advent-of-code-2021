package day1

import util.getResourceAsText

fun main() {
    println("First day!")
    getResourceAsText("/day1/input.txt")
        .map(String::toInt)
        .windowed(3, 1, false, List<Int>::sum)
        .also(::println)
        .fold(X(0, null)) { (depthIncreases, previousDepth), currentDepth ->
            if (currentDepth > (previousDepth ?: currentDepth)) {
                X(depthIncreases + 1, currentDepth)
            } else {
                X(depthIncreases, currentDepth)
            }
        }.depthIncreases
        .also(::println)
}

private data class X(val depthIncreases: Int, val previousDepth: Int?)