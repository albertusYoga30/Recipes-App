package com.example.modernrecipes.ui.fragments.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import coil.load
import com.example.modernrecipes.R
import com.example.modernrecipes.bindingAdapters.RecipesRowBinding
import com.example.modernrecipes.databinding.FragmentOverviewBinding
import com.example.modernrecipes.models.Result
import com.example.modernrecipes.util.Constants.Companion.RECIPE_RESULT_KEY
import org.jsoup.Jsoup

class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result = args!!.getParcelable<Result>(RECIPE_RESULT_KEY) as Result

        binding.mainImageView.load(myBundle.image)
        binding.titleTextView.text = myBundle.title
        binding.likesTextView.text = myBundle.aggregateLikes.toString()
        binding.timeTextView.text = myBundle.readyInMinutes.toString()
        RecipesRowBinding.parseHtml(binding.summaryTextView, myBundle.summary)

        updateColor(myBundle.vegetarian, binding.vegetarianTextView, binding.vegetarianImageView)
        updateColor(myBundle.vegan, binding.veganTextView, binding.veganImageView)
        updateColor(myBundle.glutenFree, binding.glutenFreeTextView, binding.glutenFreeImageView)
        updateColor(myBundle.dairyFree, binding.dairyFreeTextView, binding.dairyFreeImageView)
        updateColor(myBundle.veryHealthy, binding.healthyTextView, binding.healthyImageView)
        updateColor(myBundle.cheap, binding.cheapTextView, binding.cheapImageView)

        return binding.root
    }

    private fun updateColor(stateIsOn: Boolean, textView: TextView, imageView: ImageView) {
        if (stateIsOn) {
            imageView.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}