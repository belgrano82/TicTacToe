fun main() {

    val gameField = setGameField()
    play(gameField)

}

private fun setGameField(): MutableList<MutableList<Char>> =
    mutableListOf(
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' ')
    )

private fun printGameField(listOfChars: List<List<Char>>) {

    println("---------")
    println("| ${listOfChars[0].joinToString(" ")} |")
    println("| ${listOfChars[1].joinToString(" ")} |")
    println("| ${listOfChars[2].joinToString(" ")} |")
    println("---------")

}

private fun xWin(listOfChars: List<List<Char>>) =
            ((listOfChars[0][0] == 'X' && listOfChars[0][1] == 'X' && listOfChars[0][2] == 'X') ||
            (listOfChars[1][0] == 'X' && listOfChars[1][1] == 'X' && listOfChars[1][2] == 'X') ||
            (listOfChars[2][0] == 'X' && listOfChars[2][1] == 'X' && listOfChars[2][2] == 'X') ||
            (listOfChars[0][0] == 'X' && listOfChars[1][0] == 'X' && listOfChars[2][0] == 'X') ||
            (listOfChars[0][1] == 'X' && listOfChars[1][1] == 'X' && listOfChars[2][1] == 'X') ||
            (listOfChars[0][2] == 'X' && listOfChars[1][2] == 'X' && listOfChars[2][2] == 'X') ||
            (listOfChars[0][0] == 'X' && listOfChars[1][1] == 'X' && listOfChars[2][2] == 'X') ||
            (listOfChars[2][0] == 'X' && listOfChars[1][1] == 'X' && listOfChars[0][2] == 'X'))

private fun oWin(listOfChars: List<List<Char>>) =
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
    var isGameOver = false
    var x = true

    while (!isGameOver) {

        try {
            printTurn(x)

            // Лучше здесь не применять деструктуризацию сразу, иначе программу легко сломать, если ввести 1 число.
            // Сначала проверяем валидность ввода
            val coords = readln().filter { it != ' ' }.map { it.toString() }

            val (coord1, coord2) = if (coords.size == 2) {
                coords
            }
            else {
                println("You should enter 2 digits (x, y)")
                continue
            }

            when {
                coord1.toInt() in 1 until 4 &&
                coord2.toInt() in 1 until 4 &&
                field[coord1.toInt() - 1][coord2.toInt() - 1] == ' ' -> {
                    when (x) {
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

                    when {
                        xWin(field) -> {
                            println("X wins")
                            isGameOver = true
                        }
                        oWin(field) -> {
                            println("X wins")
                            isGameOver = true
                        }
                        !field.any { cell -> cell.any { it == ' ' } } -> {
                            println("Draw")
                            isGameOver = true
                        }
                    }
                }

                coord1.toInt() !in 1 until 4 || coord2.toInt() !in 1 until 4 -> println("Coordinates should be from 1 to 3!")
                else -> println("This cell is occupied! Choose another one!")
            }
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
        }
    }
}

private fun printTurn(x: Boolean) = if (x) print("X turn:") else print("O turn:")