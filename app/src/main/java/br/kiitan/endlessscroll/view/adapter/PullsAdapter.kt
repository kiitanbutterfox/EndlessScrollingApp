package br.kiitan.endlessscroll.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.kiitan.endlessscroll.R
import br.kiitan.endlessscroll.model.ReposPulls
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class PullsAdapter(private val reposPulls: Array<ReposPulls>): RecyclerView.Adapter<PullsAdapter.PullsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullsViewHolder {
        return PullsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.repository_pulls_cell, parent, false))

    }

    override fun getItemCount(): Int {
        return reposPulls.size
    }

    override fun onBindViewHolder(holder: PullsViewHolder, position: Int) {
        when(holder) {

            else -> {
                holder.bind(reposPulls.get(position))


            }
        }
    }

    class PullsViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        var imvUserAvatar: ImageView = itemView.findViewById(R.id.imvUserAvatar)
        var txtUserName: TextView = itemView.findViewById(R.id.txtUserName)
        var txtPullRequestName: TextView = itemView.findViewById(R.id.txtPullRequestName)
        var txtPullRequestDescription: TextView = itemView.findViewById(R.id.txtPullRequestDescription)
        var txtPullStatus: TextView = itemView.findViewById(R.id.txtPullStatus)

        fun bind(reposPull: ReposPulls){
            txtUserName.text = reposPull.user.login
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(reposPull.user.avatar_url)
                .into(imvUserAvatar)
            txtPullRequestName.text = reposPull.title
            txtPullRequestDescription.text = reposPull.body
            txtPullStatus.text = reposPull.state
        }

    }

}