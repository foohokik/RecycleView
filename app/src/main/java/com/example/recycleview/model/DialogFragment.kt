package com.example.recycleview.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.activityViewModels
import com.example.recycleview.R
import com.example.recycleview.UserViewModel
import com.example.recycleview.databinding.FragmentDialogBinding
import androidx.fragment.app.DialogFragment

 class DialogFragment : DialogFragment() {

    private var _binding: FragmentDialogBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: UserViewModel by activityViewModels()

     private val user: UserData? by lazy { requireArguments().get(PRODUCT_ARG) as? UserData }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogBinding.inflate(inflater, container, false)
        return binding.root
    }


     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
         initViews()
         initListeners()
     }

     private fun initViews() {
         binding.edtSurname.setText(user?.surname)
         binding.etName.setText(user?.name)
         binding.edtPhoneNumber.setText(user?.phoneNumber)
     }

     private fun initListeners() {

         binding.btnBack.setOnClickListener {
             dismiss()
         }

         binding.btnAddFD.setOnClickListener {
             val name = binding.edtSurname.text.toString()
             val surname = binding.etName.text.toString()
             val number = binding.edtPhoneNumber.text.toString()
             if (user != null) {
                 user?.let {
                     sharedViewModel.onEditUser(
                         it.id,
                         name,
                         surname,
                         number
                     )
                 }
             } else {
                 sharedViewModel.onAddUser(
                     name = name,
                     surname = surname,
                     number = number
                 )
             }
             dismiss()
         }
     }

     override fun onStart() {
         super.onStart()
         dialog?.window?.apply {
             setLayout(
                 WindowManager.LayoutParams.MATCH_PARENT,
                 WindowManager.LayoutParams.WRAP_CONTENT
             )
             setBackgroundDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.oval))
         }
     }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

     companion object {
         const val TAG = "DIALOG_FRAGMENT_TAG"
         const val PRODUCT_ARG = "PRODUCT_ARG"
       //  const val POSITION_ARG = "POSITION_ARG"
        // private const val DEFAULT_POSITION = -1

         @JvmStatic
         fun newInstance(user: UserData?) = DialogFragment().apply {
             arguments = Bundle().apply {
                 putSerializable(PRODUCT_ARG, user)
             }
         }
     }
}