package br.kiitan.endlessscroll.model

data class ReposItem(
    val id: Long,
    val name: String,
    val private: Boolean,
    val owner: GitUser,
    val html_url: String,
    val description: String,
    val stargazers_count: Int,
    val forks_count: Int

)