package com.compilercastle.analysis

data class AnalysisResult(val errorId: String, val line: Int, val column: Int, val message: String)

interface CodeAnalyzer {
    fun analyze(code: String): List<AnalysisResult>
}
