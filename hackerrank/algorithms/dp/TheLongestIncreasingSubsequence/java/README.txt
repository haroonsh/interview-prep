An Introduction to the Longest Increasing Subsequence Problem

The task is to find the length of the longest subsequence in a given array of integers such that all elements of the subsequence are sorted in strictly ascending order. This is called the Longest Increasing Subsequence (LIS) problem.

For example, the length of the LIS for  is  since the longest increasing subsequence is .

Here's a great YouTube video of a lecture from MIT's Open-CourseWare covering the topic.


This is one approach which solves this in quadratic time using dynamic programming. A more efficient algorithm which solves the problem in  time is available here.

In this challenge, you must find the length of the longest strictly increasing subsequence of the given sequence.

Input Format

The first line contains a single integer , the number of elements in . 
Each of the next  lines contains an integer, 

Constraints

Output Format

Print a single line containing a single integer denoting the length of the longest increasing subsequence.

Sample Input 0

5
2
7
4
3
8
Sample Output 0

3
Explanation 0

In the array , the longest increasing subsequence is . It has a length of .
