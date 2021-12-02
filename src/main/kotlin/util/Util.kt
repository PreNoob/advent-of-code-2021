package util

fun getResourceAsText(path: String): List<String> {
    return object {}.javaClass.getResource(path)?.readText()?.lines() ?: emptyList()
}