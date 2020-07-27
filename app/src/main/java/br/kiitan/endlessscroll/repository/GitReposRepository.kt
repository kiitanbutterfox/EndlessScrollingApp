package br.kiitan.endlessscroll.repository

import android.util.Log
import br.kiitan.endlessscroll.model.ReposItem
import br.kiitan.endlessscroll.model.ReposPulls
import br.kiitan.endlessscroll.model.TopGitRepos
import br.kiitan.endlessscroll.presenter.GitReposPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class GitReposRepository(val reposPresenter: GitReposPresenter){

    fun getTopGitRepos() {
        val retrofit: Retrofit = RetrofitConfig.retrofit
        val gitReposAPI: GitReposAPI = retrofit.create(GitReposAPI::class.java)

        gitReposAPI.getRepositories("language:java", "stars", 1).apply {
            enqueue(object : Callback<TopGitRepos?> {
                override fun onResponse(call: Call<TopGitRepos?>?, response: Response<TopGitRepos?>?) {
                    reposPresenter.setTopGitRepos(response?.body()?: TopGitRepos(arrayOf()))
                }

                override fun onFailure(call: Call<TopGitRepos?>?, t: Throwable?) {
                    reposPresenter.setTopGitRepos(TopGitRepos(arrayOf()))
                }
            })
        }

    }

    fun getRepoPulls(repo: ReposItem) {
        val retrofit: Retrofit = RetrofitConfig.retrofit
        val gitReposAPI: GitReposAPI = retrofit.create(GitReposAPI::class.java)

        gitReposAPI.getRepositoryPulls(repo.owner.login, repo.name).apply {
            enqueue(object : Callback<Array<ReposPulls>?> {
                override fun onResponse(call: Call<Array<ReposPulls>?>?, response: Response<Array<ReposPulls>?>?) {
                    reposPresenter.setGitPulls(response?.body()?: arrayOf())
                }

                override fun onFailure(call: Call<Array<ReposPulls>?>?, t: Throwable?) {
                    reposPresenter.setGitPulls(arrayOf())
                }
            })
        }

    }

}