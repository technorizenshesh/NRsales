package com.nr.nrsales.ui.fragments.home

import android.app.AlertDialog
import android.graphics.Color
import android.util.Log
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.core.view.get
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import com.nr.nrsales.R
import com.nr.nrsales.databinding.FragmentHomeBinding
import com.nr.nrsales.utils.BaseActivity.Companion.TAG
import com.nr.nrsales.utils.BaseFragment
import com.nr.nrsales.utils.GlobalUtility
import com.nr.nrsales.utils.SharedPrf
import com.nr.nrsales.viewmodel.LoginViewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private lateinit var mBinding: FragmentHomeBinding
    private val viewmodel by viewModels<LoginViewModel>()

    override fun onBindTo(binding: ViewDataBinding?) {
        mBinding = binding as FragmentHomeBinding
        init()
    }

    private fun init() {
        mBinding.navView.menu[0].setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.logout) {
                mBinding.drawerLayout.close()
                val alertDialogBuilder = AlertDialog.Builder(context)
                alertDialogBuilder.setMessage("are sure you want to logout")
                    .setCancelable(false)
                    .setPositiveButton(
                    "yes"
                ) { dialog, _ ->
                    dialog.cancel()
                    GlobalUtility.showToast("Logout Successfully", getBaseContext())
                    getSharedPrfData().setStoredTag(SharedPrf.LOGIN, "false")
                    findNavController(mBinding.root).navigate(R.id.action_homeFragment_to_splashFragment)

                }.setNegativeButton("no") { dialog, _ ->
                    dialog.cancel()
                }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }
            true
        }
        mBinding.mailLayout.menu.setOnClickListener {
            mBinding.drawerLayout.open()/*if (mBinding.drawerLayout.isDrawerOpen(it)){

            }else{

            }*/
        }
        mBinding.mailLayout.getAQuoteCard.setOnClickListener {
            Log.e(TAG, "init: " + getSharedPrfData().getStoredTag(SharedPrf.USER_ID))
            Log.e(TAG, "init: " + getBaseContext().getString(R.string.email_id))
            findNavController(it).navigate(R.id.action_homeFragment_to_getAQuoteFragment)

        }

    }

}