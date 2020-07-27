package br.kiitan.endlessscroll.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.kiitan.endlessscroll.R
import br.kiitan.endlessscroll.ReposInteractionListener
import br.kiitan.endlessscroll.model.ReposItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ReposAdapter(private val topRepos: Array<ReposItem>, private val fragmentReposInteractionListener: ReposInteractionListener): RecyclerView.Adapter<ReposAdapter.ReposViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        return ReposViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.repository_cell, parent, false), fragmentReposInteractionListener)

    }

    override fun getItemCount(): Int {
        return topRepos.size
    }

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        when(holder) {

            is ReposViewHolder -> {
                holder.bind(topRepos.get(position))
            }


        }
    }

    class ReposViewHolder (itemView: View, reposInteractionListener: ReposInteractionListener): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var imvUserAvatar: ImageView = itemView.findViewById(R.id.imvUserAvatar)
        var txtUserName: TextView = itemView.findViewById(R.id.txtUserName)
        var txtRepositoryName: TextView = itemView.findViewById(R.id.txtRepositoryName)
        var txtRepositoryDescription: TextView = itemView.findViewById(R.id.txtRepositoryDescription)
        var txtRepositoryForkCount: TextView = itemView.findViewById(R.id.txtRepositoryForkCount)
        var txtRepositoryStarCount: TextView = itemView.findViewById(R.id.txtRepositoryStarCount)
        var reposInteractionListener: ReposInteractionListener = reposInteractionListener

        init{
            itemView.setOnClickListener(this)
        }

        fun bind(reposItem: ReposItem){
            txtUserName.text = reposItem.owner.login
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(reposItem.owner.avatar_url)
                .into(imvUserAvatar)
            txtRepositoryName.text = reposItem.name
            txtRepositoryDescription.text = reposItem.description
            txtRepositoryForkCount.text = reposItem.forks_count.toString()
            txtRepositoryStarCount.text = reposItem.stargazers_count.toString()
        }

        override fun onClick(v: View?) {
            reposInteractionListener.onRepoClick(adapterPosition)
        }

    }

}