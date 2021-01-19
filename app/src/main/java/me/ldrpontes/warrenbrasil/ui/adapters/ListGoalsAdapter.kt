package me.ldrpontes.warrenbrasil.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso
import me.ldrpontes.domain.entities.Goal
import me.ldrpontes.warrenbrasil.R

class ListGoalsAdapter(private val goals: List<Goal>, private val listener: ListGoalsListener) :
    RecyclerView.Adapter<ListGoalsAdapter.ListGoalsViewHolder>(), Filterable {

    private var filteredGoals: ArrayList<Goal> = ArrayList()

    init {
        filteredGoals = goals as ArrayList<Goal>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListGoalsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.goal_item, parent, false)
        return ListGoalsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListGoalsViewHolder, position: Int) {
        holder.tvNameGoal.text = filteredGoals[position].name

        holder.tvNameGoal.transitionName = "tv_name_goal${filteredGoals[position].id}"
        holder.ivGoal.transitionName = "iv_goal${filteredGoals[position].id}"

        Picasso.get().load(filteredGoals[position].background.regular).fit().into(holder.ivGoal)

        holder.clItemGoal.setOnClickListener {
            listener.onGoalClickListener(holder.ivGoal, holder.tvNameGoal, filteredGoals[position])
        }
    }

    override fun getItemCount(): Int {
        return filteredGoals.size
    }


    class ListGoalsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivGoal: ShapeableImageView = itemView.findViewById(R.id.iv_goal)
        val tvNameGoal: MaterialTextView = itemView.findViewById(R.id.tv_name_goal)
        val clItemGoal: ConstraintLayout = itemView.findViewById(R.id.cl_item_goal)
    }

    interface ListGoalsListener {
        fun onGoalClickListener(imageView: View, titleView: View, goal: Goal)
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            private val filterResults = FilterResults()

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                filteredGoals = if (constraint.isNullOrBlank()) {
                    goals as ArrayList<Goal>
                } else {

                    val goalsFilter = goals.filter {
                        it.name.contains(constraint.toString(), true)
                    }

                    goalsFilter as ArrayList<Goal>
                }

                filterResults.values = filteredGoals

                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredGoals = results?.values as ArrayList<Goal>
                notifyDataSetChanged()
            }

        }
    }
}