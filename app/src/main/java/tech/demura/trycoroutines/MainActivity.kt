package tech.demura.trycoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tech.demura.trycoroutines.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnDownload.setOnClickListener {
            lifecycleScope.launch {
                loadData()
            }
        }
    }

    private suspend fun loadData() {
        binding.progresBar.isVisible = true
        binding.btnDownload.isEnabled = false
        val city = loadCity()
        binding.tvCity.text = city
        val temp = loadTemperature(city)
        binding.tvTemperature.text = temp.toString()
        binding.progresBar.isVisible = false
        binding.btnDownload.isEnabled = true
    }

    private suspend fun loadCity(): String {
        delay(5000)
        return ("Moscow")
    }

    private suspend fun loadTemperature(city: String): Int {
        Toast.makeText(
            this,
            "Loading temperature for city: $city",
            Toast.LENGTH_SHORT
        ).show()
        delay(5000)
        return 17
    }

}