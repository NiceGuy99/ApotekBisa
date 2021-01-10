package org.d3if4055.apotekbisa.ui.home

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3if4055.apotekbisa.R
import org.d3if4055.apotekbisa.database.ApotekBisa
import org.d3if4055.apotekbisa.databinding.RecyclerviewApotekbisaBinding
import org.d3if4055.apotekbisa.utils.RecyclerViewClickListener
import org.d3if4055.apotekbisa.utils.convertLongToDateString
import org.d3if4055.apotekbisa.utils.rupiah

class ApotekBisaAdapter(
    private val apotekBisa: List<ApotekBisa>
) : RecyclerView.Adapter<ApotekBisaAdapter.ApotekBisaViewHolder>() {

    var listener: RecyclerViewClickListener? = null

    inner class ApotekBisaViewHolder(
        val recyclerviewapotekBisaBinding: RecyclerviewApotekbisaBinding
    ) : RecyclerView.ViewHolder(recyclerviewapotekBisaBinding.root)

    override fun getItemCount() = apotekBisa.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ApotekBisaViewHolder (
        DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.recyclerview_apotekbisa, parent, false)
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ApotekBisaViewHolder, position: Int) {
        holder.recyclerviewapotekBisaBinding.tvDate.text = convertLongToDateString(apotekBisa[position].tanggal)
        holder.recyclerviewapotekBisaBinding.tvNamaPelanggan.text = apotekBisa[position].nama
        holder.recyclerviewapotekBisaBinding.tvPaket.text = "(${apotekBisa[position].paket})"
        holder.recyclerviewapotekBisaBinding.tvHarga.text = rupiah(apotekBisa[position].harga)

        when (apotekBisa[position].paket) {
            ("Kapsul") -> {
                holder.recyclerviewapotekBisaBinding.imagePaket.setImageResource(R.drawable.cut_wash)
            }
            ("Syrup") -> {
                holder.recyclerviewapotekBisaBinding.imagePaket.setImageResource(R.drawable.haircut)
            }
            ("Tablet") -> {
                holder.recyclerviewapotekBisaBinding.imagePaket.setImageResource(R.drawable.full_service)
            }
        }

        holder.recyclerviewapotekBisaBinding.listDataCukur.setOnClickListener {
            Log.i("clicked", "oke bisa")
            listener?.onRecyclerViewItemClicked(it, apotekBisa[position])
        }
    }
}