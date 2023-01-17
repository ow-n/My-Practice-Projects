// Characters for the ASCII codes

#include <iostream>
#include <cctype>
using namespace std;

// Prototype
void asciiList();       // lists Ascii from 0 - 127
void isPrintable();     // checks if 1 character is printable
void unprintableList(); // lists all unprintable characters from 0 - 127


int main() {
    asciiList();
    // Checks for Unprintable Characters
    // cout << endl;
    // isPrintable();
    // unprintableList();
    return 0;
}


void asciiList() {
    for (int asciiNumber = 0; asciiNumber < 128; asciiNumber++) {
        if (asciiNumber % 16 == 0) {
            cout << endl;
        }

        cout << char(asciiNumber);
    }
}


void isPrintable() {
    int asciiCheck = 0;
    if (isprint (asciiCheck)) {
        cout << char(asciiCheck) << " is printable." << endl;
    }  else {
        cout << char(asciiCheck) << " is not printable." << endl;
    }
}


void unprintableList() {
    for (int asciiNumber = 0; asciiNumber < 128; asciiNumber++) {
        if (!isprint (asciiNumber)) {
            cout << asciiNumber << " ";
        }
    }
    cout << "is not printable";
}

/* [Output]
 * 
 *
 *
 * ☺☻♥♦♣♠
 * 
 * ►◄↕‼¶§▬↨↑↓→∟↔▲▼
 * 
 * 123456789:;<=>?
 * @ABCDEFGHIJKLMNO
 * PQRSTUVWXYZ[\]^_
 * `abcdefghijklmno
 * pqrstuvwxyz{|}~
 */