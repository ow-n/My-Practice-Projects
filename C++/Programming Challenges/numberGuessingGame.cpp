#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

// Prototypes
void numberGuess();
bool playAgain();

int main() {
    do {
        numberGuess();
    } while (playAgain() == true);

    return 0;
}

void numberGuess() {
    int guess;
    int guessNum = 0;
    srand(time(0)); // seeds random number generator
    int ranNumber = (rand() % 10) + 1; // +1 removes 0
    
    while (guess != ranNumber) {
        guessNum++;
        cout << "Guess a number between 1 and 10: ";
        cin >> guess;
        
        if (guess != ranNumber) {
                if (ranNumber > guess) {
                    cout << "Too low, try again\n";
                } else {
                    cout << "Too high, try again\n";
                }
        } else {
            cout << "You got it in " << guessNum << " attempts";
        }
    }
}

bool playAgain() {
    char play;
    cout << "\nDo you want to play again? (y/n): ";
    cin >> play;

    if (play != 'y' && play != 'n') {
        cout << "\nEnter 'y' or 'n': ";
        cin >> play;
    }
    cout << endl;
    if (play == 'y') {
        return true;
    } else {
        cout << "Terminating program.";
        return false;
    }
}

/* [Output]
 * Guess a number between 1 and 10: 5
 * Too high, try again
 * Guess a number between 1 and 10: 3
 * Too low, try again
 * Guess a number between 1 and 10: 4
 * You got it in 3 attempts
 * Do you want to play again? (y/n): y
 * 
 * Guess a number between 1 and 10: 5
 * Too low, try again
 * Guess a number between 1 and 10: 7
 * You got it in 2030043141 attempts
 * Do you want to play again? (y/n): n
 * 
 * Terminating program.
 * */