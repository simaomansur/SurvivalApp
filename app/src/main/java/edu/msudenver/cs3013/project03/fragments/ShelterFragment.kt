package edu.msudenver.cs3013.project03.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import edu.msudenver.cs3013.project03.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShelterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShelterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shelter, container, false)
        view.findViewById<Button>(R.id.debris_shelter_button)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_shelterFragment_to_debrisFragment, null)
        )
        view.findViewById<Button>(R.id.tarp_shelter_button)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_shelterFragment_to_TarpFragment, null)
        )
        view.findViewById<Button>(R.id.snow_cave_button)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_shelterFragment_to_SnowCaveFragment, null)
        )
        view.findViewById<Button>(R.id.lean_to_button)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_shelterFragment_to_LeanToFragment, null)
        )
        view.findViewById<Button>(R.id.underground_bunker_button)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_shelterFragment_to_UndergroundFragment, null)
        )
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShelterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShelterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}