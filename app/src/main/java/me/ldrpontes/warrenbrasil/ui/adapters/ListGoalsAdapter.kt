package me.ldrpontes.warrenbrasil.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso
import me.ldrpontes.domain.entities.Goal
import me.ldrpontes.warrenbrasil.R

class ListGoalsAdapter(private val goals: List<Goal>, private val listener: ListGoalsListener) :
    RecyclerView.Adapter<ListGoalsAdapter.ListGoalsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListGoalsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.goal_item, parent, false)
        return ListGoalsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListGoalsViewHolder, position: Int) {
        holder.tvNameGoal.text = goals[position].name

        Picasso.get().load(goals[position].background.small).fit().into(holder.ivGoal)

        holder.clItemGoal.setOnClickListener {
            listener.onGoalClickListener(goals[position])
        }
    }

    override fun getItemCount(): Int {
        return goals.size
    }


    class ListGoalsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivGoal: ShapeableImageView = itemView.findViewById(R.id.iv_goal)
        val tvNameGoal: MaterialTextView = itemView.findViewById(R.id.tv_name_goal)
        val clItemGoal: ConstraintLayout = itemView.findViewById(R.id.cl_item_goal)
    }

    interface ListGoalsListener {
        fun onGoalClickListener(goal: Goal)
    }
}