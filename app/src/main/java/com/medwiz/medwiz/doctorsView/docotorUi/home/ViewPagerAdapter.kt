package com.medwiz.medwiz.doctorsView.docotorUi.home
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentOnAttachListener
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.medwiz.medwiz.doctorsView.docotorUi.CanceledFragment
import com.medwiz.medwiz.doctorsView.docotorUi.CompletedFragment
import com.medwiz.medwiz.doctorsView.docotorUi.UpcomingFragment
import com.medwiz.medwiz.patientsView.booking.patient.home.HomeScreenListener


private const val NUM_TABS = 3

public class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle,private val listener: ViewPagerListener) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0-> {
                listener.onClickFragment(position)
                return UpcomingFragment()
            }
            1 -> {
                listener.onClickFragment(position)
                return CompletedFragment()
            }
            2 ->{
                listener.onClickFragment(position)
                return CanceledFragment()
            }
        }
        return UpcomingFragment()
    }
}