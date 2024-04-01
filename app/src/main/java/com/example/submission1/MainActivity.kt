package com.example.submission1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.submission1.data.local.PreferencesTheme
import com.example.submission1.data.responsemodel.ResponseUserGithub
import com.example.submission1.databinding.ActivityMainBinding
import com.example.submission1.detail.DetailActivity
import com.example.submission1.favorite.FavoriteActivity
import com.example.submission1.theme.ThemeActivity
import com.example.submission1.utils.Result

class MainActivity : ComponentActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy {
        UserAdapter { user ->
            Intent(this, DetailActivity:: class.java).apply {
                putExtra("akun", user)
                startActivity(this)
            }
        }
    }

    private val viewModel by viewModels<MainViewModel> {
        MainViewModel.Factory(PreferencesTheme(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getTheme().observe(this) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

        }

        binding.recyclerView5.layoutManager = LinearLayoutManager(this)
        binding.recyclerView5.setHasFixedSize(true)
        binding.recyclerView5.adapter = adapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.getUser(p0.toString())
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean = false

        })

        viewModel.resultUser.observe(this) {
            when (it) {
                is Result.Success<*> -> {
                    adapter.setData(it.data as MutableList<ResponseUserGithub.Item>)
                }
                is Result.Error -> {
                    Toast.makeText(this, it.exception.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is Result.Loading -> {
                    binding.LoadingBar.isVisible = it.isLoading
                }
            }
        }

        viewModel.getUser()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                Intent(this, FavoriteActivity::class.java).apply {
                    startActivity(this)
                }
            }
            R.id.setting -> {
                Intent(this, ThemeActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}