package com.neouul.umc10android.week02.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.neouul.umc10android.week02.R
import com.neouul.umc10android.week02.databinding.FragmentCartBinding

class CartFragment : Fragment(R.layout.fragment_cart) {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCartBinding.bind(view)

        binding.btnOrder.setOnClickListener {
            // nav_graph.xml에 정의한 action ID를 사용해 이동합니다.
            findNavController().navigate(R.id.action_homeFragment_to_shopFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}