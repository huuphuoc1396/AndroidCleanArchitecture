package com.example.clean_architecture.presentation.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.clean_architecture.R
import com.example.clean_architecture.domain.core.extension.defaultEmpty

class NetworkErrorDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return createNetworkErrorDialog()
    }

    private fun createNetworkErrorDialog(): AlertDialog {
        val message: String = getErrorMessage()
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.msg_network_error)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(R.string.ok) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }.create()
    }

    private fun getErrorMessage(): String {
        return arguments?.getString(KEY_ERROR_MESSAGE).defaultEmpty()
    }

    companion object {

        private const val KEY_ERROR_MESSAGE = "KEY_ERROR_MESSAGE"

        fun newInstance(message: String): NetworkErrorDialogFragment {
            val fragment = NetworkErrorDialogFragment()
            fragment.arguments = bundleOf(
                KEY_ERROR_MESSAGE to message
            )
            return fragment
        }
    }
}