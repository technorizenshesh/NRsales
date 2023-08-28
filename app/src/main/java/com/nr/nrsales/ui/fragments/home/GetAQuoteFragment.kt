package com.nr.nrsales.ui.fragments.home

import androidx.databinding.ViewDataBinding
import com.nr.nrsales.R
import com.nr.nrsales.databinding.FragmentGetAQuoteBinding
import com.nr.nrsales.utils.BaseFragment

class GetAQuoteFragment : BaseFragment(R.layout.fragment_get_a_quote) {
    lateinit var mBinding: FragmentGetAQuoteBinding
    override fun onBindTo(binding: ViewDataBinding?) {
        mBinding = binding as FragmentGetAQuoteBinding
        init()
    }

    private fun init() {
        mBinding.headerLay.tvLogo.text = "Quote"
        mBinding.headerLay.imgHeader.setOnClickListener { onBackPressed() }
    }

}
