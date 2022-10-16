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
import com.example.gamesuit.R
import com.example.gamesuit.databinding.ActivityGamePlayerBinding
import com.example.gamesuit.until.CallBackFragment
import com.example.gamesuit.until.Callback
import com.example.gamesuit.until.Controller
import com.example.gamesuit.until.DialogHasilFragment

@RequiresApi(Build.VERSION_CODES.M)
@SuppressLint("ResourceAsColor")
open class GamePlayerActivity : AppCompatActivity(), Callback, CallBackFragment {

    private val binding by lazy { ActivityGamePlayerBinding.inflate(layoutInflater) }
    val name by lazy { intent.getStringExtra("name") }
    private var hasilPemainSatu = ""
    private var hasilPemainDua = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.pemain1.text = name
        showToast(this, "Silahkan pilih terlebih dahulu $name")

        Glide.with(this)
            .load(getString(R.string.url_tekssplashscreen))
            .into(binding.ivLogo)

        val btnPemainSatu = arrayOf(
            binding.ivBatu1,
            binding.ivGunting1,
            binding.ivKertas1,
        )

        val btnPemainDua = arrayOf(
            binding.ivBatu2,
            binding.ivGunting2,
            binding.ivKertas2,
        )


        disableClick2(false)
        val controller = Controller(this, name, "Pemain 2")
        btnPemainSatu.forEachIndexed { index, ImageView ->
            Log.d("pemain satu", "klikk")
            ImageView.setOnClickListener {
                hasilPemainSatu = btnPemainSatu[index].contentDescription.toString()

                Log.d("PEMAIN SATU", "Memilih $hasilPemainSatu")
                showToast(this, "$name Memilih $hasilPemainSatu")

                disableClick1(false)
                disableClick2(true)

                btnPemainSatu.forEach {
                    it.setBackgroundResource(android.R.color.transparent)
                }
                btnPemainSatu[index].setBackgroundResource(R.drawable.backgorund_shape)
            }
        }

        btnPemainDua.forEachIndexed { index, ImageView ->
            Log.e("pemain Dua", "klikk")
            ImageView.setOnClickListener {
                hasilPemainDua = btnPemainDua[index].contentDescription.toString()
                Log.d("PEMAIN DUA", "Memilih $hasilPemainDua")
                showToast(this, "Pemain 2 Memilih $hasilPemainDua")
                disableClick2(false)
                if (hasilPemainSatu != "") {
                    controller.cekSuit(hasilPemainSatu, hasilPemainDua)
                    btnPemainDua.forEach {
                        it.setBackgroundResource(android.R.color.transparent)
                    }
                    btnPemainDua[index].setBackgroundResource(R.drawable.backgorund_shape)
                }
            }

            binding.ivRefresh.setOnClickListener {
                Log.d("reset", "Clicked")
                reset(android.R.color.transparent)
            }

            binding.ivClose.setOnClickListener {
                finish()
            }

        }
    }

    private fun disableClick1(isEnable: Boolean) {
        binding.ivBatu1.isEnabled = isEnable
        binding.ivKertas1.isEnabled = isEnable
        binding.ivGunting1.isEnabled = isEnable
    }

    private fun disableClick2(isEnable: Boolean) {
        binding.ivBatu2.isEnabled = isEnable
        binding.ivKertas2.isEnabled = isEnable
        binding.ivGunting2.isEnabled = isEnable
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
            ivBatu1.setBackgroundResource(backgroundklik)
            ivGunting1.setBackgroundResource(backgroundklik)
            ivKertas1.setBackgroundResource(backgroundklik)
            ivBatu2.setBackgroundResource(backgroundklik)
            ivGunting2.setBackgroundResource(backgroundklik)
            ivKertas2.setBackgroundResource(backgroundklik)
        }
        hasilPemainSatu = ""
        hasilPemainDua = ""
        disableClick1(true)
        disableClick2(false)

    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}