package com.submission.propertyapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import android.content.Intent
import android.graphics.Color
import androidx.cardview.widget.CardView
import android.widget.Toast
import com.submission.propertyapp.R


class DashboardFragment : Fragment() {

//    var mainGrid: GridLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        mainGrid = .findViewById(R.id.mainGrid)
//
//        //Set Event
//        setSingleEvent(mainGrid);
//        //setToggleEvent(mainGrid);
//    }

    //Buat Intance baru
    companion object {
        fun newInstance(): DashboardFragment = DashboardFragment()
    }

//    private fun setToggleEvent(mainGrid: GridLayout) {
//        //Loop all child item of Main Grid
//        for (i in 0 until mainGrid.childCount) {
//            //You can see , all child item is CardView , so we just cast object to CardView
//            val cardView = mainGrid.getChildAt(i) as CardView
//            cardView.setOnClickListener {
//                if (cardView.cardBackgroundColor.defaultColor == -1) {
//                    //Change background color
//                    cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"))
//                    Toast.makeText(this "State : True", Toast.LENGTH_SHORT).show()
//
//                } else {
//                    //Change background color
//                    cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
//                    Toast.makeText(this, "State : False", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//
//    private fun setSingleEvent(mainGrid: GridLayout) {
//        //Loop all child item of Main Grid
//        for (i in 0 until mainGrid.childCount) {
//            //You can see , all child item is CardView , so we just cast object to CardView
//            val cardView = mainGrid.getChildAt(i) as CardView
//            cardView.setOnClickListener {
//                val intent = Intent(this@MainActivity, ActivityOne::class.java)
//                intent.putExtra("info", "This is activity from card item index  $i")
//                startActivity(intent)
//            }
//        }
//    }


}
