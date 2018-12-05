package fr.epita.hellogames

public fun imageFromid(id: String): Int {
    if (id == "SlidingPuzzle")
        return R.drawable.slidingpuzzle
    if (id == "Sudoku")
        return R.drawable.sudoku
    if (id == "TicTacToe")
        return R.drawable.tictactoe
    if (id == "GameOfLife")
        return R.drawable.gameoflife
    if (id == "Simon")
        return R.drawable.simon
    if (id == "Battleship")
        return R.drawable.battleship
    if (id == "Hangman")
        return R.drawable.hangman
    if (id == "Minesweeper")
        return R.drawable.minsweeper
    if (id == "Memory")
        return R.drawable.memory
    if (id == "Mastermind")
        return R.drawable.mastermind
    return 0
}