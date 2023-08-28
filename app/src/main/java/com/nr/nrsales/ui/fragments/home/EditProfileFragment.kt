package com.nr.nrsales.ui.fragments.home

import androidx.databinding.ViewDataBinding
import com.nr.nrsales.R
import com.nr.nrsales.databinding.FragmentEditProfileBinding
import com.nr.nrsales.utils.BaseFragment

class EditProfileFragment : BaseFragment(R.layout.fragment_edit_profile) {
    lateinit var mBinding: FragmentEditProfileBinding
    override fun onBindTo(binding: ViewDataBinding?) {
        mBinding = binding as FragmentEditProfileBinding
    }

}
