package br.edu.ifsp.scl.sc3045838.trucoscoreboard

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.sc3045838.trucoscoreboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var scoreA = 0
    private var scoreB = 0
    private var maxScore = 12

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIncrement1A.setOnClickListener { addPoints("A",1) }
        binding.btnIncrement3A.setOnClickListener { addPoints("A",3) }

        binding.btnIncrement1B.setOnClickListener { addPoints("B",1) }
        binding.btnIncrement3B.setOnClickListener { addPoints("B",3) }

        binding.btnReset.setOnClickListener { resetGame() }
    }

    fun resetGame() {
        scoreA = 0
        scoreB = 0
        binding.textScoreA.text = scoreA.toString()
        binding.textScoreB.text = scoreB.toString()
        updateButtonIsVisibility(false, false)
    }

    fun addPoints(team: String, point: Int) {
        if (scoreA >= maxScore || scoreB >= maxScore) return

        if (team == "A") {
            scoreA += point
//            if (scoreA > maxScore) scoreA = maxScore
        }

        if (team == "B") {
           scoreB += point
//           if (scoreB > maxScore) scoreB = maxScore
        }

        binding.textScoreA.text = scoreA.toString()
        binding.textScoreB.text = scoreB.toString()
        checkGameState()
    }

    private fun checkGameState() {
        when {
            scoreA >= maxScore -> {
                binding.tvStatus.text = "TEAM A venceu a partida!"
                resetGame()
            }

            scoreB >= maxScore -> {
                binding.tvStatus.text = "TEAM B venceu a partida!"
                resetGame()
            }

            scoreA == 11 && scoreB == 11 -> {
                binding.tvStatus.text = "Mão de Onze"
                updateButtonIsVisibility(true, true)
            }

            scoreA == 11 -> {
                binding.tvStatus.text = "Team A entrou na Mão de 11!"
                updateButtonIsVisibility(true, true)
            }

            scoreB == 11 -> {
                binding.tvStatus.text = "Team B entrou na Mão de 11!"
                updateButtonIsVisibility(true, true)
            }
            else -> {
                binding.tvStatus.text = ""
                updateButtonIsVisibility(false, false)
            }
        }
    }

    fun updateButtonIsVisibility(hideA: Boolean, hideB: Boolean) {
        binding.btnIncrement3A.visibility = if (hideA) View.GONE else View.VISIBLE
        binding.btnIncrement3B.visibility = if (hideA) View.GONE else View.VISIBLE
    }
    }