package com.submission.propertyapp.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager

import com.submission.propertyapp.R
import com.submission.propertyapp.util.TypeFavorite
import kotlinx.android.synthetic.main.fragment_favorite_container.*

class FavoriteContainerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_container, container, false)
    }

    companion object{
        fun newInstance() : FavoriteContainerFragment =
            FavoriteContainerFragment()
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewPager(vp_container)
        tab_layout.setupWithViewPager(vp_container)
    }

    private fun setupViewPager(viewPager: ViewPager?) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(AddtoCartFragment.newInstance(), "Keranjang")
        adapter.addFragment(FavoriteFragment.newInstance(), "Favorite")
        viewPager?.adapter = adapter
    }
//
    internal inner class ViewPagerAdapter(supportFragmentManager: FragmentManager) : FragmentStatePagerAdapter(supportFragmentManager){
        private val mFragmentList : MutableList<Fragment> = ArrayList()
        private val mFragmentTitleList : MutableList<String> = ArrayList()


        override fun getItem(position: Int): Fragment {
            return mFragmentList.get(position)
        }

        override fun getCount(): Int {
            return mFragmentTitleList.size
        }
        fun addFragment(fragment: Fragment, title: String){
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList.get(position)
        }
    }

}
