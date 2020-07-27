package br.kiitan.endlessscroll.view.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import br.kiitan.endlessscroll.R
import br.kiitan.endlessscroll.contract.ReposContract
import br.kiitan.endlessscroll.model.ReposPulls
import br.kiitan.endlessscroll.model.TopGitRepos
import br.kiitan.endlessscroll.presenter.GitReposPresenter
import br.kiitan.endlessscroll.view.fragment.BaseFragment
import br.kiitan.endlessscroll.view.fragment.ReposFragment

class RepositoriesActivity : BaseActivity(), ReposContract.View {
    private lateinit var reposPresenter: ReposContract.Presenter
    private var currentFragment: BaseFragment? = null
    private lateinit var imvBack: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repositories_activity)
        attachPresenter(GitReposPresenter(this))
//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
        loadFragment(ReposFragment())
        imvBack = findViewById(R.id.imvBack)
        imvBack.setOnClickListener({
            onBackPressed()
        })
    }

    fun loadFragment(fragment: BaseFragment){
    override fun loadFragment(fragment: BaseFragment){
        fragment.arguments = intent.extras
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.lnlFragmentHolder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun getPresenter():ReposContract.Presenter{
        return reposPresenter
    }

    override fun setTopGitRepos(topGitRepos: TopGitRepos) {
        currentFragment = supportFragmentManager.fragments.last() as BaseFragment
        if(currentFragment != null && currentFragment is ReposContract.ReposFragmentView)
        {
            (currentFragment as ReposContract.ReposFragmentView).fillRepositoriesList(topGitRepos)
        }
    }

    override fun setGitPulls(pullsList: Array<ReposPulls>) {
        currentFragment = supportFragmentManager.fragments.last() as BaseFragment
        if(currentFragment != null && currentFragment is ReposContract.ReposDetailFragmentView)
        {
            (currentFragment as ReposContract.ReposDetailFragmentView).fillPullsList(pullsList)
        }
    }

    override fun attachPresenter(presenter: ReposContract.Presenter) {
        reposPresenter = presenter
    }

    override fun onBackPressed() {
        supportFragmentManager.executePendingTransactions()
        val count = supportFragmentManager.backStackEntryCount
        if (count <= 1) {
            finish()
        } else {
            if(count <=2)
            {
                showHideBackArrow(false)
            }
            supportFragmentManager.popBackStack()
        }
    }

    override fun showHideBackArrow(show: Boolean){
        if(show) {
            imvBack.visibility = View.VISIBLE
        }
        else {
            imvBack.visibility = View.GONE
        }
    }

    }
}