package com.example.submission1.favorite

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1.UserAdapter
import com.example.submission1.data.local.DbModul
import com.example.submission1.databinding.ActivityFavoriteBinding
import com.example.submission1.detail.DetailActivity

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val adapter by lazy {
        UserAdapter { user ->
            Intent(this, DetailActivity:: class.java).apply {
                putExtra("item", user)
                startActivity(this)
            }
        }
    }

    private val viewModel by viewModels<FavoriteViewModel> {
        FavoriteViewModel.Factory(DbModul(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.adapter = adapter

        viewModel.getUserFavorite().observe(this) {
            adapter.setData(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}