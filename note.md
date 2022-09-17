<!DOCTYPE html>
<html><head>
<meta charset="utf-8">
<title>讲义</title>
</head>
<body style="font-family: verdana, sans-serif;font-size: 12px;color: #202122;">

<span style="font-size: 19px;">Today I first gave an [introduction to this course](/d2l/common/dialogs/quickLink/quickLink.d2l?ou=230447&amp;type=content&amp;rcode=dalhousie-2471119).</span>

## <span style="font-size: 24px;">RAM, Algorithm Analysis and Order Notation</span>

<span style="font-size: 19px;">At the beginning of this course, let us learn some basic stuff about algorithm analysis.</span>

### <span style="font-size: 19px;">Pseudocode</span>

<span style="font-size: 19px;">Before we analyze algorithms, let us learn how to describe an algorithm precisely. Instead of using "real" code, we use pseudocode to specify an algorithm. In a piece of pseudocode, we use the most clean and concise expressive method to describe a given algorithm. Sometimes, the pseudocode of an algorithm could even use an English sentence to describe a step of this algorithm. For example, if a complicated algorithm requires us to first sort the given input array, then we could write something like "sort the array A in ascending order using mergesort" as its first step. When writing pseudocode, we are not concerned with software engineering issues, such as data abstraction, modularity, and exception &amp; error handling. Thus, pseudocode focuses on conveying the essence of an algorithm.</span>

<span style="font-size: 19px;">In this course, you are allowed to follow any convention of pseudocode that you prefer, as long as your pseudocode is readable and consistent. If you do not have any preference yourself, feel free to follow the way in which I write pseudocode, or the convention described in the textbook (Chapter 2).</span>

### <span style="font-size: 19px;">Insertion Sort</span>

<span style="font-size: 19px;">Now, let's write down the pseudocode of insertion sort, which is a sorting algorithm taught in a previous course, before we analyze its running time.</span>

<pre><span style="font-size: 19px;">INSERTION-SORT(A[1..N])
  for j ← 2 to n
    key ← A[j]</span>
<span style="font-size: 19px;">    i ← j - 1
    while i &gt; 0 and A[i] &gt; key</span>
<span style="font-size: 19px;">      A[i+1] ← A[i]</span>
<span style="font-size: 19px;">      i ← i - 1</span>
<span style="font-size: 19px;">    A[i+1] ← key</span></pre>

<span style="font-size: 19px;">How to measure the running time of an algorithm? We may be tempted to use wall-clock time, but this is a bad measurement as it highly depends on the computer on which the algorithm is run. Hence, to analyze an algorithm, we need remove computer details such as processor speed, disk and memory, and one way of achieving this is to count the number of elementary operations only. The goal of algorithm analysis is to predict the resources required by an algorithm, and to do this, we need a machine model under which we express our algorithms, and some way of assigning costs of the operations under this model.</span>

### <span style="font-size: 19px;">Random Access Machine (RAM)</span>

<span style="font-size: 19px;">We analyze our algorithms under the random-access machine (RAM) model of computation. Algorithms are implimented as computer programs in a RAM.</span>

<span style="font-size: 19px;">A figure illustrating RAM:</span>

<span style="font-size: 19px;">![RAM](ram.png "RAM")</span>

<span style="font-size: 19px;">In this machine model, there are unbounded number of local memory cells (words). An integer or a floating-point number can be stored in a memory cell. Program instructions are executed sequentially, i.e., one after another starting from the first to the last; there is no concurrency. The location counter indicates the instruction of the program that is currently being executed.</span>

<span style="font-size: 19px;">In this model, the time complexity (running time) is defined as the number of instructions executed, and the space complexity is the number of memory cells accessed.</span>

<span style="font-size: 19px;">The instruction set of RAM contains instructions commonly found in real computers, such as arithmetic operations (add, subtract, multiply, divide, remainder, floor, ceiling), data movement (load, store, copy) and control (conditional &amp; unconditional branch). Each instruction uses 1 unit of time.</span>

<span style="font-size: 19px;">[Note: I didn't have time to talk about the following paragraph in Section 2 today, but I will talk about it in Section 2 on Friday] There are instructions in real computers that are not listed above. If your algorithm requires them, then make sure to make reasonable assumptions. For example, the exponentiation operation, i.e., computing x to the power of y, would require a numerical algorithm that is costly, and we would NOT assume that there is an instruction in the RAM that can perform this operation. On the other hand, when k is an integer &lt;= the number of bits in a word, then 2<sup>k</sup>&nbsp;can be computed using a left shift. Thus we would assume that 2<sup>k</sup>&nbsp;is a constant-time operation.</span>

<span style="font-size: 19px;">&nbsp;</span>

<span style="font-size: 19px;">[section 2 students need to finish reading the last paragraph of lecture 1 notes]</span>

<span style="font-size: 19px;">There are some limitations of the RAM, and more advanced models have been proposed to address these limitations. For example, to introduce concurrency to the RAM model, the PRAM model of computation was proposed. RAM does not consider memory hierarchy; to address this, models such as the external memory model were proposed. These are typically covered in graduate-level courses. To understand these models, a solid background of analyzing algorithms in the RAM is required. Furthermore, in many cases, algorithm analysis under RAM is sufficient. Thus, in this course, we mainly focus on RAM.</span>

### <span style="font-size: 19px;">Analysis of Insertion Sort</span>

<span style="font-size: 19px;">To analyze algorithms in RAM, we first need understand that the running time of an algorithm is expressed as a function of its input size. This is because the running time of an algorithm normally grows when the size of input grows. We choose reasonable parameters associated with the problem and use them as input size. For example, in sorting, the array size is the input size. In graph algorithms, we often choose the number of vertices and the number of edges as parameters.</span>

<span style="font-size: 19px;">The general steps of analyzing pseudocode are</span>

1.  <span style="font-size: 19px;">For each line of pseudocode, count the number of primitive operations in it. Pay attention to the word "primitive" here; sorting an array is not a primitive operation.</span>
2.  <span style="font-size: 19px;">Multiply this count with the number of times this line is executed.</span>
3.  <span style="font-size: 19px;">Sum up over all lines.</span>

<span style="font-size: 19px;">To use this to analyze insertion sort, we first observe that each line of pseudocode can be implemented using a constant number of RAM instructions. Thus, we let c<sub>i</sub>&nbsp;be the cost of line i, which is a constant. Then, for each value of j in the outer loop, we let t<sub>j</sub>&nbsp;be the number of times that the while loop test in line 4 is executed. With these definitions, we have</span>

<pre><span style="font-size: 19px;"> INSERTION-SORT(A[1..N])              cost                times
1   for j ← 2 to n                    c<sub>1</sub>                  n
2     key ← A[j]                      c<sub>2</sub>                  n-1</span>
<span style="font-size: 19px;">3     i ← j - 1                       c<sub>3</sub>                  n-1
4     while i &gt; 0 and A[i] &gt; key      c<sub>4</sub>                  ∑<sub>j=2,3,...,n</sub> t<sub>j</sub></span>
<span style="font-size: 19px;">5       A[i+1] ← A[i]                 c<sub>5</sub>                  ∑<sub>j=2,3,...,n</sub> (t<sub>j</sub>-1)</span>
<span style="font-size: 19px;">6       i ← i - 1                     c<sub>6</sub>                  ∑<sub>j=2,3,...,n</sub> (t<sub>j</sub>-1)</span>
<span style="font-size: 19px;">7     A[i+1] ← key                    c<sub>7</sub>                  n-1</span></pre>

<span style="font-size: 19px;">Now let's calculate the running time as a function of n:</span>

![T(n)](tn.jpg "T(n)")

<span style="font-size: 19px;">This does not tell us much about the time complexity, as t<sub>j</sub>&nbsp;is not known and it depends on the actual input. The only thing that we can say about t<sub>j</sub>&nbsp;is that its value is between 1 and j (inclusive). To make sense of this function, let us see how the input affects the running time.</span>

<span style="font-size: 19px;">**Best Case**: In the best case, the input array A is already sorted, as this guarantees that t<sub>j</sub>&nbsp;is equal to 1. In this case,</span>

<span style="font-size: 19px;">T(n) = c<sub>1</sub>n + c<sub>2</sub>(n-1) + c<sub>3</sub>(n-1) + c<sub>4</sub>(n-1) + c<sub>7</sub>(n-1) = (c<sub>1</sub>&nbsp;+ c<sub>2</sub>&nbsp;+ c<sub>3&nbsp;</sub>+ c<sub>4</sub>&nbsp;+ c<sub>7</sub>) n - (c<sub>2</sub>&nbsp;+ c<sub>3&nbsp;</sub>+ c<sub>4</sub>&nbsp;+ c<sub>7</sub>)</span>

<span style="font-size: 19px;">This is a linear function of n, and thus we say the running time is Θ(n).</span>

<span style="font-size: 19px;">**Worst Case**: The case in which the input array A is reverse sorted is the worst case, as this guarantees that t<sub>j</sub>&nbsp;is equal to j. In this case,</span>

![worst case](tn2.jpg "worst case")

<span style="font-size: 19px;">This is a quadratic function of n, and thus we say the running time is Θ(n<sup>2</sup>).</span>

<span style="font-size: 19px;">**Average Case**: To perform the average-case analysis, we need assume that each of the n! permutations of A is equally likely. This is much more complicated, and if you are interested, you could read the following book (available in our library) to find out (not required):</span>

<span style="font-size: 19px;">The Art of Computer Programming, Volume 3: Sorting and Searching (2nd Edition), Donald E. Knuth.</span>

<span style="font-size: 19px;">If you intend to read this to enrich your knowledge, you could start by reading the discussions on inversions in the input array (5.1.1) and other properties of permutations in the same chapter, Before reading 5.2.1: Sorting by Insertion.</span>

<span style="font-size: 19px;">In this course, we mainly (though not always) concentrate on worst-case analysis. As pointed out by the textbook, there are many good reasons: Worst-case analysis gives an upper bound on the running time of the algorithm, guaranteeing the algorithm will not be more costly than the result of the analysis. In many practical applications, the worst-case happens very frequently. For example, when searching for records in a real database application, it is very often that the query would look for some key value that is not in the database. Finally, for many algorithms, the average-case running time is roughly as bad the their worst-case running time.</span>

<span style="font-size: 19px;">To appreciate the importance of good algorithm design, take the problem of sorting an array of SIN numbers for example. According to the data published by Statistics Canada, at the end of 2015, the population in Canada is roughly 34,880,000. We use this number as the value of n. Then, n<sup>2</sup>&nbsp;is roughly 10<sup>15</sup>. Let's first throw away the constants in the analysis of insertion sort, and say this is the number of instructions required. If we use faster sorting algorithms whose cost is proportional to n lg n (in algorithms, lg n is log<sub>2</sub>&nbsp;n), then for this input, lg n is roughly 25 and n lg n is roughly 10<sup>9</sup>. If the CPU performs 10<sup>11</sup>&nbsp;(100 billion) instructions per second, then insertion sort would require 10<sup>4</sup>&nbsp;seconds which are roughly 3 hours, while mergesort and heapsort would require 1/100 second. There is a big difference. In fact, the number of instructions of insertion sort should be a constant times n<sup>2</sup>, and if the constant is about 20, then it would require a long weekend. If you use mergesort/heapsort, then, even if the constant is 200, the sorting would be done in a matter of seconds.</span>

### <span style="font-size: 19px;">Order (Rate) of Growth</span>

<span style="font-size: 19px;">Another thing to note is that, when presenting the result of analysis, we used Θ to throw away the constant factors. This is because we care about the order (rate) of the growth of the function that represents the running time, and we often care less about the coefficients. Asymptotic notation allows us to make more simplifying abstraction.</span>

### <span style="font-size: 19px;">Θ-Notation</span>

<span style="font-size: 19px;">First, the definition:</span>

<span style="font-size: 19px;">Θ(g(n)) = {f(n): there exist positive constants c<sub>1</sub>, c<sub>2</sub>&nbsp;and n<sub>0</sub>, s.t. 0 &lt;= c<sub>1</sub>g(n) &lt;= f(n) &lt;= c<sub>2</sub>g(n) for all n &gt;= n<sub>0</sub>}</span>

<span style="font-size: 19px;">Here we can say that g(n) is an asymptotically tight bound for f(n).</span>

<span style="font-size: 19px;">Observe that in the definition, Θ(g(n)) is defined as a set of functions. We however often abuse notation (acceptable here) and say f(n) = Θ(g(n)), and this means that f(n) ∈ Θ(g(n)). Sometimes this allows us to write shorter identities. For example, 20n<sup>2</sup>&nbsp;+ 13n + 1 = 20n<sup>2</sup>&nbsp;+ Θ(n) means 20n<sup>2</sup>&nbsp;+ 13n + 1 = 20n<sup>2</sup>&nbsp;+ f(n) and f(n) ∈ Θ(n) </span>

<span style="font-size: 19px;">Now, let's use the definition to prove that n<sup>2</sup>/2 + lg n = Θ(n<sup>2</sup>).</span>

<span style="font-size: 19px;">Proof. To prove this claim, we must determine positive constants c<sub>1</sub>, c<sub>2</sub>&nbsp;and n<sub>0</sub>, s.t.</span>

<span style="font-size: 19px;">c<sub>1</sub>&nbsp;n<sup>2</sup>&lt;= n<sup>2</sup>/2 + lg n &lt;= c<sub>2</sub>&nbsp;n<sup>2</sup></span>

<span style="font-size: 19px;">This is equivalent to c<sub>1</sub>&nbsp;&lt;= 1/2 + (lg n) / n<sup>2</sup>&nbsp;&lt;= c<sub>2</sub></span>

<span style="font-size: 19px;">Setting c<sub>1</sub>&nbsp;= 1/4, c<sub>2</sub>&nbsp;= 3/4 and n<sub>0</sub>&nbsp;= 2 would make this inequality hold (make sure that you do verify this).</span>

<span style="font-size: 19px;">There are also other choices of values of these constants that would guarantee the inequality, and in your proof, you just have to show the existence of one set, according to the definition.</span>

<span style="font-size: 19px;">Note that asymptotic notation applies to asymptotically positive functions only, which are functions whose values are positive for all sufficiently large n.</span>

### <span style="font-size: 19px;">O-Notation (Asymptotic Upper Bound)</span>

<span style="font-size: 19px;">Definition: O(g(n)) = {f(n): there exist positive constants c and n<sub>0</sub>, s.t. 0 &lt;= f(n) &lt;= c g(n) for all n &gt;= n<sub>0</sub>}</span>

<span style="font-size: 19px;">Following this definition, we can determine that n, n<sup>2</sup>, 3n<sup>2</sup>&nbsp;+ 4n + 5 are all O(n<sup>2</sup>), while n<sup>3</sup>&nbsp;is not.</span>

<span style="font-size: 19px;">When we say that the running time of insertion sort is O(n<sup>2</sup>), we mean that the worst-case running time of insertion sort is O(n<sup>2</sup>). When we say that an algorithm runs in polynomial time, we mean that there exists a constant k, s.t. the worst-case running time of this algorithm is O(n<sup>k</sup>).</span>

### <span style="font-size: 19px;">Ω-Notation (Asymptotic Lower Bound)</span>

<span style="font-size: 19px;">Definition: Ω(g(n)) = {f(n): there exist positive constants c and n<sub>0</sub>, s.t. 0 &lt;= c g(n) &lt;= f(n) for all n &gt;= n<sub>0</sub>}</span>

<span style="font-size: 19px;">Based on the definitions, we have the following theorem: f(n) = Θ(g(n)) if and only if f(n) = O(g(n)) and f(n) = Ω(g(n)). For example, the statement n<sup>2</sup>/2 + lg n = Θ(n<sup>2</sup>) is equivalent to n<sup>2</sup>/2 + lg n = O(n<sup>2</sup>) and n<sup>2</sup>/2 + lg n = Ω(n<sup>2</sup>).</span>

<span style="font-size: 19px;">Some quick examples: n<sup>2</sup>, (lg n) n<sup>2</sup>, 4n<sup>2</sup>+5 are all in Ω(g(n)), while n is not.</span>

### <span style="font-size: 19px;">o-Notation</span>

<span style="font-size: 19px;">Definition: o(g(n)) = {f(n): for any positive constant c, there exists a constant n<sub>0</sub>, s.t. 0 &lt;= f(n) &lt; c g(n) for all n &gt;= n<sub>0</sub>}</span>

<span style="font-size: 19px;">This definition shows that f(n) grows more slowly than g(n).</span>

<span style="font-size: 19px;">Another definition of o-Notation is that f(n) = o(g(n)) if</span>

<pre><span style="font-size: 19px;">lim  f(n)/g(n) = 0</span>
<span style="font-size: 19px;">n→∞
</span></pre>

<span style="font-size: 19px;">By the definition of limits in calculus and the first definition of o-notation, we can see that these two definitions of little-oh are equivalent.</span>

<span style="font-size: 19px;">This second definition allows us to perform some quick analysis. For example, 2n = o(n<sup>2</sup>), but 2n<sup>2</sup>&nbsp;≠o(n<sup>2</sup>).</span>

### <span style="font-size: 19px;">ω-Notation</span>

<span style="font-size: 19px;">First, f(n) = ω(g(n)) if and only if g(n) = o(f(n)).</span>

<span style="font-size: 19px;">Formal definition: ω(g(n)) = {f(n): for any positive constant c, there exists a constant n<sub>0</sub>, s.t. 0 &lt;= c g(n) &lt; f(n) for all n &gt;= n<sub>0</sub>}</span>

<span style="font-size: 19px;">Another definition using limits: f(n) = ω(g(n)) if</span>

<pre><span style="font-size: 19px;">lim  f(n)/g(n) = ∞
n→∞
</span></pre>

### <span style="font-size: 19px;">Properties</span>

<span style="font-size: 19px;">Here I give some properties of order notation, which are not difficult to prove using definitions. Think about their correctness when reviewing these properties.</span>

1.  <span style="font-size: 19px;">Transitivity: If f(n) = Θ(g(n)) and g(n) = Θ(h(n)), then f(n) = Θ(h(n)). This also applies to O, Ω, o and ω.</span>
2.  <span style="font-size: 19px;">Reflexivity: f(n) = Θ(f(n)); f(n) = O(f(n)); f(n) = Ω(f(n)). This is however not true for o or ω.</span>
3.  <span style="font-size: 19px;">Symmetry: f(n) = Θ(g(n)) if and only if g(n) = Θ(f(n)).</span>
4.  <span style="font-size: 19px;">Transpose Symmetry: f(n) = O(g(n)) if and only if g(n) = Ω(f(n)); f(n) = o(g(n)) if and only g(n) = ω(f(n)).</span>

### <span style="font-size: 19px;">Tricks</span>

<span style="font-size: 19px;">Here I give some tricks that are useful for comparing functions using order notation.</span>

<span style="font-size: 19px;">The first trick is that we can determine order notation by computing</span>

<pre><span style="font-size: 19px;">lim  f(n)/g(n) = c
n→∞
</span></pre>

<span style="font-size: 19px;">If the result, c, is 0, then f(n) = o(g(n)). If c = ∞, then f(n) = ω(g(n)). If 0 &lt; c &lt; ∞, then f(n) = Θ(g(n)), which also implies that both f(n) = O(g(n)) and f(n) = Ω(g(n)) hold.</span>

<span style="font-size: 19px;">Let us use this to prove the following claim: Let d be a nonnegative constant integer and a<sub>0</sub>, a<sub>1</sub>, ..., a<sub>d</sub>&nbsp;be constants, in which a<sub>d</sub>&nbsp;&gt; 0. Let p(n) = Σ<sub>c = 0, 1, ..., d</sub>&nbsp;(a<sub>i&nbsp;</sub>n<sup>i</sup>). Then p(n) = Θ(n<sup>d</sup>).</span>

<span style="font-size: 19px;">Proof. To prove this, we compute</span>

<span style="font-size: 19px;">lim<sub>n → ∞</sub>&nbsp;p(n)/n<sup>d</sup>&nbsp;= lim<sub>n → ∞</sub>&nbsp;(a<sub>0</sub>/n<sup>d</sup>&nbsp;+ a<sub>1</sub>/n<sup>d-1</sup>&nbsp;+ ... + a<sub>d-1</sub>/n + a<sub>d</sub>) = a<sub>d</sub>.</span>

<span style="font-size: 19px;">Since a<sub>d</sub>&nbsp;is a positive constant, we claim that p(n) = Θ(n<sup>d</sup>). [end of proof]</span>

<span style="font-size: 19px;">There are some useful theorems for limits. Here I present them in a form based on the particular task of comparing two functions of complexity (running time, space, etc).</span>

<span style="font-size: 19px;">First, L'Hopital's rule:</span>

<pre><span style="font-size: 19px;">lim  (f(n)/g(n)) = lim  (f'(n)/g'(n))
n→∞              n→∞
</span></pre>

<span style="font-size: 19px;">For example,</span>

<pre><span style="font-size: 19px;">lim (ln n / n) = lim ((1/n) / 1) = 0
n→∞             n→∞</span></pre>

<span style="font-size: 19px;">Thus ln n = o(n).</span>

<span style="font-size: 19px;">A more complicated example: Find out the relationship between f(n) = n<sup>n</sup>&nbsp;and g(n) = n!</span>

<span style="font-size: 19px;">Solution: By Stirling's approximation</span>

<math title="" xmlns="http://www.w3.org/1998/Math/MathML" display="block"><semantics><mstyle><mi>n</mi><mo>!</mo><mo>=</mo><msqrt><mn>2</mn><mi>π</mi><mi>n</mi></msqrt><mo stretchy="false">(</mo><mi>n</mi><mrow><mo>/</mo></mrow><mi>e</mi><msup><mo stretchy="false">)</mo><mi>n</mi></msup><mo stretchy="false">(</mo><mn>1</mn><mo>+</mo><mi mathvariant="normal">Θ</mi><mo stretchy="false">(</mo><mn>1</mn><mrow><mo>/</mo></mrow><mi>n</mi><mo stretchy="false">)</mo><mo stretchy="false">)</mo></mstyle><annotation encoding="latex">{"version":"1.1","math":"n!=\sqrt{2\pi n} (n/e)^n(1+\Theta(1/n))"}</annotation></semantics></math>

<span style="font-size: 19px;">we have</span>

![factorial](factorial.jpg)

<span style="font-size: 19px;">Therefore, f(n) = ω(g(n)).</span>

<span style="font-size: 19px;">&nbsp;</span>

<span style="font-size: 19px;">Two other useful theorems:</span>

<span style="font-size: 19px;">1. If f(n) &lt;= g(n) for all n &gt; 0, then</span>

<pre><span style="font-size: 19px;">lim  f(n) &lt;= lim g(n)
n→∞         n→∞
</span></pre>

<span style="font-size: 19px;">2. Squeeze Theorem: If h(n) &lt;= f(n) &lt;= g(n) for all n &gt; 0, and</span>

<pre><span style="font-size: 19px;">lim  h(n) = lim g(n)
n→∞        n→∞
</span></pre>

<span style="font-size: 19px;">then</span>

<pre><span style="font-size: 19px;">lim  f(n) = lim g(n)
n→∞        n→∞
</span></pre>

<span style="font-size: 19px;">Using the squeeze theorem, we have an alternative, simpler approach that can prove n! = o(n<sup>n</sup>): Since</span>

<pre><span style="font-size: 19px;">0 &lt;= n! / n<sup>n</sup>&nbsp;= (1/n) (2/n) ... (n/n) &lt;= 1/n</span>

<span style="font-size: 19px;">lim<sub>n → ∞</sub>0 = lim<sub>n → ∞</sub>(1/n) = 0</span>

<span style="font-size: 19px;">We have</span>

<span style="font-size: 19px;">lim<sub>n → ∞</sub>(n! / n<sup>n</sup>) = 0</span>
</pre>
</body></html>
