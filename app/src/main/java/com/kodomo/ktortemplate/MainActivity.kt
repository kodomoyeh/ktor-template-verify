package com.kodomo.ktortemplate

import android.Manifest
//import android.R
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import com.kodomo.ktortemplate.ui.CreateGame0Fragment
import freemarker.cache.ClassTemplateLoader
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.freemarker.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.routing.*
import org.conscrypt.Conscrypt
import org.slf4j.LoggerFactory
import timber.log.Timber

import java.security.Security


class MainActivity : AppCompatActivity() {

    private val create0Fragment = CreateGame0Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("kodomo", "    override fun onCreate(savedInstanceState: Bundle?) {\n")
        super.onCreate(savedInstanceState)

        appContext = applicationContext
        appContext.getExternalFilesDir(null)?.let { Log.d("kodomo", it.absolutePath) }

        setContentView(R.layout.activity_main)

        getSupportActionBar()?.hide();

        InitFrag()
        Timber.plant(Timber.DebugTree())
        Security.insertProviderAt(Conscrypt.newProvider(), 1)

    }

    private fun addFragment(f: CreateGame0Fragment) {
        val transaction = getSupportFragmentManager().beginTransaction()
        transaction.add(R.id.fragment_container, f)
        transaction.commit()
    }

    private fun replaceFragment(f : CreateGame0Fragment){
        val transaction = getSupportFragmentManager().beginTransaction()
        transaction.add(R.id.fragment_container, f)
        //transaction.addToBackStack("base")
        transaction.commit()
    }






    private fun InitFrag() {
        Log.d("kodomo","InitGetExistGame")
        replaceFragment(create0Fragment)
       // val db = DBHelper(this)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CODE_REQUEST_STORAGE_PERMISSION
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    private fun hasStoragePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

        public companion object {
                const val CODE_REQUEST_STORAGE_PERMISSION = 1
                lateinit  var appContext: Context
            }


}

