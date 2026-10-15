package stack

/**
 * A solution to the LeetCode question Evaluate Reverse Polish Notation [#150](https://leetcode.com/problems/evaluate-reverse-polish-notation/description/)
 *
 * # Description
 * You are given an array of strings `tokens` that represents an arithmetic expression in a Reverse Polish Notation.
 *
 * Evaluate the expression. Return an integer that represents the value of the expression.
 *
 * Note that:
 *
 * - The valid operators are `+`, `-`, `*`, and `/`.
 * - Each operand may be an integer or another expression.
 * - The division between two integers always truncates toward zero.
 * - There will not be any division by zero.
 * - The input represents a valid arithmetic expression in a reverse polish notation.
 * - The answer and all the intermediate calculations can be represented in a 32-bit integer.
 *
 *
 * ### Example 1:
 * ```
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * ```
 *
 * ### Example 2:
 * ```
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * ```
 * ### Example 3:
 * ```
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * ```
 *
 * ### Constraints:
 *
 * - 1 <= tokens.length <= 104
 * - tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 * */
interface EvaluateReversePolishNotation {

    fun evalRPN(tokens: Array<String>): Int

    class Solution : EvaluateReversePolishNotation {

        override fun evalRPN(tokens: Array<String>): Int {
            val stack = ArrayDeque<Operand.Value>()

            for (token in tokens) {
                val parsed = Operand.parse(token)
                if (parsed is Operand.Value) {
                    stack.addLast(parsed)
                } else if (parsed is Operand.Operator) {
                    val right = stack.removeLast()
                    val left = stack.removeLast()
                    stack.addLast(parsed.apply(left, right))
                }
            }
            return stack.last().value
        }

        interface Operand {

            interface Operator : Operand {
                fun apply(a: Value, b: Value): Value
            }

            data class Value(val value: Int) : Operand

            class Sum : Operator {
                override fun apply(a: Value, b: Value): Value {
                    return Value(a.value + b.value)
                }
            }

            class Diff : Operator {
                override fun apply(a: Value, b: Value): Value {
                    return Value(a.value - b.value)
                }
            }

            class Multiply : Operator {
                override fun apply(a: Value, b: Value): Value {
                    return Value(a.value * b.value)
                }
            }

            class Divide : Operator {
                override fun apply(a: Value, b: Value): Value {
                    return if (b.value == 0) throw IllegalArgumentException("Cannot devide by 0!")
                    else Value(a.value / b.value)
                }
            }

            companion object {
                fun parse(token: String): Operand {
                    if (token.isEmpty()) throw IllegalArgumentException("Token is empty!")

                    val intToken = token.toIntOrNull()
                    return if (intToken != null) Value(intToken)
                    else when (token[0]) {
                        '*' -> Multiply()
                        '/' -> Divide()
                        '+' -> Sum()
                        '-' -> Diff()
                        else -> throw IllegalArgumentException("Invalid operation!")
                    }
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun main(vararg args: String) {
            val solution: EvaluateReversePolishNotation = Solution()
            val example1 = arrayOf("2", "1", "+", "3", "*")
            println(solution.evalRPN(example1))
            println("------------")
            val example2 = arrayOf("10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+")
            println(solution.evalRPN(example2))
        }
    }
}