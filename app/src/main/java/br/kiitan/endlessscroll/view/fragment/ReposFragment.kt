package br.kiitan.endlessscroll.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.kiitan.endlessscroll.R
import br.kiitan.endlessscroll.ReposInteractionListener
import br.kiitan.endlessscroll.contract.ReposContract
import br.kiitan.endlessscroll.model.ReposItem
import br.kiitan.endlessscroll.model.TopGitRepos
import br.kiitan.endlessscroll.view.SpacingItemDecoration
import br.kiitan.endlessscroll.view.adapter.ReposAdapter
import java.lang.ClassCastException

class ReposFragment : BaseFragment(), ReposContract.ReposFragmentView, ReposInteractionListener{
    private lateinit var pgrLoadingRepos: ProgressBar
    private lateinit var rcvRepositories: RecyclerView
    private lateinit var repositoriesAdapter: RecyclerView.Adapter<*>
    private lateinit var repositoriesManager: RecyclerView.LayoutManager
    private lateinit var reposPresenter: ReposContract.Presenter
    private lateinit var gitReposList: Array<ReposItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.repositories_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        try {
            val reposView: ReposContract.View = activity as ReposContract.View
            reposPresenter = reposView.getPresenter()
            reposPresenter.getTopGitRepos()
            pgrLoadingRepos = requireView().findViewById<ProgressBar>(R.id.pgrLoadingRepos)
            pgrLoadingRepos.visibility = View.VISIBLE
            pgrLoadingRepos.animate()
        }
        catch (e: ClassCastException)
        {
            Log.e("REPOSFRAGMENT", e.message?:"")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun fillRepositoriesList(topGitRepos: TopGitRepos)
    {
        pgrLoadingRepos.visibility = View.GONE
        gitReposList = topGitRepos.items
        repositoriesManager = LinearLayoutManager(context)
        repositoriesAdapter = ReposAdapter(gitReposList, this)
        rcvRepositories = requireView().findViewById<RecyclerView>(R.id.rcvRepositories).apply{
            setHasFixedSize(true)
            val spacingItemDecoration = SpacingItemDecoration(30)
            addItemDecoration(spacingItemDecoration)
            layoutManager = repositoriesManager
            adapter = repositoriesAdapter
        }
    }

    fun fillRepositoriesList2(topGitRepos: TopGitRepos)
    {
        pgrLoadingRepos.visibility = View.GONE
        gitReposList = topGitRepos.items
        repositoriesManager = LinearLayoutManager(context)
        repositoriesAdapter = ReposAdapter(gitReposList, this)
        rcvRepositories = requireView().findViewById<RecyclerView>(R.id.rcvRepositories).apply{
            setHasFixedSize(true)
            val spacingItemDecoration = SpacingItemDecoration(30)
            addItemDecoration(spacingItemDecoration)
            layoutManager = repositoriesManager
            adapter = repositoriesAdapter
        }
    }

    override fun onRepoClick(repoPosition: Int) {
        reposPresenter.loadRepoDetailFragment(gitReposList[repoPosition])
    }

}