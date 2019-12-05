package com.submission.propertyapp.ui.property

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.submission.propertyapp.R
import com.submission.propertyapp.model.PropertyData
import com.submission.propertyapp.util.Const
import com.submission.propertyapp.view.CommonView
import com.submission.propertyapp.view.CommonViewDetail
import com.tfb.fbtoast.FBToast
import kotlinx.android.synthetic.main.activity_detail_property.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailPropertyActivity : AppCompatActivity(), CommonView {

    private lateinit var idProperty : String
    private lateinit var namaProperty: TextView
    private lateinit var hargaProperty: TextView
    private lateinit var lokasiProperty: TextView
    private lateinit var stockProperty: TextView
    private lateinit var detailProperty: TextView
    private lateinit var imageProperty: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_property)

        val bundle = intent.extras
        idProperty = bundle.getString(Const.KEY_IDPROPERTY)
        namaProperty = findViewById(R.id.tv_nama_property)
        hargaProperty = findViewById(R.id.tv_harga_property)
        lokasiProperty = findViewById(R.id.tv_lokasi_property)
        stockProperty = findViewById(R.id.tv_stock_property)
        detailProperty = findViewById(R.id.tv_detail_property)
        imageProperty = findViewById(R.id.iv_detail_property)

        progressBar3.visibility = View.GONE

        if (intent.extras != null) {
            val bundle = intent.extras
            namaProperty.setText(bundle?.getString(Const.KEY_NAMAPROP))
            hargaProperty.setText(bundle?.getString(Const.KEY_HARGAPROP))
            lokasiProperty.setText(bundle?.getString(Const.KEY_LOKASIPROP))
            stockProperty.setText(bundle?.getString(Const.KEY_STOCKPROP))
            detailProperty.setText(bundle?.getString(Const.KEY_DETAILPROP))
            Glide.with(this)
                .load(bundle?.getString(Const.KEY_IMAGEPROP))
                .into(imageProperty)

        }

    }

    override fun success(anyResponse: Any) {

    }

    override fun showLoading() {
        progressBar3.visibility = View.VISIBLE
    }

    override fun error(error: Throwable) {

    }

    override fun hideLoading() {

    }



}
