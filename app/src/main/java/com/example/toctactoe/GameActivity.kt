package com.example.toctactoe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.toctactoe.databinding.ActivitySecondBinding

class GameActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }
    private var player1Score = 0
    private var player2Score = 0
    private var cellCount = 0
    private var index = ""
    private var sign = ""
    private lateinit var cellSignsArray: Array<Array<String>>
    private lateinit var player1: String
    private lateinit var player2: String
    private lateinit var resultIntent: Intent
    private lateinit var cellViews: List<TextView>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        resultIntent = Intent(this, MainActivity::class.java)
        player1 = intent.getStringExtra(Constants.INTENT_PLAYER1_NAME).toString()
        player2 = intent.getStringExtra(Constants.INTENT_PLAYER2_NAME).toString()
        binding.hint.text = getString(R.string.it_s_your_turn, player1)

        cellSignsArray = arrayOf(
            arrayOf("", "", ""),
            arrayOf("", "", ""),
            arrayOf("", "", "")
        )//later will be set the and used in checking the game result
        cellViews = listOf(
            binding.p00, binding.p01, binding.p02,
            binding.p10, binding.p11, binding.p12,
            binding.p20, binding.p21, binding.p22
        )
        Toast.makeText(this, "$player1 & $player2", Toast.LENGTH_SHORT).show()
        val buttonClickListener = View.OnClickListener { view ->
            if (checkGameResult(cellSignsArray).second)
                enableBtns(false)
            handleHintAndSignText() //set appropriate sign
            handleEachBtnClickCase(view)//use the sign for checking the game result
        }
        setListenersForCells(buttonClickListener)
        binding.backBtn.setOnClickListener {
            backBtnLogic()
        }
    }

    private fun backBtnLogic() {
        resultIntent.putExtra(Constants.INTENT_PLAYER1_SCORE, player1Score)
        resultIntent.putExtra(Constants.INTENT_PLAYER2_SCORE, player2Score)
        resultIntent.putExtra(Constants.INTENT_PLAYER1_NAME, player1)
        resultIntent.putExtra(Constants.INTENT_PLAYER2_NAME, player2)
        setResult(100, resultIntent)
        finish()
    }

    private fun handleEachBtnClickCase(view: View) {
        when (view.id) {
            R.id.p00 -> {
                handleCellClick(0, 0, sign, "00", findViewById(R.id.p00))
            }

            R.id.p01 -> {
                handleCellClick(0, 1, sign, "01", findViewById(R.id.p01))
            }

            R.id.p02 -> {
                handleCellClick(0, 2, sign, "02", findViewById(R.id.p02))
            }

            R.id.p10 -> {
                handleCellClick(1, 0, sign, "10", findViewById(R.id.p10))
            }

            R.id.p11 -> {
                handleCellClick(1, 1, sign, "11", findViewById(R.id.p11))
            }

            R.id.p12 -> {
                handleCellClick(1, 2, sign, "12", findViewById(R.id.p12))
            }

            R.id.p20 -> {
                handleCellClick(2, 0, sign, "20", findViewById(R.id.p20))
            }

            R.id.p21 -> {
                handleCellClick(2, 1, sign, "21", findViewById(R.id.p21))
            }

            R.id.p22 -> {
                handleCellClick(2, 2, sign, "22", findViewById(R.id.p22))
            }
        }
    }

    private fun handleHintAndSignText() {
        if (cellCount % 2 == 0 && cellCount != 8) {
            sign = "x"
            binding.hint.text = getString(R.string.it_s_your_turn, player2)
        } else if (cellCount == 8) {
            binding.hint.text = "It's a draw"
        } else {
            sign = "o"
            binding.hint.text = getString(R.string.it_s_your_turn, player1)
        }
    }

    private fun handleCellClick(i: Int, j: Int, sign: String, s: String, view: View) {
        cellSignsArray[i][j] = sign
        (view as TextView).text = sign
        index = s
        cellCount++
        view.isEnabled = false
        checkGameResult(cellSignsArray)
    }

    private fun checkHelperLogic(i: Int, j: Int, arr: Array<Array<String>>): Pair<String, Boolean> {
        if (arr[i][j] == "x") {
            binding.hint.text = "$player1 ${getString(R.string.won)}"
            player1Score++
            enableBtns(false)//disable all buttons including those that are empty and end game
            return Pair(player1, true)
        } else if (arr[i][j] == "o") {
            binding.hint.text = "$player2 ${getString(R.string.won)}"
            player2Score++
            enableBtns(false)//disable all buttons including those that are empty and end game
            return Pair(player2, true)
        }
        return Pair("Error", false)
    }

    @SuppressLint("SetTextI18n")
    fun checkGameResult(
        arr: Array<Array<String>>
    ): Pair<String, Boolean> {
        for (i in arr.indices) {
            //check for rows
            if (arr[i][0] == arr[i][1] && arr[i][1] == arr[i][2]) {
                checkHelperLogic(i, 0, arr)
            }
            //check for columns
            if (arr[0][i] == arr[1][i] && arr[1][i] == arr[2][i]) {
                checkHelperLogic(0, i, arr)
            }
        }
        //check for diagonals
        if (arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2]) {
            checkHelperLogic(0, 0, arr)
        }
        if (arr[0][2] == arr[1][1] && arr[1][1] == arr[2][0]) {
            checkHelperLogic(0, 2, arr)
        } else return Pair("Draw", false)
        return Pair("Error", false)
    }

    private fun enableBtns(state: Boolean) {
        cellViews.forEach { it.isEnabled = state }
    }

    private fun setListenersForCells(buttonClickListener: View.OnClickListener) {
        cellViews.forEach { it.setOnClickListener(buttonClickListener) }
    }


}