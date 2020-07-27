package br.kiitan.endlessscroll.contract

import br.kiitan.endlessscroll.model.ReposItem
import br.kiitan.endlessscroll.model.ReposPulls
import br.kiitan.endlessscroll.model.TopGitRepos
import br.kiitan.endlessscroll.presenter.BasePresenter
import br.kiitan.endlessscroll.view.BaseView
import br.kiitan.endlessscroll.view.fragment.BaseFragment

interface ReposContract {
    interface View: BaseView<Presenter> {
        fun showError(message: String)
        fun getPresenter(): Presenter
        fun setTopGitRepos(topGitRepos: TopGitRepos)
        fun loadFragment(fragment: BaseFragment)
        fun setGitPulls(pullsList: Array<ReposPulls>)
        fun showHideBackArrow(show: Boolean)
    }
    interface ReposFragmentView {
        fun fillRepositoriesList(topGitRepos: TopGitRepos)
    }
    interface ReposDetailFragmentView {
        fun fillPullsList(repoPullsList: Array<ReposPulls>)
    }

    interface Presenter: BasePresenter {
       fun getTopGitRepos()
       fun getReposPulls()
       fun loadRepoDetailFragment(reposItem: ReposItem)
       fun setTopGitRepos(topGitRepos: TopGitRepos)
       fun setGitPulls(pullsList: Array<ReposPulls>)
        fun getSelectedRepoTitle(): String
    }
}