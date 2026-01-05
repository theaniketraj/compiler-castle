package com.compilercastle.core

import kotlinx.serialization.Serializable

/**
 * Represents a node in the hierarchical error tree.
 *
 * @property id Unique identifier for the error (e.g., "ERR-001").
 * @property title A concise title for the error.
 * @property description A clear explanation of what the error means.
 * @property parentId The ID of the parent error category (nullable for root nodes).
 * @property childrenIds List of IDs of more specific child errors.
 * @property examples List of code examples triggering this error.
 * @property solutions List of community-contributed solutions.
 */
@Serializable
data class ErrorNode(
        val id: String,
        val title: String,
        val description: String,
        val parentId: String? = null,
        val childrenIds: MutableList<String> = mutableListOf(),
        val examples: List<ErrorExample> = emptyList(),
        val solutions: List<String> = emptyList()
)

/** Represents a code example that triggers a specific error. */
@Serializable data class ErrorExample(val code: String, val explanation: String)
