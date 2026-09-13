package strings

fun String.sorted(): String {
    val chars = this.toCharArray()
    chars.sort()
    return String(chars)
}