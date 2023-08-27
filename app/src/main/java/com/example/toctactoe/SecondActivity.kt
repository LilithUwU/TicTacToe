package com.example.toctactoe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.toctactoe.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }
    private var player1Score = 0
    private var player2Score = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val player1 = intent.getStringExtra("player1").toString()
        val player2 = intent.getStringExtra("player2").toString()

        Toast.makeText(this, "$player1 & $player2", Toast.LENGTH_SHORT).show()
        val array = arrayOf(
            arrayOf(".", ".", "."),
            arrayOf(".", ".", "."),
            arrayOf(".", ".", ".")
        )
        var count = 0
        var index = ""
        var sign = ""
        binding.hint.text = "$player1 it's your turn"
        val buttonClickListener = View.OnClickListener { view ->
            if (checkResultForTicTacToe(array, player1, player2).second)
                disableAllButtons()
            /* sign = if (count % 2 == 0) "x"
             else "o"*/
            if (count % 2 == 0 && count != 8) {
                sign = "x"
                binding.hint.text = "$player2 it's your turn"
            } else if (count == 8) {
                binding.hint.text = "It's a draw"
            } else {
                sign = "o"
                binding.hint.text = "$player1 it's your turn"
            }
            when (view.id) {
                R.id.p00 -> {
                    array[0][0] = sign
                    binding.p00.text = sign
                    index = "00"
                    count++
                    view.isEnabled = false
                    checkResultForTicTacToe(array, player1, player2)
                }

                R.id.p01 -> {
                    array[0][1] = sign
                    binding.p01.text = sign
                    index = "01"
                    count++
                    view.isEnabled = false
                    checkResultForTicTacToe(array, player1, player2)
                }

                R.id.p02 -> {
                    array[0][2] = sign
                    binding.p02.text = sign
                    index = "02"
                    count++
                    view.isEnabled = false
                    checkResultForTicTacToe(array, player1, player2)

                }

                R.id.p10 -> {
                    array[1][0] = sign
                    binding.p10.text = sign
                    index = "10"
                    count++
                    view.isEnabled = false
                    checkResultForTicTacToe(array, player1, player2)


                }

                R.id.p11 -> {
                    array[1][1] = sign
                    binding.p11.text = sign
                    index = "11"
                    count++
                    view.isEnabled = false
                    checkResultForTicTacToe(array, player1, player2)

                }

                R.id.p12 -> {
                    array[1][2] = sign
                    binding.p12.text = sign
                    index = "12"
                    count++
                    view.isEnabled = false
                    checkResultForTicTacToe(array, player1, player2)


                }

                R.id.p20 -> {
                    array[2][0] = sign
                    binding.p20.text = sign
                    index = "20"
                    count++
                    view.isEnabled = false
                    checkResultForTicTacToe(array, player1, player2)
                }

                R.id.p21 -> {
                    array[2][1] = sign
                    binding.p21.text = sign
                    index = "21"
                    count++
                    view.isEnabled = false
                    checkResultForTicTacToe(array, player1, player2)

                }

                R.id.p22 -> {
                    array[2][2] = sign
                    binding.p22.text = sign
                    index = "22"
                    count++
                    view.isEnabled = false
                    checkResultForTicTacToe(array, player1, player2)

                }
            }
        }
        setListeners(buttonClickListener)
        binding.backBtn.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra("player1Score", player1Score)
//            intent.putExtra("player2Score", player2Score)
//            startActivity(intent)

            val resultIntent = Intent(this, MainActivity::class.java)
            resultIntent.putExtra("player1Score", player1Score)
            resultIntent.putExtra("player2Score", player2Score)
            resultIntent.putExtra("player1", player1)
            resultIntent.putExtra("player2", player2)
            setResult(100, resultIntent)
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    fun checkResultForTicTacToe(
        arr: Array<Array<String>>,
        player1: String,
        player2: String
    ): Pair<String, Boolean> {
        for (i in arr.indices) {
            if (arr[i][0] == arr[i][1] && arr[i][1] == arr[i][2]) {
                if (arr[i][0] == "x") {
                    binding.hint.text = "$player1 won"
                    player1Score++
                    disableAllButtons()
                    return Pair(player1, true)
                } else if (arr[i][0] == "o") {
                    binding.hint.text = "$player2 won"
                    player2Score++
                    disableAllButtons()
                    return Pair(player2, true)
                }
            }

            if (arr[0][i] == arr[1][i] && arr[1][i] == arr[2][i]) {
                if (arr[0][i] == "x") {
                    binding.hint.text = "$player1 won"
                    player1Score++
                    disableAllButtons()
                    return Pair(player1, true)
                } else if (arr[0][i] == "o") {
                    binding.hint.text = "$player2 won"
                    player2Score++
                    disableAllButtons()
                    return Pair(player2, true)
                }
            }
        }
        if (arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2]) {
            if (arr[0][0] == "x") {
                binding.hint.text = "$player1 won"
                disableAllButtons()
                player1Score++
                return Pair(player1, true)
            } else if (arr[0][0] == "o") {
                player2Score++
                binding.hint.text = "$player2 won"
                disableAllButtons()
                return Pair(player2, true)
            }
        }
        if (arr[0][2] == arr[1][1] && arr[1][1] == arr[2][0]) {
            if (arr[0][2] == "x") {
                player1Score++
                binding.hint.text = "$player1 won"
                disableAllButtons()
                return Pair(player1, true)
            } else if (arr[0][2] == "o") {
                player2Score++
                binding.hint.text = "$player2 won"
                disableAllButtons()
                return Pair(player2, true)
            }
        } else return Pair("Draw", false)
        return Pair("Error", false)
    }

    private fun disableAllButtons() {
        binding.p00.isEnabled = false
        binding.p01.isEnabled = false
        binding.p02.isEnabled = false
        binding.p10.isEnabled = false
        binding.p11.isEnabled = false
        binding.p12.isEnabled = false
        binding.p20.isEnabled = false
        binding.p21.isEnabled = false
        binding.p22.isEnabled = false
    }

    private fun setListeners(buttonClickListener: View.OnClickListener) {
        binding.p00.setOnClickListener(buttonClickListener)
        binding.p01.setOnClickListener(buttonClickListener)
        binding.p02.setOnClickListener(buttonClickListener)
        binding.p10.setOnClickListener(buttonClickListener)
        binding.p11.setOnClickListener(buttonClickListener)
        binding.p12.setOnClickListener(buttonClickListener)
        binding.p20.setOnClickListener(buttonClickListener)
        binding.p21.setOnClickListener(buttonClickListener)
        binding.p22.setOnClickListener(buttonClickListener)
    }


}




