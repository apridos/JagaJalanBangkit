package com.example.jagajalanbangkit.home.screen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.domain.model.Laporan
import com.example.jagajalanbangkit.MyApplication
import com.example.jagajalanbangkit.R
import com.example.jagajalanbangkit.databinding.ActivityHomeBinding
import com.example.jagajalanbangkit.viewmodels.UserViewModel
import com.example.jagajalanbangkit.viewmodels.ViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var factory: ViewModelFactory

    private val userViewModel: UserViewModel by viewModels{
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        MyApplication.appComponent.inject(this)

        GlobalScope.async {

            val login = userViewModel.login()


            login?.let {
                val sharedPref = getPreferences(Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString(getString(R.string.key_email), it.email)
                    putString(getString(R.string.key_name), it.displayName)
                    putString(getString(R.string.key_token), it.token)
                    putString(getString(R.string.key_refresh_token), it.refreshToken)
                    apply()

                }

                val laporan = userViewModel.createLaporan("Bearer ${it.token}", Laporan(
                    alamat = "Parapat",
                    kondisi_kerusakan = "Parah cok",
                    deskripsi = "ya gitulah",
                    foto = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjMwMjUxYWIxYTJmYzFkMzllNDMwMWNhYjc1OTZkNDQ5ZDgwNDI1ZjYiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiQXByaWRvIiwicm9sZSI6InVzZXIiLCJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vdGVuc2lsZS1zaGlwLTMxMjQxNSIsImF1ZCI6InRlbnNpbGUtc2hpcC0zMTI0MTUiLCJhdXRoX3RpbWUiOjE2MjI1Mzc1NTQsInVzZXJfaWQiOiJTa0c3dmJUSFAzZWJ3S3hPUm1NMmNoM2NLZmEyIiwic3ViIjoiU2tHN3ZiVEhQM2Vid0t4T1JtTTJjaDNjS2ZhMiIsImlhdCI6MTYyMjUzNzU1NCwiZXhwIjoxNjIyNTQxMTU0LCJlbWFpbCI6ImFwcmlkb0BnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZW1haWwiOlsiYXByaWRvQGdtYWlsLmNvbSJdfSwic2lnbl9pbl9wcm92aWRlciI6InBhc3N3b3JkIn19.LOHk5rZDo4OJMW5EqMYmw1NYw0KpDjyU-Nu1gVeLsTslkCIakK8VcpHFpUoH2OZOK-fvU66sEapZsBOC1T4cW2kolAe61UZ2zyIOxw2ILj7xWmpllDN35SZLAbV6hcxkB9LuS1_gyX0SpsXHDCoyTedbzoqmA4OkC17nbWe0pVmABZInF1_Xz4OiV4GAmfLkszj-a_7-ul5M_mrMJT8Crbu-g1h2Gc-ghgvRFMp3MQ0mkyDyUyqycJ95AdH7DIepWRPBcNc9R2-_NjynmoRWDVyDT6PdHYdazeyc0GHaLI8qlF2bixSaS7353M7vz5aBuWaUlXO_IGQHk0XOXmiZUw",
                    longitude = 101.4309899,
                    latitude = 101.4309899
                ))
                Log.d("laporan", laporan.toString())
            }

        }

        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}