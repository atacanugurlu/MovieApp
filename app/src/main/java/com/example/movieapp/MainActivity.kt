package com.example.movieapp
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.movieapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        // Create Toolbar and set support action bar
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        // Get NavController
        val navController = findNavController(R.id.myNavHostFragment)

        // Get App Configuration from nav graph
        appBarConfiguration = AppBarConfiguration(navController.graph)

        // Handles arrow back button
        setupActionBarWithNavController(navController, appBarConfiguration)

        listenBackStackChange()
    }


    // This function is required with appbar to handle back button
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.myNavHostFragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    private fun listenBackStackChange() {
        // Get NavHostFragment
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.myNavHostFragment)

        // ChildFragmentManager of NavHostFragment
        val navHostChildFragmentManager = navHostFragment?.childFragmentManager

        navHostChildFragmentManager?.addOnBackStackChangedListener {

            val backStackEntryCount = navHostChildFragmentManager.backStackEntryCount
            val fragments = navHostChildFragmentManager.fragments
            val fragmentCount = fragments.size

            println("🎃 Main graph backStackEntryCount: $backStackEntryCount, fragmentCount: $fragmentCount, fragments: $fragments")

            Toast.makeText(
                this,
                "🎃 Main graph backStackEntryCount: $backStackEntryCount, fragmentCount: $fragmentCount, fragments: $fragments",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
