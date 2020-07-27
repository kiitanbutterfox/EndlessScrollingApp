package br.kiitan.endlessscroll.repository

import br.kiitan.endlessscroll.model.ReposPulls
import br.kiitan.endlessscroll.model.TopGitRepos
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitReposAPI {
    @GET("search/repositories")
    fun getRepositories(@Query("q") lanquageQuery: String,
                        @Query("sort") resultSort: String,
                        @Query("page") page: Int): Call<TopGitRepos>

    @GET("repos/{repository_owner}/{repository_name}/pulls")
    fun getRepositoryPulls(@Path("repository_owner") repositoryOwner: String,
                           @Path("repository_name") repositoryName: String): Call<Array<ReposPulls>>
}