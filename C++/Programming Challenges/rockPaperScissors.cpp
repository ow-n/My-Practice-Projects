#include <iostream>
#include <string>
#include <cstdlib>
#include <ctime>
using namespace std;

// Prototypes
string compChoice();
string userChoice();
void choiceCompare(string, string);
bool playAgain();

int main() {  
    do {
        string compHand = compChoice();
        string userHand = userChoice();

        cout << "I chose " << compHand << "." << endl;

        choiceCompare(compHand, userHand);
    } while (playAgain() == true);
}

string compChoice() {
    string hand;
    srand(time(NULL));
    int choice = rand() % 3 + 1; // 3 Choices

    switch (choice) {
        case 1:
            hand = "rock";
        case 2:
            hand = "paper";
        case 3:
            hand = "scissors";
    }
    return hand;
}

string userChoice() {
    string hand;
    cout << "Enter rock/paper/scissors: ";
    cin >> hand;

    while (hand != "rock" && hand != "Rock" && hand != "paper" &&
    hand != "Paper" && hand != "scissors" && hand != "Scissors") {
        cout << "Invalid input." << endl;
        cout << "Enter rock/paper/scissors: ";
        cin >> hand;
    }

    if (hand == "Rock") {
        hand = "rock";
    } else if (hand == "Paper") {
        hand = "paper";
    } else if (hand == "Scissors") {
        hand = "scissors";
    }

    return hand;
}

void choiceCompare(string compHand, string userHand) {
    if (compHand == "rock") {
        if (userHand == "paper") {
            cout << "You win. I chose " << compHand << " and you chose " << userHand << "." << endl << endl;
        } else if (userHand == "scissors") {
            cout << "I win. I chose " << compHand << " and you chose " << userHand << "." << endl << endl;
        }
    } else if (compHand == "paper") {
        if (userHand == "rock") {
            cout << "I win. I chose " << compHand << " and you chose " << userHand << "." << endl << endl;
        } else if (userHand == "scissors") {
            cout << "You win. I chose " << compHand << " and you chose " << userHand << "." << endl << endl;
        }
    } else if (compHand == "scissors") {
        if (userHand == "paper") {
            cout << "I win. I chose " << compHand << " and you chose " << userHand << "." << endl << endl;
        } else if (userHand == "rock") {
            cout << "You win. I chose " << compHand << " and you chose " << userHand << "." << endl << endl;
        }
    } else {
        cout << "Draw. We had the same choice" << endl << endl;
    }
}

bool playAgain() {
    char rematch;
    cout << "Rematch? (y/n)";
    cin >> rematch;

    while (rematch != 'y' && rematch != 'n') {
        cout << "Invalid input." << endl;
        cout << "Rematch?";
        cin >> rematch;
    }

    if (rematch == 'y') {
        return true;
    } else {
        return false;
    }
}

/* [Output]
 * Enter rock/paper/scissors: rock
 * I chose scissors.
 * You win. I chose scissors and you chose rock.
 * 
 * Rematch? (y/n)y
 * Enter rock/paper/scissors: Paper
 * I chose scissors.
 * I win. I chose scissors and you chose paper.
 * 
 * Rematch? (y/n)y
 * Enter rock/paper/scissors: scissor
 * Invalid input.
 * Enter rock/paper/scissors: scissors
 * I chose scissors.
 * Draw. We had the same choice
 * 
 * Rematch? (y/n)n
 * */