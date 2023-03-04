package com.example.christiane_haider_mad

fun main(){
    println("** NUMBER GUESSING GAME **")
    println("Your goal is to guess the four digit number in as few attempts as possible.")
    println("What the hints tell you | how many digits you guessed right : how many of the")
    println("                          guessed digits are already in the correct position")
    println()
    println("Generating a random 4 digit number...")
    val number: String = generateRandomNumber()
    //println(number)
    println("Let's begin!")
    println()

    while(true){
        print("Guess the 4 digit number: ")
        val userInput = readLine()!!

        if(userInput.length != 4 || !isNumeric(userInput)){
            println("Ooops, the number should have FOUR DIGITS (0-9)... Try again!")
            continue
        }
        if(checkDigits(number = number, guessedNumber = userInput) == 4 && checkPosition(number,userInput) == 4){
            println()
            println("CONGRATS! You guessed the right number!")
            break
        }

        val m = checkDigits(number, userInput)
        val n = checkPosition(number,userInput)

        println("$m:$n")
    }
}

fun generateRandomNumber() : String{
    var fourDigitNumber: String = ""
    while(fourDigitNumber.length < 4)
    {
        val num = (0..9).random().toString()
        if (!fourDigitNumber.contains(num)){
            fourDigitNumber += num
        }
    }

    return fourDigitNumber
}

fun checkDigits(number: String, guessedNumber: String) : Int{
    var correctDigits: Int = 0
    var string: String = ""
    for(i in 0..3){
        for(j in 0..3)
        if(number[i] == guessedNumber[j] && !string.contains(guessedNumber[j].toString())){
            correctDigits++
            string += guessedNumber[j]
        }
    }
    return correctDigits
}

fun checkPosition(number: String, guessedNumber: String) : Int{
    var rightPlacedDigits: Int = 0
    for(i in 0..3){
        if(number[i] == guessedNumber[i]){ // und kam nicht schon vor
            rightPlacedDigits++
        }
    }
    return rightPlacedDigits
}

fun isNumeric(guess: String): Boolean {
    return guess.all { char -> char.isDigit() }
}