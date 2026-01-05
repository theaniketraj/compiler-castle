package com.compilercastle.core

import java.io.File
import kotlinx.serialization.json.Json

object KnowledgeBaseLoader {
    private val json = Json { ignoreUnknownKeys = true }

    fun loadErrors(jsonContent: String): List<ErrorNode> {
        return json.decodeFromString(jsonContent)
    }

    fun loadErrorsFromFile(file: File): List<ErrorNode> {
        return loadErrors(file.readText())
    }

    fun loadErrorsFromResources(): List<ErrorNode> {
        val resource =
                javaClass.getResource("/errors.json")
                        ?: throw IllegalStateException("errors.json not found in resources")
        return loadErrors(resource.readText())
    }
}
