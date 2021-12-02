
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.movieapp.feature.list.ListFragment
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieapp.feature.favourite.FavouriteFragment

class TabAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var numberOfTabs: Int) : FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                val bundle = Bundle()
                val listFragment = ListFragment()
                listFragment.arguments = bundle
                listFragment
            }
            1 -> {
                val bundle = Bundle()
                val favouriteFragment = FavouriteFragment()
                favouriteFragment.arguments = bundle
                favouriteFragment
            }
            else -> ListFragment()
        }
    }

    override fun getItemCount(): Int {
        return numberOfTabs
    }
}