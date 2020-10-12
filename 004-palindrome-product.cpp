/*
 * Project euler, problem 4.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */

#include <iostream>

using namespace std;

bool is_palindrome(int nr) {
    int n = nr, digit = 0, rev = 0;

    do {
        digit = nr % 10;
        rev = (rev * 10) + digit;
        nr /= 10;
    } while (nr != 0);

    return n == rev;
}

int main() {
    int max = 0, num = 0;

    for (int i = 999; i >= 100; --i) {
        for (int j = 999; j >= 100; --j) {
            num = i * j; 

            if (num <= max) break;

            if (is_palindrome(num)) {
                cout << num << " with factors " << i << ' ' << j  << endl;
                max = num; 
            }
        } 
    }

    cout << endl << "Largest: " << max << endl;
}
