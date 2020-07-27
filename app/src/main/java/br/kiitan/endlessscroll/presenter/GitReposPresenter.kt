package br.kiitan.endlessscroll.presenter

import br.kiitan.endlessscroll.contract.ReposContract
import br.kiitan.endlessscroll.model.ReposItem
import br.kiitan.endlessscroll.model.ReposPulls
import br.kiitan.endlessscroll.model.TopGitRepos
import br.kiitan.endlessscroll.repository.GitReposRepository
import br.kiitan.endlessscroll.view.fragment.ReposDetailFragment
import java.io.Serializable

class GitReposPresenter(val view: ReposContract.View):BasePresenter, ReposContract.Presenter {
    private val repository = GitReposRepository(this)
    private lateinit var gitPulls: Array<ReposPulls>
    private lateinit var selectedRepo: ReposItem

    override fun getTopGitRepos(){
        repository.getTopGitRepos()
    }

    override fun getReposPulls() {
        repository.getRepoPulls(selectedRepo)
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }

    override fun setTopGitRepos(topGitRepos: TopGitRepos){
        view.setTopGitRepos(topGitRepos)
    }

    override fun loadRepoDetailFragment(reposItem: ReposItem) {
        selectedRepo = reposItem
        view.showHideBackArrow(true)
        view.loadFragment(ReposDetailFragment())
    }

    override fun setGitPulls(pullsList: Array<ReposPulls>){
        gitPulls = pullsList
        view.setGitPulls(gitPulls)
    }

    override fun getSelectedRepoTitle(): String{
        return selectedRepo.owner.login + "\n"+ selectedRepo.name
    }
}