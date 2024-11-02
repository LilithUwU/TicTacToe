package com.example.toctactoe

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.example.toctactoe.Constants.PLAYER1_X
import com.example.toctactoe.Constants.PLAYER2_O
import com.example.toctactoe.databinding.ActivitySecondBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameUtils(
    val context: Context,
    val binding: ActivitySecondBinding,
    val player1Name: String,
    val player2Name: String
) : GameLogic { //context, binding (for btn control),
    val player1ScoreGetter: Int
        get() = player1Score

    val player2ScoreGetter: Int
        get() = player2Score
    private var player1Score = 0
    private var player2Score = 0
    private var clickedCellCount = 0
    private var currentlyClickedCellSign = ""

    val cellSignsArray = arrayOf(
        arrayOf("", "", ""),
        arrayOf("", "", ""),
        arrayOf("", "", "")
    )//later will be set the and used in checking the game result
    val cellBtnsArr = arrayOf(
        arrayOf(binding.p00, binding.p01, binding.p02),
        arrayOf(binding.p10, binding.p11, binding.p12),
        arrayOf(binding.p20, binding.p21, binding.p22)
    )

    fun btnClickListener() = View.OnClickListener { view ->
        if (checkGameResult().second)
            setEnableState(false)
        setCellSignWhenClicked() //set appropriate sign
        handleEachBtnClickCase(view)//use the sign for checking the game result
    }

    val greenBackground = AppCompatResources.getDrawable(context, R.drawable.shape_cell_win)!!

    override fun restartGame() {
        val rotateAnimation = AnimationUtils.loadAnimation(context, R.anim.animation_rotate)
        binding.btnRestart.startAnimation(rotateAnimation) //clearAnimation()
        CoroutineScope(Dispatchers.Main).launch {
            delay(context.resources.getInteger(R.integer.anim_rotate_duration).toLong())
            clearCellText()
            clearSignsArray(cellSignsArray)
            clickedCellCount = 0
            setEnableState(true)
        }
    }

    private fun setEnableState(state: Boolean) {
        for (row in cellBtnsArr) {
            for (cell in row) {
                cell.isEnabled = state // Enable or disable each cell based on the state
            }
        }
    }

    fun clearCellText() {
        for (row in cellBtnsArr) {
            for (cell in row) {
                cell.text = ""
                cell.background =
                    AppCompatResources.getDrawable(context, R.drawable.shape_cell_default)
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

    private fun handleWinningLogic(
        i: Int,
        j: Int,
        arr: Array<Array<String>>
    ): Pair<String, Boolean> {
        val player = when (arr[i][j]) { //get the value either x or y to know which player won
            PLAYER1_X -> player1Name
            PLAYER2_O -> player2Name
            else -> return Pair("Error", false)
        }
        if (arr[i][j] == PLAYER1_X) player1Score++ else player2Score++
        saveGameScore()

        binding.hint.text = StringBuilder().apply {
            append(player)
            append(" ")
            append(context.getString(R.string.won))
        }.toString()
        setEnableState(false) // disable all buttons including those that are empty and end the game
        return Pair(player, true)
    }

    private fun handleEachBtnClickCase(view: View) {
        //check result for each button click
        when (view.id) {
            R.id.p00 -> {
                handleCellClick(0, 0, currentlyClickedCellSign, binding.p00)
            }

            R.id.p01 -> {
                handleCellClick(0, 1, currentlyClickedCellSign, binding.p01)
            }

            R.id.p02 -> {
                handleCellClick(0, 2, currentlyClickedCellSign, binding.p02)
            }

            R.id.p10 -> {
                handleCellClick(1, 0, currentlyClickedCellSign, binding.p10)
            }

            R.id.p11 -> {
                handleCellClick(1, 1, currentlyClickedCellSign, binding.p11)
            }

            R.id.p12 -> {
                handleCellClick(1, 2, currentlyClickedCellSign, binding.p12)
            }

            R.id.p20 -> {
                handleCellClick(2, 0, currentlyClickedCellSign, binding.p20)
            }

            R.id.p21 -> {
                handleCellClick(2, 1, currentlyClickedCellSign, binding.p21)
            }

            R.id.p22 -> {
                handleCellClick(2, 2, currentlyClickedCellSign, binding.p22)
            }
        }
    }

    fun handleCellClick(i: Int, j: Int, sign: String, view: View) {
        //initializes string array of signs that will be used for checking the game result
        cellSignsArray[i][j] = sign //sets array for checking result
        (view as TextView).text = sign //inits text of the button
        clickedCellCount++ //used for determining whether it's a draw or win or either players
        view.isEnabled = false //disables cell buttons
        checkGameResult() //checks the result
    }

    @SuppressLint("SetTextI18n")
    override fun checkGameResult(): Pair<String, Boolean> {
        for (i in cellSignsArray.indices) {
            //check for rows
            if (cellSignsArray[i][0] == cellSignsArray[i][1] && cellSignsArray[i][1] == cellSignsArray[i][2]) {
                if (handleWinningLogic(i, 0, cellSignsArray).second) {
                    for (k in cellSignsArray.indices) {
                        cellBtnsArr[i][k].background = greenBackground
                    }
                }
            }
            //check for columns
            if (cellSignsArray[0][i] == cellSignsArray[1][i] && cellSignsArray[1][i] == cellSignsArray[2][i]) {
                if (handleWinningLogic(0, i, cellSignsArray).second) {
                    for (k in cellSignsArray.indices) {
                        cellBtnsArr[k][i].background = greenBackground
                    }
                }

            }
        }
        //check for diagonals
        if (cellSignsArray[0][0] == cellSignsArray[1][1] && cellSignsArray[1][1] == cellSignsArray[2][2]) {
            if (handleWinningLogic(0, 0, cellSignsArray).second) {
                for (i in cellSignsArray.indices) {
                    cellBtnsArr[i][i].background = greenBackground
                }
            }
        }
        if (cellSignsArray[0][2] == cellSignsArray[1][1] && cellSignsArray[1][1] == cellSignsArray[2][0]) {
            if (handleWinningLogic(0, 2, cellSignsArray).second) {
                for (i in cellSignsArray.indices) {
                    cellBtnsArr[i][cellSignsArray.size - 1 - i].background = greenBackground
                }
            }
        } else return Pair("Draw", false)
        return Pair("Error", false)
    }

    override fun saveGameScore() {
//   TODO("Not yet implemented")
    }


    fun setListenersForCells(buttonClickListener: View.OnClickListener) {
        for (row in cellBtnsArr) {
            for (cell in row) {
                cell.setOnClickListener(buttonClickListener) // Set the click listener for each cell
            }
        }
    }

    fun setCellSignWhenClicked() {
        //set sign and call each player to tap on available cells
        if (clickedCellCount % 2 == 0 && clickedCellCount != 8) {
            currentlyClickedCellSign = PLAYER1_X
            binding.hint.text = context.getString(R.string.it_s_your_turn, player2Name)
        } else if (clickedCellCount == 8) {
            binding.hint.text = context.getString(R.string.it_s_a_draw)
        } else {
            currentlyClickedCellSign = PLAYER2_O
            binding.hint.text = context.getString(R.string.it_s_your_turn, player1Name)
        }
    }

}