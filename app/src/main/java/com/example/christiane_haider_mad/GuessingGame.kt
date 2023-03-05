package com.example.christiane_haider_mad

fun main(){
    println("** NUMBER GUESSING GAME **")
    println("Your goal is to guess the four digit number in as few attempts as possible.")
    println("What the hints tell you | how many digits you guessed right : how many of the")
    println("                          guessed digits are already in the correct position")
    println()
    println("Generating a random 4 digit number...")
    val number: String = generateRandomNumber() // generate random 4 digit number
    println("Let's begin!")
    println()

    while(true){
        print("Guess the 4 digit number: ")
        val userInput = readLine()!! // get user input

        // handle if user input is valid
        if(userInput.length != 4 || !isNumeric(guess = userInput)){
            println("Ooops, the number should have FOUR DIGITS (0-9)... Try again!")
            continue
        }

        // check if game is won
        if(checkDigits(number = number, guessedNumber = userInput) == 4 && checkPosition(number = number, guessedNumber = userInput) == 4){
            println()
            println("CONGRATS! You guessed the right number!")
            break
        }

        val m = checkDigits(number = number, guessedNumber = userInput)
        val n = checkPosition(number = number, guessedNumber = userInput)

        println("$m:$n")
    }
}

// method that generates a random 4 digit number
// without repeating digits
fun generateRandomNumber() : String{
    var fourDigitNumber: String = ""
    while(fourDigitNumber.length < 4)
    {
        val num = (0..9).random().toString()
        // check if digit is already used
        if (!fourDigitNumber.contains(num)){
            fourDigitNumber += num
        }
    }

    return fourDigitNumber
}

// method that checks how many digits are guessed correctly
fun checkDigits(number: String, guessedNumber: String) : Int{
    var correctDigits: Int = 0
    var string: String = ""
    for(i in 0..3){
        for(j in 0..3) {
            // check if digit was already compared, so counter only increases once per correct digit
            if (number[i] == guessedNumber[j] && !string.contains(guessedNumber[j].toString())) {
                correctDigits++
                string += guessedNumber[j]
            }
        }
    }
    return correctDigits
}

// method that checks how many digits are in the right place
fun checkPosition(number: String, guessedNumber: String) : Int{
    var rightPlacedDigits: Int = 0
    for(i in 0..3){
        if(number[i] == guessedNumber[i]){
            rightPlacedDigits++
        }
    }
    return rightPlacedDigits
}

// method that checks if a string consists of digits only
fun isNumeric(guess: String): Boolean {
    return guess.all { char -> char.isDigit() }
}