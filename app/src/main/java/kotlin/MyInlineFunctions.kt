package com.example.practise.kotlin

/**
An inline function is a function that gets expanded at compile time,
meaning its code is directly inserted into the places where it is called.
This helps reduce function call overhead and can improve performance,
especially when using higher-order functions.
 */

/**
Why Are Inline Functions Used?
Inline functions are mainly used to:

Reduce function call overhead – Avoids the extra cost of calling a function.
Improve performance – Helps in cases where a function is called frequently (e.g., lambda expressions in loops).
Optimize higher-order functions – Prevents creating extra objects for lambda expressions.
Allow non-local returns – Lets you return from the enclosing function when using lambda expressions.
 */


/** --------- Real-World Example: Performance Optimization in Logging ------
Let’s say you are logging debug messages in an Android app.
Normally, the log function is called multiple times, which can slow down performance.
Using an inline function can optimize this.
 */

/** --------- Without Inline Function (Normal Function)
 * fun logMessage(message: () -> String) {
 *     if (BuildConfig.DEBUG) {  // Only log in debug mode
 *         println(message()) // Function call overhead here
 *     }
 * }
 *
 * fun main() {
 *     logMessage { "User clicked the button!" }  // Extra function call
 * }
 * 🔹 Here, every time logMessage is called, a lambda object is created → Extra memory usage.
 */


/** --------- With Inline Function
 *inline fun logMessage(message: () -> String) {
 *     if (BuildConfig.DEBUG) {
 *         println(message()) // Function call is inlined here, no extra lambda object
 *     }
 * }
 *
 * fun main() {
 *     logMessage { "User clicked the button!" }  // No extra function call overhead
 * }
 *
 * ✅ How does inline help?
 * ✔ No extra function call – The function body is directly placed in main().
 * ✔ No extra lambda object – Kotlin does not create a separate function instance for message.
 *  */

/** ---------🔹 Example: Non-Local Return in Inline Functions
 * One special feature of inline is that it allows non-local returns inside lambda functions.
 *
 * 🔴 Without Inline (Throws Error)
 * fun higherOrderFunction(action: () -> Unit) {
 *     action()  // Lambda function cannot return from the enclosing function
 * }
 *
 * fun main() {
 *     higherOrderFunction {
 *         println("Inside Lambda")
 *         return  // ❌ ERROR: Unresolved reference
 *     }
 * }
 */

/** --------- ✅ With Inline Function
 * inline fun higherOrderFunction(action: () -> Unit) {
 *     action()  // Function call is inlined here
 * }
 *
 * fun main() {
 *     higherOrderFunction {
 *         println("Inside Lambda")
 *         return  // ✅ Allowed because inline functions support non-local return
 *     }
 *     println("This will never be printed")
 * }
 * ✅ Why does this work?
 * ✔ The lambda body is directly inserted into main(), so return works as if it’s inside main().
 */

/**
📌 Summary
✔ Inline functions replace function calls with actual function code → No function call overhead.
✔ They are mainly used with higher-order functions to improve performance.
✔ They help avoid unnecessary object creation (for lambda functions).
✔ They allow non-local returns, which normal functions don’t support.
 */

class InlineFunctions {
    inline fun higherOrderFunction(action: () -> Unit) {
        action()
    }

    fun higherOrderFunction1(action: () -> Unit) {
        action()
    }


}


fun main() {
    // returns out of main after "JAUHAR Inside Lambda" prints,if function called is inline function,
    // if first return is not commented
    InlineFunctions().higherOrderFunction {
        println("JAUHAR Inside Lambda")
        //return
    }

    // prints "JAUHAR Inside Lambda
    //JAUHAR Inside Lambda
    //Before main EXIT", it means that returns only from lambda local scope not the complete main function
    InlineFunctions().higherOrderFunction1 {
        println("JAUHAR Inside Lambda")
        return@higherOrderFunction1
    }
    println("Before main EXIT")
}

