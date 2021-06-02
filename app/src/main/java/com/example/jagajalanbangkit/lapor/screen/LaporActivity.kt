package com.example.jagajalanbangkit.lapor.screen

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.location.LocationListener
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.model.Laporan
import com.example.jagajalanbangkit.MyApplication
import com.example.jagajalanbangkit.R
import com.example.jagajalanbangkit.databinding.ActivityLaporBinding
import com.example.jagajalanbangkit.home.screen.HomeActivity
import com.example.jagajalanbangkit.viewmodels.LaporanViewModel
import com.example.jagajalanbangkit.viewmodels.UserViewModel
import com.example.jagajalanbangkit.viewmodels.ViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.util.*
import javax.inject.Inject

class LaporActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLaporBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val laporViewModel: LaporanViewModel by viewModels {
        factory
    }

    private val userViewModel : UserViewModel by viewModels{
        factory
    }

    var image_uri: Uri? = null

    private val PERMISSION_CODE = 1000;
    private val IMAGE_CAPTURE_CODE = 1001

    private val laporan = Laporan()
    private lateinit var token : String
    private lateinit var refreshToken : String
    private var latitude : Double = 0.0
    private var longitude : Double = 0.0
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        token = sharedPreferences.getString(getString(R.string.key_token), "null")!!
        refreshToken = sharedPreferences.getString(getString(R.string.key_refresh_token), "null")!!
        latitude = sharedPreferences.getString(getString(R.string.key_latitude), "0.0")!!.toDouble()
        latitude = sharedPreferences.getString(getString(R.string.key_latitude), "0.0")!!.toDouble()
        binding = ActivityLaporBinding.inflate(layoutInflater)

        binding.progressBar.visibility = View.INVISIBLE


        MyApplication.appComponent.inject(this)

        binding.apply {
            btnBack.setOnClickListener {
                this@LaporActivity.finish()
            }

            btnCamera.setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED ||
                        checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED){ val permission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        requestPermissions(permission, PERMISSION_CODE)
                    }
                    else{
                        openCamera()
                    }
                }
                else{
                    openCamera()
                }
            }

            etDeskripsi.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    if(s?.length!! < 30){
                        etDeskripsi.setError("Deskripsi kerusakan minimal 30 karakter.")
                    }
                }
            })

            etKondisiKerusakan.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    if(s?.length!! < 10){
                        etKondisiKerusakan.setError("Kondisi kerusakan minimal 10 karakter.")
                    }
                }

            })
            etAlamat.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    if(s?.length!! < 10){
                        etAlamat.setError("Alamat minimal 10 karakter.")
                    }
                }

            })

            btnLapor.setOnClickListener{
                if(laporan.foto == null){
                    Toast.makeText(this@LaporActivity, "Silakan foto kerusakan jalan", Toast.LENGTH_SHORT).show()
                }else if(etAlamat.length() < 10){
                    Toast.makeText(this@LaporActivity, "Lengkapi alamat", Toast.LENGTH_SHORT).show()
                }else if(etKondisiKerusakan.length() < 10){
                    Toast.makeText(this@LaporActivity, "Lengkapi kondisi kerusakan", Toast.LENGTH_SHORT).show()
                }else if(etDeskripsi.length() < 30){
                    Toast.makeText(this@LaporActivity, "Lengkapi deskripsi kerusakan", Toast.LENGTH_SHORT).show()
                }else{
                    createLaporan()
                }
            }
        }
        setContentView(binding.root)
    }

    private fun createLaporan(){
        binding.progressBar.visibility = View.VISIBLE
        binding.btnLapor.isEnabled = false
        binding.btnLapor.isClickable = false
        laporan.latitude = latitude
        laporan.longitude = longitude
        laporan.alamat = binding.etAlamat.text.toString()
        laporan.deskripsi = binding.etDeskripsi.text.toString()
        laporan.kondisi_kerusakan = binding.etKondisiKerusakan.text.toString()
        GlobalScope.launch {
            Log.d("initoken", token!!.toString())
            val createLaporanResult = laporViewModel.createLaporan(token.toString(), laporan)
            if(createLaporanResult != 201){
                runOnUiThread(Runnable {
                    toastGagal()
                })
                userViewModel.reAuth(refreshToken!!)
            }else{
                runOnUiThread(Runnable {
                    toastBerhasil()
                })
                delay(2000)
                startActivity(Intent(this@LaporActivity, HomeActivity::class.java))
            }
            binding.progressBar.visibility = View.INVISIBLE
            binding.btnLapor.isClickable = true
            binding.btnLapor.isEnabled = true
        }
    }

    private fun toastGagal(){
        Toast.makeText(this@LaporActivity, "Gagal membuat laporan", Toast.LENGTH_SHORT).show()
    }

    private fun toastBerhasil(){
        Toast.makeText(this@LaporActivity, "Laporan berhasil dibuat", Toast.LENGTH_SHORT).show()
    }

    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    openCamera()
                }
                else{
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, image_uri)
            val byteArray = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArray)
            laporan.foto = "data:image/jpeg;base64," + Base64.encodeToString(byteArray.toByteArray(), Base64.DEFAULT)
        }
    }


    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            val sharedPref = getPreferences(Context.MODE_PRIVATE)
            GlobalScope.async {
                latitude = location.latitude
                longitude = location.longitude
            }
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }



}