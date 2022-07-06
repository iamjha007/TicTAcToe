package com.example.tictactoe

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),View.OnClickListener{
    lateinit var board:Array<Array<Button>>
    var player=true
    var turn_count=0
    var boardStatus=Array(3){IntArray(3)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board= arrayOf(
            arrayOf(button1,button2,button3) ,
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )
        for (i:Array<Button> in board){
            for(button:Button in i){
                button.setOnClickListener(this)
            }
        }
        intilizeBoard()

        resetbtn.setOnClickListener {
            turn_count=0;
            player=true;
            intilizeBoard()
        }
    }

    private fun intilizeBoard() {
        for(i in 0..2) {
            for (j in 0..2) {
                 boardStatus[i][j]=-1

            }
        }
        for(i:Array<Button> in board){
            for(button:Button in i){
                button.isEnabled=true;
                button.text=""
            }
        }

    }

    override fun onClick(view: View?) {

       when(view?.id){

           R.id.button1->{
              updateValue(0,0,player)
           }
           R.id.button2->{
               updateValue(0,1,player)
           }
           R.id.button3->{
               updateValue(0,2,player)
           }
           R.id.button4->{
               updateValue(1,0,player)
           }
           R.id.button5->{
               updateValue(1,1,player)
           }
           R.id.button6->{
               updateValue(1,2,player)
           }
           R.id.button7->{
              updateValue(2,0,player)
           }
           R.id.button8->{
                  updateValue(2,1,player)
           }
           R.id.button9->{
               updateValue(2,2,player)
           }


       }
        player=!player
        turn_count++
        if(player){
            updateDisplay("Player X Turn")
        }
        else{
            updateDisplay("Player 0 Turn")
        }
        if(turn_count==9)
            updateDisplay("GAME DRAW")
        checkWinner()

    }

    private fun checkWinner() {
     //horizontal Rows
        for(i in 0..2) {
            if (boardStatus[i][0]!=-1 && boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    updateDisplay("Player X is Winner")
                    break
                } else {
                    updateDisplay("Player 0 is Winner")
                    break;
                }
            }
        }
        //vertical clms
        for(i in 0..2) {
            if (boardStatus[0][i]!=-1 && boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    updateDisplay("Player X is Winner")
                    break
                } else {
                    updateDisplay("Player 0 is Winner")
                    break;
                }
            }
        }

        //principal diagonal
        if(boardStatus[0][0]!=-1 && boardStatus[0][0]==boardStatus[1][1] && boardStatus[0][0]==boardStatus[2][2])
            if (boardStatus[0][0] == 1) {
                updateDisplay("Player X is Winner")
            } else {
                updateDisplay("Player 0 is Winner")

            }
        //second diagonal
        if(boardStatus[0][2]!=-1 && boardStatus[0][2]==boardStatus[1][1] && boardStatus[0][2]==boardStatus[2][0])
            if (boardStatus[0][2] == 1) {
                updateDisplay("Player X is Winner")
            } else {
                updateDisplay("Player 0 is Winner")
            }

    }

    private fun updateDisplay(s: String) {
       displayTv.text=s
        if(s.contains("Winner"))
            disableButton()
    }
    private fun disableButton(){
        for(i:Array<Button> in board){
            for(button:Button in i){
                button.isEnabled=false;
            }
        }
    }

    private fun updateValue(r: Int,c: Int, player:Boolean) {
        val text:String=if(player) "X" else "0"
        val value:Int=if(player) 1 else 0
        board[r][c].apply {

            isEnabled=false
            setText(text)
        }
        boardStatus[r][c]=value


    }
}