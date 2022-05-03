package com.kodomo.ktortemplate.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.kodomo.ktortemplate.HttpServer

import com.kodomo.ktortemplate.R




class CreateGame0Fragment() : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.create_game_0_layout, container, false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        val button = view.findViewById<Button>(R.id.buttonto1) as Button
        button.setOnClickListener(clickListener)

    }

    val clickListener = View.OnClickListener { view ->
        //Log.d("kodomo","clickListener@fragment 0 "+view.getId())

        if(view.getId() == R.id.buttonto1) {

            requireContext().startService(Intent(requireContext(), HttpServer::class.java))
        }


    }


}