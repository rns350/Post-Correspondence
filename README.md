# The Post Correspondence Problem

You are given a collection of dominos. Each domino has a string of characters on top and another string on the bottom. (Both strings are non-empty.) You can make as many copies of the dominos as you need. The problem is to place the dominos side by side so that the combination of the strings on the top is the same as the combination of the strings on the bottom.

**Example**: Suppose:
D1 = "c/cca"
D2 = "ac/ba".
D3 = "bb/b".
D4 = "ac/cb".
Then the sequence D3, D2, D1, D4, D3 spells out the string ``bbaccacbb'' both on the top and on the bottom.

The Post correspondence problem is, surpringly, only semi-decidable. If there is an answer then, obviously, one can find it by exhaustive search; however, there is no algorithm that always terminates and that always answers correctly whether or not an instance of the problem has a solution. The problem can be characterized in terms of the following state space:

A state is a top string and a bottom string, generated by a sequence of dominos, such that either the top string is a prefix of the bottom string or the bottom string is a prefix of the top. For example, the pair of strings <"bbbb", "bb"> is a state since it is generated by the sequence D3,D3, and since the bottom "bbbb" is a prefix of the top "bbbbbb". The sequence of dominoes D1,D2 does not generate a legitimate state because the top is "cac" and the bottom is "ccaba" and neither is a prefix of the other.
An operator on a state is to add the strings from another domino at the end of the top and bottom. that leads to a legitimate state.
The start state is the pair < "", "" > of empty strings on the top and bottom.
The goal state is a state where the top string and bottom string are non-empty and equal.

The input is a text file where the maximum number of states to search.
Each successive line lists a description of a domino of the form
[domino number] [front end] [back end]

The program will output 1 of 3 results:

1. A sequence of dominos that solve the problem.

2. A message stating that no solution exists.

3. A message stating that that no solution was found within the limits of search.