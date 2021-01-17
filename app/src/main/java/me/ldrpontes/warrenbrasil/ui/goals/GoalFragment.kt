package me.ldrpontes.warrenbrasil.ui.goals

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.transition.TransitionInflater
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_goal.*
import me.ldrpontes.warrenbrasil.R
import me.ldrpontes.warrenbrasil.databinding.FragmentGoalBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class GoalFragment : Fragment() {
    private val goalViewModel: GoalViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val binding = FragmentGoalBinding.inflate(layoutInflater, container, false)

        binding.goal = goalViewModel.selectedGoal

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment = NavHostFragment.findNavController(this)
        NavigationUI.setupWithNavController(toolbar_goal, navHostFragment)

        toolbar_goal.title = goalViewModel.selectedGoal?.name

        startImageViewHandler()
        startPieChartHandler()
    }


    private fun startImageViewHandler() {
        iv_goal.apply {
            transitionName = "iv_goal_detail"
            Picasso.get().load(goalViewModel.selectedGoal?.background?.regular).fit()
                .error(R.drawable.ic_pictures)
                .into(iv_goal)
        }

    }


    private fun startPieChartHandler() {
        val goalAmount = goalViewModel.selectedGoal?.goalAmount?.toFloat()
        val totalBalance = goalViewModel.selectedGoal?.totalBalance?.toFloat()

        if (goalAmount != null && totalBalance != null) {
            startPieChart(goalAmount, totalBalance)
        } else {
            invalidDataHandler()
        }


    }


    private fun invalidDataHandler() {
        pie_chart_goal.visibility = View.GONE
        no_data_layout.visibility = View.VISIBLE
    }


    private fun startPieChart(goalAmount: Float, totalBalance: Float) {
        val entries = setupPieChartEntries()

        val dataSet = setupPieChartDataSet(entries)

        val pieData = setupPieData(dataSet)

        tv_completed.text =
            getString(R.string.completed, getGoalPercentageFormatted(goalAmount, totalBalance))

        pie_chart_goal.isRotationEnabled = true;
        pie_chart_goal.data = pieData
        pie_chart_goal.setHoleColor(Color.parseColor("#00000000"))
        pie_chart_goal.legend.isEnabled = false
        pie_chart_goal.description.isEnabled = false
    }


    private fun getGoalPercentageFormatted(goalAmount: Float, totalBalance: Float): String {
        val formatter = NumberFormat.getInstance()
        formatter.maximumFractionDigits = 2
        return formatter.format((totalBalance / goalAmount) * 100) + "%"
    }


    private fun setupPieChartEntries(): ArrayList<PieEntry> {
        val pieEntries = ArrayList<PieEntry>()

        val totalBalancePercentage: Float
        val goalAmountPercentage: Float

        if (goalViewModel.selectedGoal!!.isGoalCompleted()) {
            totalBalancePercentage = 100f
            goalAmountPercentage = 0f
        } else {
            totalBalancePercentage = goalViewModel.selectedGoal!!.getTotalBalancePercentage()!!
            goalAmountPercentage = 100 - totalBalancePercentage
        }


        pieEntries.add(PieEntry(totalBalancePercentage, getString(R.string.total)))
        pieEntries.add(PieEntry(goalAmountPercentage, getString(R.string.goal)))

        return pieEntries
    }


    private fun setupPieChartDataSet(entries: ArrayList<PieEntry>): PieDataSet {
        val dataSet = PieDataSet(entries, null)
        dataSet.colors =
            intArrayOf(Color.parseColor("#d81b60"), Color.parseColor("#757575")).toList()
        dataSet.valueTextSize = 14f
        dataSet.valueTextColor = Color.parseColor("#FFFFFF")
        dataSet.valueFormatter = setupPieChartValueFormatter()

        return dataSet
    }


    private fun setupPieChartValueFormatter(): ValueFormatter {
        return object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                var valueToFormat = 0.0f

                valueToFormat = if (goalViewModel.selectedGoal!!.isGoalCompleted()) {
                    if (value >= 100) goalViewModel.selectedGoal!!.goalAmount!!.toFloat() else goalViewModel.selectedGoal!!.totalBalance.toFloat()
                } else {
                    if (value == goalViewModel.selectedGoal!!.getTotalBalancePercentage())  goalViewModel.selectedGoal!!.totalBalance.toFloat() else goalViewModel.selectedGoal!!.goalAmount!!.toFloat()
                }

                val formatter = NumberFormat.getCurrencyInstance()
                formatter.maximumFractionDigits = 2
                formatter.currency = Currency.getInstance("BRL")

                return formatter.format(valueToFormat)
            }
        }
    }


    private fun setupPieData(dataSet: PieDataSet): PieData {
        val pieData = PieData(dataSet)
        pieData.setDrawValues(true)

        return pieData
    }
}