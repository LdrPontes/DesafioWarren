package me.ldrpontes.warrenbrasil.ui.goals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_list_goals.*
import me.ldrpontes.domain.entities.Background
import me.ldrpontes.domain.entities.Goal
import me.ldrpontes.warrenbrasil.R
import me.ldrpontes.warrenbrasil.ui.adapters.ListGoalsAdapter
import me.ldrpontes.warrenbrasil.utils.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListGoalsFragment : Fragment(), ListGoalsAdapter.ListGoalsListener {

    private val goalsList: ArrayList<Goal> = ArrayList()
    private val adapter: ListGoalsAdapter = ListGoalsAdapter(goalsList, this)

    private val goalViewModel: GoalViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_goals, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startSearchInputListeners()
        startRecyclerViewGoals()

        goalsList.addAll(
            arrayListOf(
                Goal(
                    "5d9e29c7b6c3123b9f5f3268",
                    "Disney!",
                    522.2952415271,
                    200000.0,
                    "2029-10-09",
                    background = Background(
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjg3NTU5fQ"
                    )
                ),
                Goal(
                    "5d9e29c7b6c3123b9f5f3268",
                    "Disney 1",
                    522.2952415271,
                    200000.0,
                    "2029-10-09",
                    background = Background(
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjg3NTU5fQ"
                    )
                ), Goal(
                    "5d9e29c7b6c3123b9f5f3268",
                    "Disney 1",
                    522.2952415271,
                    200000.0,
                    "2029-10-09",
                    background = Background(
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjg3NTU5fQ"
                    )
                ), Goal(
                    "5d9e29c7b6c3123b9f5f3268",
                    "Disney 1",
                    522.2952415271,
                    200000.0,
                    "2029-10-09",
                    background = Background(
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjg3NTU5fQ"
                    )
                ), Goal(
                    "5d9e29c7b6c3123b9f5f3268",
                    "Disney 1",
                    522.2952415271,
                    200000.0,
                    "2029-10-09",
                    background = Background(
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjg3NTU5fQ"
                    )
                ), Goal(
                    "5d9e29c7b6c3123b9f5f3268",
                    "Disney 1",
                    522.2952415271,
                    200000.0,
                    "2029-10-09",
                    background = Background(
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjg3NTU5fQ"
                    )
                ), Goal(
                    "5d9e29c7b6c3123b9f5f3268",
                    "Disney 1",
                    522.2952415271,
                    200000.0,
                    "2029-10-09",
                    background = Background(
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjg3NTU5fQ"
                    )
                ), Goal(
                    "5d9e29c7b6c3123b9f5f3268",
                    "Disney 1",
                    522.2952415271,
                    200000.0,
                    "2029-10-09",
                    background = Background(
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjg3NTU5fQ"
                    )
                ), Goal(
                    "5d9e29c7b6c3123b9f5f3268",
                    "Disney 1",
                    522.2952415271,
                    200000.0,
                    "2029-10-09",
                    background = Background(
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjg3NTU5fQ"
                    )
                ), Goal(
                    "5d9e29c7b6c3123b9f5f3268",
                    "Disney 1",
                    522.2952415271,
                    200000.0,
                    "2029-10-09",
                    background = Background(
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjg3NTU5fQ"
                    )
                ), Goal(
                    "5d9e29c7b6c3123b9f5f3268",
                    "Disney 1",
                    522.2952415271,
                    200000.0,
                    "2029-10-09",
                    background = Background(
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjg3NTU5fQ"
                    )
                ),
                Goal(
                    "5d9e29c7b6c3123b9f5f3268",
                    "Disney 2",
                    522.2952415271,
                    200000.0,
                    "2029-10-09",
                    background = Background(
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjg3NTU5fQ",
                        "https://images.unsplash.com/photo-1463109598173-3864231fade5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjg3NTU5fQ"
                    )
                )
            )
        )

        updateRecyclerViewGoals()
    }


    private fun startSearchInputListeners() {
        tl_search.setEndIconOnClickListener {
            ted_search.text?.clear()
            ted_search.clearFocus()
            this.hideKeyboard()
        }
    }

    private fun startRecyclerViewGoals() {
        rv_list_goals.layoutManager = LinearLayoutManager(context)
        rv_list_goals.adapter = adapter
    }


    private fun updateRecyclerViewGoals() {
        rv_list_goals.adapter?.notifyDataSetChanged()
    }

    override fun onGoalClickListener(goal: Goal) {
        TODO("Open goal fragment")
    }


}