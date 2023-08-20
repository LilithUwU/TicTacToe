package com.example.toctactoe

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.toctactoe.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val player1 = intent.getStringExtra("player1")
        val player2 = intent.getStringExtra("player2")
        Toast.makeText(this, "$player1 & $player2", Toast.LENGTH_SHORT).show()
        val array = arrayOf(
            arrayOf(".", ".", "."),
            arrayOf(".", ".", "."),
            arrayOf(".", ".", ".")
        )
//        ticTacToe(player1, player2, array)


//        binding.p00.setOnClickListener(position())
//        binding.p01.setOnClickListener {array[0][1]= binding.p00.text as String }
//        binding.p02.setOnClickListener {array[0][2]= binding.p00.text as String }
//        binding.p10.setOnClickListener {array[1][0]= binding.p00.text as String }
//        binding.p11.setOnClickListener {array[1][1]= binding.p00.text as String }
//        binding.p12.setOnClickListener {array[1][2]= binding.p00.text as String }
//        binding.p20.setOnClickListener {array[2][0]= binding.p00.text as String }
//        binding.p21.setOnClickListener {array[2][1]= binding.p00.text as String }
//        binding.p22.setOnClickListener {array[2][2]= binding.p00.text as String }


    }

    @SuppressLint("SetTextI18n")
    fun ticTacToe(player1: String?, player2: String?, array: Array<Array<String>>) {
        var count = 0
        var endResult = false
        var index = ""
        do {
            //Player 1 chooses position
            binding.hint.text = "$player1 choose your position"
            index = position('x', array)
            var row = index.substring(0, 1).toInt()
            var column = index.substring(1, 2).toInt()

            while (array[row][column] != ".") {
                binding.hint.text = "$player1 Given coordinate is already occupied"
                index = position('x', array)
                row = index.substring(0, 1).toInt()
                column = index.substring(1, 2).toInt()
            }
            array[row][column] = "x"
            count++
            if (checkResultForTicTacToe(array, player1.toString(), player2.toString()).second) {
                endResult = true
                break
            }

            //Player 2 chooses position
            binding.hint.text = "$player2 choose your position"
            index = position('o', array)
            row = index.substring(0, 1).toInt()
            column = index.substring(1, 2).toInt()
            while (array[row][column] != ".") {
                println("$player2 Given coordinate is already occupied")
                index = position('o', array)
                row = index.substring(0, 1).toInt()
                column = index.substring(1, 2).toInt()
            }
            array[row][column] = "o"
            count++
            if (checkResultForTicTacToe(array, player1.toString(), player2.toString()).second) {
                endResult = true
                break
            }
        } while (count < array.size * array[0].size && !endResult)
    }

    /*  This method works every time when we click on the position button it:
     1-initializes array with sign
     2-sets appropriate listener
     3-initializes index  */
    private fun position(sign: Char, array: Array<Array<String>>): String {
        var index = ""
        val buttonClickListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.p00 -> {
                    array[0][0] = sign.toString()
                    binding.p00.text = sign.toString()
                    index = "00"
                }

                R.id.p01 -> {
                    array[0][1] = sign.toString()
                    binding.p01.text = sign.toString()
                    index = "01"

                }

                R.id.p02 -> {
                    array[0][2] = sign.toString()
                    binding.p02.text = sign.toString()
                    index = "02"
                }

                R.id.p10 -> {
                    array[1][0] = sign.toString()
                    binding.p10.text = sign.toString()
                    index = "10"
                }

                R.id.p11 -> {
                    array[1][1] = sign.toString()
                    binding.p11.text = sign.toString()
                    index = "11"
                }

                R.id.p12 -> {
                    array[1][2] = sign.toString()
                    binding.p12.text = sign.toString()
                    index = "12"
                }

                R.id.p20 -> {
                    array[2][0] = sign.toString()
                    binding.p20.text = sign.toString()
                    index = "20"
                }

                R.id.p21 -> {
                    array[2][1] = sign.toString()
                    binding.p21.text = sign.toString()
                    index = "21"
                }

                R.id.p22 -> {
                    array[2][2] = sign.toString()
                    binding.p22.text = sign.toString()
                    index = "22"
                }
            }
        }
        binding.p01.setOnClickListener(buttonClickListener)
        binding.p02.setOnClickListener(buttonClickListener)
        binding.p10.setOnClickListener(buttonClickListener)
        binding.p11.setOnClickListener(buttonClickListener)
        binding.p12.setOnClickListener(buttonClickListener)
        binding.p20.setOnClickListener(buttonClickListener)
        binding.p21.setOnClickListener(buttonClickListener)
        binding.p22.setOnClickListener(buttonClickListener)
        return index
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
                    return Pair(player1, true)
                } else if (arr[i][0] == "o") {
                    binding.hint.text = "$player2 won"
                    return Pair(player2, true)
                }
            }

            if (arr[0][i] == arr[1][i] && arr[1][i] == arr[2][i]) {
                if (arr[0][i] == "x") {
                    binding.hint.text = "$player1 won"
                    return Pair(player1, true)
                } else if (arr[0][i] == "o") {
                    binding.hint.text = "$player2 won"
                    return Pair(player2, true)
                }
            }
        }

        if (arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2]) {
            if (arr[0][0] == "x") {
                binding.hint.text = "$player1 won"
                return Pair(player1, true)
            } else if (arr[0][0] == "o") {
                binding.hint.text = "$player2 won"
                return Pair(player2, true)
            }
        }

        if (arr[0][2] == arr[1][1] && arr[1][1] == arr[2][0]) {
            if (arr[0][2] == "x") {
                binding.hint.text = "$player1 won"
                return Pair(player1, true)
            } else if (arr[0][2] == "o") {
                binding.hint.text = "$player2 won"
                return Pair(player2, true)
            }
        }
        return Pair("Draw", false)
    }


}




