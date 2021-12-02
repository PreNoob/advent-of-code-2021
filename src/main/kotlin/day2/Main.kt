package day2

import util.getResourceAsText

fun main() {
    println("Second day!")
    getResourceAsText("/day2/input.txt")
        .map(String::toMovement)
        .fold(Position(0, 0, 0)) { oldPosition, movement ->
            oldPosition.move(movement)
        }
        .also { println(it) }
        .also { println("Depth x Horizontal: ${it.depth * it.horizontal}") }
}

private fun Position.move(movement: Movement) = when (movement) {
    is UpwardMovement -> move(movement)
    is DownwardMovement -> move(movement)
    is ForwardMovement -> move(movement)
}

private fun Position.move(movement: ForwardMovement) =
    copy(horizontal = horizontal + movement.delta, depth = depth + movement.delta * aim)

private fun Position.move(movement: UpwardMovement) = copy(aim = aim - movement.delta)
private fun Position.move(movement: DownwardMovement) = copy(aim = aim + movement.delta)

private data class Position(val horizontal: Int, val depth: Int, val aim: Int)

private sealed class Movement(open val delta: Int)
private data class ForwardMovement(override val delta: Int) : Movement(delta)
private data class UpwardMovement(override val delta: Int) : Movement(delta)
private data class DownwardMovement(override val delta: Int) : Movement(delta)

private fun String.toMovement(): Movement {
    val (direction, delta) = split(" ")
    return with(delta.toInt()) {
        when (direction) {
            "forward" -> ForwardMovement(this)
            "up" -> UpwardMovement(this)
            "down" -> DownwardMovement(this)
            else -> error("")
        }
    }
}