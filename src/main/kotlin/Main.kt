fun main() {

    val gameField = setGameField()
    play(gameField)

}

private fun setGameField(): MutableList<MutableList<Char>> {

    val symbols = mutableListOf(
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' ')
    )
    return symbols

}

private fun printGameField(listOfChars: MutableList<MutableList<Char>>) {

    println("---------")
    println("| ${listOfChars[0].joinToString(" ")} |")
    println("| ${listOfChars[1].joinToString(" ")} |")
    println("| ${listOfChars[2].joinToString(" ")} |")
    println("---------")

}

private fun xWin(listOfChars: MutableList<MutableList<Char>>) =
    ((listOfChars[0][0] == 'X' && listOfChars[0][1] == 'X' && listOfChars[0][2] == 'X') ||
            (listOfChars[1][0] == 'X' && listOfChars[1][1] == 'X' && listOfChars[1][2] == 'X') ||
            (listOfChars[2][0] == 'X' && listOfChars[2][1] == 'X' && listOfChars[2][2] == 'X') ||
            (listOfChars[0][0] == 'X' && listOfChars[1][0] == 'X' && listOfChars[2][0] == 'X') ||
            (listOfChars[0][1] == 'X' && listOfChars[1][1] == 'X' && listOfChars[2][1] == 'X') ||
            (listOfChars[0][2] == 'X' && listOfChars[1][2] == 'X' && listOfChars[2][2] == 'X') ||
            (listOfChars[0][0] == 'X' && listOfChars[1][1] == 'X' && listOfChars[2][2] == 'X') ||
            (listOfChars[2][0] == 'X' && listOfChars[1][1] == 'X' && listOfChars[0][2] == 'X'))

private fun oWin(listOfChars: MutableList<MutableList<Char>>) =
    ((listOfChars[0][0] == 'O' && listOfChars[0][1] == 'O' && listOfChars[0][2] == 'O') ||
            (listOfChars[1][0] == 'O' && listOfChars[1][1] == 'O' && listOfChars[1][2] == 'O') ||
            (listOfChars[2][0] == 'O' && listOfChars[2][1] == 'O' && listOfChars[2][2] == 'O') ||
            (listOfChars[0][0] == 'O' && listOfChars[1][0] == 'O' && listOfChars[2][0] == 'O') ||
            (listOfChars[0][1] == 'O' && listOfChars[1][1] == 'O' && listOfChars[2][1] == 'O') ||
            (listOfChars[0][2] == 'O' && listOfChars[1][2] == 'O' && listOfChars[2][2] == 'O') ||
            (listOfChars[0][0] == 'O' && listOfChars[1][1] == 'O' && listOfChars[2][2] == 'O') ||
            (listOfChars[2][0] == 'O' && listOfChars[1][1] == 'O' && listOfChars[0][2] == 'O'))


fun play(field: MutableList<MutableList<Char>>) {

    printGameField(field)

    var result = false
    var x = true

    while (!result) {

        try {
            val (coord1, coord2) = readln().filter { it != ' ' }.map { it.toString() }

            if (coord1.toInt() in 1 until 4 &&
                coord2.toInt() in 1 until 4 &&
                field[coord1.toInt() - 1][coord2.toInt() - 1] == ' '
            ) {
               when(x) {
                   true -> {
                       field[coord1.toInt() - 1][coord2.toInt() - 1] = 'X'
                       printGameField(field)
                       x = false
                   }
                   false -> {
                       field[coord1.toInt() - 1][coord2.toInt() - 1] = 'O'
                       printGameField(field)
                       x = true
                   }

               }

                if (xWin(field)) {
                    println("X wins")
                    result = true
                } else if (oWin(field)) {
                    println("X wins")
                    result = true
                } else if (!field.any { cell -> cell.any { it == ' ' } }) {
                    println("Draw")
                    result = true
                }

            } else if (coord1.toInt() !in 1 until 4 || coord2.toInt() !in 1 until 4) {
                println("Coordinates should be from 1 to 3!")
            } else {
                println("This cell is occupied! Choose another one!")
            }
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
        }

    }
}

