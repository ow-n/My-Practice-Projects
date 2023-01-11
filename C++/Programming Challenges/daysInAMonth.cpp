#include <iostream>
using namespace std;

// Prototype
bool isLeapYear(int year);

int main() {
    char again;
    int month, monthDays, year;
    do {
        cout << "Enter a month (integer 1-12) "; 
        cin >> month;
        // User must enter 1-12
        while (month < 1 || month > 12) {
            cout << "Invalid month number. Enter 1-12.";
            cin >> month;
        }

        cout << "Enter a year "; 
        cin >> year;
        isLeapYear(year);
        // Selecting month days
        if (month == 4 || month == 6 || month == 9 || month == 12) {
            monthDays = 30;
        } else if (month != 2) {
            monthDays = 31;
        } else if (isLeapYear(year)) {
        monthDays = 29; 
        } else {
            monthDays = 28;
        }
        
        cout << "This month has " << monthDays << " days.";
        cout << "\nWould you like to try again (y / n): ";
        cin >> again;
    } while (again == 'y');
}


bool isLeapYear(int year) {
    // Leap Year if divisible by 100 or 400
    if (year % 100 == 0) {
        if (year % 400 == 0) {
            return true;
        }
    } else if (year % 4 == 0) {
        return true;
    }
    return false;
}

/* [Output]
 * Enter a month (integer 1-12) 2
 * Enter a year 2000
 * This month has 29 days.
 * Would you like to try again (y / n): y
 * Enter a month (integer 1-12) 2
 * Enter a year 2100
 * This month has 28 days.
 * Would you like to try again (y / n): n   */