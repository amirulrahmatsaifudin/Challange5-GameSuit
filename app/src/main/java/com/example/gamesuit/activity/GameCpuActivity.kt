package com.example.gamesuit.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.gamesuit.until.Callback
import com.example.gamesuit.until.Controller
import com.example.gamesuit.R
import com.example.gamesuit.databinding.ActivityGameCpuBinding
import com.example.gamesuit.until.CallBackFragment
import com.example.gamesuit.until.DialogHasilFragment


@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("ResourceAsColor")
open class GameCpuActivity : AppCompatActivity(), Callback, CallBackFragment {

    private val binding by lazy { ActivityGameCpuBinding.inflate(layoutInflater) }
    val name by lazy { intent.getStringExtra("name") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.pemain1.text = name

        Glide.with(this)
            .load(getString(R.string.url_tekssplashscreen))
            .into(binding.ivLogo)

        val btnPemain = arrayOf(
            binding.ivBatu,
            binding.ivGunting,
            binding.ivKertas,
        )

        val btnCom = arrayOf(
            binding.ivBatucom,
            binding.ivKertascom,
            binding.ivGuntingcom,
        )


        val controller = Controller(this, name, "CPU")
        btnPemain.forEachIndexed { index, ImageView ->
            ImageView.setOnClickListener {
                val hasilCom = btnCom.random()
                val hasilPemain = btnPemain[index].contentDescription.toString()

                Log.d("PEMAIN SATU", "Memilih $hasilPemain")
                Log.e("PEMAIN SATU", "Memilih $hasilPemain")
                showToast(this, "$name Memilih $hasilPemain")
                disableClick(false)

                hasilCom.setBackgroundResource(R.drawable.backgorund_shape)
                controller.cekSuit(
                    hasilPemain, hasilCom.contentDescription.toString()
                )
                Log.d("CPU", "Memilih $hasilCom")
                showToast(this, "CPU Memilih ${hasilCom.contentDescription}")
                btnPemain.forEach {
                    it.setBackgroundResource(android.R.color.transparent)
                }
                btnPemain[index].setBackgroundResource(R.drawable.backgorund_shape)
            }
        }

        binding.ivRefresh.setOnClickListener {
            Log.d("reset", "Clicked")
            reset(android.R.color.transparent)
        }

        binding.ivKeluar.setOnClickListener {
            finish()

        }
    }


    private fun disableClick(isEnable: Boolean) {
        binding.apply {
            ivBatu.isEnabled = isEnable
            ivKertas.isEnabled = isEnable
            ivGunting.isEnabled = isEnable
        }
    }


    override fun hasil(hasil: String) {
        val dialogResult = DialogHasilFragment()
        val bundle = Bundle()
        bundle.putString("hasil", hasil)
        dialogResult.arguments = bundle
        dialogResult.show(supportFragmentManager, "DialogResult")
    }

    override fun reset(
        backgroundklik: Int
    ) {
        binding.apply {
            ivBatu.setBackgroundResource(backgroundklik)
            ivKertas.setBackgroundResource(backgroundklik)
            ivGunting.setBackgroundResource(backgroundklik)
            ivBatucom.setBackgroundResource(backgroundklik)
            ivKertascom.setBackgroundResource(backgroundklik)
            ivGuntingcom.setBackgroundResource(backgroundklik)
        }
        disableClick(true)

    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}