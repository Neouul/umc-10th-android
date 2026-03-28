package com.neouul.umc10android.week02.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.neouul.umc10android.week02.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    // 마지막으로 시스템 뒤로가기를 누른 시간을 저장할 변수
    private var backPressedTime: Long = 0

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Safe Args 대리자(Delegate) 선언
    private val args: HomeFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 전달받은 TITLE 데이터 가져오기
        val title = args.title

        // TextView에 값 설정하기
        binding.tvTitle.text = title

        // 콜백 등록
        // 시스템 뒤로가기 2번 연속으로 터치하면 Activity 종료시키기
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() - backPressedTime < 2000) {
                    // 2초 이내에 다시 누르면 액티비티 종료
                    requireActivity().finish()
                } else {
                    // 처음 눌렀을 때 메시지 표시
                    backPressedTime = System.currentTimeMillis()
                    Toast.makeText(requireContext(), "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}