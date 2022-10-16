package com.example.gamesuit.onboarding

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import com.example.gamesuit.databinding.FragmentOnBoarding3Binding
import com.example.gamesuit.activity.MenuActivity


class OnBoarding3Fragment : Fragment() {
    private var _binding: FragmentOnBoarding3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoarding3Binding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etName.addTextChangedListener {
            Log.e("Nama", it.toString())
            if (it.toString().isNotEmpty()) {
                binding.btnNext.visibility = View.VISIBLE
            } else {
                binding.btnNext.visibility = View.GONE
            }
        }
        binding.btnNext.setOnClickListener {
            if (binding.etName.text.isNotEmpty()) {

                val name = binding.etName.text.toString()

                val intent = Intent(activity, MenuActivity::class.java)
                intent.putExtra("name", name)
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}