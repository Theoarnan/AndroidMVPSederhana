package com.submission.propertyapp.ui.property

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import com.bumptech.glide.Glide
import com.submission.propertyapp.R
import com.submission.propertyapp.model.PropertyData
import com.submission.propertyapp.presenter.AllPresenter
import com.submission.propertyapp.view.CommonView
import com.tfb.fbtoast.FBToast
import kotlinx.android.synthetic.main.activity_add_property.*
import java.io.ByteArrayOutputStream

class EditPropertyActivity : AppCompatActivity(), CommonView {

    lateinit var property : PropertyData
    lateinit var presenter : AllPresenter
    private var imageBase64 : String = ""

    val SELECT_FILE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_property)

        property = intent.getParcelableExtra("property")

        populateData()
        settingTombolEdit()
    }

    private fun populateData(){
        et_nama_property.setText(property.namaProperty)
        et_harga_property.setText(property.hargaProperty)
        et_stock_property.setText(property.stockProperty)
        et_deskripsi_property.setText(property.detailProperty)
        et_lokasi_property.setText(property.lokasiProperty)
        Glide.with(this).load(property.imageUrl).into(iv_property)
    }

    //Validasi Data Inputan
    private fun validate(): Boolean {
        var result = true
        if (et_nama_property.text.toString() == "" ||
            et_lokasi_property.text.toString() == "" ||
            et_harga_property.text.toString() == "" ||
            et_harga_property.text.toString() == "0" ||
            et_stock_property.text.toString() == "" ||
            et_stock_property.text.toString() == "0" ||
            et_deskripsi_property.text.toString() == "" ||
            imageBase64 == ""
        ) {
            result = false
        }
        return result
    }

    //Untuk Add Data ketika di Save
    private fun settingTombolEdit() {
        btn_save_property.setOnClickListener {
            btn_save_property.isEnabled = false
            //Validasi
            var propertyData = PropertyData(
                property.idProperty,
                et_nama_property.text.toString(),
                et_harga_property.text.toString(),
                et_stock_property.text.toString(),
                et_deskripsi_property.text.toString(),
                et_lokasi_property.text.toString(),
                imageBase64,
                "",
                "",
                ""
            )
            if (validate()) {
                btn_save_property.isEnabled = false
                presenter = AllPresenter(this, this)
                presenter.updateProperty(propertyData)
                FBToast.successToast(this, "Data berhasil diupdate", FBToast.LENGTH_SHORT)
            } else {
                FBToast.errorToast(this, "Data ada yang Salah", FBToast.LENGTH_SHORT)
            }

        }
        //Click Kamera
        iv_property.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 12)
        }
        //Click Untuk Upload File Gambar
        btn_album.setOnClickListener{
            intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);
        }
    }
    //Hasil dari foto kamera
    //Cara test nya di Base64 to image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 12 && resultCode == Activity.RESULT_OK) {
            var bitmap = data?.extras?.get("data") as Bitmap
            val byteArrayOS = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOS)
            imageBase64 = Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT)
            Glide.with(this).load(bitmap).into(iv_property)
        } else if (requestCode == SELECT_FILE && data != null && data.getData() != null) {
            // mengambil gambar dari Gallery

            var bitmap =
                MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
            val byteArrayOS = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOS)
            imageBase64 = Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT)
            Glide.with(this).load(bitmap).into(iv_property)
        }
    }

    override fun showLoading() {

    }

    override fun error(error: Throwable) {
        btn_save_property.isEnabled = true
    }

    override fun success(anyResponse: Any) {
        finish()
    }

    override fun hideLoading() {

    }
}
