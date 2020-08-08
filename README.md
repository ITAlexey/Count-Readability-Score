# Count-Readability-Score

This project was created by **Java Hyperskill** academy. It applies different scientific approaches to calculate a readability score.
Besides the score, it also derives and outputs the following information about the reading file such as:
- Number of words
- Senteces
- Characters
- Syllables
- Polysyllables

Based on the obtained readability score, it compares with database and concludes what is a minimum grade level of the person needed to understand this text.

### Example
```
> java Main in.txt
```
### Output:
```
Words: 137
Sentences: 14
Characters: 687
Syllables: 210
Polysyllables: 17
Enter the score you want to calculate (ARI, FK, SMOG, CL, all): all

Automated Readability Index: 7.08 (about 13 year olds).
Flesch–Kincaid readability tests: 6.31 (about 12 year olds).
Simple Measure of Gobbledygook: 9.42 (about 15 year olds).
Coleman–Liau index: 10.66 (about 17 year olds).

This text should be understood in average by 14.25 year olds.
```
### ***Conclusion***
This project is a wonderful practice for better understanding the principles of OOP, data structures and basics of program design.
It helped me to learn a big section of topics which were considered in the course.
