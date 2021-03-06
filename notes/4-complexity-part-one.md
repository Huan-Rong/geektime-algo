# 4 复杂度分析-上篇

## 为什么需要分析数据结构与算法的执行效率以及所占用的存储空间。

数据结构与算法的最终目的是降低人的时间成本以及硬件成本，这也是数据结构与算法的价值所在。我们需要通过某种考量指标来衡量数据结构与算法最终起到的作用。但由于时间要比硬件珍贵得多，所以我们更关注算法的执行效率。

* 事后统计法
* 大 O 复杂度表示法
  * 时间复杂度
  * 空间复杂度

## 事后统计法

将代码执行一遍，并通过监控、统计等办法得到代码的执行时间和存储大小，这种分析代码的方法称为**事后统计法**。这种方法有以下局限性。

* 测试结果非常依赖测试环境（硬件）
* 测试结果受数据规模的影响很大

这些局限性容易给人带来迷惑，无法对算法的执行效率给出一个客观的评价。另外，先将代码跑一遍的方式，本身就有着较大的成本。

## 大 O 复杂度表示法的定义

**代码的执行时间 T(n) 与所有代码的执行次数 f(n) 成正比，即 T(n) = O(f(n))。其中，n 表示数据规模的大小，O 表示代码的执行时间 T(n) 与 f(n) 表达式成正比。**比如 T(n) = O(2n+2)，T(n) = O(2n^2+2n+3)，这就是大 O 时间复杂度表示法。实际上，大 O 时间复杂度表示法并不表示代码真正的执行时间，而是**表示代码执行时间随数据规模增长的变化趋势**，所以也叫作**渐进时间复杂度**，简称时间复杂度。

当数据规模 n 越来越大，f(n) 表达式中的低阶、常量、系数并不左右趋势的发展，所以都可以忽略。比如 T(n) = O(2n+2) 变成 T(n) = O(n)，T(n) = (2n^2+2n+3) 变成 T(n) = O(n^2)。

## 时间复杂度的分析方法

* 只关注循环执行次数最多的代码，忽略常量级时间复杂度。
* 加法原则：总复杂度等于量级最大的那段代码的复杂度，忽略低阶时间复杂度和常量级时间复杂度。
* 乘法原则：嵌套代码的复杂度等于嵌套内外代码复杂度的乘积，用于分析嵌套代码的时间复杂度。

## 常见的时间复杂度

代码千差万别，但常见的时间复杂度并不多，这些时间复杂度主要分为两类：多项式量级、非多项式量级。非多项式量级包括 O(2^n) 和 O(n!)，是非常低效的算法。时间复杂度为非多项式量级的算法问题被称为 NP 问题。

![常见的时间复杂度](../images/complexity.jpg)

### 理解 O(1) 

O(1) 表示代码的执行时间不随数据规模的增长而增长。如以下代码。

```java
int i = 1;
int j = 2;
int sum = i + j;
```

### 理解 O(logn) 和 O(nlogn)

对数阶的时间复杂度是最常见的时间复杂度，同时也是最难分析的一种时间复杂度。对于这两种时间复杂度的理解，需要用到对数的**换底公式**和**乘法原则**。

```java
// log2n
int i = 1;
while (i < n) {
  i = i * 2;
}
```

```java
// log3n
int i = 1;
while (i < n) {
  i = i * 3;
}
```
### 多个数据规模：理解 O(m + n) 和 O(m * n)

先前谈的时间复杂度分析都是基于一个数据规模 n 的情况下进行的。当有两个数据规模 m、n 时，如果无法实现评估 m 和 n 谁的量极大，那么加法原则就需要改变：T1(m) + T2(n) = O(f(m) + g(n))；但乘法原则不需要改变。

```java
// O(m + n)
int i = 1;
while (i < n) {
  // other operation...
  i++;
}

int j = 1;
while (j < m) {
  // other operation...
  j++;
}
```

### 需要掌握的数学知识点

* 对数
* 对数之间的转换

## 空间复杂度分析

空间复杂度，即渐进空间复杂度，表示算法的存储空间随数据规模增长的变化趋势。空间复杂度的分析方法与时间复杂度的分析方法一致。

## 关于大 O 记号的由来

The letter O is used because the growth rate of a function is also referred to as the order of the function. Refer to: https://en.wikipedia.org/wiki/Big_O_notation

## 精选留言
### 大 O 理论分析模型与基准测试并不矛盾

渐进时间，空间复杂度分析为我们提供了一个很好的理论分析的方向，并且它是宿主平台无关的，能够让我们对我们的程序或算法有一个大致的认识，让我们知道，比如在最坏的情况下程序的执行效率如何，同时也为我们交流提供了一个不错的桥梁，我们可以说，算法1的时间复杂度是O(n)，算法2的时间复杂度是O(logN)，这样我们立刻就对不同的算法有了一个“效率”上的感性认识。

当然，渐进式时间，空间复杂度分析只是一个理论模型，只能提供给粗略的估计分析，我们不能直接断定就觉得O(logN)的算法一定优于O(n), 针对不同的宿主环境，不同的数据集，不同的数据量的大小，在实际应用上面可能真正的性能会不同，个人觉得，针对不同的实际情况，进而进行一定的性能基准测试是很有必要的，比如在统一一批手机上(同样的硬件，系统等等)进行横向基准测试，进而选择适合特定应用场景下的最有算法。

综上所述，渐进式时间，空间复杂度分析与性能基准测试并不冲突，而是相辅相成的，但是一个低阶的时间复杂度程序有极大的可能性会优于一个高阶的时间复杂度程序，所以在实际编程中，时刻关心理论时间，空间度模型是有助于产出效率高的程序的，同时，因为渐进式时间，空间复杂度分析只是提供一个粗略的分析模型，因此也不会浪费太多时间，重点在于在编程时，要具有这种复杂度分析的思维。



