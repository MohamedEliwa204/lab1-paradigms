# lab1

**University:** Alexandria University (CSED)  
**Course:** Programming Paradigms  
**Student Name:** Mohamed Abdullah Ahmed Eliwa Elshewy  
**Student ID:** 23010763

## Overview

This lab implements a small Domain Specific Language (DSL) for mathematical expressions. The goal of this lab was to explore three distinct programming paradigms: Declarative/Imperative,Object-Oriented, and Functional programing. My code correctly handles operator precedence, associativity, nested parenthses, and error handling (such as division by zero) across all three phases.

---

## Phase 1: Declarative + Imperative (Flex & Bison)

**Key Considerations:**
In this phase, the primary challenge was bridging the gap between lexical analysis and syntactic parsing using C.

- **Separation of Concerns:** I utilized Flex exclusively for tokenization (identifying numbers, operators, and ignoring whitspace) and Bison for defining the grammar rules and executing the C code.
- **Resolving Ambiguity:** Mathematical expressions are inherently ambiguous (e.g., `5 + 3 * 2`). Instead of complicating the grammar rules I utilized Bison's declarative `%left` dirctives to enforce standard precedence (multiplcation/division taking priortiy over addition/subtraction) and left-associativity.

---

## Phase 2: Object-Oriented Programming (Java AST)

**Arithmetic Expression Modeling:**
To model the mathematical expressions using OOP principles, I implemented the **Interpreter Design Pattern** by constructing an Abstract Syntax Tree.

- **Polymorphism:** I defined an abstract base class, `ASTNode`, with `evaluate()` and `print()` methods. This was extended by `NumberNode` (representing the leaves of the tree) and `BinOpNode` (representing internal operator nodes).
- **Recursive Descent Parsing:** Since external parsing tools were forbidden, I implemented a manual Recursive Descent Parser. By structuring the parser into hierarchical methods (`parseExpression` for `+`/`-`, `parseTerm` for `*`/`/`, and `parseFactor` for numbers), the parser naturally enforces correct mathematical precedence. Multiplication nodes are pushed deeper into the AST, ensuring they are evaluated first.
- **Tree Visualization:** To meet the ASCII tree requirement, I utilized an inherited `prefix` string parameter in the `print()` methods. This acts as a "memory" of indentation, correctly spacing child nodes to visually represent the tree hierarchy in the console.

---

## Phase 3: Functional Transformation and Evaluation

**Building and Evaluating the Prefix Form:**
This phase strictly adhered to functional programming constraints, completely avoiding loops, global variables, and state mutation.

- **Prefix Transformation:** I added a `toPrefix()` method to the AST nodes. This method recursively reads the tree and returns a new formatted string `(operator left right)`. It operates purely and does not modify the original AST in any way.
- **Purely Recursive Evaluation:** To evaluate the prefix string without a global index counter, I created an imutable helper class called `EvalResult`.
- **State Passing:** The recursive `evaluateRecursive` function reads a token and returns an `EvalResult` object containning both the mathmatical result of that branch _and_ the next string index to be read. This allows the state to be safely passed up the call stack, ensuring left and right branches are evaluated sequentially without ever utilizing global mutable variables.
