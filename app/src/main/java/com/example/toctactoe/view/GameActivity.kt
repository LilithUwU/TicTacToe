package com.example.toctactoe.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.example.toctactoe.Constants
import com.example.toctactoe.Constants.PLAYER1_X
import com.example.toctactoe.Constants.PLAYER2_O
import com.example.toctactoe.R
import com.example.toctactoe.databinding.ActivitySecondBinding
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }
    private var player1Score = 0
    private var player2Score = 0
    private var clickedCellCount = 0
    private var currentlyClickedCellSign = ""
    private lateinit var cellSignsArray: Array<Array<String>>
    private lateinit var player1Name: String
    private lateinit var player2Name: String
    private lateinit var scoreCarrierIntent: Intent
    private lateinit var cellBtnsArr: Array<Array<MaterialButton>>
    private lateinit var greenBackground: Drawable

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        scoreCarrierIntent = Intent(this, MainActivity::class.java)
        player1Name = intent.getStringExtra(Constants.INTENT_PLAYER1_NAME).toString()
        player2Name = intent.getStringExtra(Constants.INTENT_PLAYER2_NAME).toString()
        greenBackground = AppCompatResources.getDrawable(this, R.drawable.shape_cell_win)!!
        cellSignsArray = arrayOf(
            arrayOf("", "", ""),
            arrayOf("", "", ""),
            arrayOf("", "", "")
        )//later will be set the and used in checking the game result
        cellBtnsArr = arrayOf(
            arrayOf(binding.p00, binding.p01, binding.p02),
            arrayOf(binding.p10, binding.p11, binding.p12),
            arrayOf(binding.p20, binding.p21, binding.p22)
        )
        val buttonClickListener = View.OnClickListener { view ->
            if (checkGameResult(cellSignsArray).second)
                setEnableState(false)
            setCellSignWhenClicked() //set appropriate sign
            handleEachBtnClickCase(view)//use the sign for checking the game result
        }
        setListenersForCells(buttonClickListener)
        binding.backBtn.setOnClickListener {
            backBtnLogic()
        }

        binding.btnRestart.setOnClickListener{
            val rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_rotate)
            binding.btnRestart.startAnimation(rotateAnimation) //clearAnimation()
            CoroutineScope(Dispatchers.Main).launch{
                delay(resources.getInteger(R.integer.anim_rotate_duration).toLong())
                clearCellText()
                clearSignsArray(cellSignsArray)
                clickedCellCount = 0
                setEnableState(true)
            }

        }

        Toast.makeText(this, "$player1Name & $player2Name", Toast.LENGTH_SHORT).show()
        binding.hint.text = getString(R.string.it_s_your_turn, player1Name)
    }

    private fun backBtnLogic() {
        //send scores back to main activity
        scoreCarrierIntent.putExtra(Constants.INTENT_PLAYER1_SCORE, player1Score)
        scoreCarrierIntent.putExtra(Constants.INTENT_PLAYER2_SCORE, player2Score)
        scoreCarrierIntent.putExtra(Constants.INTENT_PLAYER1_NAME, player1Name)
        scoreCarrierIntent.putExtra(Constants.INTENT_PLAYER2_NAME, player2Name)
        setResult(100, scoreCarrierIntent)
        finish()
    }

    private fun handleEachBtnClickCase(view: View) {
        //check result for each button click
        when (view.id) {
            R.id.p00 -> {
                handleCellClick(0, 0, currentlyClickedCellSign,findViewById(R.id.p00))
            }

            R.id.p01 -> {
                handleCellClick(0, 1, currentlyClickedCellSign,  findViewById(R.id.p01))
            }

            R.id.p02 -> {
                handleCellClick(0, 2, currentlyClickedCellSign,  findViewById(R.id.p02))
            }

            R.id.p10 -> {
                handleCellClick(1, 0, currentlyClickedCellSign,  findViewById(R.id.p10))
            }

            R.id.p11 -> {
                handleCellClick(1, 1, currentlyClickedCellSign,  findViewById(R.id.p11))
            }

            R.id.p12 -> {
                handleCellClick(1, 2, currentlyClickedCellSign,  findViewById(R.id.p12))
            }

            R.id.p20 -> {
                handleCellClick(2, 0, currentlyClickedCellSign, findViewById(R.id.p20))
            }

            R.id.p21 -> {
                handleCellClick(2, 1, currentlyClickedCellSign,  findViewById(R.id.p21))
            }

            R.id.p22 -> {
                handleCellClick(2, 2, currentlyClickedCellSign, findViewById(R.id.p22))
            }
        }
    }

    private fun setCellSignWhenClicked() {
        //set sign and call each player to tap on available cells
        if (clickedCellCount % 2 == 0 && clickedCellCount != 8) {
            currentlyClickedCellSign = PLAYER1_X
            binding.hint.text = getString(R.string.it_s_your_turn, player2Name)
        } else if (clickedCellCount == 8) {
            binding.hint.text = getString(R.string.it_s_a_draw)
        } else {
            currentlyClickedCellSign = PLAYER2_O
            binding.hint.text = getString(R.string.it_s_your_turn, player1Name)
        }
    }

    private fun handleCellClick(i: Int, j: Int, sign: String, view: View) {
        //initializes string array of signs that will be used for checking the game result
        cellSignsArray[i][j] = sign //sets array for checking result
        (view as TextView).text = sign //inits text of the button
        clickedCellCount++ //used for determining whether it's a draw or win or either players
        view.isEnabled = false //disables cell buttons
        checkGameResult(cellSignsArray) //checks the result
    }

    private fun handleWinningLogic(i: Int, j: Int, arr: Array<Array<String>>): Pair<String, Boolean> {
        val player = when (arr[i][j]) { //get the value either x or y to know which player won
            PLAYER1_X -> player1Name
            PLAYER2_O -> player2Name
            else -> return Pair("Error", false)
        }

        binding.hint.text = "$player ${getString(R.string.won)}"
        if (arr[i][j] == PLAYER1_X) player1Score++ else player2Score++

        setEnableState(false) // disable all buttons including those that are empty and end the game
        return Pair(player, true)
    }

    @SuppressLint("SetTextI18n")
    fun checkGameResult(
        arr: Array<Array<String>>
    ): Pair<String, Boolean> {
        for (i in arr.indices) {
            //check for rows
            if (arr[i][0] == arr[i][1] && arr[i][1] == arr[i][2]) {
                if (handleWinningLogic(i, 0, arr).second) {
                    for (k in arr.indices){
                        cellBtnsArr[i][k].background = greenBackground
                    }
                }
            }
            //check for columns
            if (arr[0][i] == arr[1][i] && arr[1][i] == arr[2][i]) {
                if (handleWinningLogic(0, i, arr).second) {
                    for (k in arr.indices){
                        cellBtnsArr[k][i].background = greenBackground
                    }
                }

            }
        }
        //check for diagonals
        if (arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2]) {
            if (handleWinningLogic(0, 0, arr).second) {
               for( i in arr.indices) {
                   cellBtnsArr[i][i].background = greenBackground
               }
            }
        }
        if (arr[0][2] == arr[1][1] && arr[1][1] == arr[2][0]) {
            if (handleWinningLogic(0, 2, arr).second) {
                for (i in arr.indices){
                    cellBtnsArr[i][arr.size - 1 - i].background = greenBackground
                }
            }
        } else return Pair("Draw", false)
        return Pair("Error", false)
    }

    private fun setEnableState(state: Boolean) {
        for (row in cellBtnsArr) {
            for (cell in row) {
                cell.isEnabled = state // Enable or disable each cell based on the state
            }
        }
    }

    private fun clearCellText() {
        for (row in cellBtnsArr) {
            for (cell in row) {
                cell.text = ""
                cell.background= AppCompatResources.getDrawable(this, R.drawable.shape_cell_default)
            }
        }
    }
    fun clearSignsArray(arr: Array<Array<String>>) {
        for (i in arr.indices) {
            for (j in arr[i].indices) {
                arr[i][j] = ""
            }
        }
    }


    private fun setListenersForCells(buttonClickListener: View.OnClickListener) {
        for (row in cellBtnsArr) {
            for (cell in row) {
                cell.setOnClickListener(buttonClickListener) // Set the click listener for each cell
            }
        }
    }
}