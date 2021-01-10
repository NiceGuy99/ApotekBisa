package org.d3if4055.apotekbisa.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import org.d3if4055.apotekbisa.R
import org.d3if4055.apotekbisa.database.ApotekBisa
import org.d3if4055.apotekbisa.database.ApotekBisaDatabase
import org.d3if4055.apotekbisa.databinding.ActivityTambahTransaksiBinding
import org.d3if4055.apotekbisa.ui.home.ApotekBisaViewModel
import org.d3if4055.apotekbisa.utils.rupiah

class TambahTransaksiActivity : AppCompatActivity() {

    private lateinit var viewModel: ApotekBisaViewModel
    private lateinit var binding: ActivityTambahTransaksiBinding
    private var paket = ""
    private var harga = 0.0
    private var kembalian = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Apotek Bisa"
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tambah_transaksi)

        cekHarga(binding.etTotalHarga)
        binding.btnProses.setOnClickListener {
            inputCheck()
        }

        val application = requireNotNull(this).application
        val dataSource = ApotekBisaDatabase.getInstance(application).apotekBisaDAO
        val viewModelFactory = ApotekBisaViewModel.Factory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ApotekBisaViewModel::class.java)
    }

    private fun cekHarga(etHarga: EditText) {
        binding.rgMenu.setOnCheckedChangeListener{ _, _ ->
            when {
                binding.rbMenu1.isChecked -> {
                    harga = 30000.0
                    paket = "Kapsul"
                    etHarga.setText(rupiah(harga))
                }
                binding.rbMenu2.isChecked -> {
                    harga = 35000.0
                    paket = "Syrup"
                    etHarga.setText(rupiah(harga))
                }
                binding.rbMenu3.isChecked -> {
                    harga = 45000.0
                    paket = "Tablet"
                    etHarga.setText(rupiah(harga))
                }
            }
        }
    }

    private fun inputCheck() {
        when {
            binding.etNamaPelanggan.text.trim().isEmpty() -> {
                binding.inputLayoutNama.error = getString(R.string.null_field, "Nama pelanggan")
            }
            binding.etTotalHarga.text.trim().isEmpty() -> {
                binding.inputLayoutTotalHarga.error = getString(R.string.null_field, "Total harga")
            }
            binding.etTotalBayar.text.trim().isEmpty() -> {
                binding.inputLayoutTotalBayar.error = getString(R.string.null_field, "Total bayar")
            }
            else -> doProcess()
        }
    }

    private fun doProcess() {
        val totalBayar = binding.etTotalBayar.text.toString().toDouble()

        if (hitungTransaksi(totalBayar, harga)) {
            val nama = binding.etNamaPelanggan.text.toString()
            val apotekbisaa = ApotekBisa(0, nama, paket, harga, totalBayar, kembalian)

            TambahTransaksiDialogFragment(
                apotekbisaa
            ).show(supportFragmentManager, "")
        } else {
            binding.inputLayoutTotalBayar.error = getString(R.string.uang_kurang)
        }
    }

    private fun hitungTransaksi(jumlahUang: Double, harga: Double): Boolean {
        return when {
            jumlahUang >= harga -> {
                kembalian = jumlahUang - harga
                true
            }
            else -> false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.reset, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.item_reset -> {
                reset()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun reset() {
        binding.etNamaPelanggan.setText("")
        binding.inputLayoutNama.error = null
        binding.rgMenu.clearCheck()
        binding.etTotalHarga.setText("")
        binding.inputLayoutTotalHarga.error = null
        binding.etTotalBayar.setText("")
        binding.inputLayoutTotalBayar.error = null
    }
}
