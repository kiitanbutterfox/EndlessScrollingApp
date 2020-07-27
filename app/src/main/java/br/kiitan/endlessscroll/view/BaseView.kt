package br.kiitan.endlessscroll.view


interface BaseView<T> {
    fun attachPresenter(presenter: T)
}