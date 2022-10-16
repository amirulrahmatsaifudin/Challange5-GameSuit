package com.example.gamesuit.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.gamesuit.databinding.ActivityMenuBinding
import com.google.android.material.snackbar.Snackbar

@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("SetTextI18n")
class MenuActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMenuBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")

        binding.tvPemainVsPemain.text = "$name VS Pemain"
        binding.tvPemainVsCPU.text = "$name VS CPU"

        binding.ivPemainVsPemain.setOnClickListener {
            val mIntent = Intent(this, GamePlayerActivity::class.java)
            mIntent.putExtra("name", name)
            startActivity(mIntent)

        }
        binding.ivPemainVsCPU.setOnClickListener {
            val mIntent = Intent(this, GameCpuActivity::class.java)
            mIntent.putExtra("name", name)
            startActivity(mIntent)
        }

        Snackbar.make(
            binding.menuActivity,
            "Selamat datang $name",
            Snackbar.LENGTH_LONG
        ).setAction("TUTUP") {
        }.show()
    }
}