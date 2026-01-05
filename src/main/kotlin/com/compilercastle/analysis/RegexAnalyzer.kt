package com.compilercastle.analysis

class RegexAnalyzer : CodeAnalyzer {
    override fun analyze(code: String): List<AnalysisResult> {
        val results = mutableListOf<AnalysisResult>()

        // Simple detection for: val x: Int = "String"
        val typeMismatchRegex = Regex("""val\s+\w+\s*:\s*Int\s*=\s*".*"""")

        code.lines().forEachIndexed { index, line ->
            if (typeMismatchRegex.containsMatchIn(line)) {
                results.add(
                        AnalysisResult(
                                errorId = "TYPE_MISMATCH",
                                line = index + 1,
                                column = 1, // Simplified
                                message =
                                        "Detected potential Type Mismatch: Assigning String to Int"
                        )
                )
            }
        }

        return results
    }
}
