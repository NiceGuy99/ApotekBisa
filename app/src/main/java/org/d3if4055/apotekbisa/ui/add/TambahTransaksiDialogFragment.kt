package org.d3if4055.apotekbisa.ui.add

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.dialog_fragment_tambah_transaksi.*

import org.d3if4055.apotekbisa.R
import org.d3if4055.apotekbisa.database.ApotekBisa
import org.d3if4055.apotekbisa.database.ApotekBisaDatabase
import org.d3if4055.apotekbisa.ui.HomeActivity
import org.d3if4055.apotekbisa.utils.rupiah
import org.d3if4055.apotekbisa.ui.home.ApotekBisaViewModel

@Suppress("SpellCheckingInspection")
class TambahTransaksiDialogFragment(
    private val dataApotekBisa: ApotekBisa
) : DialogFragment() {

    private lateinit var viewModel: ApotekBisaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dialog_fragment_tambah_transaksi, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_Material_Light_Dialog_MinWidth)

        val application = requireNotNull(this.activity).application
        val dataSource = ApotekBisaDatabase.getInstance(application).apotekBisaDAO
        val viewModelFactory = ApotekBisaViewModel.Factory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ApotekBisaViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setEditText()

        btn_submit_transaksi.setOnClickListener {
            viewModel.onClickInsert(dataApotekBisa)
            this.dismiss()
            startActivity(
                Intent(requireContext(), HomeActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            )
            activity?.finish()
            Toast.makeText(requireContext(), getString(R.string.success_insert), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setEditText() {
        et_nama_pelanggan.setText(dataApotekBisa.nama)
        et_paket.setText(dataApotekBisa.paket)
        et_total_harga.setText(rupiah(dataApotekBisa.harga))
        et_total_bayar.setText(rupiah(dataApotekBisa.bayar))
        et_kembalian.setText(rupiah(dataApotekBisa.kembalian))
    }

}
