Input:  An array, matrix, or string which represents a two dimensional grid using the characters space, period, hyphen, colon, and newline.

Output:  An array, matrix, or string which represents a two dimensional grid using the characters space, period, hyphen, colon, and newline.

The input characters have the following meaning:

- `.` period is a single rock
- `:` colon is two rocks
- `-` hyphen is a table
- space is empty space

The program should simulate gravity, pulling the rocks vertically down.  Rocks fall straight down until they hit the ground (the bottom of the grid) or a table (hyphen).  Rocks stack as densely as possible using the two-rock colon when there is more than one.  The program does not need to simulate each step.  Only the final position should be shown.

The output grid should be the same size as the input with the same number of lines.  If the output is a string, it may omit trailing whitespace on each line.

You may do this transformation in place, or allocate a new grid.

You may assume the input is valid, and skip any validation on the input grid.

## Examples

1. A single rock falls down:

Input:

```
.

```

Output:

```

.
```

2. Two single rocks fall into a dense two rock stack:

Input:

```
.
.
```

Output:

```

:
```

3. Five rocks fall into a stack of two colons and a single period:

Input:

```
.
.

:
.
```

Output:

```

.
:
:
```

4. Rock cannot fall through a table:

Input:

```
.
.
-
.

```

Output:

```

:
-

.
```

5. Each column is an independent stack of rocks:

Input:

```

.      .
: .  :
-   .  -
. -
.    .
```

Output:
```
.
:      .
- .    -
  -  .
:   .:
```