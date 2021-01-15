package me.ldrpontes.warrenbrasil.ui.goals

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_list_goals.*
import me.ldrpontes.domain.entities.Background
import me.ldrpontes.domain.entities.Goal
import me.ldrpontes.warrenbrasil.R
import me.ldrpontes.warrenbrasil.ui.adapters.ListGoalsAdapter
import me.ldrpontes.warrenbrasil.utils.State
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
        startGoalsObserver()
    }

    private fun startGoalsObserver() {
        goalViewModel.goals.observe(viewLifecycleOwner, {
            goalsObserverHandler(it)
        })
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

    private fun goalsObserverHandler(state: State<List<Goal>>) {
        when (state) {
            is State.Success -> {
                updateRecyclerViewGoals(state.data)
            }
            is State.Failure -> {
                Snackbar.make(this.requireView(), state.message, Snackbar.LENGTH_LONG).show()
            }
            is State.Loading -> {
                if (state.loading)
                    Toast.makeText(context, "Carregando", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(context, "Carregou", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateRecyclerViewGoals(data: List<Goal>) {
        goalsList.clear()
        goalsList.addAll(data)
        rv_list_goals.adapter?.notifyDataSetChanged()
    }

    override fun onGoalClickListener(goal: Goal) {
        TODO("Open goal fragment")
    }


}