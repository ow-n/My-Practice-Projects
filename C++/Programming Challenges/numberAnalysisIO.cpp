#include <iostream>
#include <fstream>
using namespace std;

// Class Variables
const int ARRAY_SIZE = 10;
int actualSize = 0;

// Prototypes
void readFile(double numbers[]);
void writeFile(double numbers[]);
void printOutput(double numbers[]);
double findSmallest(double numbers[]);
double findLargest(double numbers[]);
double findTotal(double numbers[]);
double findAvg(double numbers[]);

// MAIN
int main() {
    double numArray[ARRAY_SIZE];
    readFile(numArray);
    printOutput(numArray);
    writeFile(numArray);
}

// Methods
void readFile(double numbers[]) {
    double readNum;
    ifstream inputFile("numberAnalysisIO Input Numbers.txt");

    if(inputFile) {
        while (inputFile >> readNum && actualSize < ARRAY_SIZE)
            numbers[actualSize++] = readNum;
    } else {
        cout << "Error: unable to open input file" << endl;
    }

    inputFile.close();
}

void writeFile(double numbers[]) {
    ofstream outputFile("numberAnalysisIO Output.txt");
    outputFile << "The smallest number in the array is " << findSmallest(numbers) << endl;
    outputFile << "The largest number in the array is " << findLargest(numbers) << endl;
    
    outputFile << "The total of the numbers in the array is " << findTotal(numbers) << endl;
    outputFile << "The average of the nubmers in the array is " << findAvg(numbers);
}

double findSmallest(double numbers[]) {
    double smallNum = numbers[0];
    for (int i = 1; i < actualSize; i++) {
        if (smallNum > numbers[i]) {
            smallNum = numbers[i];
        }
    }
    return smallNum;
}

void printOutput(double numbers[]) {
    cout << "The smallest number in the array is " << findSmallest(numbers) << endl;
    cout << "The largest number in the array is " << findLargest(numbers) << endl;
    cout << "The total of the numbers in the array is " << findTotal(numbers) << endl;
    cout << "The average of the nubmers in the array is " << findAvg(numbers);
}

// Methods to find values
double findLargest(double numbers[]) {
    double largeNum = numbers[0];
    for (int i = 1; i < actualSize; i++) {
        if (largeNum < numbers[i]) {
            largeNum = numbers[i];
        }
    }
    return largeNum;
}

double findTotal(double numbers[]) {
    double total;
    for (int i = 0; i < actualSize; i++) {
        total += numbers[i];
    }
    return total;
}

double findAvg(double numbers[]) {
    double average;
    return average = findTotal(numbers) / actualSize;
}

/* [Output]
The smallest number in the array is 5
The largest number in the array is 15
The total of the numbers in the array is 50
The average of the nubmers in the array is 10
*/