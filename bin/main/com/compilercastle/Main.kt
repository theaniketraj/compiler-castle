package com.compilercastle

import com.compilercastle.core.KnowledgeBaseLoader
import com.compilercastle.core.ErrorNode
import com.compilercastle.analysis.RegexAnalyzer
import com.compilercastle.tools.MarkdownGenerator
import java.io.File

fun main(args: Array<String>) {
    println("Welcome to Compiler Castle!")
    println("Loading Error Knowledge Base...")

    val errors = try {
        val loadedErrors = KnowledgeBaseLoader.loadErrorsFromResources()
        println("Successfully loaded ${loadedErrors.size} error definitions.")
        loadedErrors
    } catch (e: Exception) {
        println("Failed to load knowledge base: ${e.message}")
        emptyList()
    }

    if (args.contains("--generate-docs")) {
        println("Generating documentation...")
        MarkdownGenerator.generateDocs(errors, File("docs/generated"))
        println("Documentation generated in docs/generated")
        return
    }

    if (errors.isNotEmpty()) {
        val root = errors.find { it.id == "ROOT" }
        if (root != null) {
            println("\n--- Error Hierarchy ---")
            printTree(root, errors)
        }
    }

    println("\n--- Running Analysis Demo ---")
    val sampleCode = """
        fun main() {
            val x: Int = "Hello"
            println(x)
        }
    """.trimIndent()
    
    println("Analyzing code:\n$sampleCode\n")
    
    val analyzer = RegexAnalyzer()
    val results = analyzer.analyze(sampleCode)
    
    if (results.isEmpty()) {
        println("No errors found.")
    } else {
        println("Found ${results.size} errors:")
        results.forEach { result ->
            val errorNode = errors.find { it.id == result.errorId }
            println("Line ${result.line}: ${result.message}")
            if (errorNode != null) {
                println("  -> See: ${errorNode.title} (${errorNode.description})")
                if (errorNode.solutions.isNotEmpty()) {
                    println("  -> Solution: ${errorNode.solutions.first()}")
                }
            }
        }
    }
}

fun printTree(node: ErrorNode, allErrors: List<ErrorNode>, indent: String = "") {
    println("$indent- [${node.id}] ${node.title}: ${node.description}")
    node.childrenIds.forEach { childId ->
        val child = allErrors.find { it.id == childId }
        if (child != null) {
            printTree(child, allErrors, "$indent  ")
        }
    }
}
