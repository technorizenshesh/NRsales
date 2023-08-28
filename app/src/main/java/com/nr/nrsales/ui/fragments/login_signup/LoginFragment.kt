package com.nr.nrsales.ui.fragments.login_signup

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.nr.nrsales.MainApplication
import com.nr.nrsales.R
import com.nr.nrsales.databinding.FragmentLoginBinding
import com.nr.nrsales.utils.BaseFragment
import com.nr.nrsales.utils.GlobalUtility
import com.nr.nrsales.utils.NetworkResult
import com.nr.nrsales.utils.SharedPrf
import com.nr.nrsales.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private lateinit var mBinding: FragmentLoginBinding
    private lateinit var context: Context
    private val viewmodel by viewModels<LoginViewModel>()
    private lateinit var sharedPrf: SharedPrf
    override fun onBindTo(binding: ViewDataBinding?) {
        mBinding = binding as FragmentLoginBinding
        context = requireActivity()
        sharedPrf = SharedPrf(requireContext())
        init()
    }

    private fun init() {
        mBinding.signUpBtn.setOnClickListener {
            Navigation.findNavController(mBinding.root)
                .navigate(R.id.action_LoginFragment_to_signupFragment)
        }
        mBinding.btnSubmit.setOnClickListener {
            // onClick(mBinding.btnSubmit)
            val email = mBinding.edtEmail.text.toString()
            val pass = mBinding.edtPass.text.toString()
            if (email.isEmpty()) {
                mBinding.edtEmail.text = (null)
                GlobalUtility.showToast(getString(R.string.invalid_email), context)
                mBinding.edtEmail.requestFocus()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                GlobalUtility.showToast(getString(R.string.valid_email), context)
                mBinding.edtEmail.requestFocus()
            } else if (pass.isEmpty()) {
                mBinding.edtEmail.requestFocus()
                GlobalUtility.showToast(getString(R.string.invalid_password), context)
            } else if (pass.length <= 5) {
                mBinding.edtEmail.requestFocus()
                GlobalUtility.showToast(getString(R.string.invalid_password_length), context)
            } else {
                if (!MainApplication.hasInternetConnection(requireContext())) {
                    GlobalUtility.showNoNetworkMessage(requireActivity())
                } else {
                    GlobalUtility.showProgressMessage(requireActivity(), requireActivity().getString(R.string.loading))
                    fetchLoginResponse(email, pass)
                }

            }
        }
        viewmodel.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    GlobalUtility.hideProgressMessage()
                    response.data?.let {
                        Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()
                        sharedPrf.setStoredTag(SharedPrf.LOGIN, "true")
                        sharedPrf.setStoredTag(SharedPrf.USER_ID, it)
                        Log.e(TAG, "init: " + sharedPrf.getStoredTag(SharedPrf.USER_ID))
                        findNavController(mBinding.root).navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                }
                is NetworkResult.Error -> {
                    GlobalUtility.hideProgressMessage()
                    Log.e(TAG, "fetchLoginResponse: " + response.message)
                    Toast.makeText(
                        context,
                        "User Not Found",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is NetworkResult.Loading -> {
                    GlobalUtility.hideProgressMessage()
                }
            }
        }
    }

    private fun fetchLoginResponse(email: String, pass: String) {
        val map: HashMap<String, Any> = HashMap()
        map["username"] = email
        map["password"] = pass
        viewmodel.fetchLoginResponse(map)
    }

    companion object {
        val TAG = LoginFragment::class.qualifiedName
        fun getInstance(bundle: Bundle): LoginFragment {
            val fragment = LoginFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}