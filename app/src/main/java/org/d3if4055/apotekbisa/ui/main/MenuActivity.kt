package org.d3if4055.apotekbisa.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_menu.*
import org.d3if4055.apotekbisa.R
import org.d3if4055.apotekbisa.databinding.ActivityMenuBinding
import org.d3if4055.apotekbisa.ui.HomeActivity
import org.d3if4055.apotekbisa.ui.add.TambahTransaksiActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu)
        supportActionBar?.hide()

        // click listener
        binding.btnHistory.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, TambahTransaksiActivity::class.java))
        }

        binding.btnProfil.setOnClickListener {
            startActivity(Intent(this, Profile1::class.java))
        }
    }
}
