package br.kiitan.endlessscroll.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.kiitan.endlessscroll.ChangeTitleListener
import br.kiitan.endlessscroll.R
import br.kiitan.endlessscroll.contract.ReposContract
import br.kiitan.endlessscroll.model.ReposPulls
import br.kiitan.endlessscroll.view.SpacingItemDecoration
import br.kiitan.endlessscroll.view.adapter.PullsAdapter
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.processNextEventInCurrentThread
import org.w3c.dom.Text
import java.lang.ClassCastException

class ReposDetailFragment : BaseFragment(), ReposContract.ReposDetailFragmentView {
    private lateinit var pgrLoadingPulls: ProgressBar
    private lateinit var rcvPulls: RecyclerView
    private lateinit var pullsAdapter: RecyclerView.Adapter<*>
    private lateinit var pullsManager: RecyclerView.LayoutManager
    private lateinit var reposPresenter: ReposContract.Presenter
    private lateinit var gitPullsList: Array<ReposPulls>
    private lateinit var changeTitleListener: ChangeTitleListener
    private lateinit var txtWarningPulls: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repository_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        try {
            val reposView: ReposContract.View = activity as ReposContract.View
            reposPresenter = reposView.getPresenter()
            reposPresenter.getReposPulls()
            pgrLoadingPulls = requireView().findViewById<ProgressBar>(R.id.pgrLoadingPulls)
            pgrLoadingPulls.visibility = View.VISIBLE
            pgrLoadingPulls.animate()
            changeTitleListener = activity as ChangeTitleListener
            changeTitleListener.changePageTitle(reposPresenter.getSelectedRepoTitle())
        }
        catch (e: ClassCastException) {
           Log.e("REPOSDETAILFRAGMENT", e.message?:"")
        }
    }

    override fun fillPullsList(repoPullsList: Array<ReposPulls>)
    {
        pgrLoadingPulls.visibility = View.GONE
        txtWarningPulls = requireView().findViewById(R.id.txtWarningPulls)
        if(repoPullsList.size > 0) {
            txtWarningPulls.visibility = View.GONE
            pgrLoadingPulls.visibility = View.GONE
            gitPullsList = repoPullsList
            pullsManager = LinearLayoutManager(context)
            pullsAdapter = PullsAdapter(gitPullsList)
            rcvPulls = requireView().findViewById<RecyclerView>(R.id.rcvRepoPulls).apply {
                setHasFixedSize(true)
                val spacingItemDecoration = SpacingItemDecoration(30)
                addItemDecoration(spacingItemDecoration)
                layoutManager = pullsManager
                adapter = pullsAdapter
            }
        } else
        {
            txtWarningPulls.visibility = View.VISIBLE
        }
    }

}