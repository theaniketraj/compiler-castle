package com.compilercastle.tools

import com.compilercastle.core.ErrorNode
import java.io.File

object MarkdownGenerator {
    fun generateDocs(errors: List<ErrorNode>, outputDir: File) {
        if (!outputDir.exists()) {
            outputDir.mkdirs()
        }

        errors.forEach { error ->
            val file = File(outputDir, "${error.id.lowercase()}.md")
            val content = buildString {
                appendLine("# ${error.title}")
                appendLine()
                appendLine(error.description)
                appendLine()

                if (error.parentId != null) {
                    appendLine("**Parent:** [${error.parentId}](${error.parentId?.lowercase()}.md)")
                    appendLine()
                }

                if (error.childrenIds.isNotEmpty()) {
                    appendLine("## Sub-errors")
                    error.childrenIds.forEach { childId ->
                        val child = errors.find { it.id == childId }
                        if (child != null) {
                            appendLine("- [${child.title}](${childId.lowercase()}.md)")
                        }
                    }
                    appendLine()
                }

                if (error.examples.isNotEmpty()) {
                    appendLine("## Examples")
                    error.examples.forEach { example ->
                        appendLine("### Code")
                        appendLine("```kotlin")
                        appendLine(example.code)
                        appendLine("```")
                        appendLine(example.explanation)
                        appendLine()
                    }
                }

                if (error.solutions.isNotEmpty()) {
                    appendLine("## Solutions")
                    error.solutions.forEach { solution -> appendLine("- $solution") }
                }
            }
            file.writeText(content)
        }
    }
}
