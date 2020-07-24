package br.kiitan.endlessscroll.presenter

import br.kiitan.endlessscroll.contract.ReposContract
import br.kiitan.endlessscroll.model.TopGitRepos
import br.kiitan.endlessscroll.repository.GitReposRepository
import java.io.Serializable

class GitReposPresenter(val view: ReposContract.View):BasePresenter, ReposContract.Presenter {
    private val repository = GitReposRepository(this)


    override fun getTopGitRepos(){
        repository.getTopGitRepos()
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }

    fun setTopGitRepos(topGitRepos: TopGitRepos){
        view.setTopGitRepos(topGitRepos)
    }
}