/* Nested Loops
 * This program finds the average time spent programming by a student
 * each day over a three day period. */

#include <iostream>
using namespace std;

int main() {
	int numStudents, numDays;
	float compHours, bioHours, compTotal, bioTotal, compAverage, bioAverage;
	int student, day;	// these are the counters for the loops

	cout << "This program will find the average number of hours a day "
		 << "that a student spent programming or studying biology "
		 << "over a long weekend\n\n";
	cout << "How many students are there? ";
	cin >> numStudents;
	cout << "Enter the number of days in the long weekend: ";
	cin >> numDays;

	for (student = 1; student <= numStudents; student++) {
		compTotal = 0;
		bioTotal = 0;

		for (day = 1; day <= numDays; day++) {
			cout << "Please enter the number of programming hours worked by student "
				 << student << " on day " << day << ": ";
			cin >> compHours;
			compTotal += compHours;
			cout << "Please enter the number of biology hours worked by student "
				 << student << " on day " << day << ": " ;
			cin >> bioHours;
			bioTotal += + bioHours;
		}

		compAverage = compTotal / numDays;
		bioAverage = bioTotal / numDays;

		cout << endl;
		cout << "The average number of hours per day spent programming by "
			 << "student " << student << " is " << compAverage << endl;
		cout << "The average number of hours per day spent studying biology by "
			 << "student " << student << " is " << bioAverage << endl << endl;
	}
	return 0;
}

/* [Output]
This program will find the average number of hours a day that a student spent programming or studying biology over a long weekend

How many students are there? 2
Enter the number of days in the long weekend: 2
Please enter the number of programming hours worked by student 1 on day 1: 4
Please enter the number of biology hours worked by student 1 on day 1: 8
Please enter the number of programming hours worked by student 1 on day 2: 6
Please enter the number of biology hours worked by student 1 on day 2: 8

The average number of hours per day spent programming by student 1 is 5
The average number of hours per day spent studying biology by student 1 is 8

Please enter the number of programming hours worked by student 2 on day 1: 0
Please enter the number of biology hours worked by student 2 on day 1: 10
Please enter the number of programming hours worked by student 2 on day 2: 10
Please enter the number of biology hours worked by student 2 on day 2: 100

The average number of hours per day spent programming by student 2 is 5
The average number of hours per day spent studying biology by student 2 is 55
 */