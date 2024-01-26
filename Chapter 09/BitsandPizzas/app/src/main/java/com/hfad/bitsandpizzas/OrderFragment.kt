package com.hfad.bitsandpizzas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class OrderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order, container, false)
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val pizzaGroup = view.findViewById<RadioGroup>(R.id.pizza_group)
            val pizzaType = pizzaGroup.checkedRadioButtonId
            if (pizzaType == -1)
                Toast.makeText(activity, R.string.no_pizza_selected, Toast.LENGTH_LONG).show()
            else {
                var text = (when (pizzaType) {
                    R.id.radio_diavolo -> resources.getString(R.string.diavolo_pizza)
                    else -> resources.getString(R.string.funghi_pizza)
                })
                if (view.findViewById<Chip>(R.id.parmesan).isChecked)
                    text += ", ${resources.getString(R.string.extra_parmesan)}"
                if (view.findViewById<Chip>(R.id.chili_oil).isChecked)
                    text += ", ${resources.getString(R.string.extra_chili_oil)}"
                Snackbar.make(fab, text, Snackbar.LENGTH_LONG).show()
            }
        }
        return view
    }
}
