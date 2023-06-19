# 32 x 32 Floating Point Matrix Multiply
# void mm (double c[][], double a[][], double b[][]) {
# 	int i, j, k;
# 	for (i = 0; i != 32; i = i + 1)
#	for (j = 0; j != 32; j = j + 1)
#	for (k = 0; k != 32; k = k + 1)
#	c[i][j] = c[i][j] + a[i][k] *b[k][j];
# }

# a0 = c
# a1 = a
# a2 = b

# Save Loop termination value (32) + Initialize loop variables
mm:...
	li $t1, 32 				# $t1 = 32 (row size/loop end)
	li $s0, 0 				# i = 0; initialize 1st for loop
	
	L1: li $s1, 0 				# j = 0; restart 2nd for loop
	L2: li $s2, 0 				# k = 0; restart 3rd for loop

		# First Step: skip over the i “1D Arrays” / Rows, to get the one we want.
		# So: multiply the index in the first dimension by the size of the row, 32.
		# Since 32 is a power of 2, we can use a shift instead:
		sll $t2, $s0, 5 		# $t2 = i * 2^5 (size of row of c)

		# Add the second index to select the jth element of the desired row:
		addu $t2, $t2, $s1 		# $t2 = i * size(row) + j

		# Multiply $t2 by the size of matrix elements in bytes to turn sum into byte index.
		# Since each element is 8 bytes for double precision, we can instead shift left by 3:
		sll $t2, $t2, 3 		# $t2 = byte offset of [i][j]

		# Add this sum to the base address of c, giving the address of c[i][j],
		# and then load the double precision number c[i][j] into $f4:
		addu $t2, $a0, $t2 		# $t2 = byte address of c[i][j]
		l.d $f4, 0($t2) 		# $f4 = 8 bytes of c[i][j]

		# Repeat last 5 instructions to calculate address and load double precision number b[k][j].
		L3: sll $t0, $s2, 5 		# $t0 = k * 2^5 (size of row of b)
			addu $t0, $t0, $s1 		# $t0 = k * size(row) + j
			sll $t0, $t0, 3 		# $t0 = byte offset of [k][j]
			addu $t0, $a2, $t0 		# $t0 = byte address of b[k][j]
			l.d $f16, 0($t0) 		# $f16 = 8 bytes of b[k][j]

			# Repeat again to calculate the address and load double precision number a[i][k].
			sll $t0, $s0, 5 		# $t0 = i * 25 (size of row of a)
			addu $t0, $t0, $s2 		# $t0 = i * size(row) + k
			sll $t0, $t0, 3 		# $t0 = byte offset of [i][k]
			addu $t0, $t0, $a1		# $t0 = byte address of a[i][k]
			l.d $f18, 0($t0) 		# $f18 = 8 bytes of a[i][k]

			# Floating Point Operations after loading all data: multiply elements of a and b
			# located in registers $f18 and $f16, and then accumulate the sum in $f4.
			mul.d $f16, $f18, $f16 	# $f16 = a[i][k] * b[k][j]
			add.d $f4, $f4, $f16 	# $f4 = c[i][j] + a[i][k] * b[k][j]

			# Store sum accumulated in $f4 into c[i][j] ($t2)
			# Increment index k, loop back if not 32, if is then end inner loop
			addiu $s2, $s2, 1 		# $k = k + 1
			bne $s2, $t1, L3 		# if (k != 32) go to L3
			s.d $f4, 0($t2) 		# c[i][j] = $f4

			# Increment index i and j for middle and outerloop until 32
			addiu $s1, $s1, 1 		# $j = j + 1
			bne $s1, $t1, L2 		# if (j != 32) go to L2
			addiu $s0, $s0, 1 		# $i = i + 1
			bne $s0, $t1, L1 		# if (i != 32) go to L1
