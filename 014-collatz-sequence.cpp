/*
 * Project Euler, problem 14.
 * intest collatz sequence with indeces between 2 and 1 000 000.
 *
 * Answer: 837799
 *
 * This approach takes a while, TODO: add caching to improve the brute force method.
 */

#include <iostream>
#include <utility>

using namespace std;

int main() {
    int bound = 1'000'000, chain = 0;
    pair<int, int> biggest_pair = make_pair(0, 0);

    for (int i = 2; i < bound; ++i, chain = 0) {
        int copy = i;

        for (; copy != 1; ++chain) {
            copy % 2 == 0 ? copy /= 2 : copy = ((3 * copy) + 1);
        }

        if (biggest_pair.second < chain) biggest_pair = make_pair(i, chain);
    }

    cout << "Index: " << biggest_pair.first << endl << "Chain length: " << biggest_pair.second << endl;
}
