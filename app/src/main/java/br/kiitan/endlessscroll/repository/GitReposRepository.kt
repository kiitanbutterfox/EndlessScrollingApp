package br.kiitan.endlessscroll.repository

import android.util.Log
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
                    Log.e("REPOSITORY", "Error: " + t?.message?:"")
                }
            })
        }

    }

}