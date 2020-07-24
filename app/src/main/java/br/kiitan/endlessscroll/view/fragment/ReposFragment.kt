package br.kiitan.endlessscroll.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.kiitan.endlessscroll.R
import br.kiitan.endlessscroll.contract.ReposContract
import br.kiitan.endlessscroll.presenter.GitReposPresenter
import br.kiitan.endlessscroll.view.SpacingItemDecoration
import br.kiitan.endlessscroll.view.adapter.ReposAdapter

class ReposFragment : Fragment(), ReposContract.ReposFragmentView {
    private lateinit var rcvRepositories: RecyclerView
    private lateinit var repositoriesAdapter: RecyclerView.Adapter<*>
    private lateinit var repositoriesManager: RecyclerView.LayoutManager

    private lateinit var reposPresenter: GitReposPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.repositories_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repositoriesManager = LinearLayoutManager(context)
        repositoriesAdapter = ReposAdapter(arrayOf())
        rcvRepositories = view.findViewById<RecyclerView>(R.id.rcvRepositories).apply{
            setHasFixedSize(true)
            val spacingItemDecoration = SpacingItemDecoration(30)
            addItemDecoration(spacingItemDecoration)
            layoutManager = repositoriesManager
            adapter = repositoriesAdapter
        }
    }

}