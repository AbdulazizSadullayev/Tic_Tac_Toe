package uz.coder.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import uz.coder.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var xScore = 0
    private var oScore = 0

    enum class Turn
    {
        NOUTH,
        CROSS
    }
    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS

    private lateinit var binding: ActivityMainBinding

    private var boardList = mutableListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
    }

    private fun initBoard() {
        boardList.add(binding.a1)
        boardList.add(binding.a2)
        boardList.add(binding.a3)
        boardList.add(binding.b1)
        boardList.add(binding.b2)
        boardList.add(binding.b3)
        boardList.add(binding.c1)
        boardList.add(binding.c2)
        boardList.add(binding.c3)
    }

    private fun fullBoard(): Boolean {
        for (button in boardList){
            if (button.text == "")
                return false
        }
        return true
    }

    fun boardTapped(view: View) {
        if (view !is Button)
            return
        addToBoard(view)

        if (chackForVictory(NOUGHT)){
            oScore++
            ruselt("O Yutdi!")
        }
        if (chackForVictory(CROSS)){
            xScore++
            ruselt("X Yutdi!")
        }

        if (fullBoard()){
            ruselt("Durrang")
        }


    }

    private fun chackForVictory(s: String): Boolean {

        if (match(binding.a1,s) && match(binding.a2,s) && match(binding.a3,s)){ return true }

        if (match(binding.b1,s) && match(binding.b2,s) && match(binding.b3,s)){ return true }

        if (match(binding.c1,s) && match(binding.c2,s) && match(binding.c3,s)){ return true }



        if (match(binding.a1,s) && match(binding.b1,s) && match(binding.c1,s)){return true }

        if (match(binding.a2,s) && match(binding.b2,s) && match(binding.c2,s)){ return true }

        if (match(binding.a3,s) && match(binding.b3,s) && match(binding.c3,s)){ return true }



        if (match(binding.a1,s) && match(binding.b2,s) && match(binding.c3,s)){return true }

        if (match(binding.a3,s) && match(binding.b2,s) && match(binding.c1,s)){ return true }



        return false
    }

    private fun match(button: Button, symbol:String): Boolean = button.text == symbol

    private fun ruselt(title: String) {
        val massage = "\nO: $oScore\n\nX: $xScore"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(massage)
            .setPositiveButton("Qaytadan boshlash")
            {_,_ ->
                resetBoard()
            }
            .setCancelable(false)
            .show()
    }

    private fun resetBoard() {
        for (button in boardList){
            button.text = ""
        }

        if (firstTurn == Turn.NOUTH)
            firstTurn = Turn.CROSS
        else if (firstTurn == Turn.CROSS)
            firstTurn = Turn.NOUTH

        currentTurn = firstTurn
        setTurnNabel()

    }

    private fun addToBoard(button: Button) {
        if (button.text != "")
            return

        if (currentTurn == Turn.NOUTH){

            button.text = NOUGHT
            currentTurn = Turn.CROSS
        }
        else if (currentTurn == Turn.CROSS){
            button.text = CROSS
            currentTurn = Turn.NOUTH
        }
        setTurnNabel()
    }

    private fun setTurnNabel() {
        var turnText = ""
        if (currentTurn == Turn.CROSS)
            turnText = "Navbat: $CROSS"
        else if (currentTurn == Turn.NOUTH)
            turnText = "Navbat: $NOUGHT"

        binding.turnTv.text = turnText
    }

    companion object{
        const val NOUGHT = "O"
        const val CROSS = "X"
    }
}