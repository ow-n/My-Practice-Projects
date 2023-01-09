#include <iostream>
#include <string>
using namespace std;

// Prototypes
void pickColors(string &colorOne, string &colorTwo);
void mixColors(string &colorOne, string &colorTwo, string &colorMixed);

// Main Function
int main() {
    string colorOne, colorTwo, colorMixed;

    cout << "This program tells you what you get when you mix two primary colors)"
    << "(red, blue, yellow).\n";

    pickColors(colorOne, colorTwo);             // User inputs 2 colors
    mixColors(colorOne, colorTwo, colorMixed);  // Program finds the mixed color

    cout << "\nWhen you mix " << colorOne << " and " << colorTwo
    << ", you get " << colorMixed << ".";

    if (colorOne == colorTwo)
        cout << "\nAlthough you did just enter two of the same color.";

    return(0);
}

// pickColors Function
void pickColors(string &colorOne, string &colorTwo) {
    int attempts = 2; // Limits loops to 3
    cout << "\nPlease enter the first primary color: ";
    cin >> colorOne;
    while ((colorOne != "red") && (colorOne != "blue") && (colorOne != "yellow") && attempts > 0) { // error Handling
        cout << "Invalid input, " << attempts << " attempt remaining. "
        << "Please input a primary color for example \"red\": ";
        attempts--;
        cin >> colorOne;
    }
    if ((colorOne != "red") && (colorOne != "blue") && (colorOne != "yellow") && attempts == 0) {
        cout << "Too many incorrect inputs. Terminating Program.";
        exit(0);
    }
    
    attempts = 2; // Resets and limits loops to 3
    cout << "Please enter the second primary color: ";
    cin >> colorTwo;
    while ((colorTwo != "red") && (colorTwo != "blue") && (colorTwo != "yellow") && attempts > 0) { // error Handling
        cout << "Invalid input. " << attempts << " attempt remaining. "
        << "Please input a primary color for example \"red\": ";
        attempts--;
        cin >> colorTwo;
    }
    if ((colorOne != "red") && (colorOne != "blue") && (colorOne != "yellow") && attempts == 0) {
        cout << "Too many incorrect inputs. Terminating Program.";
        exit(0);
    }
}

// mixColors Function
void mixColors(string &colorOne, string &colorTwo, string &colorMixed) {
    if (colorOne != colorTwo) {
        if (((colorOne == "red") && (colorTwo == "blue")) || ((colorOne == "blue") && (colorTwo == "red")))
            colorMixed = "purple";
        else if (((colorOne == "red") && (colorTwo == "yellow")) || ((colorOne == "yellow") && (colorTwo == "red")))
            colorMixed = "orange";
        else if (((colorOne == "blue") && (colorTwo == "yellow")) || ((colorOne == "yellow") && (colorTwo == "blue")))
            colorMixed = "green";
    }
    else
        colorMixed = colorOne;
}

/* [Output 1]
 * This program tells you what you get when you mix two primary colors)(red, blue, yellow).
 * Please enter the first primary color: pink  
 * Invalid input, 2 attempt remaining. Please input a primary color for example "red": #!@#%^1234blue
 * Invalid input, 1 attempt remaining. Please input a primary color for example "red": yellow
 * Please enter the second primary color: Red
 * Invalid input. 2 attempt remaining. Please input a primary color for example "red": red
 *
 * When you mix yellow and red, you get orange.
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * [Output 2]
 * This program tells you what you get when you mix two primary colors)(red, blue, yellow).
 *
 * Please enter the first primary color: 3
 * Invalid input, 2 attempt remaining. Please input a primary color for example "red": 2
 * Invalid input, 1 attempt remaining. Please input a primary color for example "red": 1
 * Too many incorrect inputs. Terminating Program.
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * [Output 3]
 * This program tells you what you get when you mix two primary colors)(red, blue, yellow).
 *
 * Please enter the first primary color: red
 * Please enter the second primary color: red
 *
 * When you mix red and red, you get red.
 * Although you did just enter two of the same color. */