#ifndef PRIMES_H
#define PRIMES_H

#include <vector>

std::vector<bool> calc_primes(int bound);

void sieve(const unsigned int, std::vector<int>&);

std::vector<int> segmented_sieve(const unsigned int);

void show_primes(std::vector<bool>&);

void nth_prime(std::vector<bool>&, int);

void sum_of_primes(std::vector<bool>&);

#endif
