package com.example.submission1.theme

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.submission1.data.local.PreferencesTheme
import com.example.submission1.databinding.ActivitySettingBinding

class ThemeActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private val viewModel by viewModels<ThemeViewModel> {
        ThemeViewModel.Factory(PreferencesTheme(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.getTheme().observe(this) {
            if (it) {
                binding.switchTheme.text = "Tema Gelap"
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                binding.switchTheme.text = "Tema Terang"
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            binding.switchTheme.isChecked = it
        }

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            viewModel.saveTheme(isChecked)
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