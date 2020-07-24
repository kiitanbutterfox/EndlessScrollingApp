package br.kiitan.endlessscroll.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.kiitan.endlessscroll.R
import br.kiitan.endlessscroll.contract.ReposContract
import br.kiitan.endlessscroll.model.TopGitRepos
import br.kiitan.endlessscroll.presenter.GitReposPresenter
import br.kiitan.endlessscroll.view.fragment.BaseFragment
import br.kiitan.endlessscroll.view.fragment.ReposFragment

class RepositoriesActivity : BaseActivity(), ReposContract.View {
    private lateinit var reposPresenter: ReposContract.Presenter
    private var currentFragment: BaseFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repositories_activity)
        attachPresenter(GitReposPresenter(this))
//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
        loadFragment(ReposFragment())
    }

    fun loadFragment(fragment: BaseFragment){
        fragment.arguments = intent.extras
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.lnlFragmentHolder, fragment, "test")
        transaction.commit()
        currentFragment = fragment
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun getPresenter():ReposContract.Presenter{
        return reposPresenter
    }

    override fun setTopGitRepos(topGitRepos: TopGitRepos) {
        if(currentFragment != null && currentFragment is ReposContract.ReposFragmentView)
        {
            (currentFragment as ReposContract.ReposFragmentView).fillRepositoriesList(topGitRepos)
        }
    }

    override fun attachPresenter(presenter: ReposContract.Presenter) {
        reposPresenter = presenter
    }

    override fun detachPresenter() {
        TODO("Not yet implemented")
    }

    override fun showProgress() {
        TODO("Not yet implemented")
    }

    override fun hideProgress() {
        TODO("Not yet implemented")
    }
}