/* 
 * Project euler, problem 9.
 * 
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 * a² + b² = c²
 * Find abc for which a + b + c = 1000
 */

#include <iostream>
#include <cmath>

using namespace std;

int main() {
    int a = 0, b = 0, c = 0, bound = 100;

    for (int n = 1; n < bound; ++n) {
        for (int m = n+1; m < bound + 1; ++m) {

            /*
             * Extension to find only primitive triplets
             *
             * bool m_even = (m % 2 == 0), n_even = (n % 2 == 0);
             * if (m_even == n_even) break;
             */
            
            // Use euclids forumula to generate Pythagorean triplets
            a = pow(m, 2) - pow(n, 2);
            b = 2 * m * n;
            c = pow(m, 2) + pow(n, 2);

            if (a + b + c == 1000) {
                cout << "Pythagorean triplet found: a = " << a << " b = " << b << " c = " << c << endl; 
                cout << "Product of the factors equals " << a * b * c << endl;

                return 0;
            }
        }  
    }

    return 0;
}
