package org.smartregister.pathzeir.reporting.monthly

import android.os.Bundle
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_monthly_reports.*
import org.smartregister.Context
import org.smartregister.pathzeir.BuildConfig
import org.smartregister.pathzeir.R
import org.smartregister.pathzeir.reporting.ReportGroupingModel
import org.smartregister.pathzeir.reporting.common.ReportingUtils
import org.smartregister.view.activity.MultiLanguageActivity

class MonthlyReportsActivity : MultiLanguageActivity() {

    object Constants {
        const val SELECT_TAB = "select_tab"
    }

    val monthlyReportsViewModel by viewModels<MonthlyReportsViewModel>
    { ReportingUtils.createFor(MonthlyReportsViewModel()) }

    private lateinit var reportsPagerAdapter: MonthlyReportsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monthly_reports)
        reportsPagerAdapter = MonthlyReportsPagerAdapter(this, supportFragmentManager)

        monthlyReportsViewModel.apply {
            draftedMonths.observe(this@MonthlyReportsActivity, {
                reportFragmentTabLayout.getTabAt(1)?.text = getString(R.string.monthly_draft_reports, it.size)
            })
        }

        //Setup UI
        nameInitialsTextView.apply {
            setOnClickListener { onBackPressed() }
            text = getLoggedInUserInitials()
        }
        containerViewPager.apply {
            adapter = reportsPagerAdapter
            currentItem = intent.getIntExtra(Constants.SELECT_TAB, 1)
        }

        reportFragmentTabLayout.apply {
            setupWithViewPager(containerViewPager)
            tabRippleColor = null
        }
        titleTextView.apply {
            if (BuildConfig.USE_HIA2_DIRECTLY)
                text = getString(R.string.hia2_reports)
            else
                text = ReportGroupingModel(this@MonthlyReportsActivity).reportGroupings.first().displayName
        }
    }

    private fun getLoggedInUserInitials(): String {
        val allSharedPreferences = Context.getInstance().allSharedPreferences()
        return allSharedPreferences.getANMPreferredName(allSharedPreferences.fetchRegisteredANM())
                .split(" ").take(2).map { it.first() }.joinToString("")
    }

    override fun onResume() {
        super.onResume()
        monthlyReportsViewModel.apply {
            fetchDraftedMonths()
            fetchUnDraftedMonths()
            fetchAllSentReportMonths()
            fetchAllDailyTalliesDays()
        }
    }
}