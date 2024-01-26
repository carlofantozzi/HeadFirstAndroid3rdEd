package com.hfad.bitsandpizzas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.hfad.bitsandpizzas.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val view = binding.root
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            val pizzaType = binding.pizzaGroup.checkedRadioButtonId
            if (pizzaType == -1)
                Toast.makeText(activity, R.string.no_pizza_selected, Toast.LENGTH_LONG).show()
            else {
                var text = (when (pizzaType) {
                    R.id.radio_diavolo -> resources.getString(R.string.diavolo_pizza)
                    else -> resources.getString(R.string.funghi_pizza)
                })
                if (binding.parmesan.isChecked) text += ", ${resources.getString(R.string.extra_parmesan)}"
                if (binding.chiliOil.isChecked) text += ", ${resources.getString(R.string.extra_chili_oil)}"
                Snackbar.make(binding.fab, text, Snackbar.LENGTH_LONG).show()
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
