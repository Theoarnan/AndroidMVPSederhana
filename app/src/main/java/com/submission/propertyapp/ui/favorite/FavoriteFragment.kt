package com.submission.propertyapp.ui.favorite

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.submission.propertyapp.R
import com.submission.propertyapp.util.TypeFavorite
import org.jetbrains.anko.bundleOf

class FavoriteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_property, container, false)
    }

    companion object{
        fun newInstance() : FavoriteFragment = FavoriteFragment()
    }

}
