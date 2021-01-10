package org.d3if4055.apotekbisa.utils

import android.view.View
import org.d3if4055.apotekbisa.database.ApotekBisa

interface RecyclerViewClickListener {

 fun onRecyclerViewItemClicked(view: View, apotekBisa: ApotekBisa)

}