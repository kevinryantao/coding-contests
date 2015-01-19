package facebookhackercup.round1;

import com.google.common.collect.Sets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by ktao on 1/17/15.
 */
public class Homework {

    public static void main(String[] args) throws IOException {
        Homework homework = new Homework();

        Collection<Integer> primes = homework.getAllPrimesUnder(10000000);
        for(int i : primes){
            System.out.print(i + ", ");
        }
        System.out.println("\nsize is " + primes.size());

        HashSet<Integer> primesHashSet = Sets.newHashSet(primes);


        PrintWriter primacityCache = new PrintWriter("primacityCache.csv");
        int[] primacityArray = new int[10000001];
        for(int i = 2; i <= 10000000; i++){
            primacityArray[i] = homework.getPrimacity(primes, primesHashSet, i);
            System.out.println(i + "\t\t" + primacityArray[i]);
            primacityCache.println(i+","+primacityArray[i]);
        }
        primacityCache.close();
    }

    public Collection<Integer> getAllPrimesUnder(int max){
        // uses sieve of eratosthenes
        SortedSet<Integer> primes = new TreeSet<Integer>();
        boolean[] notPrime = new boolean[max+1];
        for(int i = 2; i <= max; i++){
            if(!notPrime[i]){
                primes.add(i);
                if(i <= Math.sqrt(max)){
                    for(int multiplier = i; i * multiplier <= max; multiplier++){
                        notPrime[i*multiplier] = true;
                    }
                }
            }
        }
        return primes;
    }

    public int getPrimacity(Collection<Integer> primes, Set<Integer> primeSet, int numberToTest){

        int primacitySoFar = 0;

        int number = numberToTest;

        for(int prime : primes){
            if(primeSet.contains(number)){
                primacitySoFar++;
                break;
            }
            if(prime > Math.sqrt(number)){
                break;
            }
            if(number % prime == 0){
                primacitySoFar++;
            }
            while(number % prime == 0){
                number = number / prime;
            }
        }
        return primacitySoFar;
    }


    public int[] generatePrimacity(int lowerBound, int upperBound){

        return new int[0];

    }
}
