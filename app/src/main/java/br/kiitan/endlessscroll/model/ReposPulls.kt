package br.kiitan.endlessscroll.model

data class ReposPulls(
    val id: Long,
    val state: String,
    val title: String,
    val user: GitUser,
    val body: String,
    val created_at: String
)