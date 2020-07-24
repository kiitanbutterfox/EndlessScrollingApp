package br.kiitan.endlessscroll.contract

import br.kiitan.endlessscroll.model.TopGitRepos
import br.kiitan.endlessscroll.presenter.BasePresenter
import br.kiitan.endlessscroll.view.BaseView

interface ReposContract {
    interface View: BaseView<Presenter> {
        fun showError(message: String)
        fun getPresenter(): Presenter
        fun setTopGitRepos(topGitRepos: TopGitRepos)
    }
    interface ReposFragmentView {
        fun fillRepositoriesList(topGitRepos: TopGitRepos)
    }
    interface ReposDetailFragmentView {
    }

    interface Presenter: BasePresenter {
       fun getTopGitRepos()
    }
}