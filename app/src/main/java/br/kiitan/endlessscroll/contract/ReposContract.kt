package br.kiitan.endlessscroll.contract

import br.kiitan.endlessscroll.presenter.BasePresenter
import br.kiitan.endlessscroll.view.BaseView

interface ReposContract {
    interface View: BaseView<Presenter> {
        fun showError(message: String)
        fun getPresenter(): Presenter
    }
    interface ReposFragmentView {
    }
    interface ReposDetailFragmentView {
    }

    interface Presenter: BasePresenter {
       fun getTopGitRepos()
    }
}