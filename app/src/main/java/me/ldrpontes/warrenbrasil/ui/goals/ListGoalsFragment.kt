package me.ldrpontes.warrenbrasil.ui.goals

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialElevationScale
import kotlinx.android.synthetic.main.fragment_list_goals.*
import kotlinx.coroutines.*
import me.ldrpontes.domain.entities.Goal
import me.ldrpontes.warrenbrasil.R
import me.ldrpontes.warrenbrasil.ui.adapters.ListGoalsAdapter
import me.ldrpontes.warrenbrasil.utils.State
import me.ldrpontes.warrenbrasil.utils.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListGoalsFragment : Fragment(), ListGoalsAdapter.ListGoalsListener {

    private val goalsList: ArrayList<Goal> = ArrayList()
    private val adapter: ListGoalsAdapter = ListGoalsAdapter(goalsList, this)
    private val goalViewModel: GoalViewModel by sharedViewModel()
    private var getGoalsCalled = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_goals, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialElevationScale(false)
        reenterTransition = MaterialElevationScale(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        startSearchInputListeners()
        startGoalsObserver()
        startSwipeToRefreshListener()

        if (!getGoalsCalled) {
            getGoalsHandler()
            getGoalsCalled = true
        }


        startRecyclerViewGoals()
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

        ted_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }


    private fun startRecyclerViewGoals() {
        rv_list_goals.layoutManager = LinearLayoutManager(context)
        rv_list_goals.adapter = adapter

        postponeEnterTransition()
        rv_list_goals.doOnPreDraw {
            startPostponedEnterTransition()
        }
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


    private fun getGoalsHandler() {
        goalViewModel.getGoalsHandler()
    }


    private fun startSwipeToRefreshListener() {
        swipe_to_refresh_goals.setOnRefreshListener {
            getGoalsHandler()
        }
    }


    private fun loadingHandler(isLoading: Boolean) {
        swipe_to_refresh_goals.isRefreshing = isLoading
    }


    private fun failureHandler(message: String) {
        GlobalScope.launch(Dispatchers.Main) {
            ln_error_goals.visibility = View.VISIBLE
            tv_error_goals.text = message
            delay(3000)
            ln_error_goals.visibility = View.GONE
        }
    }


    override fun onGoalClickListener(imageView: View, titleView: View, goal: Goal) {
        goalViewModel.selectedGoal = goal

        val directions: NavDirections = ListGoalsFragmentDirections.nextAction()

        val extras = FragmentNavigatorExtras(
            imageView to "iv_goal_detail",
            titleView to "tv_goal_detail"
        )

        findNavController().navigate(directions, extras)
    }
}