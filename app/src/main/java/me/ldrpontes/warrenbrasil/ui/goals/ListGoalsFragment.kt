package me.ldrpontes.warrenbrasil.ui.goals

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_list_goals.*
import kotlinx.android.synthetic.main.no_data_layout.*
import kotlinx.coroutines.*
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
        startTryAgainButtonListener()
        getGoalsHandler()

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
                failureHandler(state.message)
            }
            is State.Loading -> {
                loadingHandler(state.loading)
            }
        }
    }


    private fun updateRecyclerViewGoals(data: List<Goal>) {

        goalsDataEmptyHandler(data.isEmpty())

        goalsList.clear()
        goalsList.addAll(data)
        rv_list_goals.adapter?.notifyDataSetChanged()
    }


    private fun goalsDataEmptyHandler(isEmpty: Boolean) {
        if (isEmpty) {
            rv_list_goals.visibility = View.GONE
            no_data_layout.visibility = View.VISIBLE
        } else {
            rv_list_goals.visibility = View.VISIBLE
            no_data_layout.visibility = View.GONE
        }
    }


    private fun startTryAgainButtonListener() {
        btn_try_again.setOnClickListener {
            getGoalsHandler()
        }
    }


    private fun getGoalsHandler() {
        goalViewModel.getGoalsHandler()
    }


    private fun loadingHandler(isLoading: Boolean) {
        if (isLoading) {
            loading_layout.visibility = View.VISIBLE
        } else {
            loading_layout.visibility = View.GONE
        }
    }

    private fun failureHandler(message: String) {
        GlobalScope.launch(Dispatchers.Main) {
            ln_error_goals.visibility = View.VISIBLE
            tv_error_goals.text = message
            delay(3000)
            ln_error_goals.visibility = View.GONE
        }
    }

    override fun onGoalClickListener(goal: Goal) {
        goalViewModel.selectedGoal = goal

        val action = ListGoalsFragmentDirections.nextAction()
        findNavController().navigate(action)
    }

}