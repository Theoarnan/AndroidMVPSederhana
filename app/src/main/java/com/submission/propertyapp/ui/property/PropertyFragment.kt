package com.submission.propertyapp.ui.property

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arthurivanets.bottomsheets.BaseBottomSheet
import com.arthurivanets.bottomsheets.ktx.actionPickerConfig
import com.arthurivanets.bottomsheets.ktx.showActionPickerBottomSheet
import com.arthurivanets.bottomsheets.sheets.listeners.OnItemSelectedListener
import com.submission.propertyapp.R
import com.submission.propertyapp.model.PropertyData
import com.submission.propertyapp.model.PropertysResponse
import com.submission.propertyapp.presenter.AllPresenter
import com.submission.propertyapp.util.Const
import com.submission.propertyapp.util.bottomSheet.ActionProvider
import com.submission.propertyapp.util.bottomSheet.ConfirmProvider
import com.submission.propertyapp.view.CommonView
import com.tfb.fbtoast.FBToast
import kotlinx.android.synthetic.main.fragment_property.*


class PropertyFragment : Fragment(), CommonView, PropertyAdapter.Listener{

    lateinit var presenter : AllPresenter
    var propertys : MutableList<PropertyData> = mutableListOf()
    lateinit var adapter : PropertyAdapter

    //Untuk Edit dan Delete
    var bottomSheet: BaseBottomSheet? = null

    //Variabel delete
    private var isProsesDelete = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_property, container, false)
    }

    //Buat Intance baru
    companion object {
        fun newInstance(): PropertyFragment = PropertyFragment()
    }

    //Ketika Activity Dibuat
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Panggil fungsi SettingRV
        settingRV()
    }

    //Fungsi Sett RecyclerView
    private fun settingRV(){
        recycler_view_property.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(activity!!.applicationContext, 2)
        recycler_view_property.layoutManager = layoutManager
        recycler_view_property.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(8), true))
        recycler_view_property.itemAnimator = DefaultItemAnimator()
        adapter = PropertyAdapter(propertys, this)
        recycler_view_property.adapter = adapter
        presenter = AllPresenter(this, activity!!.applicationContext)
        presenter.getAllProperty()
    }
    override fun showLoading() {
        progressBar2.visibility = View.VISIBLE
    }

    override fun error(error: Throwable) {

    }

    override fun success(anyResponse: Any) {
        if(isProsesDelete){
            isProsesDelete = false
            presenter.getAllProperty()
            FBToast.successToast(activity?.applicationContext,"Sukses DiHapus", FBToast.LENGTH_SHORT);
        } else {
            val mPropertysResponse = anyResponse as PropertysResponse
            propertys.clear()
            propertys.addAll(mPropertysResponse.propertyData)
            adapter.notifyDataSetChanged()
        }
    }

    override fun hideLoading() {
        progressBar2.visibility = View.GONE
    }

    //Aksi ketika cardview di klik
    override fun onItemClick(property: PropertyData) {
        val bundle = Bundle()
        bundle.putString(Const.KEY_IDPROPERTY,property.idProperty)
        bundle.putString(Const.KEY_NAMAPROP,property.namaProperty)
        bundle.putString(Const.KEY_HARGAPROP,property.hargaProperty)
        bundle.putString(Const.KEY_LOKASIPROP,property.lokasiProperty)
        bundle.putString(Const.KEY_DETAILPROP,property.detailProperty)
        bundle.putString(Const.KEY_STOCKPROP,property.stockProperty)
        bundle.putString(Const.KEY_IMAGEPROP,property.imageUrl)
        val intent =  Intent(activity?.applicationContext, DetailPropertyActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    //Fungsi menampilkan popup Delete dan Edit
    private fun dismissBottomSheet(animate : Boolean = true){
        bottomSheet?.dismiss(animate)
    }

    override fun onLongItemClick(property: PropertyData) {
//        Toast.makeText(activity?.applicationContext, "Long Click", Toast.LENGTH_SHORT).show()
        dismissBottomSheet()

        bottomSheet = showActionPickerBottomSheet(options = ActionProvider.getAcionOptions(
            activity!!.applicationContext
        ), config = actionPickerConfig{
            title(property.namaProperty.toString())
        }, onItemSelectedListener = OnItemSelectedListener {
            when(it.id){
                ActionProvider.Id.DELETE-> {
                    settingConfirmDelete(property)
                    onResume()
                }
                ActionProvider.Id.EDIT-> {
                    val intent = Intent(activity?.applicationContext, EditPropertyActivity::class.java)
                    intent.putExtra("property",property)
                    startActivity(intent)
                }
            }
        })
    }

    //Tombol Delete
    private fun settingConfirmDelete(property: PropertyData){
        dismissBottomSheet()

        bottomSheet = showActionPickerBottomSheet(
            options = ConfirmProvider.getConfirmAcionOptions(
                activity!!.applicationContext
            ),
            config = actionPickerConfig{
                title("Anda Yakin Hapus Ini?")
            },
            onItemSelectedListener = OnItemSelectedListener {
                when(it.id){
                    ConfirmProvider.Id.CONFIRM-> {
                        isProsesDelete = true
                        presenter.deleteProperty(property.idProperty.toString())
                        onResume()
                    }
                }
            }
        )
    }

    //Refresh Halaman
    override fun onResume() {
        super.onResume()
        presenter.getAllProperty()
    }

    //Setting Grid Layout
    private fun dpToPx(dp : Int): Int{
        val r = resources
        return Math.round(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics
            ))
    }
    inner class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view) // item position
            val column = position % spanCount // item column

            if (includeEdge) {
                outRect.left =
                    spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                outRect.right =
                    (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing // item bottom
            } else {
                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right =
                    spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing // item top
                }
            }
        }
    }
}
