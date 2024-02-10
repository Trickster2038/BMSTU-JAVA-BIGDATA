Output example

```

=== Vector class work example ===

Vector[0]: [    1,00    0,00    0,00 ]

Vector[0] * 0: [    0,00    0,00    0,00 ]

Vector[1]: [    4,00    0,00    0,00 ]

Vector[1] * 1: [    4,00    0,00    0,00 ]

Vector[2]: [    0,00    1,00    1,00 ]

Vector[2] * 2: [    0,00    2,00    2,00 ]

=== Vectors 0-th and 1-th ===

v0: [    1,00    0,00    0,00 ] (len:   1,00)
v1: [    4,00    0,00    0,00 ] (len:   4,00)

v0 + v1: [    5,00    0,00    0,00 ]

v0 - v1: [   -3,00    0,00    0,00 ]

v0 * v1 (scalar):    4,00

v0 and v1 are orthogonal: false

v0 and v1 are collinear: true

=== Vectors 0-th and 2-th ===

v0: [    1,00    0,00    0,00 ] (len:   1,00)
v2: [    0,00    1,00    1,00 ] (len:   1,41)

v0 + v2: [    1,00    1,00    1,00 ]

v0 - v2: [    1,00   -1,00   -1,00 ]

v0 * v2 (scalar):    0,00

v0 and v2 are orthogonal: true

v0 and v2 are collinear: false

=== Vectors 1-th and 0-th ===

v1: [    4,00    0,00    0,00 ] (len:   4,00)
v0: [    1,00    0,00    0,00 ] (len:   1,00)

v1 + v0: [    5,00    0,00    0,00 ]

v1 - v0: [    3,00    0,00    0,00 ]

v1 * v0 (scalar):    4,00

v1 and v0 are orthogonal: false

v1 and v0 are collinear: true

=== Vectors 1-th and 2-th ===

v1: [    4,00    0,00    0,00 ] (len:   4,00)
v2: [    0,00    1,00    1,00 ] (len:   1,41)

v1 + v2: [    4,00    1,00    1,00 ]

v1 - v2: [    4,00   -1,00   -1,00 ]

v1 * v2 (scalar):    0,00

v1 and v2 are orthogonal: true

v1 and v2 are collinear: false

=== Vectors 2-th and 0-th ===

v2: [    0,00    1,00    1,00 ] (len:   1,41)
v0: [    1,00    0,00    0,00 ] (len:   1,00)

v2 + v0: [    1,00    1,00    1,00 ]

v2 - v0: [   -1,00    1,00    1,00 ]

v2 * v0 (scalar):    0,00

v2 and v0 are orthogonal: true

v2 and v0 are collinear: false

=== Vectors 2-th and 1-th ===

v2: [    0,00    1,00    1,00 ] (len:   1,41)
v1: [    4,00    0,00    0,00 ] (len:   4,00)

v2 + v1: [    4,00    1,00    1,00 ]

v2 - v1: [   -4,00    1,00    1,00 ]

v2 * v1 (scalar):    0,00

v2 and v1 are orthogonal: true

v2 and v1 are collinear: false

```
