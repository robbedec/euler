/*
 * Find all prime numbers until a given bound
 * using the sieve of eratosthenes.
 *
 * Find the nth prime number with a given bound.
 *
 * TODO: create optimized versions (ie. segmented sieve, incremental sieve)
 */

#include<iostream>
#include<vector>
#include<cmath>

#include "primes.h"

using namespace std;

vector<bool> calc_primes(int bound) {
    vector<bool> v(bound, true);

    for (int i = 2; i <= sqrt(bound); ++i) {
        if (v[i]) {
            for (int j = i * 2; j <= bound; j += i) {
                v[j] = false; 
            }  
        } 
    }

    return v;
}

void sieve(const unsigned int bound, vector<int>& primes) {
    vector<bool> sieve(bound, true);

    for (int i = 2; i <= sqrt(bound); ++i) {
        if (sieve[i]) {
            for (int j = i * 2; j <= bound; j += i) {
                sieve[j] = false; 
            }  
        } 
    }

    for (int i = 2; i < bound; ++i) {
        if (sieve[i]) primes.push_back(i); 
    }
}

// https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes#Segmented_sieve
vector<int> segmented_sieve(const unsigned int bound) {
    vector<int> primes;
    vector<int>::const_iterator it, end;

    int limit = floor(sqrt(bound)) + 1, low = limit, high = 2 * limit;

    sieve(limit, primes);

    while (low < bound) {
        if (high >= bound) high = bound; 

        vector<bool> mark(limit, true);

        for (it = primes.begin(), end = primes.end(); it != end; ++it) {
            int low_limit = floor(low / (*it)) * (*it); 
            if (low_limit < low) low_limit += (*it);

            // Mark multiples in the segment
            for (int j = low_limit; j < high; j += (*it)) {
                mark[j - low] = false; 
            }
        }

        // Multiples not marked as false are prime
        for (int i = low; i < high; ++i) {
            if (mark[i - low]) primes.push_back(i); 
        }

        // Update to next segment
        low += limit;
        high += limit;
    }

    return primes;
}
