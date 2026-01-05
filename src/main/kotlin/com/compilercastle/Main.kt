package com.compilercastle

import com.compilercastle.core.ErrorNode

fun main() {
    println("Welcome to Compiler Castle!")
    println("Initializing Error Tree...")

    // Example initialization
    val rootError =
            ErrorNode(
                    id = "ROOT",
                    title = "Compilation Error",
                    description = "The base category for all compilation errors."
            )

    println("Created root node: ${rootError.title}")

    // TODO: Load error definitions from knowledge base or configuration
}
