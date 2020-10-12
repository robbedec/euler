/*
 * Project Euler, problem 6.
 * Sum square difference
 *
 * Answer: 25164150
 */

#include <cmath>
#include <iostream>

using namespace std;

int main() {
    int bound = 100;
    long r1 = 0, r2 = 0;

    for (int i = 0; i <= bound; ++i) {
        r1 += pow(i, 2);
        r2 += i; 
    }

    r2 = pow(r2, 2) + 1;

    cout << r2-r1 << endl;
}
